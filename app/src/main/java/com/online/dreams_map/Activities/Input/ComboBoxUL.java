package com.online.dreams_map.Activities.Input;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.online.dreams_map.R;
import com.online.dreams_map.SysLibs.DownLoadImageTask;

import java.util.HashMap;





public class ComboBoxUL extends AppCompatActivity implements View.OnClickListener {


    private String current;
    private int class_id;
    private HashMap<String, HashMap<String, String>> values;
    private String[] sort;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combobox_activity);



        Intent intent = getIntent();

        class_id = intent.getIntExtra("class_id",0);

        current = intent.getStringExtra("current");

        String title = intent.getStringExtra("title");
        title = title.length() > 0? title: "Выберите значение";

        values = (HashMap<String, HashMap<String, String>>) intent.getSerializableExtra("values");
        sort = (String[]) intent.getSerializableExtra("sort");



        // Заголовок
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle(title);



        // Отрисовка полей выбора
        {

            LinearLayout elm = (LinearLayout) findViewById(R.id.combobox_lib);


            for( int k=0; k<sort.length; k++ ){
                String key = sort[k];
                HashMap<String, String> val = values.get(key);

                String image = val.get("image");
                image = image == null? "": image;

                String text = val.get("name");
                text = text == null? "Выберите элемент": text;


                LinearLayout area = (LinearLayout) getLayoutInflater().inflate(R.layout.combobox_elm, null);


                TextView elm_id = area.findViewById(R.id.combobox_elm_id);
                elm_id.setText(key);

                TextView elm_text = area.findViewById(R.id.combobox_elm_input);
                elm_text.setText(text);

                // Картинка
                {
                    ImageView elm_image = area.findViewById(R.id.combobox_elm_image);

                    // Добавить
                    if(image.length() > 0){
                        int resID = getResources().getIdentifier(image, "drawable", getPackageName());

                        if(resID > 0){
                            elm_image.setImageResource(resID);
                            elm_image.setVisibility(ImageView.VISIBLE);
                        }

                        else{
                            String file_name = val.get("image_file_name");
                            file_name = file_name == null? "": file_name;

                            if(file_name.length() > 0){
                                new DownLoadImageTask(elm_image, file_name, this).execute(image);
                                elm_image.setVisibility(ImageView.VISIBLE);
                            }

                            else
                                {elm_image.setVisibility(ImageView.GONE);}
                        }
                    }

                    // Убрать
                    else
                        {elm_image.setVisibility(ImageView.GONE);}

                }


                if(key.equals(current))
                    {area.findViewById(R.id.combobox_elm).setBackgroundColor(getResources().getColor(R.color.textBackgroundMain));}
                area.findViewById(R.id.combobox_elm).setOnClickListener(this);


                elm.addView(area);
            }
        }

    }





    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }





    // Открыть выбор списка
    @Override
    public void onClick(View view){
        TextView elm_id = view.findViewById(R.id.combobox_elm_id);
        String key = String.valueOf(elm_id.getText());

        Intent result = new Intent();
        result.putExtra("key", key);
        result.putExtra("class_id", class_id);
        result.putExtra("MESSAGE", "COMBOBOX_OK");
        setResult(Activity.RESULT_OK, result);

        finish();
    }


}
