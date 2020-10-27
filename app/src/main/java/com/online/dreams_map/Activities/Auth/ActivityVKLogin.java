package com.online.dreams_map.Activities.Auth;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.online.dreams_map.R;
import com.online.dreams_map.Activities.DreamsLib.ActivityLib;
import com.online.dreams_map.SysLibs.DeviceID;
import com.online.dreams_map.SysLibs.VKAPI;

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





public class ActivityVKLogin extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_VKSTATE = "vk_state";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_USER = "user";
    private SharedPreferences mSettings;

    private String server = "dreams.online-we.ru:443";
    private String uniqueID;
    private String owe_token = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vklogin_activity);

        // Уникальный код устройства
        DeviceID deviceID = new DeviceID(this);
        uniqueID = deviceID.uniqueHash();

        String url = "https://"+server+"/build/sites/dreams-online-we-ru/includes/android_server/v1/vk_auth/main.php?act=open_auth&android_id="+uniqueID;

        WebView webView = findViewById(R.id.webView);
        MyWebViewClient webClient = new MyWebViewClient();
        webClient.startClass(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(webClient);
        webView.loadUrl(url);

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

        loader_show("Загрузка страницы");

    }





    // События загрузки блока просмотра веб-страниц
    private class MyWebViewClient extends WebViewClient {

        Activity activity;

        public void startClass(Activity activity){
            this.activity = activity;
        }



        @TargetApi(Build.VERSION_CODES.N)



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());

            loader_show("Загрузка страницы");

            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            loader_show("Загрузка страницы");

            return true;
        }

        // Страница загружена
        public void onPageFinished(WebView view, String url) {
            loader_hide();


            // Получить содержимое
            {

                try{
                    JSONObject result = new JSONObject(view.getTitle());

                    if(result.getString("type").equals("vk")){
                        if(result.getString("status").equals("success")){
                            String state = result.getString("token");

                            mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=mSettings.edit();
                            editor.putString(APP_PREFERENCES_VKSTATE,state);
                            editor.apply();
                            owe_token = result.getString("owe_token");

                            MyVKAPI vkapi = new MyVKAPI();
                            vkapi.startAPI(activity, result.getString("token"));
                            vkapi.get_user(Integer.parseInt(result.getString("id")));
                        }
                    }
                }

                catch(JSONException e)
                    {}

            }

        }

    }

    // События работы с VKAPI OWE
    private class MyVKAPI extends VKAPI {

        @Override
        public void afterGetUserData(JSONObject user_data){
            loader_show("Авторизация");

            ActivityVKLogin.sendAPIPost sendPost=new ActivityVKLogin.sendAPIPost();
            sendPost.method="vk_auth";
            sendPost.params=new HashMap<String,String>();
            sendPost.params.put("user",user_data.toString());
            sendPost.params.put("token",owe_token);

            try
                {sendPost.execute();}

            catch (Exception e)
                {loader_hide();}
        }

    }




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

        Intent intent = new Intent(this, ActivityLoginForm.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        finish();
    }





    // Текущий статус
    public boolean loader_get(){
        FrameLayout frameLayout=findViewById(R.id.mainLoader);
        int visible = frameLayout.getVisibility();

        if(visible == View.VISIBLE)
            {return true;}

        return false;
    }

    // Показать лоадер
    public void loader_show(String text){
        if(!loader_get()) {
            FrameLayout frameLayout = this.findViewById(R.id.mainLoader);
            frameLayout.setVisibility(View.VISIBLE);
        }

        TextView textView = this.findViewById(R.id.mainLoaderText);
        textView.setText(text);
    }

    // Скрыть лоадер
    public void loader_hide(){
        if(loader_get()){
            TextView textView=this.findViewById(R.id.mainLoaderText);
            FrameLayout frameLayout=this.findViewById(R.id.mainLoader);

            frameLayout.setVisibility(View.INVISIBLE);
            textView.setText("");
        }
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
            String myURL = "https://"+server+"/build/sites/dreams-online-we-ru/includes/android_server/v1/"+method+"/vk_reg.php";
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
                    loader_hide();
                }

                conn.disconnect();

            }

            catch (MalformedURLException e) {
                loader_hide();
            }

            catch (IOException e) {
                loader_hide();
            }

            catch (Exception e) {
                loader_hide();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void r) {
            super.onPostExecute(r);
            String error_mess="";
            String code="0000";
            String token="";
            String user="0";



            // Обработка JSON
            try {
                JSONObject json = new JSONObject(result);
                code = json.getString("code");
                token = json.getString("token");
                user = json.getString("id");
            }

            catch (JSONException e) {
                loader_hide();
            }



            // Интерпритация ошибок
            {

                if(code.equals("0001")) {
                    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=mSettings.edit();
                    editor.putString(APP_PREFERENCES_TOKEN,token);
                    editor.putString(APP_PREFERENCES_USER,user);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), ActivityLib.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                    finish();
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

        }

    }

}
