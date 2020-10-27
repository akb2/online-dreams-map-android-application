package com.online.dreams_map.Activities.DreamsLib;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.online.dreams_map.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.SubtitleCollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.online.dreams_map.Activities.EditDream.EditDreamActivity;
import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.DreamsData.Dreams;
import com.online.dreams_map.Menus.BottomMenu;
import com.online.dreams_map.Menus.MainMenu;
import com.online.dreams_map.OWEServer.SyncAllDreams;
import com.online.dreams_map.OWEServer.SyncDreams;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class ActivityLib extends AppCompatActivity {

    private AuthData authData;
    private MainMenu mainMenu;
    private BottomMenu bottomMenu;
    public Dreams dreamsClass;
    public SyncMyDreamsClass syncMyDreams;
    public SyncAllDreamsClass syncAllDreams;

    public String lib_type ="my";
    private String lib_user ="0";

    private boolean enabled_upload = true;
    private int dreams_count = 0;
    private int last_count = 0;
    private List<Card> cards = new ArrayList<Card>();
    private HashMap<String, Boolean> shown_dreams = new HashMap<>();
    private int load_limit = 1;

    private RVAdapter old_adapter;





    // Объявление класса
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dreamslib_activity);



        // Данные авторизации
        authData = new AuthData(this);

        // Сервис фонового обновления сновидений
        syncMyDreams = new SyncMyDreamsClass(this);
        syncAllDreams = new SyncAllDreamsClass(this);



        Intent intent = getIntent();

        lib_type = intent.getStringExtra("lib_type");
        lib_type = lib_type == null? "my": lib_type;
        lib_type = lib_type.equals("all") || lib_type.equals("user")? lib_type: "my";

        lib_user = intent.getStringExtra("lib_user");
        lib_user = lib_user == null? authData.getUser(): lib_user;
        if(lib_type.equals("all"))
            {lib_user = "0";}
        else if(lib_type.equals("user"))
            {lib_user = Integer.parseInt(lib_user)>0? lib_user: authData.getUser();}
        else
            {lib_user = authData.getUser();}

        load_limit = lib_type.equals("my")? 1: 10;



        // Класс списка снов
        dreamsClass = new Dreams(this);
        dreamsClass.setFilterUser(Integer.parseInt(lib_user));
        dreamsClass.setFilterLimit(load_limit);



        // Пометки меню
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainMenu = new MainMenu(this, toolbar, (lib_type.equals("my")?"dreams":"all_dreams") );
        bottomMenu = new BottomMenu(this, (lib_type.equals("my")?"dreams":"all_dreams") );



        // Смена цвета коллапсирующего меню
        AppBarLayout appbar = findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(toolbarColorChange);

        SubtitleCollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("Дневник снов");
        collapsingToolbarLayout.setSubtitle(lib_type.equals("my")?"Ваши сновидения":"Сновидения других людей");

        ImageView app_bar_image = findViewById(R.id.app_bar_image);
        app_bar_image.setImageResource(lib_type.equals("my")?R.drawable.title_background_1:R.drawable.title_background_2);



        // Настройка блока списка элементов
        RecyclerView rv = findViewById(R.id.forDreamsLib);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);



        // Загрузка списка
        if(lib_type.equals("my")){
            syncMyDreams.startSync();
            loadDreams(10, 0, false);
        }

        else if(lib_type.equals("all")){
            dreamsClass.setFilterLimit(10);
            syncAllDreams.sendServer(10, 0, dreamsClass);
        }



        // Бесконечная прокрутка
        NestedScrollView scroll = findViewById(R.id.nestedScrollView);
        scroll.setOnScrollChangeListener(infinityLib);



        loader_hide();
        ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
        progressBarLib.setVisibility(ProgressBar.VISIBLE);
    }





    // Вывести на экран список сновидений
    public void loadDreams( int limit, int start, boolean show_old ){
        LoadDreams loadDreams = new LoadDreams(this, limit, start, show_old);
        loadDreams.execute();
    }


    // Обновить карточку сна
    public void updateDreamCard( int id){
        UpdateDream updateDream = new UpdateDream(this, id);
        updateDream.execute();
    }


    // Вспомогательная функция для списка снов
    private void initializeAdapter(boolean show_old){
        RVAdapter adapter;
        RecyclerView rv = findViewById(R.id.forDreamsLib);


        if(show_old){
            int l = cards.size();
            for( int k=0; k<l; k++ ){
                if(k>=last_count){
                    old_adapter.notifyItemChanged(k);
                }
            }
        }

        else{
            adapter = new RVAdapter(cards,this, (ActivityLib) this);
            old_adapter = adapter;
            rv.setAdapter(adapter);

            NestedScrollView scroll = findViewById(R.id.nestedScrollView);
            scroll.setScrollY(0);

            AppBarLayout appbar = findViewById(R.id.appbar);
            appbar.setExpanded(true);
        }


        last_count = cards.size();
    }


    // Вывод снов при бесконечном списке
    public void onEnter(int count) {
        if(enabled_upload){
            if(dreams_count>count){

                if(lib_type.equals("my"))
                    {loadDreams(load_limit, count, true);}

                else if(lib_type.equals("all")){
                    RecyclerView recV = findViewById(R.id.forDreamsLib);
                    LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());

                    enabled_upload = false;
                    dreamsClass.setFilterLimit(load_limit);
                    syncAllDreams.sendServer(load_limit, lm.getChildCount(), dreamsClass);
                }

            }

            else{
                ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
                progressBarLib.setVisibility(ProgressBar.GONE);
            }
        }

        else{
            ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
            progressBarLib.setVisibility(ProgressBar.GONE);
        }
    }





    // Показать уведомление
    private void error_mess_show(String text, String status){
        TextView textView=findViewById(R.id.errorMess);
        Resources r = getResources();
        float dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, r.getDisplayMetrics());

        textView.setText(text);
        textView.setVisibility(View.VISIBLE);
        textView.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        int margin = Math.round(15*dp);
        ViewGroup.MarginLayoutParams backParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        backParams.setMargins(margin, margin, margin, margin);

        if(status.equals("error"))
            {textView.setBackgroundColor(getResources().getColor(R.color.noticeColorError));}
        else if(status.equals("success"))
            {textView.setBackgroundColor(getResources().getColor(R.color.noticeColorSuccess));}
    }


    // Скрыть уведомление
    private void error_mess_hide(){
        TextView textView = findViewById(R.id.errorMess);

        int margin = 0;
        ViewGroup.MarginLayoutParams backParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        backParams.setMargins(margin, margin, margin, margin);

        textView.setText("");
        textView.setVisibility(View.GONE);
        textView.setHeight(0);
    }





    // Кнопки в шапки страницы
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(lib_type.equals("my"))
            {getMenuInflater().inflate(R.menu.mydreams_button, menu);}

        else if(lib_type.equals("all"))
            {getMenuInflater().inflate(R.menu.alldreams_button, menu);}


        return true;
    }


    // Нажатие на кнопки в шапке
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_search) {
            Intent intent = new Intent(this, ActivitySearch.class);
            intent.putExtra("search_text",dreamsClass.getFilterSearch());
            intent.putExtra("search_public",dreamsClass.getFilterPublic());
            intent.putExtra("search_category",dreamsClass.getFilterCategory());
            intent.putExtra("search_mood",dreamsClass.getFilterMood());
            intent.putExtra("lib_type",lib_type);

            startActivityForResult(intent, 1);
            overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
        }

        else if (id == R.id.navigation_refresh) {
            syncMyDreams.forseSync = true;
            syncMyDreams.sendServer(1);
        }

        else if (id == R.id.navigation_add) {
            Intent intent = new Intent(this, EditDreamActivity.class);
            startActivityForResult(intent,1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

        return super.onOptionsItemSelected(item);
    }


    // Получить результат из другой активити
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            if(data.hasExtra("MESSAGE")){

                // Обновление после перехода с просмотра сна
                if(data.getStringExtra("MESSAGE").equals("RELOAD_MYDREAMS_OK")){

                    // Загрузка списка
                    if(lib_type.equals("my")){
                        syncMyDreams.forseSync = true;
                        syncMyDreams.sendServer(1);
                        loadDreams(10, 0, false);
                    }

                    else if(lib_type.equals("all")){
                        dreamsClass.setFilterLimit(10);
                        syncAllDreams.sendServer(10, 0, dreamsClass);
                    }
                }

                // Обновление после выбора фильтров для поиска
                else if(data.getStringExtra("MESSAGE").equals("CHANGE_SEARCH_FILTER")){
                    dreamsClass.setFilterSearch(data.getStringExtra("search_text"));
                    dreamsClass.setFilterPublic(Integer.parseInt(data.getStringExtra("search_public")));
                    dreamsClass.setFilterCategory(Integer.parseInt(data.getStringExtra("search_category")));
                    dreamsClass.setFilterMood(Integer.parseInt(data.getStringExtra("search_mood")));


                    if(lib_type.equals("my"))
                        {loadDreams(10, 0, false);}

                    else if(lib_type.equals("all")){
                        dreamsClass.setFilterLimit(10);
                        syncAllDreams.sendServer(10,0,dreamsClass);
                    }
                }

                // Обновление после выбора ключевого слова на странице просмотра сна
                else if(data.getStringExtra("MESSAGE").equals("SELECT_KEYWORD_ON_VIEWDREAM")){
                    dreamsClass.setFilterSearch(data.getStringExtra("search"));
                    loadDreams(10, 0, false);
                }

            }
        }
    }


    // Нажатие кнопки назад
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if(drawer.isDrawerOpen(GravityCompat.START))
            {drawer.closeDrawer(GravityCompat.START);}
        else
            {super.onBackPressed();}
    }





    // Синхронизация данных своего дневника
    public class SyncMyDreamsClass extends SyncDreams{

        private Activity activity;
        private Context context;

        private boolean firstSync = true;
        public boolean forseSync = false;

        public final String APP_PREFERENCES = "mysettings";
        public final String APP_PREFERENCES_FIRSTSYNC = "first_sync";





        public SyncMyDreamsClass(Context context){
            super(context);

            this.activity = (Activity) context;
            this.context = context;

            SharedPreferences mSettings = this.activity.getSharedPreferences(this.APP_PREFERENCES, Context.MODE_PRIVATE);
            if (mSettings.contains(this.APP_PREFERENCES_FIRSTSYNC)){
                String firstSyncS = mSettings.getString(this.APP_PREFERENCES_FIRSTSYNC, "0");
                this.firstSync = firstSyncS.equals("1")?false:true;
            }
        }


        // Функция перед обновлением сна
        @Override
        public void beforeUpdate(){

            // Первая синхронизация
            if(firstSync)
                {loader_show("Синхронизация вашего дневника\nПожалуйста подождите");}

            // Принудительная синхронизация
            else if(forseSync)
                {loader_show("Синхронизация вашего дневника\nПожалуйста подождите");}

        }


        // Функция в момент обновления сна
        @Override
        public void progressUpdate(Integer... v){
        }


        // Функция при ошибке синхронизации
        @Override
        public void errorUpdate(){
            loader_hide();
        }


        // Функция после обновления сна
        @Override
        public void afterUpdate(HashMap<Integer, Integer> updated_dreams, HashMap<Integer, Integer> deleted_dreams){
            loader_hide();


            // Первая синхронизация
            if(firstSync){
                SharedPreferences mSettings = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=mSettings.edit();
                editor.putString(APP_PREFERENCES_FIRSTSYNC,"1");
                editor.apply();

                firstSync=false;
            }

            // Принудительная синхронизация
            else if(forseSync)
                {forseSync=false;}


            // Обновление списка снов
            {
                RecyclerView recV = findViewById(R.id.forDreamsLib);
                LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());


                // Обновление карточек сновидений
                if (lm.getChildCount() > 0) {
                    int count = cards.size();

                    for( int k=0; k<count; k++ ){
                        if(cards.size() > k) {
                            if(cards.get(k) != null) {
                                int id = Integer.parseInt(cards.get(k).getServerID());

                                if(deleted_dreams.get(id) !=null){
                                    if (deleted_dreams.get(id) == id) {
                                        cards.remove(k);
                                        updateDreamCard(id);
                                    }
                                }

                                else if (updated_dreams.get(id) != null) {
                                    if (updated_dreams.get(id) == id) {
                                        updateDreamCard(id);
                                    }
                                }
                            }

                            else
                                {cards.remove(k);}
                        }
                    }
                }

                // Обновление всего списка
                else {
                    loadDreams(10, 0, false);
                }
            }

        }

    }


    // Синхронизация данных общего дневника
    public class SyncAllDreamsClass extends SyncAllDreams {

        private Activity activity;
        private Context context;





        public SyncAllDreamsClass(Context context){
            super(context);

            this.activity = (android.app.Activity) context;
            this.context = context;
        }


        // Функция перед обновлением сна
        @Override
        public void beforeUpdate(){
        }


        // Функция при ошибке синхронизации
        @Override
        public void errorUpdate(){

            // Обновление списка снов
            {
                RecyclerView recV = findViewById(R.id.forDreamsLib);
                LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());


                // Обновление карточек сновидений
                if (lm.getChildCount()>0 & skip>0) {
                    loadDreams(load_limit, lm.getChildCount(), true);
                }

                // Обновление всего списка
                else{
                    loadDreams(10, 0, false);
                }
            }

        }


        // Функция после обновления сна
        @Override
        public void afterUpdate(HashMap<Integer, Integer> updated_dreams, HashMap<Integer, Integer> deleted_dreams){

            // Обновление списка снов
            {
                RecyclerView recV = findViewById(R.id.forDreamsLib);
                LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());


                // Обновление карточек сновидений
                if (lm.getChildCount()>0 & skip>0) {
                    loadDreams(load_limit, lm.getChildCount(), true);
                }

                // Обновление всего списка
                else{
                    loadDreams(10, 0, false);
                }
            }

        }

    }


    // Фоновое обновление списка сноведений
    public class LoadDreams extends AsyncTask<Void, Card, List<Card>>{

        private Context context;
        private android.app.Activity activity;

        private int limit;
        private int start;
        private boolean show_old;

        private int count = 0;





        public LoadDreams(Context context, int limit, int start, boolean show_old){
            this.context = context;
            this.activity = (android.app.Activity) context;
            this.limit = limit;
            this.start = start;
            this.show_old = show_old;
        }





        @Override
        protected void onPreExecute(){
            ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
            progressBarLib.setVisibility(ProgressBar.VISIBLE);
        }


        @Override
        protected List<Card> doInBackground(Void... p){
            List<Card> _card = cards;

            enabled_upload = false;

            start = start<0? 0 : start;
            limit = limit<1? 10 : limit;

            dreamsClass.setFilterLimit(limit);
            HashMap<Integer, HashMap<String,String>> dreams = dreamsClass.getDreams((int) Math.ceil((double) start / (double) limit) + 1 );

            count = Integer.parseInt(dreams.get(0).get("count"));
            int count_elms = Integer.parseInt(dreams.get(0).get("count_elms")==null?"0":dreams.get(0).get("count_elms"));
            dreams_count = count;



            if(!show_old){
                _card = new ArrayList<Card>();
                shown_dreams = new HashMap<>();
            }



            if(count>0){
                for ( int k=1; k<=count_elms; k++ ) {
                    HashMap<String, String> v = dreams.get(k);

                    if(shown_dreams.get(v.get(dreamsClass.field_id)) == null){
                        Card card = new Card(
                            v.get(dreamsClass.field_id),
                            v.get(dreamsClass.field_server_id),
                            v.get(dreamsClass.field_act).equals("delete")?"НУЖНО УДАЛИТЬ":v.get(dreamsClass.field_title),
                            v.get(dreamsClass.field_description),
                            v.get(dreamsClass.field_keywords),
                            v.get(dreamsClass.field_cover),
                            String.valueOf(v.get(dreamsClass.field_date)),
                            String.valueOf(v.get(dreamsClass.field_category)),
                            String.valueOf(v.get(dreamsClass.field_mood))
                        );

                        shown_dreams.put(v.get(dreamsClass.field_id),true);
                        _card.add(card);
                    }
                }
            }

            else{
                _card = new ArrayList<Card>();
            }



            return _card;
        }


        @Override
        protected void onPostExecute(List<Card> card){

            if(card.size() > 0){
                RecyclerView rv = activity.findViewById(R.id.forDreamsLib);
                LinearLayoutManager lm = ((LinearLayoutManager) rv.getLayoutManager());

                cards = card;

                if(count-lm.getChildCount() > 0)
                    {enabled_upload = true;}
                else{
                    ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
                    progressBarLib.setVisibility(ProgressBar.GONE);
                }

                error_mess_hide();
                initializeAdapter(show_old);
            }

            else{
                if(lib_type.equals("my")) {
                    error_mess_show("У Вас еще нет сновидений", "error");
                    enabled_upload = true;
                }

                else if(lib_type.equals("all")) {
                    error_mess_show("Включите интернет, чтобы просматривать дневники других пользователей", "error");
                    enabled_upload = true;
                }



                ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
                progressBarLib.setVisibility(ProgressBar.GONE);
            }
        }

    }


    // Фоновое обновление определенного сновидения в списке
    public class UpdateDream extends AsyncTask<Void, Void, Card>{

        private Context context;
        private android.app.Activity activity;
        private ODM_SqlLite sqLite;

        private int id;


        public UpdateDream(Context context, int id){
            this.context = context;
            this.activity = (android.app.Activity) context;
            this.id = id;

            sqLite = new ODM_SqlLite(context);
        }



        @Override
        protected Card doInBackground(Void... p){
            Card _card = null;

            HashMap<String,String> dream = sqLite.getDream(id, "server");
            int count = Integer.parseInt(dream.get("count"));



            if(count>0 & !dream.get(sqLite.DREAMS_KEY_ACTION).equals("delete")){
                _card = new Card(
                    dream.get(sqLite.DREAMS_KEY_LOCAL_ID),
                    dream.get(sqLite.DREAMS_KEY_ID),
                    dream.get(sqLite.DREAMS_KEY_TITLE),
                    dream.get(sqLite.DREAMS_KEY_DESCRIPTION),
                    dream.get(sqLite.DREAMS_KEY_KEYWORDS),
                    dream.get(sqLite.DREAMS_KEY_COVER),
                    String.valueOf(dream.get(sqLite.DREAMS_KEY_DATE)),
                    String.valueOf(dream.get(sqLite.DREAMS_KEY_CATEGORY)),
                    String.valueOf(dream.get(sqLite.DREAMS_KEY_MOOD))
                );
            }



            return _card;
        }


        @Override
        protected void onPostExecute(Card card){

            if(card != null){
                int l = cards.size();
                for( int k=0; k<l; k++ ){
                    Card old_card = cards.get(k);

                    if(old_card.getServerID().equals(card.getServerID())){
                        cards.set(k, card);
                        old_adapter.notifyItemChanged(k);
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
            FrameLayout frameLayout = findViewById(R.id.mainLoader);
            frameLayout.setVisibility(View.VISIBLE);

            NestedScrollView scroll = findViewById(R.id.nestedScrollView);
            scroll.setEnabled(false);

            BottomNavigationView bottomNav = findViewById(R.id.bottom_menu);
            bottomNav.setVisibility(View.INVISIBLE);
        }

        TextView textView = findViewById(R.id.mainLoaderText);
        textView.setText(text);
    }


    // Скрыть лоадер
    public void loader_hide(){
        if(loader_get()){

            TextView textView = findViewById(R.id.mainLoaderText);
            FrameLayout frameLayout = findViewById(R.id.mainLoader);

            frameLayout.setVisibility(View.INVISIBLE);
            textView.setText("");

            NestedScrollView scroll = findViewById(R.id.nestedScrollView);
            scroll.setEnabled(true);

            BottomNavigationView bootomNav = findViewById(R.id.bottom_menu);
            bootomNav.setVisibility(View.VISIBLE);
        }
    }





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


    // Бесконечная прокрутка
    NestedScrollView.OnScrollChangeListener infinityLib = new NestedScrollView.OnScrollChangeListener() {

        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            RecyclerView recV = findViewById(R.id.forDreamsLib);
            LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());
            int lastView = lm.findLastVisibleItemPosition();

            if(lastView>=0){
                int listTop = v.getHeight();

                View lastElm = recV.getChildAt(lastView);
                int lastToTop = lastElm.getTop();

                if(scrollY + listTop >= lastToTop){
                    v.stopNestedScroll();
                    onEnter(lm.getChildCount());
                }
            }
        }

    };


}
