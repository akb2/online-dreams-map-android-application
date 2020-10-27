package com.online.dreams_map.Menus;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.online.dreams_map.Activities.Blog.BlogLib;
import com.online.dreams_map.R;
import com.google.android.material.navigation.NavigationView;
import com.online.dreams_map.Activities.DreamsLib.ActivityLib;
import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.SysLibs.Accounts;
import com.online.dreams_map.SysLibs.DownLoadImageTask;
import com.online.dreams_map.SysLibs.ODM_SqlLite;
import com.online.dreams_map.SysLibs.OWEApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;





public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Activity activity;
    private Context context;
    private Toolbar toolbar;
    private String current_page;

    private AuthData authData;

    private final HashMap<String, Integer> menus_area;{
        menus_area = new HashMap<>();

        menus_area.put("dreams",1);
        menus_area.put("all_dreams",1);
        menus_area.put("blog",1);
    }





    public MainMenu(Context context, Toolbar toolbar, String current_page){
        this.activity = (Activity)context;
        this.context = context;
        this.toolbar = toolbar;
        this.current_page = current_page;


        authData = new AuthData(context);


        if(menus_area.get(current_page) != null){
            DrawerLayout drawer = this.activity.findViewById(R.id.drawer_layout);
            NavigationView navigationView = this.activity.findViewById(R.id.nav_view);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this.activity, drawer, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);

            put_userData(true);
            selected_item();
        }
    }





    // Выбрана кнопка в главном меню
    public void onSelectButton(MenuItem item){
        int id = item.getItemId();



        // Мои сновидения
        if(id == R.id.nav_dreams & !current_page.equals("dreams")){
            Intent intent = new Intent(context, ActivityLib.class);
            intent.putExtra("lib_type","my");
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            activity.finish();
        }

        // Все дневники
        else if(id == R.id.nav_all_dreams & !current_page.equals("all_dreams")){
            Intent intent = new Intent(context, ActivityLib.class);
            intent.putExtra("lib_type","all");
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            activity.finish();
        }

        // Блог сновидящего
        else if(id == R.id.nav_blog & !current_page.equals("blog")){
            Intent intent = new Intent(context, BlogLib.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            activity.finish();
        }

        // Выход из профиля
        else if(id == R.id.nav_quit){
            Accounts acc = new Accounts(context);
            acc.quit();
        }



        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }





    // Клик по пункту меню
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        onSelectButton(item);
        return false;
    }





    // Высота статус бара
    public int getStatusBarHeight() {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0)
            {return res.getDimensionPixelSize(resourceId);}

        return 0;
    }





    // Запрос сновидений с сервера
    private class sendAPIPost extends OWEApi {

        Context context;
        Activity activity;
        String method;
        HashMap<String, String> params;


        public sendAPIPost(Context context, String method, HashMap<String, String> params){
            super.OWEApi(context, method, params);

            this.context = context;
            this.activity = (Activity) context;
            this.method = method;
            this.params = params;
        }

        @Override
        public void errorSend(){
        }

        @Override
        public void successGet(JSONObject json){
            String error_mess="";
            String code="0000";
            ODM_SqlLite dreams_db = new ODM_SqlLite(activity);



            // Обработка JSON
            try {
                JSONObject user = json.getJSONObject("user");


                if(user.getInt("id") > 0){
                    dreams_db.saveUser(user);
                    put_userData(false);
                }

            }

            catch (JSONException e)
                {}

        }

    }





    // Вывод данных пользователя в меню
    public void put_userData(boolean sync){
        NavigationView navigationView = activity.findViewById(R.id.nav_view);
        View nav_view = navigationView.getHeaderView(0);
        int user_id = Integer.valueOf(authData.getUser());

        if(user_id>0){
            ODM_SqlLite db = new ODM_SqlLite(context);
            HashMap<String, String> user_data = db.getUser(user_id);
            int count = Integer.parseInt(user_data.get("count"));

            if(count > 0){

                // Аватарка
                {
                    ImageView imgView = nav_view.findViewById(R.id.mainMenuUserAva);

                    if(imgView != null) {
                        String filename = "user_ava_" + user_data.get(db.USER_KEY_ID);
                        new DownLoadImageTask(imgView, filename, context).execute(user_data.get(db.USER_KEY_SMALL_AVA_LINK));
                    }

                }

                // Имя
                {
                    TextView name = nav_view.findViewById(R.id.mainMenuUserName);
                    if(name != null)
                        {name.setText(user_data.get(db.USER_KEY_NAME)+" "+user_data.get(db.USER_KEY_LASTNAME));}
                }

                // Дата рождения
                {
                    String[] months = {
                            "Января","Февраля","Марта",
                            "Апреля","Мая","Июня",
                            "Июля","Августа","Сентября",
                            "Октября","Ноября","Декабря"
                    };

                    TextView bdate = nav_view.findViewById(R.id.mainMenuUserBDate);
                    if(bdate != null){
                        bdate.setText(
                                user_data.get(db.USER_KEY_BDAY) + " "+
                                        months[Integer.parseInt(user_data.get(db.USER_KEY_BMON))-1] + " "+
                                        user_data.get(db.USER_KEY_BYEAR)
                        );
                    }
                }

            }


            if(sync){
                if(authData.isOnline()){
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", authData.getToken());
                    params.put("user", authData.getUser());
                    sendAPIPost owe_api = new sendAPIPost(context, "get_user", params);
                    try
                        {owe_api.execute();}

                    catch(Exception e)
                        {}
                }
            }
        }
    }


    // Отметить текущий пункт меню
    private void selected_item(){
        NavigationView navigationView = this.activity.findViewById(R.id.nav_view);
        View nav_view = navigationView.getHeaderView(0);
        MenuItem item = null;


        // Мои сновидения
        if(current_page.equals("dreams"))
            {item = navigationView.getMenu().findItem(R.id.nav_dreams);}

        // Все сновидения
        if(current_page.equals("all_dreams"))
            {item = navigationView.getMenu().findItem(R.id.nav_all_dreams);}

        // Все сновидения
        if(current_page.equals("blog"))
            {item = navigationView.getMenu().findItem(R.id.nav_blog);}


        if(item != null)
            {item.setChecked(true);}
    }


}
