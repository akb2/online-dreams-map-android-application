package com.online.dreams_map.SysLibs;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.online.dreams_map.R;
import com.online.dreams_map.Activities.Auth.ActivityLoginForm;
import com.online.dreams_map.Activities.Auth.ActivitySelectAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;





public class Accounts {
    private Context context;
    public Activity activity;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_USER = "user";
    public static final String APP_PREFERENCES_ACCOUNTS = "accounts";



    public Accounts(Context context){
        this.context = context;
        this.activity = (Activity)context;
    }





    // Получение аккаунтов
    public HashMap<String, HashMap<String, String>> getAccounts(){
        HashMap<String, HashMap<String, String>> ret = new HashMap<>();
        SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);



        // Проверка наличия записей об аккаунтах
        if(mSettings.contains(APP_PREFERENCES_ACCOUNTS)){

            try{
                JSONObject accounts = new JSONObject(mSettings.getString(APP_PREFERENCES_ACCOUNTS, "{}"));

                if(accounts.length() > 0){
                    Iterator<String> keys = accounts.keys();
                    ODM_SqlLite db = new ODM_SqlLite(context);

                    while(keys.hasNext()) {
                        String key = keys.next();
                        if(accounts.get(key) instanceof JSONObject){
                            JSONObject account = accounts.getJSONObject(key);
                            HashMap<String, String> ret_account = new HashMap<>();

                            HashMap<String, String> user_data = db.getUser(Integer.parseInt(account.getString(db.USER_KEY_ID)));
                            int count = Integer.parseInt(user_data.get("count"));


                            if(count > 0){
                                ret_account.put(db.USER_KEY_ID,user_data.get(db.USER_KEY_ID));
                                ret_account.put(db.USER_KEY_TOKEN,account.getString(db.USER_KEY_TOKEN));
                                ret_account.put(db.USER_KEY_AVA_LINK,user_data.get(db.USER_KEY_AVA_LINK));
                                ret_account.put(db.USER_KEY_SMALL_AVA_LINK,user_data.get(db.USER_KEY_SMALL_AVA_LINK));
                                ret_account.put(db.USER_KEY_NAME,user_data.get(db.USER_KEY_NAME));
                                ret_account.put(db.USER_KEY_LASTNAME,user_data.get(db.USER_KEY_LASTNAME));
                                ret_account.put(db.USER_KEY_BDAY,user_data.get(db.USER_KEY_BDAY));
                                ret_account.put(db.USER_KEY_BMON,user_data.get(db.USER_KEY_BMON));
                                ret_account.put(db.USER_KEY_BYEAR,user_data.get(db.USER_KEY_BYEAR));

                                ret.put(account.getString(db.USER_KEY_ID),ret_account);
                            }
                        }
                    }
                }
            }

            catch(JSONException e)
                {}

        }



        return ret;
    }


    // Сохраниение аккаунта
    public void saveAccount(String id, String token){
        SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        JSONObject accounts = new JSONObject();
        ODM_SqlLite db = new ODM_SqlLite(context);

        try{

            // Проверка наличия записей об аккаунтах
            if(mSettings.contains(APP_PREFERENCES_ACCOUNTS))
                {accounts = new JSONObject(mSettings.getString(APP_PREFERENCES_ACCOUNTS, "{}"));}

            accounts.put(id,new JSONObject());
            accounts.getJSONObject(id).put(db.USER_KEY_ID,id);
            accounts.getJSONObject(id).put(db.USER_KEY_TOKEN,token);

            SharedPreferences.Editor editor=mSettings.edit();
            editor.putString(APP_PREFERENCES_ACCOUNTS,accounts.toString());
            editor.apply();
        }

        catch(JSONException e)
            {}

    }





    // Выйти из аккаунта
    public void quit(){
        SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mSettings.edit();
        editor.putString(APP_PREFERENCES_TOKEN,"");
        editor.putString(APP_PREFERENCES_USER,"0");
        editor.apply();


        Intent intent;
        HashMap<String, HashMap<String, String>> accounts = getAccounts();
        if(accounts.size() > 0)
            {intent = new Intent(context, ActivitySelectAuth.class);}
        else
            {intent = new Intent(context, ActivityLoginForm.class);}


        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.left_in, R.anim.right_out);
        activity.finish();
    }

}
