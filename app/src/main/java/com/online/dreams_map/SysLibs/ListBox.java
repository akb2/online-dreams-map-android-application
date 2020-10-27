package com.online.dreams_map.SysLibs;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.online.dreams_map.R;

import org.apmem.tools.layouts.FlowLayout;

import java.util.HashMap;





public class ListBox{


    private LinearLayout field;
    private Activity activity;
    private Context context;
    private String[] replacement;
    private String placeholder;

    private HashMap <Integer, String> values = new HashMap<>();





    // Инициализация полей
    public ListBox(LinearLayout field, Context context, String[] replacement, String placeholder){
        this.field = field;
        this.context = context;
        this.replacement = replacement;
        this.activity = (Activity) context;
        this.placeholder = placeholder;



        // Само поле
        field.setOrientation(LinearLayout.VERTICAL);

        // Поле для значений
        FlowLayout area = (FlowLayout) activity.getLayoutInflater().inflate(R.layout.listbox_itemsbox, null);
        TextView elm_place = area.findViewById(R.id.listbox_placeholder);
        elm_place.setText(placeholder);
        elm_place.setLayoutParams(new FlowLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Поле ввода текста
        LinearLayout input = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.listbox_input, null);
        input.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));



        field.addView(area);
        field.addView(input);



        field.findViewById(R.id.listbox_button).setOnClickListener(addValue);
    }





    // Добавить значение
    public void addValue(String value){

        if(value.length() > 0){
            int id = View.generateViewId();

            value = value.trim();
            for(String v : replacement){
                value = value.replace(v," ");
            }
            value = value.replace("  "," ");

            FlowLayout area = (FlowLayout) field.findViewById(R.id.listbox_elmsarea);
            LinearLayout elm = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.listbox_elm, null);
            TextView elm_value = elm.findViewById(R.id.elm_value);
            elm_value.setText(value);

            TextView elm_id = elm.findViewById(R.id.elm_id);
            elm_id.setText(String.valueOf(id));

            elm.findViewById(R.id.listbox_button).setOnClickListener(deleteValue);

            TextView elm_place = area.findViewById(R.id.listbox_placeholder);
            elm_place.setText("");
            elm_place.setVisibility(TextView.GONE);

            values.put(id, value);
            area.addView(elm);
        }

    }


    // Получить значения
    public String getValue(){
        String ret = "";



        FlowLayout area = (FlowLayout) field.findViewById(R.id.listbox_elmsarea);
        int count = area.getChildCount();

        if(count > 1){
            for( int i=0; i<count; i++ ){
                View elm_v = area.getChildAt(i);

                if(elm_v.getId() != R.id.listbox_placeholder){
                    LinearLayout elm = (LinearLayout) elm_v;

                    TextView elm_value = elm.findViewById(R.id.elm_value);
                    String value = elm_value.getText().toString();

                    ret = ret.trim() + (ret.length()>0? ", ": "" ) + value.trim();
                }
            }
        }



        return ret;
    }





    // Добавить значение по нажатию кнопки
    View.OnClickListener addValue = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

            EditText input = (EditText) field.findViewById(R.id.listbox_input);
            String value = input.getText().toString();

            addValue(value);

            input.setText("");

        }
    };


    // Удалить значение по нажатию кнопки
    View.OnClickListener deleteValue = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

            LinearLayout elm = (LinearLayout) view.getParent().getParent();
            TextView elm_id = (TextView) elm.findViewById(R.id.elm_id);
            FlowLayout area = (FlowLayout) field.findViewById(R.id.listbox_elmsarea);

            int id = Integer.parseInt(String.valueOf(elm_id.getText()));

            values.remove(id);
            area.removeView(elm);

            if(area.getChildCount() <= 1){
                TextView elm_place = area.findViewById(R.id.listbox_placeholder);
                elm_place.setText(placeholder);
                elm_place.setVisibility(TextView.VISIBLE);
            }

        }
    };

}
