package com.online.dreams_map.Activities.ViewNote;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.SubtitleCollapsingToolbarLayout;
import com.online.dreams_map.Activities.Blog.BlogLib;
import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.DreamsData.BlogNotes;
import com.online.dreams_map.Menus.MainMenu;
import com.online.dreams_map.R;
import com.online.dreams_map.SysLibs.DownLoadImageTask;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ActivityViewNote extends AppCompatActivity {

    private AuthData authData;
    private MainMenu mainMenu;

    private int note_id = 0;
    private int note_user = 0;
    private int keyText;
    private String prev_activity = "";
    private String note_title = "";





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewnote_activity);



        // Данные авторизации
        authData = new AuthData(this);



        // Заголовок
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(backButtonClick);

        mainMenu = new MainMenu(this, toolbar, "view_note" );



        // Смена цвета коллапсирующего меню
        AppBarLayout appbar = findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(toolbarColorChange);



        // ID просматриваемого сновидения
        {

            // ID из предыдущей активности
            Intent intent = getIntent();
            note_id = intent.getIntExtra("note_id",0);
            prev_activity = intent.getStringExtra("activity");
            prev_activity = prev_activity == null? "": prev_activity;
            keyText = View.generateViewId();

            // ID из ссылки
            if(note_id <= 0){
                String action = intent.getAction();
                String data = intent.getDataString();

                if(Intent.ACTION_VIEW.equals(action) && data != null) {
                    String[] url = data.split("\\?");
                    String[] params = url[0].split("/");

                    BlogNotes blogNotes = new BlogNotes(this);
                    blogNotes.setFilterLimit(1);
                    blogNotes.setFilterURLTitle(params[4]);
                    HashMap<Integer, HashMap<String, String>> note = blogNotes.getNotes(1);

                    if(Integer.parseInt(note.get(0).get("count_elms")) > 0)
                        {note_id = Integer.parseInt(note.get(1).get(blogNotes.field_id));}
                }
            }

        }



        putData();
    }





    // Обновить содержимое окна
    private void putData(){

        int px = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics() );



        // Отображение содержимого сновидения
        {
            BlogNotes blogNotes = new BlogNotes(this);
            blogNotes.setFilterID(note_id);
            HashMap<Integer, HashMap<String, String>> notes = blogNotes.getNotes(1);


            // Сон найден
            if(Integer.parseInt(notes.get(0).get("count")) > 0) {
                List<Card> cards = new ArrayList<Card>();
                HashMap<String, String> note = notes.get(1);



                // Пользователь сна
                note_user = Integer.parseInt(note.get(blogNotes.field_author));



                // Заголовок
                {
                    note_title = note.get(blogNotes.field_title);
                    SubtitleCollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
                    collapsingToolbarLayout.setTitle(note_title);
                }



                // Дата
                {
                    SubtitleCollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);

                    DateFormat df = new SimpleDateFormat("d MMMM yyyy (EEEE)");
                    String date_str = df.format(new Date(Long.parseLong(note.get(blogNotes.field_create_date)) * 1000));

                    collapsingToolbarLayout.setSubtitle(date_str);
                }



                // Фото и карта
                {

                    // Фото
                    if (note.get(blogNotes.field_cover).length() > 0) {
                        String filename = "note_cover_" + note.get(blogNotes.field_id);
                        new DownLoadImageTask((ImageView) findViewById(R.id.app_bar_image), filename, this).execute(note.get(blogNotes.field_cover));

                        cards.add(new Card(0,""));
                    }
                }



                // Содержимое
                try {
                    JSONArray contents = new JSONArray(note.get(blogNotes.field_content));

                    int length = contents.length();
                    if(length > 0){
                        for( int k=0; k<length; k++ ){
                            JSONObject v = contents.getJSONObject(k);
                            String type = v.getString("type");
                            int _type = type.equals("title")? 5: (type.equals("image")? 6: 4);

                            cards.add(new Card(_type, v.toString()));
                        }
                    }
                }
                catch (JSONException e){
                    Log.e("note_content_error", e.toString());
                    cards.add(new Card(4, "[]"));
                }



                // Ключевые слова
                {
                    if(note.get(blogNotes.field_keywords).length() > 0){
                        cards.add(new Card(2, note.get(blogNotes.field_keywords)));
                    }
                }



                // Юзер
                if(note_user > 0){
                    ODM_SqlLite db = new ODM_SqlLite(this);
                    HashMap<String, String> user_data = db.getUser(note_user);
                    int count = Integer.parseInt(user_data.get("count"));

                    if(count > 0)
                        {cards.add(new Card(3, String.valueOf(note_user)));}
                }





                RecyclerView rv = findViewById(R.id.recyclerView);

                RVAdapter adapter = new RVAdapter(cards, this, note_id);
                rv.setLayoutManager(new LinearLayoutManager(this));
                rv.setAdapter(adapter);
            }

            // Сон не найден
            else{
                onBackPressed();
            }

        }
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

        if(!prev_activity.equals("DreamsLib")){
            Intent intent = new Intent(this, BlogLib.class);

            startActivity(intent);
        }

        finish();
    }


    @Override
    public void onResume(){
        super.onResume();
    }





    // Клик по кнопке назад
    View.OnClickListener backButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            onBackPressed();
        }

    };


    // Смена цвета коллапсирующего меню
    private AppBarLayout.OnOffsetChangedListener toolbarColorChange = new AppBarLayout.OnOffsetChangedListener() {

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            View backElm = findViewById(R.id.toolbarBackground);
            Toolbar toolbar = findViewById(R.id.toolbar);
            AppBarLayout appbar = findViewById(R.id.appbar);

            int appbar_height = appbar.getHeight();
            int toolbar_height = toolbar.getHeight();
            int real_height = appbar_height + verticalOffset - mainMenu.getStatusBarHeight();
            int max_height = appbar_height - toolbar_height - mainMenu.getStatusBarHeight();
            int invert_height = Math.abs((real_height - toolbar_height) - max_height);
            int alpha = real_height>toolbar_height ? (invert_height * 255) / max_height : 255;

            backElm.setBackgroundColor(Color.argb(alpha, 1, 25, 80));
        }

    };


}
