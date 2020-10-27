package com.online.dreams_map.DreamsData;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;





public class AuthData {

    private final String APP_PREFERENCES = "mysettings";
    private final String APP_PREFERENCES_TOKEN = "token";
    private final String APP_PREFERENCES_USER = "user";
    private final String APP_PREFERENCES_FIRSTSYNC = "first_sync";
    private final String APP_PREFERENCES_CORRECTTIME = "correct_time";

    private SharedPreferences mSettings;

    private String token = "";
    private String user = "0";
    private boolean firstSync = false;
    private int correct_time = 0;

    private Context context;





    // Запуск класса
    public AuthData(Context context){
        this.context = context;
        this.mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


        this.token = this.mSettings.contains(APP_PREFERENCES_TOKEN)? this.mSettings.getString(APP_PREFERENCES_TOKEN, ""): "";
        this.user = this.mSettings.contains(APP_PREFERENCES_USER)? this.mSettings.getString(APP_PREFERENCES_USER, "0"): "0";
        this.firstSync = this.mSettings.getString(APP_PREFERENCES_FIRSTSYNC, "0").equals("1")?true:false;
        this.correct_time = this.mSettings.contains(APP_PREFERENCES_CORRECTTIME)? this.mSettings.getInt(APP_PREFERENCES_CORRECTTIME, 0): 0;
    }





    // Сохранить время сервера
    public void setServerTime( int server_time ){
        int device_time = Math.round(System.currentTimeMillis() / 1000);
        int _correct_time = server_time - device_time;

        correct_time = _correct_time;
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_CORRECTTIME,correct_time);
        editor.apply();
    }





    // Получить текущий токен
    public String getToken(){
        return token;
    }


    // Получить ID текущего пользователя
    public String getUser(){
        return user;
    }


    // Получить текущий токен
    public boolean getFirstSync(){
        return firstSync;
    }


    // Пучить метку корректировки времени
    public int getCorrectTime(){
        return correct_time;
    }





    // Статс в сети
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting())
            {return true;}

        return false;
    }





}
