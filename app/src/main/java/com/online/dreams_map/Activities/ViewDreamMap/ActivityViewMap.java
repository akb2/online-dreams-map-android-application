package com.online.dreams_map.Activities.ViewDreamMap;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.online.dreams_map.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.DreamsData.DreamMap;
import com.online.dreams_map.Menus.MainMenu;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class ActivityViewMap extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{


    private AuthData authData;
    private MainMenu mainMenu;
    private DreamMap dreamMap;
    private PutDreamBlocks putDreamBlocks;
    private MBAdapter adapter;

    private int dream_id = 0;
    private int px = 1;
    float mx=0, my=0;

    private LinearLayout mapContainer;
    private List<MapBlock> mapBlocks = new ArrayList<>();

    private int old_rotate = 0;
    private int old_size = 0;





    // Запуск активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdreammap_activity);



        // Данные авторизации
        authData = new AuthData(this);



        // Заголовок
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(backButtonClick);

        mainMenu = new MainMenu(this, toolbar, "view_dream_map" );



        // Нижнее меню
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);



        // ID просматриваемого сновидения
        Intent intent = getIntent();
        dream_id = intent.getIntExtra("dream_id",0);



        // Размер зерна пикселя
        px = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics() );



        mapContainer = (LinearLayout) findViewById(R.id.mapContainer);
        adapter = new MBAdapter(mapBlocks, this, mapContainer, 0, 0);

        loader_hide();
        putData();
    }





    // Обновить содержимое окна
    private void putData(){
        ODM_SqlLite dreams_sql = new ODM_SqlLite(this);
        HashMap<String, String> dream = dreams_sql.getDream(dream_id, "local");


        // Сон найден
        if(Integer.parseInt(dream.get("count")) > 0) {

            // Заголовок
            {
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle(dream.get(dreams_sql.DREAMS_KEY_TITLE));
                toolbar.setSubtitle("Просмотр сновидения");
            }


            try{
                dreamMap = new DreamMap(new JSONObject(dream.get("map_data")), dream_id, this);

                if(dreamMap.getBackCount() == 0)
                    {onBackPressed();}

                else{
                    putDreamBlocks = new PutDreamBlocks(dreamMap, this);
                    putDreamBlocks.execute();
                }
            }

            catch (JSONException e){}

        }

        // Сон не найден
        else{
            onBackPressed();
        }

    }


    // Изменить скролл
    private void correctScroll(){
        int size = dreamMap.getRealSize();
        int rotate = dreamMap.getRotate();



        if(old_rotate>0 & old_size>0){
            ScrollView vScroll = findViewById(R.id.verticalScroll);
            HorizontalScrollView hScroll = findViewById(R.id.horizontalScroll);
            LinearLayout map_helper = findViewById(R.id.mapContainerHelper);
            LinearLayout map_container = findViewById(R.id.mapContainer);

            int old_x = (int) hScroll.getScrollX();
            int old_y = (int) vScroll.getScrollY();
            int new_x = old_x;
            int new_y = old_y;

            int container_width = map_container.getWidth();
            int container_height = map_container.getHeight();
            int helper_width = map_helper.getWidth();
            int helper_height = map_helper.getHeight();

            int old_center_u = old_y + (helper_height / 2);
            int old_center_l = old_x + (helper_width / 2);
            int old_center_d = container_height - old_center_u;
            int old_center_r = container_width - old_center_l;



            // При повороте вправо
            if( old_rotate+1 == rotate || (old_rotate==4 & rotate==1)){
                new_x = old_center_d - (helper_width / 2);
                new_y = old_center_l - (helper_height / 2);
            }

            // При повороте влево
            else if( old_rotate-1 == rotate || (old_rotate==1 & rotate==4)){
                new_x = old_center_u - (helper_width / 2);
                new_y = old_center_r - (helper_height / 2);
            }

            // При увеличении
            else if(old_size != size){
                new_x = ((old_center_l / old_size) * size) - (helper_width / 2);
                new_y = ((old_center_u / old_size) * size) - (helper_height / 2);
            }



            vScroll.setScrollY(new_y);
            hScroll.setScrollX(new_x);
        }



        old_rotate = rotate;
        old_size = size;
    }





    // Фоновая вставка данных
    private class PutDreamBlocks extends AsyncTask<Void, Integer, Void>{

        private Context context;
        private Activity activity;

        private DreamMap dreamMap;
        private MapBlock default_block;





        // Конструктор
        public PutDreamBlocks(DreamMap dreamMap, Context context){
            this.dreamMap = dreamMap;
            this.context = context;
            this.activity = (Activity) context;
        }





        // Начало выполнения цикла
        @Override
        protected void onPreExecute(){
            int max_progress = dreamMap.getWidth() * dreamMap.getHeight();
            loader_show("Отрисовка карты", max_progress);


            mapContainer.setLayoutParams(new FrameLayout.LayoutParams(
                (dreamMap.getWidth() + 10) * dreamMap.getSize(),
                (dreamMap.getHeight() + 10) * dreamMap.getSize()
            ));
            mapContainer.setMinimumWidth((dreamMap.getWidth() + 10) * dreamMap.getSize());
            mapContainer.setMinimumHeight((dreamMap.getHeight() + 10) * dreamMap.getSize());
            mapContainer.setPadding(
                dreamMap.getSize()*5,
                dreamMap.getSize()*5,
                dreamMap.getSize()*5,
                0
            );
            adapter.setSize( dreamMap.getWidth(), dreamMap.getHeight() );
            adapter.clear();


            default_block = new MapBlock(
                    "empty",
                    "empty",
                    dreamMap.getSize(),
                    dreamMap.getRotate(),
                    dreamMap.getOrigRotate(),
                    0,
                    new String[]{"empty","t1"},
                    new String[]{"empty","t1"},
                    new String[]{"empty","t1"},
                    new String[]{"empty","t1"}
            );


            mapBlocks.clear();
            super.onPreExecute();
        }


        // Вставка одного блока
        @Override
        protected void onProgressUpdate(Integer... coords){
            super.onProgressUpdate(coords);

            int y = coords[0];
            int x = coords[1];
            int m_y = coords[2];
            int m_x = coords[3];
            int key = (y * m_x) + x;


            // Отображение прогресса
            int progress = key + 1;
            loader_change(null, progress);
        }


        // Вставка одного блока
        @Override
        protected Void doInBackground(Void... aVoid){
            int m_y = dreamMap.getHeight();
            int m_x = dreamMap.getWidth();



            for( int y=0; y<m_y; y+=1 ){
                for( int x=0; x<m_x; x+=1 ){
                    String back = dreamMap.getBlockBack(y,x);

                    // Карточка блока
                    if(!back.equals("empty")){
                        mapBlocks.add(new MapBlock(
                                back,
                                dreamMap.getBlockObject(y,x),
                                dreamMap.getSize(),
                                dreamMap.getRotate(),
                                dreamMap.getOrigRotate(),
                                dreamMap.getBlock3DHeight(y,x) * dreamMap.get3DHeightStep(),
                                dreamMap.getBlockBorderUP(y,x),
                                dreamMap.getBlockBorderRIGHT(y,x),
                                dreamMap.getBlockBorderDOWN(y,x),
                                dreamMap.getBlockBorderLEFT(y,x)
                        ));
                    }

                    else
                        {mapBlocks.add(default_block);}



                    adapter.changeBlock( y, x );
                    publishProgress(y, x, m_y, m_x);
                }
            }



            dreamMap.saveCache();

            return null;
        }


        // Конец выполнения
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Resources res = context.getResources();


            adapter.end();
            LinearLayout end_line = new LinearLayout(context);
            end_line.setLayoutParams(new FrameLayout.LayoutParams(
                    dreamMap.getWidth() * dreamMap.getSize(),
                    dreamMap.getSize() * 5
            ));
            end_line.setBackgroundColor(res.getColor(R.color.colorMapContentView));
            mapContainer.addView(end_line);

            loader_hide();
            correctScroll();
        }


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
    public void loader_show(String text, int max_progress){
        if(!loader_get()) {
            FrameLayout frameLayout = findViewById(R.id.mainLoader);
            frameLayout.setVisibility(View.VISIBLE);


            BottomNavigationView bottomNav = findViewById(R.id.bottom_menu);
            bottomNav.setVisibility(View.GONE);


            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(max_progress);
            progressBar.setProgress(0);

            TextView progressLeft = findViewById(R.id.progressLeft);
            progressLeft.setText(String.valueOf(0));

            TextView progressRight = findViewById(R.id.progressRight);
            progressRight.setText(String.valueOf(max_progress));


            TextView textView = findViewById(R.id.mainLoaderText);
            textView.setText(text);
        }
    }


    // Показать лоадер
    public void loader_change(String text, int progress){
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(progress);

        TextView progressLeft = findViewById(R.id.progressLeft);
        progressLeft.setText(String.valueOf(progress));

        if(text != null) {
            TextView textView = findViewById(R.id.mainLoaderText);
            textView.setText(text);
        }
    }


    // Скрыть лоадер
    public void loader_hide(){
        if(loader_get()){

            TextView textView=findViewById(R.id.mainLoaderText);
            FrameLayout frameLayout=findViewById(R.id.mainLoader);

            frameLayout.setVisibility(View.GONE);
            textView.setText("");

            BottomNavigationView bootomNav = findViewById(R.id.bottom_menu);
            bootomNav.setVisibility(View.VISIBLE);
        }
    }





    // Закрытие активности
    @Override
    public void finish() {
        super.finish();

        if(putDreamBlocks != null)
            {putDreamBlocks.cancel(false);}
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


    // Нажатие кнопки назад
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    // Скроллинг карты
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX, curY;
        ScrollView vScroll = (ScrollView) findViewById(R.id.verticalScroll);
        HorizontalScrollView hScroll = (HorizontalScrollView) findViewById(R.id.horizontalScroll);

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


    // Выбрана кнопка в главном меню
    public void onSelectButton(MenuItem item){
        int id = item.getItemId();



        // Повернуть влево
        if(id == R.id.rotate_left){
            if(dreamMap.rotateLEFT()){
                putDreamBlocks = new PutDreamBlocks(dreamMap, this);
                putDreamBlocks.execute();
            }
        }

        // Повернуть вправо
        else if(id == R.id.rotate_right){
            if(dreamMap.rotateRIGHT()){
                putDreamBlocks = new PutDreamBlocks(dreamMap, this);
                putDreamBlocks.execute();
            }
        }



        // Уменьшить
        else if(id == R.id.zoom_out){
            if(dreamMap.zoomOUT()){
                putDreamBlocks = new PutDreamBlocks(dreamMap, this);
                putDreamBlocks.execute();
            }
        }

        // Увеличить
        else if(id == R.id.zoom_in){
            if(dreamMap.zoomIN()){
                putDreamBlocks = new PutDreamBlocks(dreamMap, this);
                putDreamBlocks.execute();
            }
        }

    }





    // Клик по кнопке назад
    View.OnClickListener backButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            onBackPressed();
        }

    };


    // Клик по пункту меню
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        onSelectButton(item);
        return false;
    }


}
