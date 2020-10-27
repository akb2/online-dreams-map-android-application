package com.online.dreams_map.SysLibs;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.online.dreams_map.R;
import com.online.dreams_map.Activities.Input.ComboBoxUL;

import java.util.HashMap;
import java.util.Map;





public class ComboBox extends Activity implements View.OnClickListener {

    private Activity activity;
    private Context context;
    private LinearLayout field;

    private HashMap<String, HashMap<String, String>> values = new HashMap();
    private String[] sort;
    private String title;
    private String current;

    private int class_id = 0;





    // Инициализация полей
    public ComboBox(LinearLayout field, Context context, String title, final HashMap values, final String[] sort){
        title = title.length() > 0? title: "Выберите значение";


        this.field = field;
        this.context = context;
        this.activity = (Activity) context;
        this.values = values;
        this.sort = sort;
        this.title = title;
        this.class_id = View.generateViewId();


        // Само поле
        field.setOrientation(LinearLayout.VERTICAL);

        // Поле для значений
        LinearLayout area = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.combobox_field, null);


        area.findViewById(R.id.combobox_image).setOnClickListener(this);
        area.findViewById(R.id.combobox_input).setOnClickListener(this);
        area.findViewById(R.id.combobox_button).setOnClickListener(this);
        field.addView(area);


        setValue(sort[0]);
    }





    // Установить значение
    public void setValue(String key){
        HashMap<String, String> val = values.get(key);

        String image = val.get("image");
        image = image == null? "": image;

        String text = val.get("name");
        text = text == null? "Выберите элемент": text;



        // Картинка
        {
            ImageView elm_image = field.findViewById(R.id.combobox_image);

            // Добавить
            if(image.length() > 0){
                int resID = context.getResources().getIdentifier(image, "drawable", context.getPackageName());

                if(resID > 0) {
                    elm_image.setImageResource(resID);
                    elm_image.setVisibility(ImageView.VISIBLE);
                }

                else{
                    String file_name = val.get("image_file_name");
                    file_name = file_name == null? "": file_name;

                    if(file_name.length() > 0){
                        new DownLoadImageTask(elm_image, file_name, context).execute(image);
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


        // Текст
        TextView elm_text = field.findViewById(R.id.combobox_input);
        elm_text.setText(text);


        // ID
        TextView elm_key = field.findViewById(R.id.elm_id);
        elm_key.setText(key);


        current = key;

    }


    // Получить значение
    public String getValue(){
        return current;
    }





    // Открыть выбор списка
    @Override
    public void onClick(View view){
        Intent intent = new Intent(context, ComboBoxUL.class);

        intent.putExtra("title",title);
        intent.putExtra("current",current);
        intent.putExtra("values",values);
        intent.putExtra("sort",sort);
        intent.putExtra("class_id",class_id);

        activity.startActivityForResult(intent,1);
        activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
    }


    // Получить результат
    public void returnValue(String key, int r_class_id) {
        if (r_class_id == class_id)
            {setValue(key);}
    }


}
