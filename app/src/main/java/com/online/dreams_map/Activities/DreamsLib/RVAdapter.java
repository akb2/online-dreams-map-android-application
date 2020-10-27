package com.online.dreams_map.Activities.DreamsLib;



import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.online.dreams_map.R;
import com.online.dreams_map.Activities.ViewDream.ActivityViewDream;
import com.online.dreams_map.DreamsData.Catalogs;
import com.online.dreams_map.SysLibs.DownLoadImageTask;
import com.online.dreams_map.SysLibs.ODM_SqlLite;

import org.apmem.tools.layouts.FlowLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;





public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    private List cards;
    private android.app.Activity activity;
    private Context context;
    public HashMap<Integer, String> idMap;
    private String sub_id = "ActivityLib";

    private Catalogs catalogs = new Catalogs();
    private ActivityLib activityLib;

    private Resources res;
    private int px;





    public RVAdapter(List cards, android.app.Activity activity, ActivityLib activityLib){
        this.activity = activity;
        this.context = (Context) activity;
        this.cards = cards;
        this.activityLib = activityLib;
        this.idMap = new HashMap<>();

        this.res = this.context.getResources();
        this.px = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 1, res.getDisplayMetrics() );
    }





    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public Context mContext;
        public android.app.Activity mActivity;
        public int currentCardPosition;
        public ActivityLib activityLib;

        public CardViewHolder(CardView cv, Context context, android.app.Activity activity, ActivityLib activityLib) { //добавили Context context
            super(cv);
            this.cardView = cv;
            this.mContext = context;
            this.mActivity = activity;
            this.activityLib = activityLib;
        }
    }


    @Override
    public RVAdapter.CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CardView v = (CardView)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dreamslib_element, viewGroup, false);

        return new RVAdapter.CardViewHolder(v, v.getContext(), activity, activityLib);
    }





    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        Card v = (Card) cards.get(position);

        CardView cardView = cardViewHolder.cardView;
        String idName = sub_id + v.getID();
        int id = View.generateViewId();

        idMap.put(id, idName);
        cardView.setId(id);



        // ID сновидения
        TextView dream_id = (TextView) cardView.findViewById(R.id.dream_id);
        dream_id.setText(v.getID());


        // Заголовок
        TextView title = (TextView) cardView.findViewById(R.id.title);
        title.setText(v.getTitle());
        title.setOnClickListener(new ViewDreamOpen(Integer.parseInt(v.getID())));
        title = (TextView) cardView.findViewById(R.id.image_title);
        title.setText(v.getTitle());
        title.setOnClickListener(new ViewDreamOpen(Integer.parseInt(v.getID())));


        // Дата
        DateFormat df = new SimpleDateFormat("d MMMM yyyy (EEEE)");
        String date_str = df.format(new Date(Long.parseLong(v.getDate()) * 1000));
        TextView date = (TextView) cardView.findViewById(R.id.date);
        date.setText(date_str);
        date = (TextView) cardView.findViewById(R.id.image_date);
        date.setText(date_str);
        date.setOnClickListener(new ViewDreamOpen(Integer.parseInt(v.getID())));


        // Ключевые слова
        {
            FlowLayout keywords = (FlowLayout) cardView.findViewById(R.id.keywords);
            keywords.removeAllViews();

            String str_keywords = v.getKeywords();
            String[] keywords_a = new String[]{};
            if(str_keywords.length() > 0)
                {keywords_a = str_keywords.split(",");}


            if(keywords_a.length > 0){
                int i = 0;
                int l = keywords_a.length;
                keywords.setVisibility(FlowLayout.VISIBLE);

                for(String keyword : keywords_a){
                    keyword = keyword.trim();
                    LinearLayout layout = new LinearLayout((Context) activity);
                    layout.setLayoutParams(new FlowLayout.LayoutParams(
                            FlowLayout.LayoutParams.WRAP_CONTENT,
                            FlowLayout.LayoutParams.WRAP_CONTENT
                    ));
                    layout.setOrientation(LinearLayout.HORIZONTAL);


                    TextView keyword_field = new TextView((Context) activity);

                    keyword_field.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    keyword_field.setTextSize(res.getDimension(R.dimen.dreamsLibCardView_keyword_size) / px);
                    keyword_field.setTextColor(ContextCompat.getColor(context, R.color.dreamsLibCardView_keywordColor));
                    keyword_field.setText(keyword.substring(0, 1).toUpperCase() + keyword.substring(1).toLowerCase());
                    keyword_field.setOnClickListener(searchKeyword);
                    layout.addView(keyword_field);


                    i++;
                    if(i < l){
                        TextView keyword_point = new TextView((Context) activity);

                        keyword_point.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        ));
                        keyword_point.setTextSize(res.getDimension(R.dimen.dreamsLibCardView_keyword_size) / px);
                        keyword_point.setTextColor(ContextCompat.getColor(context, R.color.dreamsLibCardView_mainColor));
                        keyword_point.setText(", ");
                        layout.addView(keyword_point);
                    }


                    keywords.addView(layout);
                }
            }

            else{
                keywords.setVisibility(FlowLayout.GONE);
            }
        }


        // Описание
        TextView content = (TextView) cardView.findViewById(R.id.content);
        content.setText(v.getContent());


        // Картинка
        {
            if(v.getCover().length() > 0){
                ImageView image = cardView.findViewById(R.id.image);
                String filename = "dreams_cover_" + v.getID();
                new DownLoadImageTask((ImageView) image, filename, (Context) activity).execute(v.getCover());
                image.setOnClickListener(new ViewDreamOpen(Integer.parseInt(v.getID())));

                FrameLayout frame = cardView.findViewById(R.id.image_frame);
                frame.setVisibility(FrameLayout.VISIBLE);

                title = (TextView) cardView.findViewById(R.id.title);
                title.setVisibility(TextView.GONE);

                date = (TextView) cardView.findViewById(R.id.date);
                date.setVisibility(TextView.GONE);
            }

            else{
                FrameLayout frame = cardView.findViewById(R.id.image_frame);
                frame.setVisibility(FrameLayout.GONE);

                title = (TextView) cardView.findViewById(R.id.title);
                title.setVisibility(TextView.VISIBLE);

                date = (TextView) cardView.findViewById(R.id.date);
                date.setVisibility(TextView.VISIBLE);
            }
        }


        // Настроение и категория
        {

            ODM_SqlLite catalog_db = new ODM_SqlLite(activity);
            HashMap<String, String> catalog = catalogs.getCatalog(v.getCategory());
            HashMap<String, String> mood = catalogs.getCatalog(v.getMood());

            // Категория
            {
                TextView catalogName = cardView.findViewById(R.id.catalogName);
                TextView catalogID = cardView.findViewById(R.id.catalogID);
                ImageView catalogImage = (ImageView) cardView.findViewById(R.id.catalogImage);
                catalogName.setText(catalog.get("name"));
                catalogID.setText(v.getCategory());

                ((LinearLayout)catalogID.getParent()).setOnClickListener(searchCategory);

                new DownLoadImageTask(catalogImage, catalog.get("image"), (Context)activity).execute("local");
                cardView.findViewById(R.id.catalogImage).setBackgroundColor(res.getColor(R.color.transparent));
            }

            // Настроение
            {
                TextView moodName = cardView.findViewById(R.id.moodName);
                TextView moodID = cardView.findViewById(R.id.moodID);
                ImageView moodImage = (ImageView) cardView.findViewById(R.id.moodImage);
                moodName.setText(mood.get("name"));
                moodID.setText(v.getMood());

                ((LinearLayout)moodID.getParent()).setOnClickListener(searchMood);

                new DownLoadImageTask(moodImage,mood.get("image"),(Context) activity).execute("local");
                cardView.findViewById(R.id.moodImage).setBackgroundColor(res.getColor(R.color.transparent));
            }

        }


        // Кнопка
        Button button = cardView.findViewById(R.id.viewDream_button);
        button.setOnClickListener(new ViewDreamOpen(Integer.parseInt(v.getID())));



        cardViewHolder.currentCardPosition = position;


        {
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            cardView.setLayoutParams(cardViewParams);
            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
            cardViewMarginParams.setMargins(0, 20 * px, 0, 0);
            cardView.requestLayout();
        }
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }





    // Читать сон
    private class ViewDreamOpen implements View.OnClickListener{

        private int dream_id;


        public ViewDreamOpen(int dream_id){
            this.dream_id = dream_id;
        }


        @Override
        public void onClick(View view){
            Intent intent = new Intent(context, ActivityViewDream.class);
            intent.putExtra("dream_id",dream_id);
            intent.putExtra("activity","DreamsLib");
            activity.startActivityForResult(intent,1);
            activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

    }

    // Поиск по ключевым словам
    private View.OnClickListener searchKeyword = new View.OnClickListener(){

        @Override
        public void onClick(View view){
            String search = String.valueOf(((TextView)view).getText());

            activityLib.dreamsClass.setFilterSearch(search);

            if(activityLib.lib_type.equals("my"))
                {activityLib.loadDreams(0, 10, false);}

            else if(activityLib.lib_type.equals("all"))
                {activityLib.syncAllDreams.sendServer(10, 0, activityLib.dreamsClass);}
        }

    };

    // Поиск по категории
    private View.OnClickListener searchCategory = new View.OnClickListener(){

        @Override
        public void onClick(View view){
            TextView elm = (TextView) view.findViewById(R.id.catalogID);
            String category = String.valueOf(elm.getText());

            activityLib.dreamsClass.setFilterCategory(Integer.parseInt(category));

            if(activityLib.lib_type.equals("my"))
                {activityLib.loadDreams(0, 10, false);}

            else if(activityLib.lib_type.equals("all"))
                {activityLib.syncAllDreams.sendServer(10, 0, activityLib.dreamsClass);}
        }

    };

    // Поиск по настроению
    private View.OnClickListener searchMood = new View.OnClickListener(){

        @Override
        public void onClick(View view){
            TextView elm = (TextView) view.findViewById(R.id.moodID);
            String mood = String.valueOf(elm.getText());

            activityLib.dreamsClass.setFilterMood(Integer.parseInt(mood));

            if(activityLib.lib_type.equals("my"))
                {activityLib.loadDreams(0, 10, false);}

            else if(activityLib.lib_type.equals("all"))
                {activityLib.syncAllDreams.sendServer(10, 0, activityLib.dreamsClass);}
        }

    };


}