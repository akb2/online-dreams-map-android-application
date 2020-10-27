package com.online.dreams_map.Activities.Blog;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.online.dreams_map.DreamsData.BlogCatalogs;
import com.online.dreams_map.R;
import com.online.dreams_map.SysLibs.ComboBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class ActivitySearch extends AppCompatActivity {


    private Toolbar toolbar;

    private String search = "";
    private int catalog = 0;
    private BlogCatalogs catalogs;

    private EditText searchText;
    private ComboBox searchCatalog;

    private HashMap <String, HashMap<String,String>> catalogs_lib = new HashMap<>();
    private String[] catalogs_lib_sort = new String[]{};





    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_searchform_activity);


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
        catalogs = new BlogCatalogs(this);
        catalogs.setFilterLimit(0);



        // Список рубрик статей
        HashMap<Integer, HashMap<String,String>> catalogs_temp = catalogs.getCatalogs(1);
        HashMap<Integer, Integer> _catalogs_lib_sort = new HashMap<>();
        int count = Integer.parseInt(catalogs_temp.get(0).get("count_elms"));
        if(count > 0){
            for(Map.Entry<Integer, HashMap<String,String>> entry : catalogs_temp.entrySet()){
                int k = entry.getKey();

                if(k > 0){
                    HashMap<String,String> v = entry.getValue();
                    String key = v.get("id");

                    _catalogs_lib_sort.put(
                            Integer.parseInt(v.get("id")),
                            Integer.parseInt(v.get("sort_index"))
                    );

                    catalogs_lib.put(key, new HashMap<String, String>());
                    catalogs_lib.get(key).put("name", v.get("name"));

                    if(v.get("cover").length() > 0){
                        String filename = "dreams_cover_" + v.get("id");
                        catalogs_lib.get(key).put("image", v.get("cover"));
                        catalogs_lib.get(key).put("image_file_name", filename);
                    }
                }
            }

            catalogs_lib_sort = new String[count + 1];
        }

        else
            {catalogs_lib_sort = new String[1];}

        catalogs_lib.put("0", new HashMap<String, String>());
        catalogs_lib.get("0").put("name","Любая рубрика");

        int length = _catalogs_lib_sort.size();
        if(length > 0){
            List list = new ArrayList(_catalogs_lib_sort.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                    return a.getValue() - b.getValue();
                }
            });

            int i = 1;
            catalogs_lib_sort[0] = "0";
            for(Map.Entry<Integer,Integer> entry : _catalogs_lib_sort.entrySet()){
                catalogs_lib_sort[i] = String.valueOf(entry.getKey());
                i++;
            }
        }



        // Поля
        searchText = (EditText) findViewById(R.id.searchText);

        LinearLayout tvCategory = (LinearLayout) findViewById(R.id.searchCatalog);
        searchCatalog = new ComboBox(tvCategory, this, "Рубрика", catalogs_lib, catalogs_lib_sort);



        // Получение параметров поиска
        search = intent.getStringExtra("search_text");
        search = search==null? "": search;

        catalog = intent.getIntExtra("search_catalog", 0);
        catalog = catalog<0? 0: catalog;



        // Установка значений
        searchText.setText(search);
        searchCatalog.setValue(String.valueOf(catalog));


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
            String res_catalog = searchCatalog.getValue();

            Intent result = new Intent();
            result.putExtra("MESSAGE", "CHANGE_SEARCH_FILTER");
            result.putExtra("search_text", res_search);
            result.putExtra("search_catalog", res_catalog);

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

                    searchCatalog.returnValue(key,class_id);
                }
            }
        }
    }
}
