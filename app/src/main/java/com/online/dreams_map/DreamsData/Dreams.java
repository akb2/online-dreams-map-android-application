package com.online.dreams_map.DreamsData;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.online.dreams_map.SysLibs.ODM_SqlLite;

import java.util.HashMap;





public class Dreams {

    private Context context;
    private Activity activity;
    private ODM_SqlLite dreams_db;

    private int dream_id = 0;
    private int dream_local_id = 0;
    private int user = 0;
    private String search = "";
    private int category = 0;
    private int mood = 0;
    private int _public = -1;
    private int limit = 10;
    private String action = "view";

    public String field_id;
    public String field_server_id;
    public String field_act;
    public String field_public;
    public String field_count;
    public String field_title;
    public String field_keywords;
    public String field_content;
    public String field_description;
    public String field_category;
    public String field_mood;
    public String field_date;
    public String field_create_date;
    public String field_edit_date;
    public String field_user;
    public String field_map_data;
    public String field_use_map;
    public String field_cover;





    public Dreams(Context context){
        this.context = context;
        this.activity = (Activity) context;

        this.dreams_db = new ODM_SqlLite(context);

        this.field_id = this.dreams_db.DREAMS_KEY_LOCAL_ID;
        this.field_server_id = this.dreams_db.DREAMS_KEY_ID;
        this.field_act = this.dreams_db.DREAMS_KEY_ACTION;
        this.field_public = this.dreams_db.DREAMS_KEY_PUBLIC;
        this.field_count = this.dreams_db.DREAMS_KEY_VIEW_COUNT;
        this.field_title = this.dreams_db.DREAMS_KEY_TITLE;
        this.field_keywords = this.dreams_db.DREAMS_KEY_KEYWORDS;
        this.field_content = this.dreams_db.DREAMS_KEY_CONTENT;
        this.field_description = this.dreams_db.DREAMS_KEY_DESCRIPTION;
        this.field_category = this.dreams_db.DREAMS_KEY_CATEGORY;
        this.field_mood = this.dreams_db.DREAMS_KEY_MOOD;
        this.field_date = this.dreams_db.DREAMS_KEY_DATE;
        this.field_create_date = this.dreams_db.DREAMS_KEY_CREATE_DATE;
        this.field_edit_date = this.dreams_db.DREAMS_KEY_EDIT_DATE;
        this.field_user = this.dreams_db.DREAMS_KEY_USER;
        this.field_map_data = this.dreams_db.DREAMS_KEY_MAP_DATA;
        this.field_use_map = this.dreams_db.DREAMS_KEY_USE_MAP;
        this.field_cover = this.dreams_db.DREAMS_KEY_COVER;
    }





    // Установить фильтр по серверному ID сновидения
    public void setFilterID( int dream_local_id ){
        dream_local_id = dream_local_id<0? 0: dream_local_id;

        this.dream_local_id = dream_local_id;
    }

    // Установить фильтр по серверному ID сновидения
    public void setFilterServerID( int dream_id ){
        dream_id = dream_id<0? 0: dream_id;

        this.dream_id = dream_id;
    }

    // Установить фильтр пользователя
    public void setFilterUser( int user ){
        user = user<0? 0: user;

        this.user = user;
    }

    // Установить фильтр поиска
    public void setFilterSearch( String search ){
        search = search == null? "": search;

        this.search = search;
    }

    // Установить фильтр категории
    public void setFilterCategory( int category ){
        category = category<0? 0: category;
        category = category>6? 0: category;

        this.category = category;
    }

    // Установить фильтр настроения
    public void setFilterMood( int mood ){
        mood = mood<7? 0: mood;
        mood = mood>12? 0: mood;

        this.mood = mood;
    }

    // Установить фильтр статуса публикации
    public void setFilterPublic( int _public ){
        _public = _public<-1? -1: _public;
        _public = _public>2? -1: _public;

        this._public = _public;
    }

    // Установить фильтр статуса публикации
    public void setFilterAction( String _action ){
        _action = _action.equals("view") || _action.equals("all")? _action: "view";

        this.action = _action;
    }

    // Установить ограничение на количество поисковой выдачи
    public void setFilterLimit( int limit ){
        limit = limit<1? 1: limit;
        limit = limit>10? 10: limit;

        this.limit = limit;
    }





    // Получить фильтр по юзеру
    public int getFilterID(){
        return dream_local_id;
    }

    // Получить фильтр по юзеру
    public int getFilterServerID(){
        return dream_id;
    }

    // Получить фильтр по юзеру
    public int getFilterUser(){
        return user;
    }

    // Получить фильтр по поисковому запросу
    public String getFilterSearch(){
        return search;
    }

    // Получить фильтр по категории
    public int getFilterCategory(){
        return category;
    }

    // Получить фильтр по настроению
    public int getFilterMood(){
        return mood;
    }

    // Получить фильтр по статусу публикации
    public int getFilterPublic(){
        return _public;
    }

    // Получить лимит на поиск
    public int getFilterLimit(){
        return limit;
    }





    // Список снов по параметрам поиска
    public HashMap<Integer, HashMap<String, String>> getDreams( int page ){

        HashMap<Integer, HashMap<String, String>> ret = new HashMap<>();
        SQLiteDatabase db = dreams_db.getReadableDatabase();

        int count = 0;
        int all_page = 0;



        // Фильтр по серверному ID
        String search_id = "";
        if(dream_id > 0){
            search_id = " AND "+field_server_id+" = "+dream_id+" ";
        }

        // Фильтр по локальному ID
        String search_local_id = "";
        if(dream_local_id > 0){
            search_local_id = " AND "+field_id+" = "+dream_local_id+" ";
        }

        // Фильтр по юзеру
        String search_user = "";
        if(user > 0){
            search_user = " AND "+field_user+" = "+user+" ";
        }

        // Поиск по словам
        String search_text = "";
        if(search.length() > 0){
            String[] titles = new String[]{field_title, field_keywords, field_content};
            String[] symbs = new String[]{" ",",",".","(",")","\"","\'","\n","\r"};

            search_text += " AND ( ";
            for( int k=0; k<3; k++ ){
                String title = titles[k];

                search_text +=
                    " "+title+" LIKE '"+search+"' OR "+
                    " "+title+" LIKE '%"+search+"' OR "+
                    " "+title+" LIKE '"+search+"%' OR "+
                    " "+title+" LIKE '%"+search+"%' "+
                    (k+1 < 3? " OR ": "")
                ;
            }
            search_text += " ) ";
        }

        // Фильтр по категории
        String search_category = "";
        if(category > 0){
            search_category = " AND "+field_category+" = "+category+" ";
        }

        // Фильтр по настроению
        String search_mood = "";
        if(mood > 0){
            search_mood = " AND "+field_mood+" = "+mood+" ";
        }

        // Фильтр по статусу публикации
        String search_public = "";
        if(_public >= 0){
            search_public = " AND "+field_public+" = "+_public+" ";
        }

        else if(user == 0){
            search_public =
                " AND ( "+
                    field_public+" = 1 OR "+
                    field_public+" = 2 "+
                " ) "
            ;
        }

        // По статусу действий
        String search_act = "";
        if(action.equals("view")){
            search_act = " AND "+field_act+" != 'delete' ";
        }



        // Запрос для подсчета данных
        Cursor count_cur = db.rawQuery(
        "SELECT (COUNT("+field_id+")) AS count "+
            "FROM "+dreams_db.TABLE_DREAMS+" "+
            "WHERE "+
                " "+field_id+">0 "+
                search_id+
                search_act+
                search_local_id+
                search_user+
                search_text+
                search_category+
                search_mood+
                search_public+
            "",
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

        count_cur.close();

        all_page = (int) Math.ceil((double) count / (double) limit);
        page = page<1? 1: page;
        page = page>all_page? all_page :page;



        // Вывод значений
        if(count>0) {

            // Запрос для получения данных
            Cursor cursor = db.rawQuery(
            "SELECT * "+
                "FROM "+dreams_db.TABLE_DREAMS+" "+
                "WHERE "+
                    " "+field_id+">0 "+
                    search_id+
                    search_act+
                    search_local_id+
                    search_user+
                    search_text+
                    search_category+
                    search_mood+
                    search_public+
                "ORDER BY "+field_date+" DESC, "+field_server_id+" DESC "+
                "LIMIT "+((page - 1) * limit)+", "+limit+" "+
                "",
            null
            );
            ret.get(0).put("count_elms",String.valueOf(cursor.getCount()));

            if (cursor.moveToFirst()) {
                int i=1;
                HashMap<Integer, String> res = new HashMap<>();

                do{
                    ret.put(i,new HashMap());
                    String description = cursor.getString(cursor.getColumnIndex(field_content));
                    int content_length = description.length();
                    description = description.substring(0,(content_length<160?content_length:160));

                    res.put(i, cursor.getString(cursor.getColumnIndex(field_title)));
                    ret.get(i).put(field_id,cursor.getString(cursor.getColumnIndex(field_id)));
                    ret.get(i).put(field_server_id,cursor.getString(cursor.getColumnIndex(field_server_id)));
                    ret.get(i).put(field_act,cursor.getString(cursor.getColumnIndex(field_act)));
                    ret.get(i).put(field_create_date,cursor.getString(cursor.getColumnIndex(field_create_date)));
                    ret.get(i).put(field_edit_date,cursor.getString(cursor.getColumnIndex(field_edit_date)));
                    ret.get(i).put(field_category,cursor.getString(cursor.getColumnIndex(field_category)));
                    ret.get(i).put(field_mood,cursor.getString(cursor.getColumnIndex(field_mood)));
                    ret.get(i).put(field_date,cursor.getString(cursor.getColumnIndex(field_date)));
                    ret.get(i).put(field_title,cursor.getString(cursor.getColumnIndex(field_title)));
                    ret.get(i).put(field_description,description);
                    ret.get(i).put(field_keywords,cursor.getString(cursor.getColumnIndex(field_keywords)));
                    ret.get(i).put(field_content,cursor.getString(cursor.getColumnIndex(field_content)));
                    ret.get(i).put(field_cover,cursor.getString(cursor.getColumnIndex(field_cover)));

                    i++;
                }

                while(cursor.moveToNext());
            }

            cursor.close();
        }



        db.close();

        return ret;
    }


}
