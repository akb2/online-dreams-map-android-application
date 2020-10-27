package com.online.dreams_map.DreamsData;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.online.dreams_map.SysLibs.ODM_SqlLite;

import java.util.HashMap;





public class BlogCatalogs {

    private Activity activity;
    private Context context;
    private ODM_SqlLite sqlLite;

    public final String field_id = "id";
    public final String field_edit_date = "edit_date";
    public final String field_sort_index = "sort_index";
    public final String field_url_title = "url_title";
    public final String field_name = "name";
    public final String field_description = "description";
    public final String field_cover = "cover";

    private int catalog_id = 0;
    private String url_title = "";
    private int limit = 0;





    public BlogCatalogs(Context context){
        this.activity = (Activity) context;
        this.context = context;

        this.sqlLite = new ODM_SqlLite(context);
    }





    // Установить фильтр по серверному ID сновидения
    public void setFilterID( int catalog_id ){
        catalog_id = catalog_id<0? 0: catalog_id;

        this.catalog_id = catalog_id;
    }

    // Установить фильтр по URL названию
    public void setFilterURLTitle( String url_title ){
        url_title = url_title == null? "": url_title;

        this.url_title = url_title;
    }

    // Установить ограничение на количество поисковой выдачи
    public void setFilterLimit( int limit ){
        limit = limit<0? 10: limit;
        limit = limit>10? 10: limit;

        this.limit = limit;
    }





    // Получить фильтр по юзеру
    public int getFilterID(){
        return catalog_id;
    }

    // Получить фильтр по URL названию
    public String getFilterURLTitle(){
        return url_title;
    }

    // Получить лимит на поиск
    public int getFilterLimit(){
        return limit;
    }





    public HashMap<Integer, HashMap<String, String>> getCatalogs(int page){

        HashMap<Integer, HashMap<String, String>> ret = new HashMap<>();
        SQLiteDatabase db = sqlLite.getReadableDatabase();

        int count = 0;
        int all_page = 0;



        // Фильтр по серверному ID
        String search_id = "";
        if(catalog_id > 0){
            search_id = " AND "+field_id+" = "+catalog_id+" ";
        }

        // Фильтр по URL названию
        String search_url_title = "";
        if(url_title.length() > 0){
            search_url_title = " AND "+field_url_title+" = '"+url_title+"' ";
        }



        // Запрос для подсчета данных
        Cursor count_cur = db.rawQuery(
        "SELECT (COUNT("+field_id+")) AS count "+
            "FROM "+sqlLite.TABLE_BLOG_CAT+" "+
            "WHERE "+
                " "+field_id+">0 "+
                search_id+
                search_url_title+
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
                    "FROM "+sqlLite.TABLE_BLOG_CAT+" "+
                    "WHERE "+
                        " "+field_id+">0 "+
                        search_id+
                        search_url_title+
                    "ORDER BY "+field_sort_index+" DESC, "+field_id+" DESC "+
                    ( limit>0? "LIMIT "+((page - 1) * limit)+", "+limit+" ": "" )+
                        "",
                null
            );
            ret.get(0).put("count_elms",String.valueOf(cursor.getCount()));

            if (cursor.moveToFirst()) {
                int i=1;

                do{
                    ret.put(i,new HashMap());

                    ret.get(i).put(field_id,cursor.getString(cursor.getColumnIndex(field_id)));
                    ret.get(i).put(field_sort_index,cursor.getString(cursor.getColumnIndex(field_sort_index)));
                    ret.get(i).put(field_url_title,cursor.getString(cursor.getColumnIndex(field_url_title)));
                    ret.get(i).put(field_edit_date,cursor.getString(cursor.getColumnIndex(field_edit_date)));
                    ret.get(i).put(field_name,cursor.getString(cursor.getColumnIndex(field_name)));
                    ret.get(i).put(field_description,cursor.getString(cursor.getColumnIndex(field_description)));
                    ret.get(i).put(field_cover,cursor.getString(cursor.getColumnIndex(field_cover)));

                    i++;
                }

                while(cursor.moveToNext());
            }

            cursor.close();
        }





        return ret;
    }
}
