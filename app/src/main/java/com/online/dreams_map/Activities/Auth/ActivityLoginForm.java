package com.online.dreams_map.Activities.Auth;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import android.os.AsyncTask;

import com.online.dreams_map.R;
import com.online.dreams_map.Activities.DreamsLib.ActivityLib;
import com.online.dreams_map.SysLibs.Accounts;
import com.online.dreams_map.SysLibs.DeviceID;

import org.json.JSONException;
import org.json.JSONObject;





public class ActivityLoginForm extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_USER = "user";
    private SharedPreferences mSettings;
    private String uniqueID;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform_activity);


        // Уникальный код устройства
        DeviceID deviceID = new DeviceID(this);
        uniqueID = deviceID.uniqueHash();


        ImageView vkButton = findViewById(R.id.authVKButton);
        Button authButton = findViewById(R.id.authButton);
        Button goToRegister = findViewById(R.id.registerButton);
        EditText passField = findViewById(R.id.pass);

        authButton.setOnClickListener(tryAuth);
        vkButton.setOnClickListener(tryAuthVK);
        passField.setOnKeyListener(tryAuthKey);


        loader_hide();
    }

    // Попытка авторизации через логин и пароль
    protected void tryAuthCallback(){

        // Значение поля логин
        String login=((EditText)findViewById(R.id.login)).getText().toString();
        // Значение поля пароля
        String pass=((EditText)findViewById(R.id.pass)).getText().toString();
        // Текст сообщения об ошибке
        String error_mess = "";



        // Обработка
        {

            if(isOnline(this)){

                // Пустые значения
                if(login.length()==0 || pass.length()==0){

                    // Оба поля путсые
                    if(login.length()==0 & pass.length()==0)
                        {error_mess="Введите Ваши логин (почту) и пароль";}

                    // Логин
                    else if(login.length()==0)
                        {error_mess="Введите логин или почту";}

                    // Пароль
                    else if(pass.length()==0)
                        {error_mess="Введите пароль";}

                }

                // Запрос на сервер
                else{
                    loader_show("Авторизация");

                    sendAPIPost sendPost=new sendAPIPost(this);
                    sendPost.method="auth";
                    sendPost.params=new HashMap<String,String>();
                    sendPost.params.put("login",login);
                    sendPost.params.put("pass",pass);

                    try
                        {sendPost.execute();}

                    catch (Exception e)
                        {loader_hide();}
                }

            }

            // Отправка сведений на сервер
            else
               {error_mess="Нет подключения к интернету";}

        }




        // Вывести сообщение или спрятать
        {
            TextView textView=findViewById(R.id.errorMess);

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

    // Попытка авторизации через ВКонтакте
    protected void tryAuthVKCallback(){

        // Текст сообщения об ошибке
        String error_mess=new String();



        // Обработка
        {

            if(isOnline(this)){

                Intent intent = new Intent(this, ActivityVKLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();

            }

            // Отправка сведений на сервер
            else
               {error_mess="Нет подключения к интернету";}

        }




        // Вывести сообщение или спрятать
        {
            TextView textView=findViewById(R.id.errorMess);

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
            TextView textView=findViewById(R.id.errorMess);



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

                    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
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

                else{

                                if(code.equals("8101"))     {error_mess="Введите Ваши логин (почту) и пароль";}
                    else        if(code.equals("8001"))     {error_mess="Введите логин или почту";}
                    else        if(code.equals("8002"))     {error_mess="Введите пароль";}
                    else        if(code.equals("0002"))     {error_mess="Неверные логин или пароль";}
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





    // Переход к следующему слайду
    OnClickListener tryAuth = new OnClickListener() {
        @Override
        public void onClick(View v){
            tryAuthCallback();
        }
    };

    // Переход к следующему слайду
    OnClickListener tryAuthVK = new OnClickListener() {
        @Override
        public void onClick(View v){
            tryAuthVKCallback();
        }
    };

    // Подтверждение формы через клавиатуру
    OnKeyListener tryAuthKey = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event){
            if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                tryAuthCallback();
                return true;
            }


            return false;
        }
    };





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