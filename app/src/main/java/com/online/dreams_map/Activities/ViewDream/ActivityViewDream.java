package com.online.dreams_map.Activities.ViewDream;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.online.dreams_map.Activities.DreamsLib.ActivityLib;
import com.online.dreams_map.Activities.EditDream.EditDreamActivity;
import com.online.dreams_map.OWEServer.SyncDream;
import com.online.dreams_map.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.SubtitleCollapsingToolbarLayout;
import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.Menus.MainMenu;
import com.online.dreams_map.SysLibs.DownLoadImageTask;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;





public class ActivityViewDream extends AppCompatActivity {


    private AuthData authData;
    private MainMenu mainMenu;
    public syncDreamClass syncDream;

    private int dream_id = 0;
    private int dream_user = 0;
    private int keyText;
    private String prev_activity = "";
    private String dream_title = "";

    private boolean reload_parent = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdream_activity);



        // Данные авторизации
        authData = new AuthData(this);



        // Заголовок
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(backButtonClick);

        mainMenu = new MainMenu(this, toolbar, "view_dream" );



        // Смена цвета коллапсирующего меню
        AppBarLayout appbar = findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(toolbarColorChange);



        // ID просматриваемого сновидения
        {

            // ID из предыдущей активности
            Intent intent = getIntent();
            dream_id = intent.getIntExtra("dream_id",0);
            prev_activity = intent.getStringExtra("activity");
            prev_activity = prev_activity == null? "": prev_activity;
            keyText = View.generateViewId();

            // ID из ссылки
            if(dream_id <= 0){
                String action = intent.getAction();
                String data = intent.getDataString();

                if(Intent.ACTION_VIEW.equals(action) && data != null) {
                    String[] url = data.split("\\?");
                    String[] params = url[0].split("/");

                    ODM_SqlLite dreams_sql = new ODM_SqlLite(this);
                    HashMap<String, String> dream = dreams_sql.getDream(Integer.parseInt(params[4]), "server");

                    if(Integer.parseInt(dream.get("count")) > 0)
                        {dream_id = Integer.parseInt(dream.get(dreams_sql.DREAMS_KEY_LOCAL_ID));}
                }
            }

        }



        putData();
    }


    // Иконки в тулбаре
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(Integer.parseInt(authData.getUser()) == dream_user){
            getMenuInflater().inflate(R.menu.viewdream_menu_top, menu);
            return true;
        }

        
        return false;
    }


    // Обработка событий кнопок
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_edit) {
            Intent intent = new Intent(this, EditDreamActivity.class);
            intent.putExtra("dream_id",dream_id);
            startActivityForResult(intent,1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

        else if (id == R.id.navigation_delete) {
            DeleteDialog myDialogFragment = new DeleteDialog(this, this, dream_id);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            myDialogFragment.show(transaction, "delete_dream_dialog");
        }

        return super.onOptionsItemSelected(item);
    }





    // Обновить содержимое окна
    private void putData(){

        int px = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics() );



        // Отображение содержимого сновидения
        {

            // Сервис фонового обновления сновидений
            if(syncDream == null) {
                syncDream = new syncDreamClass(this, dream_id);
                syncDream.startSync();
            }


            ODM_SqlLite dreams_sql = new ODM_SqlLite(this);
            HashMap<String, String> dream = dreams_sql.getDream(dream_id, "local");


            // Сон найден
            if(Integer.parseInt(dream.get("count")) > 0) {
                List<Card> cards = new ArrayList<Card>();



                // Пользователь сна
                dream_user = Integer.parseInt(dream.get(dreams_sql.DREAMS_KEY_USER));


                // Заголовок
                {
                    SubtitleCollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
                    collapsingToolbarLayout.setTitle(dream.get(dreams_sql.DREAMS_KEY_TITLE));
                    dream_title = dream.get(dreams_sql.DREAMS_KEY_TITLE);
                }


                // Дата
                {
                    SubtitleCollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);

                    DateFormat df = new SimpleDateFormat("d MMMM yyyy (EEEE)");
                    String date_str = df.format(new Date(Long.parseLong(dream.get(dreams_sql.DREAMS_KEY_DATE)) * 1000));

                    collapsingToolbarLayout.setSubtitle(date_str);
                }


                // Фото и карта
                {

                    // Фото
                    if (dream.get(dreams_sql.DREAMS_KEY_COVER).length() > 0) {
                        String filename = "dreams_cover_" + dream.get(dreams_sql.DREAMS_KEY_LOCAL_ID);
                        new DownLoadImageTask((ImageView) findViewById(R.id.app_bar_image), filename, this).execute(dream.get(dreams_sql.DREAMS_KEY_COVER));

                        cards.add(new Card(0,""));
                    }
                }


                // Содержимое
                {
                    String[] contents = dream.get(dreams_sql.DREAMS_KEY_CONTENT).split("\n");

                    int i = 0;
                    for (int k = 0; k < contents.length; k++) {
                        String content = contents[k];

                        if (content.length() > 0 & content.replaceAll(" ", "").length() > 0) {
                            cards.add(new Card( 1, content ));
                            i++;
                        }
                    }

                    if (i == 0) {
                        Card card = new Card( 1, "У этого сновидения еще нет описания . . .");
                        cards.add(card);
                    }
                }


                // Ключевые слова
                {
                    if(dream.get(dreams_sql.DREAMS_KEY_KEYWORDS).length() > 0){
                        cards.add(new Card(2, dream.get(dreams_sql.DREAMS_KEY_KEYWORDS)));
                    }
                }


                // Юзер
                if(dream_user > 0){
                    ODM_SqlLite db = new ODM_SqlLite(this);
                    HashMap<String, String> user_data = db.getUser(dream_user);
                    int count = Integer.parseInt(user_data.get("count"));

                    if(count > 0)
                        {cards.add(new Card(3, String.valueOf(dream_user)));}
                }





                RecyclerView rv = findViewById(R.id.recyclerView);

                RVAdapter adapter = new RVAdapter(cards, this, dream_id);
                rv.setLayoutManager(new LinearLayoutManager(this));
                rv.setAdapter(adapter);
            }

            // Сон не найден
            else{
                onBackPressed();
            }

        }
    }





    // Фоновое обновление сна
    public class syncDreamClass extends SyncDream{

        public syncDreamClass(Context context, int dream_id){
            super(context, dream_id);
        }


        // Функция после обновления сна
        @Override
        public void afterUpdate(){
            putData();
        }

    }





    @Override
    protected void onPause(){
        super.onPause();
    }


    @Override
    public void finish() {
        if(reload_parent == true) {
            Intent result = new Intent();
            result.putExtra("MESSAGE", "RELOAD_MYDREAMS_OK");
            setResult(android.app.Activity.RESULT_OK, result);
        }

        super.finish();

        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(!prev_activity.equals("DreamsLib")){
            reload_parent = false;
            Intent intent = new Intent(this, ActivityLib.class);

            if(dream_user != Integer.parseInt(authData.getUser()))
                {intent.putExtra("lib_type","all");}

            startActivity(intent);
        }

        finish();
    }


    @Override
    public void onResume(){
        super.onResume();

        if(reload_parent == true){
            putData();
            syncDream.sendServer();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            if(data.hasExtra("MESSAGE")){
                if(data.getStringExtra("MESSAGE").equals("SAVE_DATA_OK")){
                    reload_parent = true;
                }
            }
        }
    }





    // Окно удаления сновидения
    public static class DeleteDialog extends DialogFragment{
        private Activity activity;
        private Context context;
        private ActivityViewDream parent_class;
        private int dream_id;

        public DeleteDialog(Context context, ActivityViewDream parent_class, int dream_id){
            this.activity = (Activity) context;
            this.context = context;
            this.parent_class = parent_class;
            this.dream_id = dream_id;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Удаление сна");
            builder.setMessage("Вы действительно желаете удалить это сновидение?");

            builder.setPositiveButton("Удалить", new WindowDeleteYes(context, parent_class, dream_id));
            builder.setCancelable(true);

            return builder.create();
        }
    }

    // Клик по кнопке удаления сновидения
    public static class WindowDeleteYes implements DialogInterface.OnClickListener{
        private Activity activity;
        private Context context;
        private ActivityViewDream parent_class;
        private int dream_id;

        public WindowDeleteYes(Context context, ActivityViewDream parent_class, int dream_id){
            this.activity = (Activity) context;
            this.context = context;
            this.parent_class = parent_class;
            this.dream_id = dream_id;
        }

        @Override
        public void onClick(DialogInterface dialog, int id) {
            ODM_SqlLite dreams_sql = new ODM_SqlLite(context);
            AuthData authData = new AuthData(context);


            try{
                HashMap<String,String> dream = dreams_sql.getDream(dream_id,"local");

                JSONObject dreamData = new JSONObject();
                dreamData.put("id",dream.get(dreams_sql.DREAMS_KEY_LOCAL_ID));
                dreamData.put("server_id",dream.get(dreams_sql.DREAMS_KEY_ID));
                dreamData.put("act","delete");
                dreamData.put("edit_date",Math.round(System.currentTimeMillis() / 1000) + authData.getCorrectTime());

                dreams_sql.saveDream(dreamData);
                parent_class.reload_parent = true;
                parent_class.finish();
            }

            catch(JSONException e){}
        }

    };





    // Клик по кнопке назад
    View.OnClickListener backButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            onBackPressed();
        }

    };


    // Смена цвета коллапсирующего меню
    private AppBarLayout.OnOffsetChangedListener toolbarColorChange = new AppBarLayout.OnOffsetChangedListener() {

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            View backElm = findViewById(R.id.toolbarBackground);
            Toolbar toolbar = findViewById(R.id.toolbar);
            AppBarLayout appbar = findViewById(R.id.appbar);

            int appbar_height = appbar.getHeight();
            int toolbar_height = toolbar.getHeight();
            int real_height = appbar_height + verticalOffset - mainMenu.getStatusBarHeight();
            int max_height = appbar_height - toolbar_height - mainMenu.getStatusBarHeight();
            int invert_height = Math.abs((real_height - toolbar_height) - max_height);
            int alpha = real_height>toolbar_height ? (invert_height * 255) / max_height : 255;

            backElm.setBackgroundColor(Color.argb(alpha, 1, 25, 80));
        }

    };


}
