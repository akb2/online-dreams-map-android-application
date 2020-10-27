package com.online.dreams_map.Activities.EditDream;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.online.dreams_map.Activities.ViewDream.ActivityViewDream;
import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.R;
import com.online.dreams_map.DreamsData.Catalogs;
import com.online.dreams_map.SysLibs.ComboBox;
import com.online.dreams_map.SysLibs.ListBox;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;





public class EditDreamActivity extends AppCompatActivity {

    private float mx, my;
    private ScrollView vScroll;
    private Date currentDate = new Date();
    private int myYear = 1994;
    private int myMonth = 9;
    private int myDay = 27;

    private int DIALOG_DATE = 1;

    private EditText dreamsTitle;
    private TextView dreamsDate;
    private ListBox dreamsKeywords;
    private ComboBox dreamsPublic;
    private ComboBox dreamsCategory;
    private ComboBox dreamsMood;
    private EditText dreamsContent;
    private JSONObject dreamsMap;
    private AuthData authData;

    private int dream_id = 0;
    private HashMap<String, String> dream = new HashMap<>();
    private boolean new_dream = true;
    private ODM_SqlLite dreams_sql;

    private String[] replacement = new String[]{
            "`","~","!","@","\"","#","№","$",";","%",":","^","?","&","*","(",")","-","_",
            "+","=","[","]","{","}","\\","/","|",".",",","<",">"
    };
    private Catalogs catalogs = new Catalogs();


    private final HashMap <String, HashMap <String,String>> public_lib;
    {
        public_lib = new HashMap<>();

        public_lib.put("0", new HashMap());
        public_lib.put("1", new HashMap());
        public_lib.put("2", new HashMap());

        public_lib.get("0").put("name","Только вы");
        public_lib.get("1").put("name","Только пользователи сайта и приложения");
        public_lib.get("2").put("name","Весь интернет");
    }
    private final String[] public_lib_sort = new String[]{"0","1","2"};


    private final HashMap <String, HashMap <String,String>> category_lib;
    {
        category_lib = catalogs.getCatalogs();
    }
    private final String[] category_lib_sort = new String[]{"1","2","3","4","5","6"};


    private final HashMap <String, HashMap <String,String>> mood_lib;
    {
        mood_lib = catalogs.getMoods();
    }
    private final String[] mood_lib_sort = new String[]{"7","8","9","10","11","12"};


    private String user="0";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdream_activity);



        // Данные о токене
        authData = new AuthData(this);
        user = authData.getUser();



        // ID просматриваемого сновидения
        Intent intent = getIntent();
        dream_id = Integer.valueOf(intent.getIntExtra("dream_id",0));


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



        // Поля
        vScroll = (ScrollView) findViewById(R.id.verticalScroll);

        dreamsDate = findViewById(R.id.dreamDate);

        dreamsTitle = (EditText) findViewById(R.id.dreamTitle);

        LinearLayout tvKeywords = (LinearLayout) findViewById(R.id.dreamKeywords);
        dreamsKeywords = new ListBox(tvKeywords,this, replacement, "Введите ключевые слова");

        LinearLayout tvPublic = (LinearLayout) findViewById(R.id.dreamPublic);
        dreamsPublic = new ComboBox(tvPublic, this, "Кто может просматривать сновидение", public_lib, public_lib_sort);

        LinearLayout tvCategory = (LinearLayout) findViewById(R.id.dreamCategory);
        dreamsCategory = new ComboBox(tvCategory, this, "Категория сновидения", category_lib, category_lib_sort);

        LinearLayout tvMood = (LinearLayout) findViewById(R.id.dreamMood);
        dreamsMood = new ComboBox(tvMood, this, "Настроение сновидения", mood_lib, mood_lib_sort);

        dreamsContent = (EditText) findViewById(R.id.dreamContent);



        // Установка значений
        {

            // Новый сон
            if(dream_id == 0){

                // Дата
                {
                    currentDate = new Date();
                    myYear = Integer.parseInt((new SimpleDateFormat("yyyy", Locale.getDefault())).format(currentDate));
                    myMonth = Integer.parseInt((new SimpleDateFormat("MM", Locale.getDefault())).format(currentDate));
                    myDay = Integer.parseInt((new SimpleDateFormat("dd", Locale.getDefault())).format(currentDate));
                    dreamsDate.setText(
                        (myDay<10?"0":"")+ myDay + "."+
                        (myMonth<10?"0":"")+ myMonth + "." +
                        myYear
                    );
                }

            }

            // Редактирование сна
            else{
                dreams_sql = new ODM_SqlLite(this);
                dream = dreams_sql.getDream(dream_id, "local");
                new_dream = false;


                // Сон найден
                if(dream.get("count").equals("1")){

                    // Заголовок
                    getSupportActionBar().setTitle("Редактирование сна");
                    dreamsTitle.setText(dream.get(dreams_sql.DREAMS_KEY_TITLE));

                    // Дата
                    {
                        currentDate = new Date(Long.parseLong(dream.get(dreams_sql.DREAMS_KEY_DATE)) * 1000);
                        myYear = Integer.parseInt((new SimpleDateFormat("yyyy", Locale.getDefault())).format(currentDate));
                        myMonth = Integer.parseInt((new SimpleDateFormat("MM", Locale.getDefault())).format(currentDate));
                        myDay = Integer.parseInt((new SimpleDateFormat("dd", Locale.getDefault())).format(currentDate));
                        dreamsDate.setText(
                                (myDay<10?"0":"")+ myDay + "."+
                                        (myMonth<10?"0":"")+ myMonth + "." +
                                        myYear
                        );
                    }

                    // Ключевые слова
                    {
                        String[] values = new String[]{};
                        if(dream.get(dreams_sql.DREAMS_KEY_KEYWORDS).length()>0)
                            {values = dream.get(dreams_sql.DREAMS_KEY_KEYWORDS).split(",");}
                        for(String v : values){
                            dreamsKeywords.addValue(v);
                        }
                    }

                    // Статус публикации
                    {
                        dreamsPublic.setValue(dream.get(dreams_sql.DREAMS_KEY_PUBLIC));
                    }

                    // Категория сновидения
                    {
                        dreamsCategory.setValue(dream.get(dreams_sql.DREAMS_KEY_CATEGORY));
                    }

                    // Настроения сновидения
                    {
                        dreamsMood.setValue(dream.get(dreams_sql.DREAMS_KEY_MOOD));
                    }

                    // Текст сновидения
                    {
                        dreamsContent.setText(dream.get(dreams_sql.DREAMS_KEY_CONTENT));
                    }

                    // Карта сновидения
                    {
                        try{
                            dreamsMap = new JSONObject(dream.get(dreams_sql.DREAMS_KEY_MAP_DATA));
                        }

                        catch (JSONException e){}
                    }

                }

                // Сон не найден
                else{
                    onBackPressed();
                }
            }

        }

    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curY;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                my = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                curY = event.getY();
                vScroll.scrollBy(0, (int) (my - curY));
                my = curY;

                break;
            case MotionEvent.ACTION_UP:
                curY = event.getY();
                vScroll.scrollBy(0, (int) (my - curY));

                break;
        }

        return true;
    }

    // Открытие диалоговых окон
    protected Dialog onCreateDialog(int id) {

        // Диалог выбора даты
        if (id == DIALOG_DATE){
            myYear = Integer.parseInt((new SimpleDateFormat("yyyy", Locale.getDefault())).format(currentDate));
            myMonth = Integer.parseInt((new SimpleDateFormat("MM", Locale.getDefault())).format(currentDate))-1;
            myDay = Integer.parseInt((new SimpleDateFormat("dd", Locale.getDefault())).format(currentDate));

            DatePickerDialog tpd = new DatePickerDialog(this, mySelectDateCallBack, myYear, myMonth, myDay);
            return tpd;
        }

        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener mySelectDateCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear+1;
            myDay = dayOfMonth;
            dreamsDate.setText(
                (myDay<10?"0":"")+ myDay + "."+
                (myMonth<10?"0":"")+ myMonth + "." +
                myYear
            );
        }

    };





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





    // Кнопка сохранить в меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_button, menu);
        return true;
    }


    // Нажатие кнопки меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Сохранение сна
        if (item.getItemId() == R.id.navigation_save){


            // Дата сна
            try {
                dreams_sql = new ODM_SqlLite(this);

                // Заголовок сна
                String getTitle = dreamsTitle.getText().toString();

                // Дата сна
                String date_s = dreamsDate.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");
                Date date_d = format.parse(date_s);
                String getDate = String.valueOf(Math.round((float)(date_d.getTime() / 1000)));

                // Ключевые слова
                String getKeywords = dreamsKeywords.getValue();

                // Кто может смотреть сон
                String getPublic = dreamsPublic.getValue();

                // Категория сна
                String getCategory = dreamsCategory.getValue();

                // Настроение сна
                String getMood = dreamsMood.getValue();

                // Содержимое сна
                String getContent = dreamsContent.getText().toString();



                // Локальный ID
                String sysLocalID = "0";

                // Серверный ID
                String sysServerID = "0";

                // Просмотры
                String sysViewCount = "0";

                // Дата создания и редактирования
                String sysCreateDate = String.valueOf(Math.round(System.currentTimeMillis() / 1000) + authData.getCorrectTime());
                String sysEditDate = sysCreateDate;

                // Пользователь
                String sysUser = user;

                // Карта
                String sysMap = "";

                // Использование карты
                String sysUseMap = "0";

                // Обложка
                String sysCover = "";


                // Если не новый сон
                if(new_dream == false){

                    sysLocalID = dream.get(dreams_sql.DREAMS_KEY_LOCAL_ID);
                    sysServerID = dream.get(dreams_sql.DREAMS_KEY_ID);
                    sysViewCount = dream.get(dreams_sql.DREAMS_KEY_VIEW_COUNT);
                    sysCreateDate = dream.get(dreams_sql.DREAMS_KEY_CREATE_DATE);
                    sysMap = dream.get(dreams_sql.DREAMS_KEY_MAP_DATA);
                    sysUseMap = dream.get(dreams_sql.DREAMS_KEY_USE_MAP);
                    sysCover = dream.get(dreams_sql.DREAMS_KEY_COVER);

                    if(sysEditDate.equals(dream.get(dreams_sql.DREAMS_KEY_EDIT_DATE))){
                        sysEditDate = String.valueOf(Integer.parseInt(sysEditDate)+1);
                    }
                }



                // Сохранение сна
                JSONObject dataDream = new JSONObject();

                dataDream.put(dreams_sql.DREAMS_KEY_LOCAL_ID,           sysLocalID);
                dataDream.put(dreams_sql.DREAMS_KEY_ID,                 sysServerID);
                dataDream.put(dreams_sql.DREAMS_KEY_VIEW_COUNT,         sysViewCount);
                dataDream.put("create",                           sysCreateDate);
                dataDream.put(dreams_sql.DREAMS_KEY_EDIT_DATE,          sysEditDate);
                dataDream.put(dreams_sql.DREAMS_KEY_USER,               sysUser);
                dataDream.put(dreams_sql.DREAMS_KEY_TITLE,              getTitle);
                dataDream.put("date",                             getDate);
                dataDream.put(dreams_sql.DREAMS_KEY_KEYWORDS,           getKeywords);
                dataDream.put(dreams_sql.DREAMS_KEY_PUBLIC,             getPublic);
                dataDream.put(dreams_sql.DREAMS_KEY_CONTENT,            getContent);
                dataDream.put(dreams_sql.DREAMS_KEY_MAP_DATA,           sysMap);
                dataDream.put(dreams_sql.DREAMS_KEY_USE_MAP,            sysUseMap);
                dataDream.put(dreams_sql.DREAMS_KEY_COVER,              sysCover);
                dataDream.put(dreams_sql.DREAMS_KEY_CATEGORY,           new JSONObject());
                dataDream.put(dreams_sql.DREAMS_KEY_MOOD,               new JSONObject());
                dataDream.getJSONObject(dreams_sql.DREAMS_KEY_CATEGORY).put("id",getCategory);
                dataDream.getJSONObject(dreams_sql.DREAMS_KEY_MOOD).put("id",getMood);




                // Сохранить существующий сон
                if(new_dream == false) {
                    boolean save = dreams_sql.saveDream(dataDream);

                    if(save == true) {
                        Intent result = new Intent();
                        result.putExtra("MESSAGE", "SAVE_DATA_OK");
                        setResult(android.app.Activity.RESULT_OK, result);

                        finish();
                    }
                }

                // Новый сон
                else {
                    long save = dreams_sql.saveEditDream(dataDream);

                    if(save > 0) {
                        Intent intent = new Intent(this, ActivityViewDream.class);
                        intent.putExtra("dream_id", (int) save);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_in, R.anim.right_out);

                        finish();
                    }
                }

            }

            catch(java.text.ParseException e){}

            catch(JSONException e){}

        }


        return super.onOptionsItemSelected(item);

    }


    // Выбор даты
    public void openDateDialog(View view) {
        showDialog(DIALOG_DATE);
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

                    dreamsPublic.returnValue(key,class_id);
                    dreamsCategory.returnValue(key,class_id);
                    dreamsMood.returnValue(key,class_id);
                }
            }
        }
    }





}
