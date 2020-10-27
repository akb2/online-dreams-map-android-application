package com.online.dreams_map.Activities.ViewDreamMap;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.online.dreams_map.R;
import com.online.dreams_map.SysLibs.DreamsObjects;

import java.util.HashMap;
import java.util.List;





public class MBAdapter {

    private List<MapBlock> cards;
    private Activity activity;
    private Context context;

    private int width;
    private int height;

    private LinearLayout container;
    private HashMap<Integer, LinearLayout> lines = new HashMap<>();

    private HashMap<Integer, Integer> blocks_ids = new HashMap<>();
    private HashMap<Integer, Integer> lines_ids = new HashMap<>();

    private final HashMap<String, HashMap<Integer, Integer>> rotations;{
        rotations = new HashMap<>();

        rotations.put("up", new HashMap<Integer, Integer>());
        rotations.put("right", new HashMap<Integer, Integer>());
        rotations.put("down", new HashMap<Integer, Integer>());
        rotations.put("left", new HashMap<Integer, Integer>());

        rotations.get("up").put(1, 0);
        rotations.get("up").put(2, 90);
        rotations.get("up").put(3, 180);
        rotations.get("up").put(4, 270);

        rotations.get("right").put(1, 90);
        rotations.get("right").put(2, 180);
        rotations.get("right").put(3, 270);
        rotations.get("right").put(4, 0);

        rotations.get("down").put(1, 180);
        rotations.get("down").put(2, 270);
        rotations.get("down").put(3, 0);
        rotations.get("down").put(4, 90);

        rotations.get("left").put(1, 270);
        rotations.get("left").put(2, 0);
        rotations.get("left").put(3, 90);
        rotations.get("left").put(4, 180);
    }
    private HashMap<String, HashMap<Integer, String[]>> objects;





    // Конструктор
    public MBAdapter(List<MapBlock> cards, Context context, LinearLayout container, int width, int height){
        this.context = context;
        this.activity = (Activity) context;
        this.cards = cards;
        this.width = width;
        this.height = height;
        this.container = container;

        this.objects = (new DreamsObjects()).get_objects();
    }





    // Отрисовать все блоки блок
    public void start() {
        int size = cards.size();

        clear();

        if(size>0){
            for( int y=0; y<height; y++ ) {
                for( int x=0; x<width; x++ ) {
                    int key = (y * width) + x;

                    MapBlock data = cards.get(key);
                    FrameLayout elm = data.getBack().equals("empty")? getDefaultElement(): getElement(key);

                    blockData( elm, data, y, x );
                }
            }
        }
    }


    // Отрисовать все блоки блок
    public void clear() {
        if(container.getChildCount() > 0)
            {container.removeAllViews();}

        lines = new HashMap<>();
        blocks_ids = new HashMap<>();
        lines_ids = new HashMap<>();
    }


    // Отрисовать все блоки блок
    public void end() {
        int size = lines.size();

        if(size>0){
            for( int k=0; k<size; k++ ) {
                int id = getLineID(k);

                if(container.findViewById(id) == null){
                    container.addView(lines.get(k));
                }
            }
        }
    }


    // Изменить блок
    public void changeBlock(int y, int x) {
        int key = (y * width) + x;

        MapBlock data = cards.get(key);
        FrameLayout elm = data.getBack().equals("empty")? getDefaultElement(): getElement(key);

        blockData( elm, data, y, x );
    }


    // Изменить ширину и высоту
    public void setSize( int width, int height ){
        this.width = width;
        this.height = height;
    }





    // Новый блок или ссылка на существующий
    private FrameLayout getElement(int position){
        int id = getElementID(position);
        FrameLayout elm;


        elm = (FrameLayout) activity.getLayoutInflater().inflate(R.layout.viewdreammap_element, null);
        elm.setClipChildren(false);
        elm.setClipToPadding(false);
        elm.setId(id);


        return elm;
    }


    // Новый блок или ссылка на существующий
    private FrameLayout getDefaultElement(){
        return (FrameLayout) activity.getLayoutInflater().inflate(R.layout.viewdreammap_element_empty, null);
    }


    // Новый блок или ссылка на существующий
    private int getElementID(int position){
        int ret;


        if(blocks_ids.get(position) == null){
            ret = View.generateViewId();
            blocks_ids.put(position, ret);
        }

        else
            {ret = blocks_ids.get(position);}


        return ret;
    }


    // Данные блока
    private void blockData( FrameLayout elm, MapBlock data, int y, int x ){

        Resources res = context.getResources();
        elm.setLayoutParams(new LinearLayout.LayoutParams( data.getSize(), data.getSize() ));



        if(!data.getBack().equals("empty")){
            int resID;


            // Высота блока
            {
                FrameLayout elm_3dh_frame = (FrameLayout) elm.findViewById(R.id.map_3dh_frame);
                elm_3dh_frame.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, data.get3DHeight(), Gravity.BOTTOM));
                elm_3dh_frame.setVisibility(data.get3DHeight() > 0 ? FrameLayout.VISIBLE : FrameLayout.GONE);

                ImageView elm_3dh_image = (ImageView) elm.findViewById(R.id.map_3dh_image);
                elm_3dh_image.setLayoutParams(new FrameLayout.LayoutParams(data.getSize(), data.getSize() * 6, Gravity.TOP));
                resID = res.getIdentifier("map_data_height_" + data.getBack(), "drawable", context.getPackageName());
                elm_3dh_image.setImageResource(resID);
            }

            // Картинка фона местности
            {
                ImageView elm_back = (ImageView) elm.findViewById(R.id.map_back_image);
                resID = res.getIdentifier("map_data_backs_" + data.getBack(), "drawable", context.getPackageName());

                elm_back.setLayoutParams(new FrameLayout.LayoutParams(data.getSize(), data.getSize(), Gravity.BOTTOM));
                ViewGroup.MarginLayoutParams backParams = (ViewGroup.MarginLayoutParams) elm_back.getLayoutParams();
                backParams.setMargins(0, 0, 0, data.get3DHeight());
                elm_back.setVisibility(ImageView.VISIBLE);
                elm_back.setImageResource(resID);
            }

            // Картинка объекта
            if(!data.getObject().equals("empty")) {
                if(objects.get(data.getObject()) != null){
                    String[] object = objects.get(data.getObject()).get(data.getOrigRotate());
                    ImageView elm_object = (ImageView) elm.findViewById(R.id.map_back_object);
                    resID = res.getIdentifier("map_data_object_" + object[1], "drawable", context.getPackageName());

                    elm_object.setLayoutParams(new FrameLayout.LayoutParams(
                            data.getSize(),
                            data.getSize() * Integer.parseInt(object[0]),
                            Gravity.BOTTOM
                    ));
                    ViewGroup.MarginLayoutParams objectParams = (ViewGroup.MarginLayoutParams) elm_object.getLayoutParams();
                    objectParams.setMargins(0, 0, 0, data.get3DHeight());
                    elm_object.setVisibility(ImageView.VISIBLE);
                    elm_object.setImageResource(resID);
                }
            }

            // Границы
            {

                // Есть границы
                if(data.getBorder()){

                    FrameLayout elm_borders = (FrameLayout) elm.findViewById(R.id.map_border_frame);
                    elm_borders.setLayoutParams(new FrameLayout.LayoutParams(data.getSize(),data.getSize(), Gravity.BOTTOM));
                    ViewGroup.MarginLayoutParams borderParams = (ViewGroup.MarginLayoutParams) elm_borders.getLayoutParams();
                    borderParams.setMargins(0, 0, 0, data.get3DHeight());
                    elm_borders.setVisibility(FrameLayout.VISIBLE);

                    String[] dimensions = new String[]{"up", "right", "down", "left"};
                    String[] borders = new String[]{data.getBorderUp(),data.getBorderRight(),data.getBorderDown(),data.getBorderLeft()};
                    int[] borders_ids = new int[]{R.id.map_border_up,R.id.map_border_right,R.id.map_border_down,R.id.map_border_left};

                    for( int k=0; k<4; k++ ){
                        String border = borders[k];
                        String dimension = dimensions[k];
                        int id = borders_ids[k];
                        int rotate = rotations.get(dimension).get(data.getOrigRotate());
                        ImageView elm_border = elm.findViewById(id);
                        boolean show = (rotate==0 & y>0) || (rotate==90 & x+1<width) || (rotate==180 & y+1<height) || (rotate==270 & x>0)? true: false;

                        if(!border.equals("empty_t1") & !border.equals("empty_t2") & show){
                            resID = res.getIdentifier("map_data_border_"+border, "drawable", context.getPackageName());

                            elm_border.setVisibility(ImageView.VISIBLE);
                            elm_border.setImageResource(resID);

                            elm_border.setRotation(rotate);
                        }

                        else
                            {elm_borders.removeView(elm_border);}
                    }
                }

                // Нет границ
                else{
                    View elm_borders = (View) elm.findViewById(R.id.map_border_frame);
                    elm.removeView(elm_borders);
                }

            }


        }



        insertBlock( elm, y, x );
    }


    // Вставка блока
    private void insertBlock( FrameLayout elm, int y, int x ){
        int key = (y * width) + x;


        LinearLayout current_line = getLine( y );
        current_line.addView(elm);


        if(lines.get(y) == null)
            {lines.put(y, current_line);}
    }





    // Текущая строка
    private LinearLayout getLine( int y_position ){
        int id = getLineID(y_position);
        LinearLayout elm;



        if(lines.get(y_position) != null)
            {elm = lines.get(y_position);}

        else{
            elm = new LinearLayout(context);
            elm.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            elm.setOrientation(LinearLayout.HORIZONTAL);
            elm.setClipChildren(false);
            elm.setClipToPadding(false);
            elm.setId(id);
        }



        return elm;
    }


    // Новый блок или ссылка на существующий
    private int getLineID( int y_position ){
        int ret;


        if(lines_ids.get(y_position) == null){
            ret = View.generateViewId();
            lines_ids.put(y_position, ret);
        }

        else
        {ret = lines_ids.get(y_position);}


        return ret;
    }

}
