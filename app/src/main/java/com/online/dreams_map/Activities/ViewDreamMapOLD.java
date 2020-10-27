package com.online.dreams_map.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.online.dreams_map.R;
import com.online.dreams_map.SysLibs.DreamsObjects;
import com.online.dreams_map.SysLibs.ODM_SqlLite;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;





public class ViewDreamMapOLD extends AppCompatActivity {
    int dream_id_g = 0;
    Activity activity = (Activity)this;
    Context context = (Context)this;
    ViewDreamMapOLD mContext = (ViewDreamMapOLD) this;

    private ScrollView vScroll;
    private HorizontalScrollView hScroll;
    private float mx, my;
    private float curX, curY;
    private int endId;

    // Настройки отображения карты
    private ODM_SqlLite dreams_sql;
    private HashMap<String, String> dream = new HashMap<>();
    private int rotate = 1;
    private int rotate_old = 1;
    private int orig_rotate = 1;
    private int m_width = 0;
    private int m_height = 0;
    int md_block_size = 0;
    int md_block_size_old = 0;
    private HashMap <Integer, Integer> md_block_sizes = new HashMap<>();


    String def_block_data =
        "{" +
            "\"h\":0," +
            "\"object\":\"empty\"," +
            "\"back\":\"empty\"," +
            "\"border\":{" +
                "\"up\":[\"empty\",\"t1\"]," +
                "\"right\":[\"empty\",\"t1\"]," +
                "\"down\":[\"empty\",\"t1\"]," +
                "\"left\":[\"empty\",\"t1\"]" +
            "}" +
        "}"
    ;
    HashMap<String, HashMap<Integer, String>> border_dim_rot = new HashMap<>();

    public int size;
    public int new_size;
    public int curr_vScroll;
    public int curr_hScroll;
    public int curr_width;
    public int curr_height;
    public int map_width2;
    public int map_height2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdreammap_activity);

        vScroll = (ScrollView) findViewById(R.id.verticalScroll);
        hScroll = (HorizontalScrollView) findViewById(R.id.horizontalScroll);

        // ID просматриваемого сновидения
        Intent intent = getIntent();
        int dream_id = Integer.valueOf(intent.getIntExtra("dream_id",0));
        dream_id_g = dream_id;

        // Заголовок
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });





        md_block_sizes.put(0,8);
        md_block_sizes.put(1,16);
        md_block_sizes.put(2,32);
        md_block_sizes.put(3,64);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.rotate_left:
                    rotate = rotate-1 < 1? 4: rotate-1;
                    writeMap();
                    break;
                case R.id.rotate_right:
                    rotate = rotate+1 > 4? 1: rotate+1;
                    writeMap();
                    break;
                case R.id.zoom_out:
                    md_block_size = md_block_size-1 < 0? 0: md_block_size-1;
                    writeMap();
                    break;
                case R.id.zoom_in:
                    md_block_size = md_block_size+1 > 3? 3: md_block_size+1;
                    writeMap();
                    break;
            }

            return false;
            }
        });





        // Верхняя граница
        HashMap<Integer, String> temp_u = new HashMap<>();
        temp_u.put(1, "up");
        temp_u.put(2, "right");
        temp_u.put(3, "down");
        temp_u.put(4, "left");
        border_dim_rot.put("up", temp_u);

        // Правая граница
        HashMap<Integer, String> temp_r = new HashMap<>();
        temp_r.put(1, "right");
        temp_r.put(2, "down");
        temp_r.put(3, "left");
        temp_r.put(4, "up");
        border_dim_rot.put("right", temp_r);

        // Нижняя граница
        HashMap<Integer, String> temp_d = new HashMap<>();
        temp_d.put(1, "down");
        temp_d.put(2, "left");
        temp_d.put(3, "up");
        temp_d.put(4, "right");
        border_dim_rot.put("down", temp_d);

        // Левая граница
        HashMap<Integer, String> temp_l = new HashMap<>();
        temp_l.put(1, "left");
        temp_l.put(2, "up");
        temp_l.put(3, "right");
        temp_l.put(4, "down");
        border_dim_rot.put("left", temp_l);





        // Отображение содержимого сновидения
        {
            dreams_sql = new ODM_SqlLite(this);
            dream = dreams_sql.getDream(dream_id, "local");


            // Сон найден
            if(dream.get("count").equals("1")){

                // Заголовок
                getSupportActionBar().setTitle(dream.get(dreams_sql.DREAMS_KEY_TITLE));

                // Отрисовка карты
                {
                    try {
                        JSONObject map_data = new JSONObject(dream.get(dreams_sql.DREAMS_KEY_MAP_DATA));
                        rotate = 1;

                        if(map_data.has("rotate"))
                            {rotate = map_data.getInt("rotate");}

                        rotate = rotate>4? 4: (rotate<1? 1: rotate);
                        writeMap();
                    }

                    catch(JSONException e)
                        {Log.e("JSONException",e.getMessage());}
                }

            }

            // Сон не найден
            else{
                onBackPressed();
            }
        }

    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX, curY;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mx = event.getX();
                my = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                curX = event.getX();
                curY = event.getY();
                vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                mx = curX;
                my = curY;

                break;
            case MotionEvent.ACTION_UP:
                curX = event.getX();
                curY = event.getY();
                vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));

                break;
        }

        return true;
    }




    // Отрисовка карты
    public void writeMap(){
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_menu);

        MenuItem zoom_out = bottomNavigationView.getMenu().findItem(R.id.zoom_out);
        if(md_block_size <= 0)
            {zoom_out.setEnabled(false);}
        else
            {zoom_out.setEnabled(true);}

        MenuItem zoom_in = bottomNavigationView.getMenu().findItem(R.id.zoom_in);
        if(md_block_size >= 3)
            {zoom_in.setEnabled(false);}
        else
            {zoom_in.setEnabled(true);}



        try{
            JSONObject map_data = new JSONObject(dream.get(dreams_sql.DREAMS_KEY_MAP_DATA));
            int map_width = map_data.getInt("width");
            int map_height = map_data.getInt("height");
            JSONObject map_counts = map_data.getJSONObject("counts");
            int map_object_count = map_counts.getInt("object");
            int map_back_count = map_counts.getInt("back");
            JSONArray blocks = map_data.getJSONArray("blocks");
            orig_rotate = map_data.has("rotate")? map_data.getInt("rotate"): 1;
            orig_rotate = orig_rotate>4? 4: (orig_rotate<1? 1: orig_rotate);


            // Найдены не пустые блоки карты
            if((map_object_count>0 || map_back_count>0) & map_width>0 & map_height>0){
                int rotate_shift = orig_rotate - rotate;

                // Создать строки
                JSONObject new_map_data = new JSONObject(map_data.toString());
                new_map_data.remove("blocks");
                new_map_data.put("blocks",new JSONArray());
                for(int ch=0; ch<map_height; ch++){
                    new_map_data.getJSONArray("blocks").put(ch,new JSONArray());

                    for(int cw=0; cw<map_width; cw++){
                        new_map_data.getJSONArray("blocks").getJSONArray(ch).put(cw,new JSONObject());
                    }
                }



                // Ничего не делать
                if(rotate_shift == 0){
                    write_to_rotate(map_data, map_width, map_height);
                }

                // Повернуть на 90
                else if(rotate_shift == -1 || rotate_shift == 3){

                    // Добавить блоки
                    int n_h = 0;
                    for(int w=0; w<map_width; w++){
                        int n_w = 0;

                        for(int h=map_height-1; h>=0; h--) {
                            JSONObject v = new JSONObject();
                            JSONArray line_v = new JSONArray();

                            if(h < blocks.length()){
                                Object thing = blocks.get(h);

                                if (thing instanceof JSONObject) {
                                    for (int t_w = 0; t_w < map_width; t_w++) {
                                        JSONObject test = blocks.getJSONObject(h);

                                        if (test.has(String.valueOf(t_w))) {
                                            line_v.put(t_w, test.getJSONObject(String.valueOf(t_w)));
                                        } else {
                                            line_v.put(t_w, new JSONObject(def_block_data));
                                        }
                                    }
                                }

                                else if (thing instanceof JSONArray) {
                                    line_v = h < blocks.length() ?
                                            blocks.getJSONArray(h) :
                                            new JSONArray();
                                }

                                else
                                    {line_v = new JSONArray();}
                            }

                            else
                                {line_v = new JSONArray();}

                            if (w < line_v.length()) {
                                Object thing = blocks.get(h);

                                thing = line_v.get(w);
                                v = thing instanceof JSONObject ?
                                        line_v.getJSONObject(w) :
                                        new JSONObject(def_block_data);
                            }

                            else
                                {v = new JSONObject(def_block_data);}

                            new_map_data.getJSONArray("blocks").getJSONArray(n_h).put(n_w,v);

                            n_w++;
                        }

                        n_h++;
                    }

                    write_to_rotate(new_map_data, map_height, map_width);
                }

                // Повернуть на 180
                else if(rotate_shift == -2 || rotate_shift == 2){

                    // Добавить блоки
                    int n_h = 0;
                    for(int h=map_height-1; h>=0; h--){
                        int n_w = 0;

                        for(int w=map_width-1; w>=0; w--){
                            JSONObject v = new JSONObject();
                            JSONArray line_v = new JSONArray();


                            if(h < blocks.length()){
                                Object thing = blocks.get(h);

                                if (thing instanceof JSONObject) {
                                    for (int t_w = 0; t_w < map_width; t_w++) {
                                        JSONObject test = blocks.getJSONObject(h);

                                        if (test.has(String.valueOf(t_w)))
                                            {line_v.put(t_w, test.getJSONObject(String.valueOf(t_w)));}
                                        else
                                            {line_v.put(t_w, new JSONObject(def_block_data));}
                                    }
                                }

                                else if (thing instanceof JSONArray) {
                                    line_v = h < blocks.length() ?
                                        blocks.getJSONArray(h) :
                                        new JSONArray();
                                }

                                else
                                    {line_v = new JSONArray();}
                            }

                            else
                                {line_v = new JSONArray();}

                            if (w < line_v.length()) {
                                Object thing = blocks.get(h);

                                thing = line_v.get(w);
                                v = thing instanceof JSONObject ?
                                        line_v.getJSONObject(w) :
                                        new JSONObject(def_block_data);
                            }

                            else
                                {v = new JSONObject(def_block_data);}

                            new_map_data.getJSONArray("blocks").getJSONArray(n_h).put(n_w,v);

                            n_w++;
                        }

                        n_h++;
                    }

                    write_to_rotate(new_map_data, map_width, map_height);
                }

                // Повернуть на 270
                else if(rotate_shift == -3 || rotate_shift == 1){

                    // Добавить блоки
                    int n_h = 0;
                    for(int w=map_width-1; w>=0; w--){
                        int n_w = 0;

                        for(int h=0; h<map_height; h++){
                            JSONObject v = new JSONObject();
                            JSONArray line_v = new JSONArray();


                            if(h < blocks.length()){
                                Object thing = blocks.get(h);

                                if (thing instanceof JSONObject) {
                                    for (int t_w = 0; t_w < map_width; t_w++) {
                                        JSONObject test = blocks.getJSONObject(h);

                                        if (test.has(String.valueOf(t_w)))
                                            {line_v.put(t_w, test.getJSONObject(String.valueOf(t_w)));}
                                        else
                                            {line_v.put(t_w, new JSONObject(def_block_data));}
                                    }
                                }

                                else if (thing instanceof JSONArray) {
                                    line_v = h < blocks.length() ?
                                        blocks.getJSONArray(h) :
                                        new JSONArray();
                                }

                                else
                                    {line_v = new JSONArray();}
                            }

                            else
                                {line_v = new JSONArray();}

                            if (w < line_v.length()) {
                                Object thing = blocks.get(h);

                                thing = line_v.get(w);
                                v = thing instanceof JSONObject ?
                                    line_v.getJSONObject(w) :
                                    new JSONObject(def_block_data);
                            }

                            else
                                {v = new JSONObject(def_block_data);}

                            new_map_data.getJSONArray("blocks").getJSONArray(n_h).put(n_w,v);

                            n_w++;
                        }

                        n_h++;
                    }

                    write_to_rotate(new_map_data, map_height, map_width);
                }

            }

            // Карта не найдена
            else
                {onBackPressed();}

        }

        catch(JSONException e)
            {Log.e("JSONException",e.getMessage());}

    }

    // Отрисовка поворота
    private void write_to_rotate(JSONObject map_data, int map_width, int map_height){

        try {
            loader_show("Пожалуйста подождите\nОтрисовка карты");



            Resources r = getResources();
            LinearLayout mapContainerHelper = findViewById(R.id.mapContainerHelper);
            ScrollView vScroll = (ScrollView) findViewById(R.id.verticalScroll);
            HorizontalScrollView hScroll = (HorizontalScrollView) findViewById(R.id.horizontalScroll);
            LinearLayout mapContainer = findViewById(R.id.mapContainer);
            if (mapContainer.getChildCount() > 0)
                {mapContainer.removeAllViews();}

            DreamsObjects dreamsObjects = new DreamsObjects();
            int block_size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, md_block_sizes.get(md_block_size), r.getDisplayMetrics());
            int block_height_step = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (md_block_sizes.get(md_block_size) / 4), r.getDisplayMetrics());
            JSONArray blocks = map_data.getJSONArray("blocks");
            HashMap<String, HashMap<Integer,String[]>> objects = dreamsObjects.get_objects();

            m_width = map_width;
            m_height = map_height;


            // Текущий скролл
            curr_vScroll = vScroll.getScrollY();
            curr_hScroll = hScroll.getScrollX();
            curr_width = mapContainer.getWidth();
            curr_height = mapContainer.getHeight();

            size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, md_block_sizes.get(md_block_size_old), r.getDisplayMetrics());
            new_size = size;

            map_width2 = map_width + 12;
            map_height2 = map_height + 12;



            // Обход cтрок
            for (int h = 0; h < map_height; h++) {
                LinearLayout new_LL = new LinearLayout(this);
                new_LL.setOrientation(LinearLayout.HORIZONTAL);
                new_LL.setLayoutParams(new LinearLayout.LayoutParams(block_size*map_width, block_size));
                new_LL.setClipChildren(false);
                new_LL.setClipToPadding(false);
                JSONArray line_v = new JSONArray();
                String line_name = "filed_h_"+h;


                // Данные строки
                {
                    if(h < blocks.length()){
                        Object thing = blocks.get(h);

                        if (thing instanceof JSONObject) {
                            for (int t_w = 0; t_w < map_width; t_w++) {
                                JSONObject test = blocks.getJSONObject(h);

                                if (test.has(String.valueOf(t_w)))
                                    {line_v.put(t_w, test.getJSONObject(String.valueOf(t_w)));}
                                else
                                    {line_v.put(t_w, new JSONObject(def_block_data));}
                            }
                        }

                        else if (thing instanceof JSONArray) {
                            line_v = h < blocks.length() ?
                                blocks.getJSONArray(h) :
                                new JSONArray();
                        }

                        else
                            {line_v = new JSONArray();}
                    }

                    else
                        {line_v = new JSONArray();}
                }



                // Обработка блоков
                for (int w = 0; w < map_width; w++) {
                    JSONObject v;
                    int md_height;
                    String md_back;
                    String md_object;

                    FrameLayout new_block;


                    // Данные блока
                    {

                        if (w < line_v.length()) {
                            Object thing = line_v.get(w);
                            v = thing instanceof JSONObject ?
                                    line_v.getJSONObject(w) :
                                    new JSONObject(def_block_data);
                        }

                        else
                            {v = new JSONObject(def_block_data);}

                        md_back = v.has("back") ? v.getString("back") : "empty";
                        md_object = v.has("object") ? v.getString("object") : "empty";
                        md_height = (md_back.equals("empty") ? 0 : (v.has("h") ? v.getInt("h") + 6 : 6));
                    }

                    // Сам блок
                    {
                        new_block = new FrameLayout(this);
                        new_block.setLayoutParams(new FrameLayout.LayoutParams(block_size, block_size));
                        new_block.setClipChildren(false);
                        new_block.setClipToPadding(false);
                        new_LL.addView(new_block);
                    }


                    // Непустой блок
                    if (!md_back.equals("empty")) {

                        // Подземные текстуры
                        {
                            FrameLayout back_height_for = new FrameLayout(this);
                            back_height_for.setLayoutParams(new FrameLayout.LayoutParams(block_size, (md_height * block_height_step), Gravity.BOTTOM));
                            back_height_for.setClipChildren(true);

                            ImageView back_height = new ImageView(this);

                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(block_size, (6 * block_size), Gravity.TOP);
                            back_height.setLayoutParams(params);

                            String mDrawableName = "map_data_height_" + md_back;
                            int resID = back_height.getContext().getResources().getIdentifier(mDrawableName, "drawable", back_height.getContext().getPackageName());
                            back_height.setImageResource(resID);

                            back_height_for.addView(back_height);
                            new_block.addView(back_height_for);
                        }

                        // Текстура местности
                        {
                            ImageView back = new ImageView(this);

                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(block_size, block_size, Gravity.BOTTOM);
                            back.setLayoutParams(params);

                            ViewGroup.MarginLayoutParams backParams = (ViewGroup.MarginLayoutParams) back.getLayoutParams();
                            backParams.setMargins(0, 0, 0, md_height * block_height_step);

                            String mDrawableName = "map_data_backs_" + md_back;
                            int resID = back.getContext().getResources().getIdentifier(mDrawableName, "drawable", back.getContext().getPackageName());
                            back.setImageResource(resID);

                            new_block.addView(back);
                        }

                        // Границы в блоке
                        {

                            // Обработка данных границ
                            HashMap<Integer, String> test_border = new HashMap<>();
                            test_border.put(0, "up");
                            test_border.put(1, "right");
                            test_border.put(2, "down");
                            test_border.put(3, "left");



                            // Проверка и обработка данных
                            JSONObject md_border;

                            if (v.has("border")) {
                                Object thing = v.get("border");
                                md_border = thing instanceof JSONObject ?
                                    v.getJSONObject("border") :
                                    new JSONObject(def_block_data).getJSONObject("border");
                            }

                            else
                                {md_border = new JSONObject(def_block_data).getJSONObject("border");}


                            for (int i = 0; i < 4; i++) {
                                String dim_data = test_border.get(i);

                                if (md_border.has(dim_data)) {
                                    Object thing = md_border.get(dim_data);

                                    JSONArray temp_border = thing instanceof JSONArray ?
                                        md_border.getJSONArray(dim_data) :
                                        new JSONObject(def_block_data).getJSONObject("border").getJSONArray("up");

                                    md_border.remove(dim_data);
                                    md_border.put(dim_data, temp_border);
                                }

                                else
                                    {md_border.put(dim_data, new JSONObject(def_block_data).getJSONObject("border").getJSONArray("up"));}
                            }


                            // Вставка данных
                            for (int i = 0; i < 4; i++) {
                                String curr_dim = test_border.get(i);
                                String show_dim = border_dim_rot.get(curr_dim).get(rotate);
                                JSONArray curr_border;

                                if (md_border.has(curr_dim)) {
                                    Object thing = md_border.get(curr_dim);
                                    curr_border = thing instanceof JSONArray ?
                                            md_border.getJSONArray(curr_dim) :
                                            new JSONObject(def_block_data).getJSONObject("border").getJSONArray(curr_dim);
                                }

                                else
                                    {curr_border = new JSONObject(def_block_data).getJSONObject("border").getJSONArray(show_dim);}

                                String border_back = curr_border.getString(0);
                                String border_type = curr_border.getString(1);


                                if (!border_back.equals("empty")) {
                                    border_type = border_type.equals("t1") || border_type.equals("t2") ? border_type : "t1";
                                    border_type = border_back.equals("shadow") || border_back.equals("empty") ? "t1" : border_type;

                                    ImageView border = new ImageView(this);

                                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(block_size, block_size, Gravity.BOTTOM);
                                    border.setLayoutParams(params);

                                    ViewGroup.MarginLayoutParams backParams = (ViewGroup.MarginLayoutParams) border.getLayoutParams();
                                    backParams.setMargins(0, 0, 0, md_height * block_height_step);

                                    String mDrawableName = "map_data_border_" + border_back + "_" + border_type;
                                    int resID = border.getContext().getResources().getIdentifier(mDrawableName, "drawable", border.getContext().getPackageName());
                                    border.setImageResource(resID);

                                    if (show_dim.equals("up"))
                                        {border.setRotation(0);}
                                    else if (show_dim.equals("right"))
                                        {border.setRotation(90);}
                                    else if (show_dim.equals("down"))
                                        {border.setRotation(180);}
                                    else if (show_dim.equals("left"))
                                        {border.setRotation(270);}

                                    new_block.addView(border);
                                }
                            }

                        }

                        // Объект
                        {
                            String obj_name="";
                            int obj_height=1;

                            if(objects.get(md_object) != null) {
                                String[] d_object = objects.get(md_object).get(rotate);
                                obj_height = Integer.parseInt(d_object[0]);
                                obj_name = d_object[1];
                            }

                            ImageView object = new ImageView(this);

                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(block_size, (block_size * obj_height), Gravity.BOTTOM);
                            object.setLayoutParams(params);

                            ViewGroup.MarginLayoutParams backParams = (ViewGroup.MarginLayoutParams) object.getLayoutParams();
                            backParams.setMargins(0, 0, 0, md_height * block_height_step);

                            String mDrawableName = "map_data_object_" + obj_name;
                            int resID = object.getContext().getResources().getIdentifier(mDrawableName, "drawable", object.getContext().getPackageName());
                            object.setImageResource(resID);

                            new_block.addView(object);
                        }

                    }

                    // Пустой блок
                    else {
                        ImageView back = new ImageView(this);

                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(block_size, block_size, Gravity.BOTTOM);
                        back.setLayoutParams(params);

                        String mDrawableName = "map_data_backs_empty";
                        int resID = back.getContext().getResources().getIdentifier(mDrawableName, "drawable", back.getContext().getPackageName());
                        back.setImageResource(resID);

                        new_block.addView(back);
                    }



                    // Последний блок
                    if(h+1 == map_height){
                        if(w+1 == map_width){
                            loader_hide();
                        }
                    }
                }



                mapContainer.addView(new_LL);
            }



            endId = View.generateViewId();
            LinearLayout end_LL = new LinearLayout(this);
            end_LL.setId(endId);
            end_LL.setOrientation(LinearLayout.HORIZONTAL);
            end_LL.setLayoutParams(new LinearLayout.LayoutParams((block_size * map_width), (block_size * 6)));
            end_LL.setBackgroundColor(Color.rgb(0,0,0));
            mapContainer.addView(end_LL);
            mapContainer.setPadding((block_size * 6), (block_size * 6), (block_size * 6), 0);



            // Определить новый центр экрана
            mapContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

                @Override
                public void onGlobalLayout(){
                    {
                        Resources r = getResources();

                        LinearLayout mapContainer = findViewById(R.id.mapContainer);
                        LinearLayout mapContainerHelper = findViewById(R.id.mapContainerHelper);
                        ScrollView vScroll = (ScrollView) findViewById(R.id.verticalScroll);
                        HorizontalScrollView hScroll = (HorizontalScrollView) findViewById(R.id.horizontalScroll);

                        mapContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                        new_size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, md_block_sizes.get(md_block_size), r.getDisplayMetrics());
                        int koof = Math.round((new_size * 100) / size);
                        int new_vScroll = curr_vScroll;
                        int new_hScroll = curr_hScroll;

                        int new_width = mapContainer.getWidth();
                        int new_height = mapContainer.getHeight();

                        int width = mapContainerHelper.getWidth();
                        int height = mapContainerHelper.getHeight();

                        // Определить текущий центр скрола для экрана
                        int s_center_h = curr_hScroll + (width / 2);
                        int s_center_v = curr_vScroll + (height / 2);


                        // При увеличении масштаба
                        if (koof > 100) {

                            // Горизонтальный скролл
                            if(width > curr_width){
                                if(width > new_width)
                                    {new_hScroll = 0;}
                                else
                                    {new_hScroll = Math.round(new_width / 2) - Math.round(width / 2);}
                            }

                            else
                                {new_hScroll = Math.round((s_center_h * koof) / 100) - Math.round(width / 2);}

                            // Вертикальный скролл
                            if(height > curr_height){
                                if(height > new_height)
                                    {new_vScroll = 0;}
                                else
                                    {new_vScroll = Math.round(new_height / 2) - Math.round(height / 2);}
                            }

                            else
                                {new_vScroll = Math.round((s_center_v * koof) / 100) - Math.round(height / 2);}
                        }

                        // При уменьшении масштаба
                        else if(koof < 100){

                            // Горизонтальный скролл
                            if(width > new_width)
                                {new_hScroll = 0;}
                            else
                                {new_hScroll = Math.round((s_center_h * koof) / 100) - Math.round(width / 2);}

                            // Вертикальный скролл
                            if(height > new_height)
                                {new_vScroll = 0;}
                            else
                                {new_vScroll = Math.round((s_center_v * koof) / 100) - Math.round(height / 2);}

                        }

                        // Поворот по часовой стрелке
                        else if ((rotate_old < rotate || (rotate == 1 & rotate_old == 4)) & !(rotate == 4 & rotate_old == 1)) {
                            new_vScroll = (curr_hScroll + (width / 2)) - (height / 2);
                            new_hScroll = new_height - (curr_vScroll + (height / 2)) - (width / 2);
                        }

                        // Поворот против часовой стрелке
                        else if (rotate_old > rotate || (rotate == 4 & rotate_old == 1)) {
                            new_vScroll = new_width - (curr_hScroll + (width / 2)) - (height / 2);
                            new_hScroll = (curr_vScroll + (height / 2)) - (width / 2);
                        }


                        new_hScroll = new_hScroll > new_width - width ? new_width - width : new_hScroll;
                        new_hScroll = new_hScroll < 0 ? 0 : new_hScroll;
                        new_vScroll = new_vScroll > new_height - height ? new_height - height : new_vScroll;
                        new_vScroll = new_vScroll < 0 ? 0 : new_vScroll;


                        hScroll.setScrollX(new_hScroll);
                        vScroll.setScrollY(new_vScroll);

                        md_block_size_old = md_block_size;
                        rotate_old = rotate;
                    }
                }

            });

        }

        catch(JSONException e)
            {Log.e("JSONException",e.getMessage());}

    }





    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }





    // Текущий статус
    public boolean loader_get(){
        FrameLayout frameLayout=findViewById(R.id.mainLoader);
        int visible = frameLayout.getVisibility();

        if(visible == View.VISIBLE)
            {return true;}

        return false;
    }

    // Показать лоадер
    public void loader_show(String text){
        if(!loader_get()) {
            FrameLayout frameLayout = this.findViewById(R.id.mainLoader);
            frameLayout.setVisibility(View.VISIBLE);

            ScrollView scroll_v = this.findViewById(R.id.verticalScroll);
            scroll_v.setEnabled(false);
            HorizontalScrollView scroll_h = this.findViewById(R.id.horizontalScroll);
            scroll_h.setEnabled(false);

            BottomNavigationView bottomNav = this.findViewById(R.id.bottom_menu);
            bottomNav.setVisibility(View.INVISIBLE);
        }

        TextView textView = this.findViewById(R.id.mainLoaderText);
        textView.setText(text);
    }

    // Скрыть лоадер
    public void loader_hide(){
        if(loader_get()){
            TextView textView=this.findViewById(R.id.mainLoaderText);
            FrameLayout frameLayout=this.findViewById(R.id.mainLoader);

            frameLayout.setVisibility(View.INVISIBLE);
            textView.setText("");

            ScrollView scroll_v = this.findViewById(R.id.verticalScroll);
            scroll_v.setEnabled(true);
            HorizontalScrollView scroll_h = this.findViewById(R.id.horizontalScroll);
            scroll_h.setEnabled(true);

            BottomNavigationView bootomNav = this.findViewById(R.id.bottom_menu);
            bootomNav.setVisibility(View.VISIBLE);
        }
    }

}
