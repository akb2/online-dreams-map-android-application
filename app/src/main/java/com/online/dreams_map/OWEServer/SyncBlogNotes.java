package com.online.dreams_map.OWEServer;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.DreamsData.BlogNotes;
import com.online.dreams_map.SysLibs.ODM_SqlLite;
import com.online.dreams_map.SysLibs.OWEApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;





public class SyncBlogNotes {

    private Activity activity;
    private Context context;

    private AuthData authData;
    private ODM_SqlLite sqlLite;

    private long lastSync;
    private final long period = 120;
    private final long offline_period = 10;

    public int skip = 0;





    // Конструктор
    public SyncBlogNotes(Context context){
        this.activity = (Activity) context;
        this.context = context;

        this.authData = new AuthData(context);
        this.sqlLite = new ODM_SqlLite((Activity) context);
    }





    // Синхронизация
    public void sendServer( int skip, BlogNotes filter ){
        beforeUpdate();
        this.skip = skip;

        lastSync = System.currentTimeMillis() / 1000;


        if(authData.isOnline()){
            if(authData.getToken().length()>0){

                // Параметры передачи данных на сервер
                HashMap<String, String> params = new HashMap<>();
                params.put("token", authData.getToken());

                params.put("page", String.valueOf(skip));
                params.put("limit", String.valueOf(filter.getFilterLimit()));

                params.put("id", String.valueOf(filter.getFilterID()));
                params.put("url_title", String.valueOf(filter.getFilterURLTitle()));
                params.put("search", String.valueOf(filter.getFilterSearch()));
                params.put("catalog", String.valueOf(filter.getFilterCatalog()));

                // Список моих сновидений
                HashMap<Integer, HashMap<String,String>> notes = filter.getNotes((int) Math.ceil((skip / filter.getFilterLimit())+1));
                JSONArray notes_json = new JSONArray();
                for ( Integer k : notes.keySet() ) {
                    if(k>0){
                        try {
                            HashMap<String, String> v = notes.get(k);

                            JSONObject temp_json_obj = new JSONObject();
                            temp_json_obj.put("id", v.get("id"));
                            temp_json_obj.put("edit_date", v.get("edit_date"));

                            notes_json.put(k, temp_json_obj);
                        }

                        catch (JSONException e){}
                    }
                }
                params.put("dreams", notes_json.toString());

                // Запрос
                SendAPIPost sendPost = new SendAPIPost(context, "get_blog", params);



                try {sendPost.execute();}
                catch(Exception e) {errorUpdate();}
            }

            else
                {errorUpdate();}
        }

        else
            {errorUpdate();}
    }





    // Функция перед обновлением статьи
    public void beforeUpdate(){

    }


    // Функция перед обновлением статей
    public void progressUpdate(Integer... v){

    }


    // Функция после обновления статей
    public void afterUpdate(HashMap<Integer, Integer> updated_dreams, HashMap<Integer, Integer> deleted_dreams){

    }


    // Функция при ошибке синхронизации
    public void errorUpdate(){

    }





    // Запрос сновидений с сервера
    class SendAPIPost extends OWEApi {

        private Context context;
        private Activity activity;


        public SendAPIPost(Context context, String method, HashMap<String, String> params){
            super.OWEApi(context, method, params);

            this.context = context;
            this.activity = (Activity) context;
        }

        @Override
        public void errorSend(){
            errorUpdate();
        }

        @Override
        public void successGet(JSONObject json){
            HashMap<Integer, Integer> updated_notes = new HashMap<>();
            HashMap<Integer, Integer> deleted_notes = new HashMap<>();

            try {
                String code = json.getString("code");

                JSONObject notes = json.getJSONObject("blog");
                JSONObject catalogs = json.getJSONObject("catalogs");
                JSONArray delete = json.getJSONArray("delete");
                JSONArray delete_catalogs = json.getJSONArray("delete_catalogs");
                JSONArray users = json.getJSONArray("users");



                // Удалить Статью в приложении
                int len = delete.length();
                HashMap<Integer, Boolean> deleted = new HashMap<>();
                if(len > 0){
                    for( int k=0; k<len; k++ ){
                        int id = delete.getInt(k);
                        deleted_notes.put(id,id);
                        sqlLite.deleteBlogNote(id);
                    }
                }



                // Обход по списку пользователей
                if(users.length() > 0){
                    for(int k=0; k<users.length(); k++){
                        JSONObject v = users.getJSONObject(k);
                        sqlLite.saveUser(v);
                    }
                }



                // Обход по списку статей
                JSONArray resultat = notes.getJSONArray("resultat");
                int length=resultat.length();
                if(length>0){
                    for( int i=0; i<length; i++ ){
                        JSONObject v = resultat.getJSONObject(i);

                        sqlLite.saveBlogNote(v);
                        updated_notes.put(v.getInt("id"), v.getInt("id"));
                    }

                    afterUpdate(updated_notes, deleted_notes);
                }

                else
                    {afterUpdate(updated_notes, deleted_notes);}



                // Обход по списку рубрик
                JSONArray cat_resultat = catalogs.getJSONArray("resultat");
                length = cat_resultat.length();
                if(length>0){
                    for( int i=0; i<length; i++ ){
                        JSONObject v = cat_resultat.getJSONObject(i);
                        sqlLite.saveBlogCatalog(v);
                    }
                }

            }

            catch (JSONException e)
                {errorUpdate();}

        }

    }

}
