package com.online.dreams_map.Activities.ViewNote;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.online.dreams_map.R;
import com.online.dreams_map.SysLibs.DownLoadImageTask;
import com.online.dreams_map.SysLibs.MD5;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import org.apmem.tools.layouts.FlowLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;





public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    private List cards;
    private Activity activity;
    private Context context;

    private int note_id;
    private int g_position = 0;

    private Resources res;
    private int px;
    private int keyText;





    // Конструктор
    public RVAdapter(List cards, Activity activity, int note_id){
        this.activity = activity;
        this.context = (Context) activity;
        this.cards = cards;

        this.note_id = note_id;

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



        // Ключевые слова
        if(data.getType() == 2) {
            elm = (CardView)
                    LayoutInflater
                            .from(viewGroup.getContext())
                            .inflate(R.layout.viewnote_keywords_element, viewGroup, false)
            ;
        }

        // Автор сновидения
        else if(data.getType() == 3) {
            elm = (CardView)
                    LayoutInflater
                            .from(viewGroup.getContext())
                            .inflate(R.layout.viewnote_user_element, viewGroup, false)
            ;
        }

        // Абзац статьи
        else if(data.getType() == 4) {
            elm = (CardView)
                    LayoutInflater
                            .from(viewGroup.getContext())
                            .inflate(R.layout.viewnote_content_element, viewGroup, false)
            ;
        }

        // Заголовок абзаца
        else if(data.getType() == 5) {
            elm = (CardView)
                    LayoutInflater
                            .from(viewGroup.getContext())
                            .inflate(R.layout.viewnote_title_element, viewGroup, false)
            ;
        }

        // Картинка
        else if(data.getType() == 6) {
            elm = (CardView)
                    LayoutInflater
                            .from(viewGroup.getContext())
                            .inflate(R.layout.viewnote_image_element, viewGroup, false)
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

        int[] margin = new int[]{ 0, 0, 0, 20 * px };
        //margin[1] = position > 0? margin[1]: margin[3];
        margin[3] = position+1 < getItemCount()? margin[3]: 0;



        // Ключевые слова
        if(data.getType() == 2){
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


                    ((FlowLayout) cardView.findViewById(R.id.note_keywords)).addView(layout);
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
                        new DownLoadImageTask((ImageView) cardView.findViewById(R.id.noteUserAva), filename, context)
                                .execute(user_data.get(db.USER_KEY_SMALL_AVA_LINK));
                    }

                    // Имя
                    {
                        TextView name = cardView.findViewById(R.id.noteUserName);
                        name.setText(user_data.get(db.USER_KEY_NAME)+" "+user_data.get(db.USER_KEY_LASTNAME));
                    }

                    // Дата рождения
                    {
                        String[] months = {"Января","Февраля","Марта","Апреля","Мая","Июня","Июля","Августа","Сентября","Октября","Ноября","Декабря"};

                        TextView bdate = cardView.findViewById(R.id.noteUserBDate);
                        bdate.setText(
                                user_data.get(db.USER_KEY_BDAY) + " "+
                                        months[Integer.parseInt(user_data.get(db.USER_KEY_BMON))-1] + " "+
                                        user_data.get(db.USER_KEY_BYEAR)
                        );
                    }
                }
            }
        }

        // Содержимое статьи
        else if(data.getType() == 4){
            TextView container = cardView.findViewById(R.id.note_content);

            try{
                JSONObject content = new JSONObject(data.getContent());
                JSONArray contents = content.getJSONArray("content");


                int length = contents.length();
                String _text = "";
                if(length > 0){
                    for( int k=0; k<length; k++ ){
                        JSONObject v = contents.getJSONObject(k);
                        _text += ( k>0? " ": "" ) + v.getString("content");
                    }
                }
                _text.replaceAll( "\\s", " " );


                int text_s = 0;
                int text_l = 0;
                int text_e = 0;
                Spannable spannable = new SpannableString(_text);
                if(length > 0){
                    for( int k=0; k<length; k++ ){
                        JSONObject v = contents.getJSONObject(k);
                        String __type = v.getString("type");
                        String __text = v.getString("content");

                        int cor = k>0? 1: 0;
                        text_l = __text.length() + cor;
                        text_e = text_s+text_l;


                        if( __type.equals("bold") )
                            {spannable.setSpan(new StyleSpan(Typeface.BOLD), text_s+cor, text_e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}
                        else if( __type.equals("italic") )
                            {spannable.setSpan(new StyleSpan(Typeface.ITALIC), text_s+cor, text_e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}
                        else if( __type.equals("underline") )
                            {spannable.setSpan(new UnderlineSpan(), text_s+cor, text_e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}
                        else if( __type.equals("strike") )
                            {spannable.setSpan(new StrikethroughSpan(), text_s+cor, text_e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}
                        else if( __type.equals("link") ){
                            spannable.setSpan(new goToURL(v.getString("link")), text_s+cor, text_e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            spannable.setSpan(new UnderlineSpan(), text_s+cor, text_e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.keywordsBackground)), text_s+cor, text_e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }


                        text_s += text_l;
                    }
                }


                container.setText(spannable);
                container.setMovementMethod(LinkMovementMethod.getInstance());
            }
            catch(JSONException e){Log.e("viewnote_content4", e.toString());}
        }

        // Заголовок абзаца
        else if(data.getType() == 5){
            TextView content_elm = cardView.findViewById(R.id.note_title);
            ImageView image_elm = cardView.findViewById(R.id.note_title_image);
            margin[3] = 0;

            try{
                JSONObject content = new JSONObject(data.getContent());

                // Картинка
                String filename = "note_content_image_" + MD5.generate(content.getString("image"));
                new DownLoadImageTask(image_elm, filename, context).execute(content.getString("image"));

                // Текст заголовка
                content_elm.setText(content.getString("content"));
            }
            catch(JSONException e){Log.e("viewnote_content5",e.toString());}
        }

        // Слайд-картинка
        else if(data.getType() == 6){
            TextView alt_elm = cardView.findViewById(R.id.slide_alt);
            ImageView image_elm = cardView.findViewById(R.id.slide_image);

            try{
                JSONObject content = new JSONObject(data.getContent());

                // Картинка
                String filename = "note_content_image_" + MD5.generate(content.getString("image"));
                new DownLoadImageTask(image_elm, filename, context).execute(content.getString("image"));

                // Текст заголовка
                alt_elm.setText(content.getString("alt"));
            }
            catch(JSONException e){Log.e("viewnote_content6",e.toString());}
        }



        cardView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
        cardViewMarginParams.setMargins( margin[0], margin[1], margin[2], margin[3] );
        cardView.requestLayout();
    }


    // Количество элементов
    @Override
    public int getItemCount() {
        return cards.size();
    }





    // Поиск по ключевым словам
    private class searchKeyword implements View.OnClickListener {

        public searchKeyword(){
        }


        @Override
        public void onClick(View view){
            String search = String.valueOf(((TextView)view.findViewById(keyText)).getText());

            Intent result = new Intent();
            result.putExtra("MESSAGE", "SELECT_KEYWORD_ON_VIEWNOTE");
            result.putExtra("search", search);
            activity.setResult(android.app.Activity.RESULT_OK, result);

            activity.finish();

            activity.overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }

    }


    // Переход по внешней ссылке
    private class goToURL extends ClickableSpan {

        private String url;


        public goToURL( String url ){
            this.url = url;
        }


        @Override
        public void onClick(View view){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);

            activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        }
    }

}
