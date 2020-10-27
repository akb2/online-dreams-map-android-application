package com.online.dreams_map.SysLibs;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;





public class VKAPI {

    public Activity activity;
    public Context context;
    private String token;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_VKSTATE = "vk_state";
    public static final String APP_PREFERENCES_USER = "user";
    private SharedPreferences mSettings;

    String state = "";
    String curr_user = "0";





    // Настройки класса
    public void startAPI(Activity activity, String token){
        this.activity = activity;
        this.context = (Context)activity;
        this.token = token;

        getSettings();
    }

    // Настройки данных
    public void getSettings(){
        if(context != null) {
            mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


            if (mSettings.contains(APP_PREFERENCES_VKSTATE)) {
                state = mSettings.getString(APP_PREFERENCES_VKSTATE, "");
            }
            state = state == null ? "" : state;

            if (mSettings.contains(APP_PREFERENCES_USER)) {
                curr_user = mSettings.getString(APP_PREFERENCES_USER, "0");
            }
            curr_user = curr_user == null ? "0" : curr_user;
        }
    }





    // Получить данные пользователя
    public String[] get_user(int id){
        String[] ret = new String[]{};



        String method = "users_get";

        String user_id = String.valueOf(id);
        user_id = user_id == null? "0": user_id;



        VKAPI.sendAPIPost sendPost=new VKAPI.sendAPIPost();
        sendPost.params=new HashMap<String,String>();
        sendPost.params.put("method","users_get");
        sendPost.params.put("id",user_id);
        sendPost.params.put("curr",curr_user);
        sendPost.params.put("token",token);

        try{
            sendPost.execute();
        }

        catch (Exception e)
            {}



        return ret;
    }





    // После получения данных о пользователе
    public void afterGetUserData(JSONObject user_data){

    }





    // Класс для отправки данных авторизации через логин и пароль
    class sendAPIPost extends AsyncTask<Void, Void, Void> {
        private String server="dreams.online-we.ru:443";
        public String method="null";
        public Map<String,String> params;
        public String result="{}";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... v) {
            super.onProgressUpdate(v);
        }

        @Override
        protected Void doInBackground(Void... p) {
            String myURL = "https://"+server+"/build/sites/dreams-online-we-ru/includes/android_server/v1/vk_auth/main.php";
            String parammetrs="";

            if(!params.isEmpty()) {
                for (Map.Entry<String,String> value : params.entrySet()) {
                    if(parammetrs.length()>0)
                    {parammetrs += "&";}

                    parammetrs += value.getKey() + "=" + value.getValue();
                }
            }

            byte[] data = null;
            InputStream is = null;


            try {
                URL url = new URL(myURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", "" + Integer.toString(parammetrs.getBytes().length));
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // конвертируем передаваемую строку в UTF-8
                data = parammetrs.getBytes("UTF-8");

                OutputStream os = conn.getOutputStream();

                // передаем данные на сервер
                os.write(data);
                os.flush();
                os.close();
                conn.connect();
                int responseCode= conn.getResponseCode();

                // передаем ответ сервер
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                if (responseCode == 200){
                    is = conn.getInputStream();

                    byte[] buffer = new byte[8192];
                    int bytesRead;

                    while ((bytesRead = is.read(buffer)) != -1)
                    {baos.write(buffer, 0, bytesRead);}

                    data = baos.toByteArray();
                    result = new String(data, "UTF-8");
                }

                conn.disconnect();

            }

            catch (MalformedURLException e) {
            }

            catch (IOException e) {
            }

            catch (Exception e) {
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void r) {
            super.onPostExecute(r);
            String error_mess="";
            String code="0000";



            // Обработка JSON
            try {
                JSONObject json = new JSONObject(result);
                code = json.getString("code");
                String method = json.getJSONObject("data").getString("method");



                if(code.equals("0001")) {

                    // Загрузка пользователей
                    if(method.equals("users_get")){
                        afterGetUserData(json.getJSONObject("vk").getJSONArray("response").getJSONObject(0));
                    }

                }

                else{

                    if(code.equals("8101"))                 {error_mess="Введите Ваши логин (почту) и пароль";}
                    else        if(code.equals("8001"))     {error_mess="Введите логин или почту";}
                    else        if(code.equals("8002"))     {error_mess="Введите пароль";}
                    else        if(code.equals("0002"))     {error_mess="Неверные логин или пароль";}
                    else        if(code.equals("8222"))     {error_mess="Ошибка преобразования JSON данных";}
                    else                                    {error_mess="Неизвестная ошибка";}

                }
            }

            catch (JSONException e) {
            }

        }

    }

}