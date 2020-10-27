package com.online.dreams_map.Activities.ViewDream;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.online.dreams_map.Activities.ViewDreamMap.ActivityViewMap;
import com.online.dreams_map.R;
import com.online.dreams_map.SysLibs.DownLoadImageTask;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import org.apmem.tools.layouts.FlowLayout;

import java.util.HashMap;
import java.util.List;





public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    private List cards;
    private Activity activity;
    private Context context;

    private int dream_id;
    private int g_position = 0;

    private Resources res;
    private int px;
    private int keyText;





    // Конструктор
    public RVAdapter(List cards, Activity activity, int dream_id){
        this.activity = activity;
        this.context = (Context) activity;
        this.cards = cards;

        this.dream_id = dream_id;

        this.res = this.context.getResources();
        this.px = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 1, res.getDisplayMetrics() );
        this.keyText = View.generateViewId();
    }





    // Элемент
    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        int currentCardPosition;
        Context mContext;

        CardViewHolder(CardView cv, Context context) {
            super(cv);
            cardView = cv;
            mContext = context;
        }
    }





    // Определение типа элемента
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        CardView elm = new CardView(viewGroup.getContext());
        Card data = (Card) cards.get(g_position);



        // Кнопка
        if(data.getType() == 0) {
            elm = (CardView)
                LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.viewdream_mapbutton_element, viewGroup, false)
            ;
        }

        // Содержимое
        else if(data.getType() == 1) {
            elm = (CardView)
                LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.viewdream_content_element, viewGroup, false)
            ;
        }

        // Ключевые слова
        else if(data.getType() == 2) {
            elm = (CardView)
                LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.viewdream_keywords_element, viewGroup, false)
            ;
        }

        // Автор сновидения
        else if(data.getType() == 3) {
            elm = (CardView)
                LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.viewdream_user_element, viewGroup, false)
            ;
        }



        g_position++;
        return new CardViewHolder(elm, elm.getContext());
    }


    // Прорисовка элементов
    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        cardViewHolder.currentCardPosition = position;
        CardView cardView = cardViewHolder.cardView;
        Card data = (Card) cards.get(position);



        // Кнопка
        if(data.getType() == 0){
            Button openMap = cardView.findViewById(R.id.openDreamMap);
            openMap.setOnClickListener(new openMapView(context));
        }

        // Содержимое
        else if(data.getType() == 1){
            TextView content = cardView.findViewById(R.id.dream_content);
            content.setText(data.getContent());
        }

        // Ключевые слова
        else if(data.getType() == 2){
            String str_keywords = data.getContent();
            String[] keywords_a = new String[]{};
            if(str_keywords.length() > 0)
                {keywords_a = str_keywords.split(",");}

            if(keywords_a.length > 0){
                for(String keyword : keywords_a){
                    keyword = keyword.trim();
                    LinearLayout layout = new LinearLayout(context);
                    layout.setLayoutParams(new FlowLayout.LayoutParams(
                            FlowLayout.LayoutParams.WRAP_CONTENT,
                            FlowLayout.LayoutParams.WRAP_CONTENT
                    ));
                    layout.setOrientation(LinearLayout.HORIZONTAL);


                    TextView keyword_field = new TextView(context);

                    ViewGroup.MarginLayoutParams keywordsMarginParams = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
                    keywordsMarginParams.setMargins(px * 2, px * 2, px * 2, px * 2);
                    layout.setPadding(px * 15, px * 5, px * 12, px * 5);

                    keyword_field.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    keyword_field.setTextSize(px * 7);
                    keyword_field.setText(keyword.substring(0, 1).toUpperCase() + keyword.substring(1).toLowerCase());
                    keyword_field.setId(keyText);
                    keyword_field.setOnClickListener(new searchKeyword());

                    layout.setBackgroundColor(ContextCompat.getColor(context, R.color.keywordsBackground));
                    keyword_field.setTextColor(ContextCompat.getColor(context, R.color.keywordsColor));

                    layout.addView(keyword_field);


                    ((FlowLayout) cardView.findViewById(R.id.dreams_keywords)).addView(layout);
                }
            }
        }

        // Автор сновидения
        else if(data.getType() == 3){
            int user = Integer.parseInt(data.getContent());


            if(user > 0) {
                ODM_SqlLite db = new ODM_SqlLite(context);
                HashMap<String, String> user_data = db.getUser(user);
                int count = Integer.parseInt(user_data.get("count"));

                if(count > 0){

                    // Аватарка
                    {
                        String filename = "user_ava_" + user_data.get(db.USER_KEY_ID);
                        new DownLoadImageTask((ImageView) cardView.findViewById(R.id.dreamsUserAva), filename, context)
                                .execute(user_data.get(db.USER_KEY_SMALL_AVA_LINK));
                    }

                    // Имя
                    {
                        TextView name = cardView.findViewById(R.id.dreamsUserName);
                        name.setText(user_data.get(db.USER_KEY_NAME)+" "+user_data.get(db.USER_KEY_LASTNAME));
                    }

                    // Дата рождения
                    {
                        String[] months = {"Января","Февраля","Марта","Апреля","Мая","Июня","Июля","Августа","Сентября","Октября","Ноября","Декабря"};

                        TextView bdate = cardView.findViewById(R.id.dreamsUserBDate);
                        bdate.setText(
                            user_data.get(db.USER_KEY_BDAY) + " "+
                            months[Integer.parseInt(user_data.get(db.USER_KEY_BMON))-1] + " "+
                            user_data.get(db.USER_KEY_BYEAR)
                        );
                    }
                }
            }
        }



        cardView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
        cardViewMarginParams.setMargins(0, 20 * px, 0, 0);
        cardView.requestLayout();
    }


    // Количество элементов
    @Override
    public int getItemCount() {
        return cards.size();
    }





    // Просмотр карты сновидения
    private class openMapView implements View.OnClickListener {

        private Context context;


        public openMapView(Context context){
            this.context = context;
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ActivityViewMap.class);
            intent.putExtra("dream_id",dream_id);
            context.startActivity(intent);
            activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

    }


    // Поиск по ключевым словам
    private class searchKeyword implements View.OnClickListener {

        public searchKeyword(){
        }


        @Override
        public void onClick(View view){
            String search = String.valueOf(((TextView)view.findViewById(keyText)).getText());

            Intent result = new Intent();
            result.putExtra("MESSAGE", "SELECT_KEYWORD_ON_VIEWDREAM");
            result.putExtra("search", search);
            activity.setResult(android.app.Activity.RESULT_OK, result);

            activity.finish();

            activity.overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }

    }

}
