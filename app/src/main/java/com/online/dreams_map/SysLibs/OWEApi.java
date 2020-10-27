package com.online.dreams_map.SysLibs;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;


import com.online.dreams_map.DreamsData.AuthData;

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





public class OWEApi extends AsyncTask<Void, Integer, Void> {

    Context context;
    Activity activity;

    private String server="dreams.online-we.ru:443";
    public String method="null";
    public Map<String,String> params;
    public String result="{}";

    private AuthData authData;





    public void OWEApi(Context context, String method, HashMap<String, String> params){
        this.context = context;
        this.activity = (Activity) context;
        this.method = method;
        this.params = params;
        this.authData = new AuthData(context);
    }





    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... v) {
        super.onProgressUpdate(v);
        onProgress(v);
    }

    @Override
    protected Void doInBackground(Void... p) {
        String myURL = "https://"+server+"/build/sites/dreams-online-we-ru/includes/android_server/v1/"+method+"/main.php";
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

            else {
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


        // Обработка JSON
        try {
            JSONObject json = new JSONObject(result);
            successGet(json);

            if(json.getInt("server_time") > 0){
                authData.setServerTime(json.getInt("server_time"));
            }
        }

        catch (JSONException e) {
            errorSend();
        }

    }





    // Ошибка соединения
    public void onProgress(Integer... v){

    }


    // Ошибка соединения
    public void errorSend(){

    }


    // Успешный запрос
    public void successGet(JSONObject resultat){
    }


}