package com.online.dreams_map.SysLibs;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.DreamsData.BlogCatalogs;
import com.online.dreams_map.DreamsData.BlogNotes;
import com.online.dreams_map.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ODM_SqlLite extends SQLiteOpenHelper {
    private Context context;
    public ContentValues contentValues;

    private String user="0";
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_USER= "user";

    public String TABLE_DREAMS = "dreams_lib";
    public String TABLE_USERS = "user_lib";
    public String TABLE_BLOG = "blog";
    public String TABLE_BLOG_CAT = "blog_category";


    public final String DREAMS_KEY_LOCAL_ID = "id";
    public final String DREAMS_KEY_ID = "server_id";
    public final String DREAMS_KEY_ACTION = "act";
    public final String DREAMS_KEY_PUBLIC = "public";
    public final String DREAMS_KEY_VIEW_COUNT = "view_count";
    public final String DREAMS_KEY_TITLE = "title";
    public final String DREAMS_KEY_KEYWORDS = "keywords";
    public final String DREAMS_KEY_CONTENT = "content";
    public final String DREAMS_KEY_DESCRIPTION = "description";
    public final String DREAMS_KEY_CATEGORY = "category";
    public final String DREAMS_KEY_MOOD = "mood";
    public final String DREAMS_KEY_DATE = "where_date";
    public final String DREAMS_KEY_CREATE_DATE = "create_date";
    public final String DREAMS_KEY_EDIT_DATE = "edit_date";
    public final String DREAMS_KEY_USER = "user";
    public final String DREAMS_KEY_MAP_DATA = "map_data";
    public final String DREAMS_KEY_USE_MAP = "use_map";
    public final String DREAMS_KEY_COVER = "cover";

    public final String BLOG_KEY_ID = "id";
    public final String BLOG_KEY_CATALOG = "catalog";
    public final String BLOG_KEY_URL_TITLE = "url_title";
    public final String BLOG_KEY_CREATE_DATE = "create_date";
    public final String BLOG_KEY_EDIT_DATE = "edit_date";
    public final String BLOG_KEY_TITLE = "title";
    public final String BLOG_KEY_KEYWORDS = "keywords";
    public final String BLOG_KEY_DESCRIPTION = "description";
    public final String BLOG_KEY_CONTENT = "content";
    public final String BLOG_KEY_COVER = "cover";
    public final String BLOG_KEY_AUTHOR = "autor";

    public final String BLOG_CAT_KEY_ID = "id";
    public final String BLOG_CAT_KEY_EDIT_DATE = "edit_date";
    public final String BLOG_CAT_KEY_SORT_INDEX = "sort_index";
    public final String BLOG_CAT_KEY_URL_TITLE = "url_title";
    public final String BLOG_CAT_KEY_NAME = "name";
    public final String BLOG_CAT_KEY_DESCRIPTION = "description";
    public final String BLOG_CAT_KEY_COVER = "cover";

    public final String USER_KEY_ID = "id";
    public final String USER_KEY_TOKEN = "token";
    public final String USER_KEY_NAME = "name";
    public final String USER_KEY_LASTNAME = "lastname";
    public final String USER_KEY_FATHER = "father";
    public final String USER_KEY_BDAY = "bday";
    public final String USER_KEY_BMON = "bmon";
    public final String USER_KEY_BYEAR = "byear";
    public final String USER_KEY_AVA_LINK = "ava_link";
    public final String USER_KEY_SMALL_AVA_LINK = "ava_small_link";
    public final String USER_KEY_LAST_VISIT = "last_visit";





    public ODM_SqlLite(Context context) {
        super(
                context,
                context.getResources().getString(R.string.db_name),
                null,
                Integer.parseInt(context.getResources().getString(R.string.db_ver))
        );
        this.context=context;

        // Данные о токене
        {
            SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, context.MODE_PRIVATE);

            if (mSettings.contains(APP_PREFERENCES_USER))
                {user = mSettings.getString(APP_PREFERENCES_USER, "0");}
            else
                {user = "0";}
        }
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

        // Создание таблицы сновидений
        db.execSQL(
            "create table " + TABLE_DREAMS + "(" +
                DREAMS_KEY_LOCAL_ID + " integer primary key autoincrement not null, " +
                DREAMS_KEY_ID + " integer, " +
                DREAMS_KEY_ACTION + " text not null, " +
                DREAMS_KEY_PUBLIC + " integer, " +
                DREAMS_KEY_VIEW_COUNT + " integer, " +
                DREAMS_KEY_TITLE + " text not null, " +
                DREAMS_KEY_KEYWORDS + " text not null, " +
                DREAMS_KEY_CONTENT + " text not null, " +
                DREAMS_KEY_CATEGORY + " integer, " +
                DREAMS_KEY_MOOD + " integer, " +
                DREAMS_KEY_DATE + " integer, " +
                DREAMS_KEY_CREATE_DATE + " integer, " +
                DREAMS_KEY_EDIT_DATE + " integer, " +
                DREAMS_KEY_USER + " integer, " +
                DREAMS_KEY_MAP_DATA + " text not null, " +
                DREAMS_KEY_USE_MAP + " integer, " +
                DREAMS_KEY_COVER + " text not null " +
            ")"
        );

        // Создание таблицы пользователей
        db.execSQL(
            "create table " + TABLE_USERS + "(" +
                USER_KEY_ID + " integer primary key, " +
                USER_KEY_NAME + " text not null, " +
                USER_KEY_LASTNAME + " text not null, " +
                USER_KEY_FATHER + " text not null, " +
                USER_KEY_BDAY + " integer, " +
                USER_KEY_BMON+ " integer, " +
                USER_KEY_BYEAR + " integer, " +
                USER_KEY_AVA_LINK + " text not null, " +
                USER_KEY_SMALL_AVA_LINK + " text not null, " +
                USER_KEY_LAST_VISIT + " integer " +
            ")"
        );

        // Создание таблицы статей
        db.execSQL(
            "create table " + TABLE_BLOG + "(" +
                BLOG_KEY_ID + " integer primary key, " +
                BLOG_KEY_CATALOG + " text not null, " +
                BLOG_KEY_URL_TITLE + " text not null, " +
                BLOG_KEY_CREATE_DATE + " integer, " +
                BLOG_KEY_EDIT_DATE + " integer, " +
                BLOG_KEY_TITLE + " text not null, " +
                BLOG_KEY_KEYWORDS + " text not null, " +
                BLOG_KEY_DESCRIPTION + " text not null, " +
                BLOG_KEY_CONTENT + " text not null, " +
                BLOG_KEY_COVER + " text not null, " +
                BLOG_KEY_AUTHOR + " integer " +
            ")"
        );

        // Создание таблицы рубрик статей
        db.execSQL(
            "create table " + TABLE_BLOG_CAT + "(" +
                BLOG_CAT_KEY_ID + " integer primary key, " +
                BLOG_CAT_KEY_URL_TITLE + " text not null, " +
                BLOG_CAT_KEY_EDIT_DATE + " integer, " +
                BLOG_CAT_KEY_SORT_INDEX + " integer, " +
                BLOG_CAT_KEY_NAME + " text not null, " +
                BLOG_CAT_KEY_DESCRIPTION + " text not null, " +
                BLOG_CAT_KEY_COVER + " text not null " +
            ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion){
            db.execSQL("drop table if exists " + TABLE_DREAMS);
            db.execSQL("drop table if exists " + TABLE_USERS);
            db.execSQL("drop table if exists " + TABLE_BLOG);
            db.execSQL("drop table if exists " + TABLE_BLOG_CAT);

            onCreate(db);
        }
    }





    // Мои сновидения
    public HashMap<Integer, HashMap<String,String>> getMyDreams(int start, int limit){
        HashMap<Integer, HashMap<String,String>> ret = new HashMap();



        if(Integer.parseInt(user)>0){

            // Лимиты
            limit=limit<0?0:(limit>30?30:limit);
            start=start<0?0:start;
            String limit_str = limit > 0 ? "LIMIT "+start+","+limit : "";
            int count = 0;


            SQLiteDatabase db = this.getReadableDatabase();

            // Запрос для подсчета данных
            Cursor count_cur = db.rawQuery(
                "SELECT (COUNT("+DREAMS_KEY_LOCAL_ID+")) AS count "+
                        "FROM "+TABLE_DREAMS+" "+
                        "WHERE "+
                        DREAMS_KEY_USER+" = "+user,
                null
            );
            if (count_cur.moveToFirst()) {
                while(count_cur.isAfterLast() == false) {
                    count = count_cur.getInt(count_cur.getColumnIndex("count"));
                    count_cur.moveToNext();
                }
            }
            ret.put(0,new HashMap());
            ret.get(0).put("count",String.valueOf(count));



            // Вывод значений
            if(count>0) {
                // Запрос для получения данных
                Cursor cursor = db.rawQuery(
                        "SELECT * "+
                                "FROM "+TABLE_DREAMS+" "+
                                "WHERE "+
                                DREAMS_KEY_USER+" = "+user+" "+
                                "ORDER BY "+DREAMS_KEY_DATE+" DESC, "+DREAMS_KEY_ID+" DESC "+
                                limit_str,
                        null
                );
                ret.get(0).put("count_elms",String.valueOf(cursor.getCount()));

                if (cursor.moveToFirst()) {
                    int i=1;

                    do{
                        ret.put(i,new HashMap());
                        int content_length = cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)).length();

                        ret.get(i).put(DREAMS_KEY_LOCAL_ID,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_LOCAL_ID)));
                        ret.get(i).put(DREAMS_KEY_ID,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_ID)));
                        ret.get(i).put(DREAMS_KEY_ACTION,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_ACTION)));
                        ret.get(i).put(DREAMS_KEY_CREATE_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CREATE_DATE)));
                        ret.get(i).put(DREAMS_KEY_EDIT_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_EDIT_DATE)));
                        ret.get(i).put(DREAMS_KEY_CATEGORY,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CATEGORY)));
                        ret.get(i).put(DREAMS_KEY_MOOD,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_MOOD)));
                        ret.get(i).put(DREAMS_KEY_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_DATE)));
                        ret.get(i).put(DREAMS_KEY_TITLE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_TITLE)));
                        ret.get(i).put(DREAMS_KEY_DESCRIPTION,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)).substring(0,(content_length<160?content_length:160)));
                        ret.get(i).put(DREAMS_KEY_KEYWORDS,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_KEYWORDS)));
                        ret.get(i).put(DREAMS_KEY_CONTENT,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)));
                        ret.get(i).put(DREAMS_KEY_COVER,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_COVER)));

                        i++;
                    }

                    while(cursor.moveToNext());

                    cursor.close();
                }
            }


            db.close();
        }



        return ret;
    }

    // Все сновидения
    public HashMap<Integer, HashMap<String,String>> getAllDreams(int start, int limit){
        HashMap<Integer, HashMap<String,String>> ret = new HashMap();



        if(Integer.parseInt(user)>0){

            // Лимиты
            limit=limit<0?0:(limit>30?30:limit);
            start=start<0?0:start;
            String limit_str = limit > 0 ? "LIMIT "+start+","+limit : "";
            int count = 0;


            SQLiteDatabase db = this.getReadableDatabase();

            // Запрос для подсчета данных
            Cursor count_cur = db.rawQuery(
                "SELECT (COUNT("+DREAMS_KEY_LOCAL_ID+")) AS count "+
                        "FROM "+TABLE_DREAMS+" "+
                        "WHERE "+
                            " ( "+
                                DREAMS_KEY_PUBLIC+" = 1 OR "+
                                DREAMS_KEY_PUBLIC+" = 2 "+
                            " ) ",
                null
            );
            if (count_cur.moveToFirst()) {
                while(count_cur.isAfterLast() == false) {
                    count = count_cur.getInt(count_cur.getColumnIndex("count"));
                    count_cur.moveToNext();
                }
            }
            ret.put(0,new HashMap());
            ret.get(0).put("count",String.valueOf(count));



            // Вывод значений
            if(count>0) {
                // Запрос для получения данных
                Cursor cursor = db.rawQuery(
                        "SELECT * "+
                                "FROM "+TABLE_DREAMS+" "+
                                "WHERE "+
                                    " ( "+
                                        DREAMS_KEY_PUBLIC+" = 1 OR "+
                                        DREAMS_KEY_PUBLIC+" = 2 "+
                                    " ) "+
                                "ORDER BY "+DREAMS_KEY_DATE+" DESC, "+DREAMS_KEY_ID+" DESC "+
                                limit_str,
                        null
                );
                ret.get(0).put("count_elms",String.valueOf(cursor.getCount()));

                if (cursor.moveToFirst()) {
                    int i=1;

                    do{
                        ret.put(i,new HashMap());
                        int content_length = cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)).length();

                        ret.get(i).put(DREAMS_KEY_LOCAL_ID,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_LOCAL_ID)));
                        ret.get(i).put(DREAMS_KEY_ID,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_ID)));
                        ret.get(i).put(DREAMS_KEY_ACTION,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_ACTION)));
                        ret.get(i).put(DREAMS_KEY_CREATE_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CREATE_DATE)));
                        ret.get(i).put(DREAMS_KEY_EDIT_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_EDIT_DATE)));
                        ret.get(i).put(DREAMS_KEY_CATEGORY,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CATEGORY)));
                        ret.get(i).put(DREAMS_KEY_MOOD,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_MOOD)));
                        ret.get(i).put(DREAMS_KEY_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_DATE)));
                        ret.get(i).put(DREAMS_KEY_TITLE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_TITLE)));
                        ret.get(i).put(DREAMS_KEY_DESCRIPTION,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)).substring(0,(content_length<160?content_length:160)));
                        ret.get(i).put(DREAMS_KEY_CONTENT,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)));
                        ret.get(i).put(DREAMS_KEY_COVER,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_COVER)));

                        i++;
                    }

                    while(cursor.moveToNext());

                    cursor.close();
                }
            }


            db.close();
        }



        return ret;
    }

    // Определенный сон
    public HashMap<String,String> getDream(int id, String type){
        HashMap<String,String> ret = new HashMap<>();
        ret.put("count","0");
        int count = 0;

        String id_type = type.equals("server")? DREAMS_KEY_ID: DREAMS_KEY_LOCAL_ID;


        if(id>0){
            SQLiteDatabase db = this.getReadableDatabase();

            // Запрос для подсчета данных
            Cursor count_cur = db.rawQuery(
                "SELECT (COUNT("+id_type+")) AS count "+
                        "FROM "+TABLE_DREAMS+" "+
                        "WHERE "+
                            id_type+" = "+id+" AND ("+
                                DREAMS_KEY_USER+"="+user+" OR "+
                                DREAMS_KEY_PUBLIC+"=1 OR "+
                                DREAMS_KEY_PUBLIC+"=2 "+
                            ")",
                null
            );
            if (count_cur.moveToFirst()) {
                while(count_cur.isAfterLast() == false) {
                    count = count_cur.getInt(count_cur.getColumnIndex("count"));
                    count_cur.moveToNext();
                }
            }



            if(count>0){
                ret.put("count","1");


                // Запрос для получения данных
                Cursor cursor = db.rawQuery(
                        "SELECT * "+
                                "FROM "+TABLE_DREAMS+" "+
                                "WHERE "+
                                    id_type+" = "+id+" AND ("+
                                        DREAMS_KEY_USER+"="+user+" OR "+
                                        DREAMS_KEY_PUBLIC+"=1 OR "+
                                        DREAMS_KEY_PUBLIC+"=2 "+
                                    ")",
                        null
                );

                if (cursor.moveToFirst()) {
                    do{
                        int content_length = cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)).length();

                        ret.put(DREAMS_KEY_ID,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_ID)));
                        ret.put(DREAMS_KEY_LOCAL_ID,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_LOCAL_ID)));
                        ret.put(DREAMS_KEY_USER,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_USER)));
                        ret.put(DREAMS_KEY_VIEW_COUNT,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_VIEW_COUNT)));
                        ret.put(DREAMS_KEY_ACTION,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_ACTION)));
                        ret.put(DREAMS_KEY_CREATE_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CREATE_DATE)));
                        ret.put(DREAMS_KEY_EDIT_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_EDIT_DATE)));
                        ret.put(DREAMS_KEY_CATEGORY,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CATEGORY)));
                        ret.put(DREAMS_KEY_MOOD,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_MOOD)));
                        ret.put(DREAMS_KEY_DATE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_DATE)));
                        ret.put(DREAMS_KEY_TITLE,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_TITLE)));
                        ret.put(DREAMS_KEY_KEYWORDS,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_KEYWORDS)));
                        ret.put(DREAMS_KEY_DESCRIPTION,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)).substring(0,(content_length<160?content_length:160)));
                        ret.put(DREAMS_KEY_CONTENT,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_CONTENT)));
                        ret.put(DREAMS_KEY_COVER,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_COVER)));
                        ret.put(DREAMS_KEY_MAP_DATA,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_MAP_DATA)));
                        ret.put(DREAMS_KEY_USE_MAP,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_USE_MAP)));
                        ret.put(DREAMS_KEY_PUBLIC,cursor.getString(cursor.getColumnIndex(DREAMS_KEY_PUBLIC)));
                    }

                    while(cursor.moveToNext());

                    cursor.close();
                }
            }

        }


        return ret;
    }

    // Удалить сон
    public void deleteDream(int id, String type){
        if(id>0) {
            String id_type = type.equals("server")? DREAMS_KEY_ID: DREAMS_KEY_LOCAL_ID;

            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_DREAMS, id_type + "=" + id, null);
        }
    }

    // Сохранить сновидение
    public boolean saveDream(JSONObject v){
        boolean ret = false;
        AuthData authData = new AuthData(context);



        try {
            // Категория
            if(v.has("category")){
                if(v.getJSONObject("category").has("id"))
                    {v.put("category",v.getJSONObject("category").getInt("id"));}
            }

            // Настроение
            if(v.has("mood")){
                if(v.getJSONObject("mood").has("id"))
                    {v.put("mood",v.getJSONObject("mood").getInt("id"));}
            }


            int curr_date = Math.round(System.currentTimeMillis() / 1000) + authData.getCorrectTime();
            final HashMap<String, String> keys;{
                keys = new HashMap<>();
                keys.put("id",DREAMS_KEY_LOCAL_ID);
                keys.put("server_id",DREAMS_KEY_ID);
                keys.put("act",DREAMS_KEY_ACTION);
                keys.put("public",DREAMS_KEY_PUBLIC);
                keys.put("view_count",DREAMS_KEY_VIEW_COUNT);
                keys.put("title",DREAMS_KEY_TITLE);
                keys.put("keywords",DREAMS_KEY_KEYWORDS);
                keys.put("content",DREAMS_KEY_CONTENT);
                keys.put("category",DREAMS_KEY_CATEGORY);
                keys.put("mood",DREAMS_KEY_MOOD);
                keys.put("date",DREAMS_KEY_DATE);
                keys.put("create",DREAMS_KEY_CREATE_DATE);
                keys.put("edit_date",DREAMS_KEY_EDIT_DATE);
                keys.put("user",DREAMS_KEY_USER);
                keys.put("map_data",DREAMS_KEY_MAP_DATA);
                keys.put("use_map",DREAMS_KEY_USE_MAP);
                keys.put("cover",DREAMS_KEY_COVER);
            }
            final HashMap<String, String> default_values;{
                default_values = new HashMap<>();
                default_values.put(DREAMS_KEY_LOCAL_ID,"0");
                default_values.put(DREAMS_KEY_ID,"0");
                default_values.put(DREAMS_KEY_ACTION,"");
                default_values.put(DREAMS_KEY_PUBLIC,"0");
                default_values.put(DREAMS_KEY_VIEW_COUNT,"0");
                default_values.put(DREAMS_KEY_TITLE,"Новый сон");
                default_values.put(DREAMS_KEY_KEYWORDS,"");
                default_values.put(DREAMS_KEY_CONTENT,"Описание сна");
                default_values.put(DREAMS_KEY_CATEGORY,"1");
                default_values.put(DREAMS_KEY_MOOD,"7");
                default_values.put(DREAMS_KEY_DATE,String.valueOf(curr_date));
                default_values.put(DREAMS_KEY_CREATE_DATE,String.valueOf(curr_date));
                default_values.put(DREAMS_KEY_EDIT_DATE,String.valueOf(curr_date));
                default_values.put(DREAMS_KEY_USER,authData.getUser());
                default_values.put(DREAMS_KEY_MAP_DATA,"");
                default_values.put(DREAMS_KEY_USE_MAP,"0");
                default_values.put(DREAMS_KEY_COVER,"");
            }

            ContentValues c_values = new ContentValues();
            c_values.put(DREAMS_KEY_LOCAL_ID,0);
            c_values.put(DREAMS_KEY_ID,0);
            for( Map.Entry<String, String> entry : keys.entrySet() ){
                String key = entry.getKey();
                String value = entry.getValue();

                if(v.has(key)){
                    if(v.get(key) != null)
                        {c_values.put(value, v.getString(key));}
                }
            }

            int count = 0;
            boolean insert = true;



            // Поиск по локальному ID
            if(c_values.getAsInteger(DREAMS_KEY_LOCAL_ID) > 0){

                SQLiteDatabase db = getReadableDatabase();
                Cursor cursor = db.rawQuery(
                        "SELECT * FROM " + TABLE_DREAMS + " WHERE " + DREAMS_KEY_LOCAL_ID + " = " + c_values.getAsString(DREAMS_KEY_LOCAL_ID),
                        null
                );
                count = cursor.getCount();


                if (count > 0) {
                    if (cursor.moveToFirst()) {

                        do {
                            db = getWritableDatabase();
                            c_values.remove(DREAMS_KEY_LOCAL_ID);

                            db.update(
                                    TABLE_DREAMS,
                                    c_values,
                                    DREAMS_KEY_LOCAL_ID + "= ?",
                                    new String[]{v.getString(DREAMS_KEY_LOCAL_ID)}
                            );

                            ret = true;
                        }

                        while (cursor.moveToNext());
                    }

                    insert = false;
                }

                cursor.close();
                db.close();


            }


            // Поиск по серверному ID
            if(insert == true & c_values.getAsInteger(DREAMS_KEY_ID) > 0) {
                SQLiteDatabase db = getReadableDatabase();
                Cursor cursor = db.rawQuery(
                        "SELECT * FROM " + TABLE_DREAMS + " WHERE " + DREAMS_KEY_ID + " = " + c_values.getAsString(DREAMS_KEY_ID),
                        null
                );
                count = cursor.getCount();


                // Запись найдена
                if (count > 0) {
                    if (cursor.moveToFirst()) {
                        int edit_date_Index = cursor.getColumnIndex(DREAMS_KEY_EDIT_DATE);

                        do {
                            int edit_date = cursor.getInt(edit_date_Index);

                            if (c_values.getAsInteger(DREAMS_KEY_EDIT_DATE) > edit_date) {
                                db = getWritableDatabase();
                                c_values.remove(DREAMS_KEY_ID);
                                c_values.remove(DREAMS_KEY_LOCAL_ID);

                                db.update(
                                        TABLE_DREAMS,
                                        c_values,
                                        DREAMS_KEY_ID + "= ?",
                                        new String[]{v.getString(DREAMS_KEY_ID)}
                                );

                                ret = true;
                            }
                        }

                        while (cursor.moveToNext());
                    }

                    insert = false;
                }

                cursor.close();
                db.close();
            }


            // Новая запись
            if(insert == true){
                SQLiteDatabase db = getWritableDatabase();


                for( Map.Entry<String, String> entry : keys.entrySet() ){
                    String value = entry.getValue();
                    if(!value.equals(DREAMS_KEY_LOCAL_ID)){
                        if(c_values.get(value) == null)
                            {c_values.put(value,default_values.get(value));}
                    }
                }
                c_values.remove(DREAMS_KEY_LOCAL_ID);


                db.insertOrThrow(
                        TABLE_DREAMS,
                        null,
                        c_values
                );

                ret = true;
                db.close();
            }
        }

        catch(JSONException e)
            {ret = false;}

        catch(SQLException e)
            {ret = false;}



        return ret;
    }

    // Сохранить новый сон
    public long saveEditDream(JSONObject v){
        long ret = 0;



        try {
            ContentValues c_values = new ContentValues();
            c_values.put(DREAMS_KEY_ACTION,         "" );
            c_values.put(DREAMS_KEY_PUBLIC,         v.getInt("public") );
            c_values.put(DREAMS_KEY_VIEW_COUNT,     v.getInt("view_count") );
            c_values.put(DREAMS_KEY_TITLE,          v.getString("title") );
            c_values.put(DREAMS_KEY_KEYWORDS,       v.getString("keywords") );
            c_values.put(DREAMS_KEY_CONTENT,        v.getString("content") );
            c_values.put(DREAMS_KEY_CATEGORY,       v.getJSONObject("category").getInt("id") );
            c_values.put(DREAMS_KEY_MOOD,           v.getJSONObject("mood").getInt("id") );
            c_values.put(DREAMS_KEY_DATE,           v.getInt("date") );
            c_values.put(DREAMS_KEY_CREATE_DATE,    v.getInt("create") );
            c_values.put(DREAMS_KEY_EDIT_DATE,      v.getInt("edit_date") );
            c_values.put(DREAMS_KEY_USER,           v.getInt("user") );
            c_values.put(DREAMS_KEY_MAP_DATA,       v.getString("map_data") );
            c_values.put(DREAMS_KEY_USE_MAP,        v.getInt("use_map") );
            c_values.put(DREAMS_KEY_COVER,          v.getString("cover") );


            SQLiteDatabase db = getWritableDatabase();

            ret = db.insert(
                    TABLE_DREAMS,
                    null,
                    c_values
            );

            db.close();
        }

        catch(JSONException e)
            {ret = 0;}



        return ret;
    }





    // Сохранить сновидение
    public boolean saveBlogNote(JSONObject v){
        boolean ret = false;
        AuthData authData = new AuthData(context);



        try {

            int curr_date = Math.round(System.currentTimeMillis() / 1000) + authData.getCorrectTime();
            final HashMap<String, String> keys;{
                keys = new HashMap<>();
                keys.put("id",BLOG_KEY_ID);
                keys.put("catalog",BLOG_KEY_CATALOG);
                keys.put("url_title",BLOG_KEY_URL_TITLE);
                keys.put("create_date",BLOG_KEY_CREATE_DATE);
                keys.put("edit_date",BLOG_KEY_EDIT_DATE);
                keys.put("title",BLOG_KEY_TITLE);
                keys.put("keywords",BLOG_KEY_KEYWORDS);
                keys.put("description",BLOG_KEY_DESCRIPTION);
                keys.put("content",BLOG_KEY_CONTENT);
                keys.put("cover",BLOG_KEY_COVER);
                keys.put("autor",BLOG_KEY_AUTHOR);
            }
            final HashMap<String, String> default_values;{
                default_values = new HashMap<>();
                default_values.put(BLOG_KEY_ID,"0");
                default_values.put(BLOG_KEY_CATALOG,"0");
                default_values.put(BLOG_KEY_URL_TITLE,"");
                default_values.put(BLOG_KEY_CREATE_DATE,String.valueOf(curr_date));
                default_values.put(BLOG_KEY_EDIT_DATE,String.valueOf(curr_date));
                default_values.put(BLOG_KEY_TITLE,"");
                default_values.put(BLOG_KEY_KEYWORDS,"");
                default_values.put(BLOG_KEY_DESCRIPTION,"");
                default_values.put(BLOG_KEY_CONTENT,"");
                default_values.put(BLOG_KEY_COVER,"");
                default_values.put(BLOG_KEY_AUTHOR,"1");
            }

            ContentValues c_values = new ContentValues();
            c_values.put(BLOG_KEY_ID,0);
            for( Map.Entry<String, String> entry : keys.entrySet() ){
                String key = entry.getKey();
                String value = entry.getValue();

                if(v.has(key)){
                    if(v.get(key) != null)
                        {c_values.put(value, v.getString(key));}
                }
            }

            int count = 0;



            BlogNotes blogNotes = new BlogNotes(context);
            blogNotes.setFilterID(c_values.getAsInteger(BLOG_KEY_ID));
            HashMap<Integer, HashMap<String, String>> test_note = blogNotes.getNotes(1);



            // Обновить статью
            if(Integer.parseInt(test_note.get(0).get("count")) > 0){

                SQLiteDatabase db = getReadableDatabase();
                Cursor cursor = db.rawQuery(
                    "SELECT * FROM " + TABLE_BLOG + " WHERE " + BLOG_KEY_ID + " = " + c_values.getAsString(BLOG_KEY_ID),
                    null
                );
                count = cursor.getCount();


                if (count > 0) {
                    if (cursor.moveToFirst()) {

                        do {
                            db = getWritableDatabase();
                            c_values.remove(BLOG_KEY_ID);

                            db.update(
                                    TABLE_BLOG,
                                    c_values,
                                    BLOG_KEY_ID + "= ?",
                                    new String[]{v.getString(BLOG_KEY_ID)}
                            );

                            ret = true;
                        }

                        while (cursor.moveToNext());
                    }
                }

                cursor.close();
                db.close();


            }

            // Новая запись
            else{
                SQLiteDatabase db = getWritableDatabase();


                for( Map.Entry<String, String> entry : keys.entrySet() ){
                    String value = entry.getValue();
                    if(c_values.get(value) == null)
                        {c_values.put(value,default_values.get(value));}
                }


                db.insertOrThrow(
                        TABLE_BLOG,
                        null,
                        c_values
                );

                ret = true;
                db.close();
            }

        }

        catch(JSONException e)
            {ret = false;}

        catch(SQLException e)
            {ret = false;}



        return ret;
    }

    // Сохранить рубрику
    public boolean saveBlogCatalog(JSONObject v){
        boolean ret = false;
        AuthData authData = new AuthData(context);



        try {

            int curr_date = Math.round(System.currentTimeMillis() / 1000) + authData.getCorrectTime();
            final HashMap<String, String> keys;{
                keys = new HashMap<>();
                keys.put("id",BLOG_CAT_KEY_ID);
                keys.put("edit_date",BLOG_CAT_KEY_EDIT_DATE);
                keys.put("sort_index",BLOG_CAT_KEY_SORT_INDEX);
                keys.put("url_title",BLOG_CAT_KEY_URL_TITLE);
                keys.put("name",BLOG_CAT_KEY_NAME);
                keys.put("description",BLOG_CAT_KEY_DESCRIPTION);
                keys.put("cover",BLOG_CAT_KEY_COVER);
            }
            final HashMap<String, String> default_values;{
                default_values = new HashMap<>();
                default_values.put(BLOG_CAT_KEY_ID,"0");
                default_values.put(BLOG_CAT_KEY_EDIT_DATE,String.valueOf(curr_date));
                default_values.put(BLOG_CAT_KEY_SORT_INDEX,"0");
                default_values.put(BLOG_CAT_KEY_URL_TITLE,"");
                default_values.put(BLOG_CAT_KEY_NAME,"");
                default_values.put(BLOG_CAT_KEY_DESCRIPTION,"");
                default_values.put(BLOG_CAT_KEY_COVER,"");
            }

            ContentValues c_values = new ContentValues();
            c_values.put(BLOG_CAT_KEY_ID,0);
            for( Map.Entry<String, String> entry : keys.entrySet() ){
                String key = entry.getKey();
                String value = entry.getValue();

                if(v.has(key)){
                    if(v.get(key) != null)
                    {c_values.put(value, v.getString(key));}
                }
            }

            int count = 0;



            BlogCatalogs blogCatalog = new BlogCatalogs(context);
            blogCatalog.setFilterID(c_values.getAsInteger(BLOG_CAT_KEY_ID));
            HashMap<Integer, HashMap<String, String>> test_catalog = blogCatalog.getCatalogs(1);



            // Обновить статью
            if(Integer.parseInt(test_catalog.get(0).get("count")) > 0){

                SQLiteDatabase db = getReadableDatabase();
                Cursor cursor = db.rawQuery(
                        "SELECT * FROM " + TABLE_BLOG_CAT + " WHERE " + BLOG_CAT_KEY_ID + " = " + c_values.getAsString(BLOG_CAT_KEY_ID),
                        null
                );
                count = cursor.getCount();


                if (count > 0) {
                    if (cursor.moveToFirst()) {

                        do {
                            db = getWritableDatabase();
                            c_values.remove(BLOG_CAT_KEY_ID);

                            db.update(
                                    TABLE_BLOG_CAT,
                                    c_values,
                                    BLOG_CAT_KEY_ID + "= ?",
                                    new String[]{v.getString(BLOG_CAT_KEY_ID)}
                            );

                            ret = true;
                        }

                        while (cursor.moveToNext());
                    }
                }

                cursor.close();
                db.close();


            }

            // Новая запись
            else{
                SQLiteDatabase db = getWritableDatabase();


                for( Map.Entry<String, String> entry : keys.entrySet() ){
                    String value = entry.getValue();
                    if(c_values.get(value) == null)
                    {c_values.put(value,default_values.get(value));}
                }


                db.insertOrThrow(
                        TABLE_BLOG_CAT,
                        null,
                        c_values
                );

                ret = true;
                db.close();
            }

        }

        catch(JSONException e)
            {ret = false;}

        catch(SQLException e)
            {ret = false;}



        return ret;
    }

    // Удалить статью
    public void deleteBlogNote(int id){
        if(id>0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_BLOG, BLOG_KEY_ID + "=" + id, null);
        }
    }





    // Сохранить пользователя
    public boolean saveUser(JSONObject v){
        boolean ret = false;


        try {
            ContentValues c_values = new ContentValues();
            c_values.put(USER_KEY_ID, v.getInt("id"));
            c_values.put(USER_KEY_NAME, v.getString("name"));
            c_values.put(USER_KEY_LASTNAME, v.getString("lastname"));
            c_values.put(USER_KEY_FATHER, v.getString("father"));
            c_values.put(USER_KEY_BDAY, v.getInt("bday"));
            c_values.put(USER_KEY_BMON, v.getInt("bmon"));
            c_values.put(USER_KEY_BYEAR, v.getInt("byear"));
            c_values.put(USER_KEY_AVA_LINK, v.getString("ava_link"));
            c_values.put(USER_KEY_SMALL_AVA_LINK, v.getString("ava_small_link"));
            c_values.put(USER_KEY_LAST_VISIT, v.getInt("last_visit"));

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(
                "SELECT * FROM "+TABLE_USERS+" WHERE "+USER_KEY_ID+" = "+c_values.getAsString(USER_KEY_ID),
                null
            );
            int count = cursor.getCount();



            // Запись найдена
            if (count > 0) {
                if (cursor.moveToFirst()) {

                    do {
                        db = getWritableDatabase();
                        c_values.remove(USER_KEY_ID);

                        db.update(
                            TABLE_USERS,
                            c_values,
                            USER_KEY_ID + "= ?",
                            new String[]{v.getString("id")}
                        );

                        ret = true;
                    }

                    while (cursor.moveToNext());
                }
            }

            // Еще нет записи
            else {
                db = getWritableDatabase();

                db.insert(
                    TABLE_USERS,
                    null,
                    c_values
                );

                ret = true;
            }



            cursor.close();
            db.close();
        }

        catch(JSONException e)
            {ret = false;}



        return ret;
    }

    // Определенный пользователь
    public HashMap<String,String> getUser(int id){
        HashMap<String,String> ret = new HashMap<>();
        ret.put("count","0");
        int count = 0;


        if(id>0){
            SQLiteDatabase db = this.getReadableDatabase();

            // Запрос для подсчета данных
            Cursor count_cur = db.rawQuery(
                "SELECT (COUNT("+USER_KEY_ID+")) AS count "+
                        "FROM "+TABLE_USERS+" "+
                        "WHERE "+
                            USER_KEY_ID+" = "+id,
                null
            );
            if (count_cur.moveToFirst()) {
                while(count_cur.isAfterLast() == false) {
                    count = count_cur.getInt(count_cur.getColumnIndex("count"));
                    count_cur.moveToNext();
                }
            }



            if(count>0){
                ret.put("count","1");


                // Запрос для получения данных
                Cursor cursor = db.rawQuery(
                        "SELECT * "+
                                "FROM "+TABLE_USERS+" "+
                                "WHERE "+
                                    USER_KEY_ID+" = "+id,
                        null
                );

                if (cursor.moveToFirst()) {
                    do{
                        String ava_default = "https://dreams.online-we.ru/images/system/question.png";

                        String ava = cursor.getString(cursor.getColumnIndex(USER_KEY_AVA_LINK));
                        ava = ava == null? ava_default: (ava.length()<=0? ava_default: ava);
                        String ava_small = cursor.getString(cursor.getColumnIndex(USER_KEY_SMALL_AVA_LINK));
                        ava_small = ava_small == null? ava_default: (ava_small.length()<=0? ava_default: ava_small);


                        ret.put(USER_KEY_ID,cursor.getString(cursor.getColumnIndex(USER_KEY_ID)));
                        ret.put(USER_KEY_NAME,cursor.getString(cursor.getColumnIndex(USER_KEY_NAME)));
                        ret.put(USER_KEY_LASTNAME,cursor.getString(cursor.getColumnIndex(USER_KEY_LASTNAME)));
                        ret.put(USER_KEY_FATHER,cursor.getString(cursor.getColumnIndex(USER_KEY_FATHER)));
                        ret.put(USER_KEY_BDAY,cursor.getString(cursor.getColumnIndex(USER_KEY_BDAY)));
                        ret.put(USER_KEY_BMON,cursor.getString(cursor.getColumnIndex(USER_KEY_BMON)));
                        ret.put(USER_KEY_BYEAR,cursor.getString(cursor.getColumnIndex(USER_KEY_BYEAR)));
                        ret.put(USER_KEY_AVA_LINK,ava);
                        ret.put(USER_KEY_SMALL_AVA_LINK,ava_small);
                        ret.put(USER_KEY_LAST_VISIT,cursor.getString(cursor.getColumnIndex(USER_KEY_LAST_VISIT)));
                    }

                    while(cursor.moveToNext());

                    cursor.close();
                }
            }

        }


        return ret;
    }

}
