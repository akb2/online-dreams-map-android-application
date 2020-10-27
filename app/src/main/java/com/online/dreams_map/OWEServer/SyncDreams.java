package com.online.dreams_map.OWEServer;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.SysLibs.ODM_SqlLite;
import com.online.dreams_map.SysLibs.OWEApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;





public class SyncDreams {

    private Activity activity;
    private Context context;

    private AuthData authData;
    private ODM_SqlLite sqlLite;

    private long lastSync;
    private final long period = 120;
    private final long offline_period = 10;





    // Конструктор
    public SyncDreams(Context context){
        this.activity = (Activity) context;
        this.context = context;

        this.authData = new AuthData(context);
        this.sqlLite = new ODM_SqlLite((Activity) context);
    }





    // Старт синхронизации
    public void startSync(){
        lastSync = 0;

        Thread thread = new Thread(new Runnable() {
            public void run(){
                while(true){
                    if(System.currentTimeMillis() / 1000 >= lastSync + (authData.isOnline()? period: offline_period))
                        {sendServer(1);}

                    try {Thread.sleep(1000);}
                    catch (InterruptedException ex){}
                }
            }
        });
        thread.start();
    }


    // Синхронизация
    public void sendServer( int page ){
        beforeUpdate();

        lastSync = System.currentTimeMillis() / 1000;


        if(authData.isOnline()){
            if(authData.getToken().length()>0){

                // Параметры передачи данных на сервер
                HashMap<String, String> params = new HashMap<>();
                params.put("token", authData.getToken());
                params.put("page", String.valueOf(page));

                // Список моих сновидений
                HashMap<Integer, HashMap<String,String>> dreams = sqlLite.getMyDreams(1,0);
                JSONArray dreams_json = new JSONArray();
                for ( Integer k : dreams.keySet() ) {
                    try {
                        HashMap<String, String> v = dreams.get(k);
                        v.put("local_id",v.get("id"));
                        v.put("id",v.get("server_id"));

                        JSONObject temp_json_obj = new JSONObject();
                        temp_json_obj.put("id", v.get("id"));
                        temp_json_obj.put("local_id", v.get("local_id"));
                        temp_json_obj.put("edit_date", v.get("edit_date"));
                        temp_json_obj.put("act", v.get("act"));

                        dreams_json.put(k, temp_json_obj);
                    }

                    catch (JSONException e){}
                }
                params.put("dreams", dreams_json.toString());

                // Запрос
                SendAPIPost sendPost = new SendAPIPost(context, "get_dreams", params);



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





    // Функция перед обновлением сна
    public void beforeUpdate(){

    }


    // Функция перед обновлением сна
    public void progressUpdate(Integer... v){

    }


    // Функция после обновления сна
    public void afterUpdate(HashMap<Integer, Integer> updated_dreams, HashMap<Integer, Integer> deleted_dreams){

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
            HashMap<Integer, Integer> updated_dreams = new HashMap<>();
            HashMap<Integer, Integer> deleted_dreams = new HashMap<>();

            try {
                String code = json.getString("code");
                int page = json.getInt("page");

                JSONObject dreams = json.getJSONObject("dreams");
                JSONArray delete = json.getJSONArray("delete");
                JSONArray update_dreams = json.getJSONArray("update_dreams");



                // Удалить сновидение в приложении
                int len = delete.length();
                HashMap<Integer, Boolean> deleted = new HashMap<>();
                if(len > 0){
                    for( int k=0; k<len; k++ ){
                        int id = delete.getInt(k);
                        deleted_dreams.put(id,id);
                        sqlLite.deleteDream(id,"local");
                    }
                }



                // Обновить сны на сервере
                len = update_dreams.length();
                if(len > 0){
                    for( int k=0; k<len; k++ ){
                        int id = update_dreams.getInt(k);
                        if(deleted_dreams.get(id) == null) {
                            SyncDream tempSyncDream = new SyncDream(context, id);
                            tempSyncDream.sendServer();
                        }
                    }
                }



                // Обход по списку сновидений
                if(dreams.getInt("count")>0){
                    JSONArray resultat=dreams.getJSONArray("resultat");
                    int length=resultat.length();

                    for( int i=0; i<length; i++ ){
                        JSONObject v = resultat.getJSONObject(i);

                        v.put("server_id",v.getString("id"));
                        v.put("id",v.getString("local_id"));
                        v.put("act","");

                        sqlLite.saveDream(v);
                        updated_dreams.put(v.getInt("server_id"), v.getInt("server_id"));
                    }

                    if(page>0)
                        {sendServer(1);}
                    else{
                        afterUpdate(updated_dreams, deleted_dreams);
                    }
                }

                else
                    {errorUpdate();}

            }

            catch (JSONException e)
                {errorUpdate();}

        }


    }


}
