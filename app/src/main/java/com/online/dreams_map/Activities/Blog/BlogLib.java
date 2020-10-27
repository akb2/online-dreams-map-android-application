package com.online.dreams_map.Activities.Blog;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.online.dreams_map.DreamsData.AuthData;
import com.online.dreams_map.DreamsData.BlogNotes;
import com.online.dreams_map.Menus.BottomMenu;
import com.online.dreams_map.Menus.MainMenu;
import com.online.dreams_map.OWEServer.SyncBlogNotes;
import com.online.dreams_map.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class BlogLib extends AppCompatActivity {

    private AuthData authData;
    private MainMenu mainMenu;
    private BottomMenu bottomMenu;
    public BlogNotes blogNotes;
    public SyncBlogNotesClass syncBlogNotes;

    private boolean enabled_upload = true;
    private List<Card> cards = new ArrayList<Card>();
    private int last_count = 0;
    private int notes_count = 0;
    private int load_limit = 10;
    private HashMap<String, Boolean> shown_notes = new HashMap<>();

    private RVAdapter old_adapter;





    // Объявление класса
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloglib_activity);



        // Данные авторизации
        authData = new AuthData(this);

        // Сервис фонового обновления сновидений
        syncBlogNotes = new SyncBlogNotesClass(this);



        // Класс списка снов
        blogNotes = new BlogNotes(this);
        blogNotes.setFilterLimit(load_limit);



        // Пометки меню
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainMenu = new MainMenu(this, toolbar, "blog" );
        bottomMenu = new BottomMenu(this, "blog" );



        // Смена цвета коллапсирующего меню
        AppBarLayout appbar = findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(toolbarColorChange);



        // Настройка блока списка элементов
        RecyclerView rv = findViewById(R.id.forNotesLib);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);



        // Загрузка списка
        blogNotes.setFilterLimit(load_limit);
        syncBlogNotes.sendServer(0,blogNotes);



        // Бесконечная прокрутка
        NestedScrollView scroll = findViewById(R.id.nestedScrollView);
        scroll.setOnScrollChangeListener(infinityLib);



        loader_hide();
        ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
        progressBarLib.setVisibility(ProgressBar.VISIBLE);
    }





    // Вывести на экран список сновидений
    public void loadDreams( int limit, int start, boolean show_old ){
        LoadNotes loadNotes = new LoadNotes(this, limit, start, show_old);
        loadNotes.execute();
    }


    // Вспомогательная функция для списка снов
    private void initializeAdapter(boolean show_old){
        RVAdapter adapter;
        RecyclerView rv = findViewById(R.id.forNotesLib);


        if(show_old){
            int l = cards.size();
            for( int k=0; k<l; k++ ){
                if(k>=last_count){
                    old_adapter.notifyItemChanged(k);
                }
            }
        }

        else{
            adapter = new RVAdapter(cards,this, this);
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
            if(notes_count>count){

                RecyclerView recV = findViewById(R.id.forNotesLib);
                LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());

                enabled_upload = false;
                blogNotes.setFilterLimit(load_limit);
                syncBlogNotes.sendServer(lm.getChildCount(), blogNotes);

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
        getMenuInflater().inflate(R.menu.alldreams_button, menu);

        return true;
    }


    // Нажатие на кнопки в шапке
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_search) {
            Intent intent = new Intent(this, ActivitySearch.class);
            intent.putExtra("search_text", blogNotes.getFilterSearch());
            intent.putExtra("search_catalog", blogNotes.getFilterCatalog());

            startActivityForResult(intent, 1);
            overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
        }

        return super.onOptionsItemSelected(item);
    }


    // Получить результат из другой активити
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            if(data.hasExtra("MESSAGE")){

                // Обновление после выбора фильтров для поиска
                if(data.getStringExtra("MESSAGE").equals("CHANGE_SEARCH_FILTER")){
                    blogNotes.setFilterSearch(data.getStringExtra("search_text"));
                    blogNotes.setFilterCatalog(Integer.parseInt(data.getStringExtra("search_catalog")));

                    blogNotes.setFilterLimit(load_limit);
                    syncBlogNotes.sendServer(0, blogNotes);
                }

                // Обновление после выбора ключевого слова на странице просмотра сна
                else if(data.getStringExtra("MESSAGE").equals("SELECT_KEYWORD_ON_VIEWNOTE")){
                    blogNotes.setFilterSearch(data.getStringExtra("search"));
                    syncBlogNotes.sendServer(0, blogNotes);
                }

            }
        }
    }





    // Синхронизация данных общего дневника
    public class SyncBlogNotesClass extends SyncBlogNotes {

        private Activity activity;
        private Context context;





        public SyncBlogNotesClass(Context context){
            super(context);

            this.activity = (Activity) context;
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
                RecyclerView recV = findViewById(R.id.forNotesLib);
                LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());


                // Обновление карточек сновидений
                if (lm.getChildCount()>0 & skip>0){
                    loadDreams(load_limit, lm.getChildCount(), true);
                }

                // Обновление всего списка
                else{
                    loadDreams(load_limit, 0, false);
                }
            }

        }


        // Функция после обновления сна
        @Override
        public void afterUpdate(HashMap<Integer, Integer> updated_dreams, HashMap<Integer, Integer> deleted_dreams){

            // Обновление списка снов
            {
                RecyclerView recV = findViewById(R.id.forNotesLib);
                LinearLayoutManager lm = ((LinearLayoutManager) recV.getLayoutManager());


                // Обновление карточек сновидений
                if (lm.getChildCount()>0 & skip>0){
                    loadDreams(load_limit, lm.getChildCount(), true);
                }

                // Обновление всего списка
                else{
                    loadDreams(load_limit, 0, false);
                }
            }

        }

    }


    // Фоновое обновление списка статей
    public class LoadNotes extends AsyncTask<Void, Card, List<Card>> {

        private Context context;
        private android.app.Activity activity;

        private int limit;
        private int start;
        private boolean show_old;

        private int count = 0;





        public LoadNotes(Context context, int limit, int start, boolean show_old){
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

            blogNotes.setFilterLimit(limit);
            HashMap<Integer, HashMap<String,String>> notes = blogNotes.getNotes((int) Math.ceil((double) start / (double) limit) + 1 );
            Log.e("get_note",notes.toString());

            count = Integer.parseInt(notes.get(0).get("count"));
            int count_elms = Integer.parseInt(notes.get(0).get("count_elms")==null?"0":notes.get(0).get("count_elms"));
            notes_count = count;



            if(!show_old){
                _card = new ArrayList<Card>();
                shown_notes = new HashMap<>();
            }



            if(count>0){
                for ( int k=1; k<=count_elms; k++ ) {
                    HashMap<String, String> v = notes.get(k);

                    if(shown_notes.get(v.get(blogNotes.field_id)) == null){
                        Card card = new Card(
                                v.get(blogNotes.field_id),
                                v.get(blogNotes.field_title),
                                v.get(blogNotes.field_description),
                                v.get(blogNotes.field_keywords),
                                v.get(blogNotes.field_cover),
                                String.valueOf(v.get(blogNotes.field_edit_date))
                        );

                        shown_notes.put(v.get(blogNotes.field_id),true);
                        _card.add(card);
                    }
                }
            }

            else
                {_card = new ArrayList<Card>();}



            return _card;
        }


        @Override
        protected void onPostExecute(List<Card> card){

            if(card.size() > 0){
                RecyclerView rv = activity.findViewById(R.id.forNotesLib);
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
                error_mess_show("Включите интернет, чтобы просматривать статьи из блога", "error");
                enabled_upload = true;

                ProgressBar progressBarLib = findViewById(R.id.progressBarLib);
                progressBarLib.setVisibility(ProgressBar.GONE);
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
            RecyclerView recV = findViewById(R.id.forNotesLib);
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
