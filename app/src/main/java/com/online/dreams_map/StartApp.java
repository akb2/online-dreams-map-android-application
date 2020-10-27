package com.online.dreams_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;

import com.online.dreams_map.R;
import com.online.dreams_map.Activities.Auth.ActivityLoginForm;
import com.online.dreams_map.Activities.Auth.ActivitySelectAuth;
import com.online.dreams_map.Activities.DreamsLib.ActivityLib;
import com.online.dreams_map.Activities.StartSlides.FirstSlideShow;
import com.online.dreams_map.SysLibs.Accounts;

import java.util.HashMap;





public class StartApp extends AppCompatActivity {
    private int lounchCount;
    private String token;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    public static final String APP_PREFERENCES_TOKEN = "token";
    private SharedPreferences mSettings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startapp_activity);


        // Открытые нужного экрана
        {
            Intent intent;
            mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

            // Данные о первом запуске
            {
                if (mSettings.contains(APP_PREFERENCES_COUNTER))
                    {lounchCount = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);}
                else
                    {lounchCount = 0;}
            }

            // Данные о токене
            {
                if (mSettings.contains(APP_PREFERENCES_TOKEN))
                    {token = mSettings.getString(APP_PREFERENCES_TOKEN, "");}
                else
                    {token = "";}
            }



            // Токен не пустой
            if(token.length()>0){
                intent = new Intent(getApplicationContext(), ActivityLib.class);
            }

            // Пустой токен
            else{

                // Если НЕ первый запуск
                if (lounchCount > 0) {
                    Accounts acc = new Accounts(this);
                    HashMap<String, HashMap<String,String>> accounts = acc.getAccounts();

                    if(accounts.size() > 0)
                        {intent = new Intent(getApplicationContext(), ActivitySelectAuth.class);}
                    else
                        {intent = new Intent(getApplicationContext(), ActivityLoginForm.class);}

                }

                // Первый запуск
                else {
                    intent = new Intent(getApplicationContext(), FirstSlideShow.class);
                }

            }



            // Сохранить новое значение запуска
            {
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putInt(APP_PREFERENCES_COUNTER, ++lounchCount);
                editor.apply();
            }



            startActivity(intent);

        }

    }
}
