package com.online.dreams_map.OWEServer;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.SysLibs.ODM_SqlLite;
import com.online.dreams_map.SysLibs.OWEApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;





public class SyncDream {

    private Activity activity;
    private Context context;

    private AuthData authData;
    private ODM_SqlLite sqlLite;

    private int dream_id = 0;

    private long lastSync;
    private final long period = 120;
    private final long offline_period = 10;





    // Конструктор
    public SyncDream(Context context, int dream_id){
        this.activity = (Activity) context;
        this.context = context;
        this.dream_id = dream_id<=0? 0: dream_id;

        this.authData = new AuthData(context);
        this.sqlLite = new ODM_SqlLite((Activity) context);
    }





    // Старт синхронизации
    public void startSync(){
        lastSync = 0;

        Thread thread = new Thread(new Runnable() {
            public void run(){
                if(dream_id > 0){
                    while (true) {
                        if (System.currentTimeMillis() / 1000 >= lastSync + (authData.isOnline() ? period : offline_period))
                            {sendServer();}

                        try {Thread.sleep(1000);}
                        catch (InterruptedException ex){}
                    }
                }
            }
        });
        thread.start();
    }


    // Синхронизация
    public void sendServer(){
        beforeUpdate();

        lastSync = System.currentTimeMillis() / 1000;


        if(dream_id > 0){
            if(authData.isOnline()){
                if(authData.getToken().length()>0){

                    // Параметры передачи данных на сервер
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", authData.getToken());

                    // Данные сновидения
                    HashMap<String,String> dreams = sqlLite.getDream(dream_id, "local");

                    if(Integer.parseInt(dreams.get("count")) > 0){
                        params.put("dream_id", dreams.get("server_id"));

                        dreams.put("local_id",dreams.get("id"));
                        dreams.put("id",dreams.get("server_id"));
                        dreams.put("date",dreams.get("where_date"));
                        dreams.remove("server_id");
                        dreams.remove("count");
                        dreams.remove("where_date");


                        try {
                            JSONObject temp_json_obj = new JSONObject();
                            for ( String k : dreams.keySet() ) {
                                String v = dreams.get(k);
                                temp_json_obj.put( k, v );
                            }
                            params.put("dreams", temp_json_obj.toString());
                        }

                        catch (JSONException e){}
                    }


                    // Запрос
                    SendAPIPost sendPost = new SendAPIPost(context, "get_dream", params);



                    try
                        {sendPost.execute();}

                    catch(Exception e)
                        {errorUpdate();}
                }

                else
                    {errorUpdate();}
            }

            else
                {errorUpdate();}
        }

        else
            {errorUpdate();}
    }


    // Функция перед обновлением сна
    public void beforeUpdate(){

    }


    // Функция перед обновлением сна
    public void progressUpdate(Integer... v){

    }


    // Функция после обновления сна
    public void afterUpdate(){

    }


    // Функция после удаления сна
    public void afterDelete(){

    }


    // Функция при ошибке синхронизации
    public void errorUpdate(){

    }





    // Запрос сновидений с сервера
    class SendAPIPost extends OWEApi {

        Context context;
        Activity activity;





        public SendAPIPost(Context context, String method, HashMap<String, String> params){
            super.OWEApi(context, method, params);

            this.context = context;
            this.activity = (Activity) context;
        }





        @Override
        public void onProgress(Integer... v){
            progressUpdate(v);
        }


        @Override
        public void errorSend(){
            errorUpdate();
        }


        @Override
        public void successGet(JSONObject json){
            try {
                String code = json.getString("code");

                JSONObject dreams = json.getJSONObject("dreams");
                boolean delete = json.getBoolean("delete");



                // Обход по списку сновидений
                if(dreams.getInt("count")>0){
                    JSONArray resultat = dreams.getJSONArray("resultat");
                    int length = resultat.length();

                    for( int i=0; i<length; i++ ){
                        JSONObject v = resultat.getJSONObject(i);

                        v.put("server_id",v.getString("id"));
                        v.put("id",v.getString("local_id"));

                        sqlLite.saveDream(v);
                    }

                    afterUpdate();
                }

                else
                    {errorUpdate();}



                // удалить сновидение в приложении
                if(delete == true){
                    sqlLite.deleteDream(dream_id,"local");
                }
            }

            catch (JSONException e)
                {errorUpdate();}
        }


    }


}
