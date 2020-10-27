package com.online.dreams_map.Activities.DreamsLib;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.online.dreams_map.R;
import com.online.dreams_map.DreamsData.Catalogs;
import com.online.dreams_map.SysLibs.ComboBox;

import java.util.HashMap;





public class ActivitySearch extends AppCompatActivity {


    private Toolbar toolbar;

    private String search = "";
    private int category = 0;
    private int mood = 0;
    private int _public = -1;
    private Catalogs catalogs = new Catalogs();

    private EditText searchText;
    private ComboBox searchPublic;
    private ComboBox searchCategory;
    private ComboBox searchMood;


    private final HashMap <String, HashMap<String,String>> public_lib;{
        public_lib = new HashMap<>();

        public_lib.put("-1", new HashMap());
        public_lib.put("0", new HashMap());
        public_lib.put("1", new HashMap());
        public_lib.put("2", new HashMap());

        public_lib.get("-1").put("name","Любой статус");
        public_lib.get("0").put("name","Только вы");
        public_lib.get("1").put("name","Только пользователи сайта и приложения");
        public_lib.get("2").put("name","Весь интернет");
    }
    private final String[] public_lib_sort = new String[]{"-1","0","1","2"};


    private final HashMap <String, HashMap<String,String>> _public_lib;{
        _public_lib = new HashMap<>();

        _public_lib.put("-1", new HashMap());
        _public_lib.put("1", new HashMap());
        _public_lib.put("2", new HashMap());

        _public_lib.get("-1").put("name","Любой статус");
        _public_lib.get("1").put("name","Только пользователи сайта и приложения");
        _public_lib.get("2").put("name","Весь интернет");
    }
    private final String[] _public_lib_sort = new String[]{"-1","1","2"};

    private final HashMap <String, HashMap <String,String>> category_lib;{
        category_lib = catalogs.getCatalogs();

        category_lib.put("0", new HashMap<String, String>());
        category_lib.get("0").put("name","Любая категория");
    }
    private final String[] category_lib_sort = new String[]{"0","1","2","3","4","5","6"};

    private final HashMap <String, HashMap <String,String>> mood_lib;{
        mood_lib = catalogs.getMoods();

        mood_lib.put("0", new HashMap<String, String>());
        mood_lib.get("0").put("name","Любое настроение");
    }
    private final String[] mood_lib_sort = new String[]{"0","7","8","9","10","11","12"};





    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dreams_searchform_activity);


        // Заголовок
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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



        Intent intent = getIntent();
        String lib_type = intent.getStringExtra("lib_type");
        lib_type = lib_type==null? "": lib_type;
        lib_type = lib_type.equals("all") || lib_type.equals("user")? lib_type: "my";



        // Поля
        searchText = (EditText) findViewById(R.id.searchText);

        LinearLayout tvPublic = (LinearLayout) findViewById(R.id.searchPublic);
        if(lib_type.equals("all"))
            {searchPublic = new ComboBox(tvPublic, this, "Кто может просматривать сновидение", _public_lib, _public_lib_sort);}
        else
            {searchPublic = new ComboBox(tvPublic, this, "Кто может просматривать сновидение", public_lib, public_lib_sort);}

        LinearLayout tvCategory = (LinearLayout) findViewById(R.id.searchCategory);
        searchCategory = new ComboBox(tvCategory, this, "Категория сновидения", category_lib, category_lib_sort);

        LinearLayout tvMood = (LinearLayout) findViewById(R.id.searchMood);
        searchMood = new ComboBox(tvMood, this, "Настроение сновидения", mood_lib, mood_lib_sort);



        // Получение параметров поиска
        search = intent.getStringExtra("search_text");
        search = search==null? "": search;

        _public = intent.getIntExtra("search_public", -1);
        _public = _public<-1 || _public>2? -1: _public;

        category = intent.getIntExtra("search_category", 0);
        category = category<0 || category>6? 0: category;

        mood = intent.getIntExtra("search_mood", 0);
        mood = mood<7 || mood>12? 0: mood;



        // Установка значений
        searchText.setText(search);
        searchPublic.setValue(String.valueOf(_public));
        searchCategory.setValue(String.valueOf(category));
        searchMood.setValue(String.valueOf(mood));


    }





    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }





    // Кнопка сохранить в меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.apply_button, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_apply){
            String res_search = String.valueOf(searchText.getText());
            String res_public = searchPublic.getValue();
            String res_category = searchCategory.getValue();
            String res_mood = searchMood.getValue();

            Intent result = new Intent();
            result.putExtra("MESSAGE", "CHANGE_SEARCH_FILTER");
            result.putExtra("search_text", res_search);
            result.putExtra("search_public", res_public);
            result.putExtra("search_category", res_category);
            result.putExtra("search_mood", res_mood);

            setResult(Activity.RESULT_OK, result);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }





    // Получить результат
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            if(data.hasExtra("MESSAGE")){
                if(data.getStringExtra("MESSAGE").equals("COMBOBOX_OK")){
                    String key = data.getStringExtra("key");
                    int class_id = data.getIntExtra("class_id",0);

                    searchPublic.returnValue(key,class_id);
                    searchCategory.returnValue(key,class_id);
                    searchMood.returnValue(key,class_id);
                }
            }
        }
    }


}
