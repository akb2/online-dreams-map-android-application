package com.online.dreams_map.Activities.Auth;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.online.dreams_map.R;
import com.online.dreams_map.Activities.DreamsLib.ActivityLib;
import com.online.dreams_map.SysLibs.Accounts;
import com.online.dreams_map.SysLibs.DownLoadImageTask;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

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





public class ActivitySelectAuth extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_USER = "user";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectauth_activity);


        Button authButton = findViewById(R.id.authButton);

        authButton.setOnClickListener(goToLoginForm);


        displayAccounts();
    }





    // Вывести список аккаунтов
    private void displayAccounts(){
        Accounts acc = new Accounts(this);
        HashMap<String, HashMap<String, String>> accounts = acc.getAccounts();
        float dp = this.getResources().getDisplayMetrics().density;
        ODM_SqlLite db = new ODM_SqlLite(this);
        Resources r = getResources();


        if(accounts.size() > 0){
            LinearLayout libs = findViewById(R.id.AccountsLib);

            for(Map.Entry<String, HashMap<String, String>> entry : accounts.entrySet()){
                String k = entry.getKey();
                HashMap<String, String> v = entry.getValue();

                // Сам блок
                LinearLayout block = new LinearLayout(this);
                block.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                ));
                block.setGravity(Gravity.CENTER_VERTICAL);
                block.setOrientation(LinearLayout.HORIZONTAL);
                block.setPadding((int)dp*50, (int)dp*10, (int)dp*50, 0 );

                // Автарка пользователя
                CardView for_ava = new CardView(this);
                for_ava.setLayoutParams(new FrameLayout.LayoutParams( (int)dp*50, (int)dp*50 ));
                for_ava.setRadius(dp*25);
                for_ava.setCardElevation(0);
                for_ava.setMaxCardElevation(0);
                ImageView ava = new ImageView(this);
                String filename = "user_ava_" + v.get(db.USER_KEY_ID);
                DownLoadImageTask download = new DownLoadImageTask(ava, filename, this);
                download.execute(v.get(db.USER_KEY_SMALL_AVA_LINK));
                ava.setLayoutParams(new FrameLayout.LayoutParams( (int)dp*50, (int)dp*50 ));
                for_ava.addView(ava);
                block.addView(for_ava);

                // Имя пользователя
                TextView name = new TextView(this);
                name.setLayoutParams(new LinearLayout.LayoutParams( FrameLayout.LayoutParams.MATCH_PARENT, (int)dp*30, 1 ));
                name.setPadding((int)dp*10, 0, 0, 0 );
                name.setText(v.get(db.USER_KEY_NAME) +" "+ v.get(db.USER_KEY_LASTNAME));
                name.setTextSize(dp*10);
                name.setTextColor(r.getColor(R.color.CardViewMainColor));
                block.addView(name);

                selectUser click_listener = new selectUser(this,v.get(db.USER_KEY_ID),v.get(db.USER_KEY_TOKEN));
                block.setOnClickListener(click_listener);

                libs.addView(block);
            }
        }


        loader_hide();
    }


    // Переход к авторизации
    View.OnClickListener goToLoginForm = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplicationContext(), ActivityLoginForm.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            finish();
        }
    };


    // Выбор авторизации
    public class selectUser implements View.OnClickListener{
        private Activity activity;
        private Context context;
        private String id;
        private String token;


        public selectUser(Context context, String id, String token) {
            this.activity = (Activity) context;
            this.context = context;
            this.id = id;
            this.token = token;
        }

        @Override
        public void onClick(View v){
            String error_mess = "";



            if(isOnline(activity)){
                loader_show("Авторизация");

                sendAPIPost sendPost=new sendAPIPost(context);
                sendPost.method="auth";
                sendPost.params=new HashMap<String,String>();
                sendPost.params.put("id",id);
                sendPost.params.put("token",token);

                try
                    {sendPost.execute();}

                catch (Exception e)
                    {loader_hide();}
            }

            // Отправка сведений на сервер
            else
                {error_mess="Нет подключения к интернету";}



            // Вывести сообщение или спрятать
            {
                TextView textView=activity.findViewById(R.id.errorMess);

                if(error_mess.length()>0){
                    textView.setText(error_mess);
                    textView.setAlpha(1);
                }

                else{
                    textView.setText("");
                    textView.setAlpha(0);
                }
            }

        }

    };





    // Проверка подключения к интернету
    protected static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {return true;}

        return false;
    }

    // Класс для отправки данных авторизации через логин и пароль
    class sendAPIPost extends AsyncTask<Void, Void, Void> {
        private String server="dreams.online-we.ru:443";
        public String method="null";
        public Map<String,String> params;
        public String result="{}";
        private Context context;
        private Activity activity;



        public sendAPIPost(Context context){
            this.context = context;
            this.activity = (Activity)context;
        }



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
            String myURL = "https://"+server+"/build/sites/dreams-online-we-ru/includes/android_server/v1/"+method+"/check_token.php";
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
            TextView textView = findViewById(R.id.errorMess);



            // Обработка JSON
            try {
                JSONObject json = new JSONObject(result);
                code = json.getString("code");
                token = json.getString("token");
                user = json.getString("id");
            }

            catch (JSONException e) {
            }



            // Интерпритация ошибок
            {

                if(code.equals("0001")) {
                    textView.setText("");
                    textView.setAlpha(0);

                    SharedPreferences mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=mSettings.edit();
                    editor.putString(APP_PREFERENCES_TOKEN,token);
                    editor.putString(APP_PREFERENCES_USER,user);
                    editor.apply();

                    Accounts acc = new Accounts(context);
                    acc.saveAccount(user,token);

                    Intent intent = new Intent(getApplicationContext(), ActivityLib.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                    finish();
                }

                else if(code.equals("8001")){
                    Intent intent = new Intent(getApplicationContext(), ActivityLoginForm.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    finish();
                }

                else{

                    if(code.equals("8101"))     {error_mess="Введите Ваши логин (почту) и пароль";}
                    else        if(code.equals("8001"))     {error_mess="Неверный токен. Авторизуйтесь напрямую";}
                    else        if(code.equals("8222"))     {error_mess="Ошибка преобразования JSON данных";}
                    else                                    {error_mess="Неизвестная ошибка";}



                    // Вывести сообщение или спрятать
                    {
                        loader_hide();

                        if(error_mess.length()>0){
                            textView.setText(error_mess);
                            textView.setAlpha(1);
                        }

                        else{
                            textView.setText("");
                            textView.setAlpha(0);
                        }
                    }
                }

            }

        }

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

}
