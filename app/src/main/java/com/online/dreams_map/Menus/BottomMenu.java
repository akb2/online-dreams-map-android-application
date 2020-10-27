package com.online.dreams_map.Menus;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;

import com.online.dreams_map.Activities.Blog.BlogLib;
import com.online.dreams_map.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.online.dreams_map.Activities.DreamsLib.ActivityLib;

import java.util.HashMap;





public class BottomMenu extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Activity activity;
    private Context context;
    private String current_page;

    private final HashMap<String, Integer> menus_area;{
        menus_area = new HashMap<>();

        menus_area.put("dreams",1);
        menus_area.put("all_dreams",1);
        menus_area.put("blog",1);
    }





    public BottomMenu(Context context, String current_page){
        this.activity = (Activity)context;
        this.context = context;
        this.current_page = current_page;


        if(menus_area.get(current_page) != null){
            BottomNavigationView bottomNavigationView = this.activity.findViewById(R.id.bottom_menu);
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
        }

        else
            {this.current_page = "";}
    }





    // Выбрана кнопка в главном меню
    public void onSelectButton(MenuItem item){
        int id = item.getItemId();



        // Все дневники
        if(id == R.id.navigation_dreams & !current_page.equals("dreams")){
            Intent intent = new Intent(context, ActivityLib.class);
            intent.putExtra("lib_type","my");
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            activity.finish();
        }

        // Блог
        else if(id == R.id.navigation_blog & !current_page.equals("blog")){
            Intent intent = new Intent(context, BlogLib.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            activity.finish();
        }

    }





    // Клик по пункту меню
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        onSelectButton(item);
        return false;
    }


}
