package com.online.dreams_map.DreamsData;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.online.dreams_map.SysLibs.ODM_SqlLite;

import java.util.HashMap;





public class BlogNotes {

    private Activity activity;
    private Context context;

    private ODM_SqlLite sqlLite;

    public final String field_id = "id";
    public final String field_catalog = "catalog";
    public final String field_url_title = "url_title";
    public final String field_create_date = "create_date";
    public final String field_edit_date = "edit_date";
    public final String field_title = "title";
    public final String field_keywords = "keywords";
    public final String field_description = "description";
    public final String field_content = "content";
    public final String field_cover = "cover";
    public final String field_author = "autor";

    private int note_id = 0;
    private String search = "";
    private String url_title = "";
    private int catalog = 0;
    private int limit = 0;





    public BlogNotes(Context context){
        this.activity = (Activity) context;
        this.context = context;

        this.sqlLite = new ODM_SqlLite(this.activity);
    }





    // Установить фильтр по серверному ID сновидения
    public void setFilterID( int note_id ){
        note_id = note_id<0? 0: note_id;

        this.note_id = note_id;
    }

    // Установить фильтр по URL названию
    public void setFilterURLTitle( String url_title ){
        url_title = url_title == null? "": url_title;

        this.url_title = url_title;
    }

    // Установить фильтр поиска
    public void setFilterSearch( String search ){
        search = search == null? "": search;

        this.search = search;
    }

    // Установить фильтр категории
    public void setFilterCatalog( int catalog ){
        Log.e("test_filter",catalog+"");
        catalog = catalog<0? 0: catalog;
        Log.e("test_filter",catalog+"");

        this.catalog = catalog;
    }

    // Установить ограничение на количество поисковой выдачи
    public void setFilterLimit( int limit ){
        limit = limit<0? 10: limit;
        limit = limit>10? 10: limit;

        this.limit = limit;
    }





    // Получить фильтр по юзеру
    public int getFilterID(){
        return note_id;
    }

    // Получить фильтр по URL названию
    public String getFilterURLTitle(){
        return url_title;
    }

    // Получить фильтр по поисковому запросу
    public String getFilterSearch(){
        return search;
    }

    // Получить фильтр по категории
    public int getFilterCatalog(){
        return catalog;
    }

    // Получить лимит на поиск
    public int getFilterLimit(){
        return limit;
    }





    public HashMap<Integer, HashMap<String, String>> getNotes(int page){

        HashMap<Integer, HashMap<String, String>> ret = new HashMap<>();
        SQLiteDatabase db = sqlLite.getReadableDatabase();

        int count = 0;
        int all_page = 0;



        // Фильтр по серверному ID
        String search_id = "";
        if(note_id > 0){
            search_id = " AND "+field_id+" = "+note_id+" ";
        }

        // Фильтр по URL названию
        String search_url_title = "";
        if(url_title.length() > 0){
            search_url_title = " AND "+field_url_title+" = '"+url_title+"' ";
        }

        // Поиск по словам
        String search_text = "";
        if(search.length() > 0){
            String[] titles = new String[]{field_title, field_keywords, field_description};

            search_text += " AND ( ";
            for( int k=0; k<3; k++ ){
                String title = titles[k];

                search_text +=
                        " "+title+" = '"+search+"' OR "+
                                " "+title+" LIKE '%"+search+"' OR "+
                                " "+title+" LIKE '"+search+"%' OR "+
                                " "+title+" LIKE '%"+search+"%' "+
                                (k+1 < 3? " OR ": "")
                ;
            }
            search_text += " ) ";
        }

        // Фильтр по категории
        String search_catalog = "";
        if(catalog > 0){
            search_catalog =
                " AND ("+
                    field_catalog+" = '"+catalog+"' OR "+
                    field_catalog+" LIKE '%,"+catalog+"' OR "+
                    field_catalog+" LIKE '"+catalog+",%' OR "+
                    field_catalog+" LIKE '%,"+catalog+",%' "+
                ") "
            ;
        }



        // Запрос для подсчета данных
        Cursor count_cur = db.rawQuery(
            "SELECT (COUNT("+field_id+")) AS count "+
                "FROM "+sqlLite.TABLE_BLOG+" "+
                "WHERE "+
                    " "+field_id+">0 "+
                    search_id+
                    search_url_title+
                    search_text+
                    search_catalog+
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
                    "FROM "+sqlLite.TABLE_BLOG+" "+
                    "WHERE "+
                        " "+field_id+">0 "+
                        search_id+
                        search_url_title+
                        search_text+
                        search_catalog+
                    "ORDER BY "+field_create_date+" DESC, "+field_id+" DESC "+
                    (limit>0? "LIMIT "+((page - 1) * limit)+", "+limit+" ": "")+
                    "",
                    null
            );
            ret.get(0).put("count_elms",String.valueOf(cursor.getCount()));

            if (cursor.moveToFirst()) {
                int i=1;

                do{
                    ret.put(i,new HashMap());

                    ret.get(i).put(field_id,cursor.getString(cursor.getColumnIndex(field_id)));
                    ret.get(i).put(field_catalog,cursor.getString(cursor.getColumnIndex(field_catalog)));
                    ret.get(i).put(field_url_title,cursor.getString(cursor.getColumnIndex(field_url_title)));
                    ret.get(i).put(field_create_date,cursor.getString(cursor.getColumnIndex(field_create_date)));
                    ret.get(i).put(field_edit_date,cursor.getString(cursor.getColumnIndex(field_edit_date)));
                    ret.get(i).put(field_title,cursor.getString(cursor.getColumnIndex(field_title)));
                    ret.get(i).put(field_keywords,cursor.getString(cursor.getColumnIndex(field_keywords)));
                    ret.get(i).put(field_description,cursor.getString(cursor.getColumnIndex(field_description)));
                    ret.get(i).put(field_content,cursor.getString(cursor.getColumnIndex(field_content)));
                    ret.get(i).put(field_cover,cursor.getString(cursor.getColumnIndex(field_cover)));
                    ret.get(i).put(field_author,cursor.getString(cursor.getColumnIndex(field_author)));

                    i++;
                }

                while(cursor.moveToNext());
            }

            cursor.close();
        }





        return ret;
    }


}
