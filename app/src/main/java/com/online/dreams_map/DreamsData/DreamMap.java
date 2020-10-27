package com.online.dreams_map.DreamsData;


import android.app.Activity;
import android.content.Context;

import com.online.dreams_map.SysLibs.DreamsObjects;
import com.online.dreams_map.SysLibs.FileHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;





public class DreamMap {

    private Activity activity;
    private Context context;
    private int dream_id;
    private FileHashMap fileHashMap;

    private JSONObject map_data;
    private int rotate = 1;
    private int orig_rotate = 1;
    private int size = 1;
    private int width = 0;
    private int height = 0;
    private int back_count = 0;
    private int object_count = 0;

    private boolean save_chache = false;
    private HashMap<Integer, String> cache_backs = new HashMap<>();
    private HashMap<Integer, String> cache_objects = new HashMap<>();
    private HashMap<Integer, Integer> cache_3dhs = new HashMap<>();
    private HashMap<Integer, String[]> cache_borders_up = new HashMap<>();
    private HashMap<Integer, String[]> cache_borders_right = new HashMap<>();
    private HashMap<Integer, String[]> cache_borders_down = new HashMap<>();
    private HashMap<Integer, String[]> cache_borders_left = new HashMap<>();

    private final int default_rotate = 1;
    private final int default_3dh = 0;
    private final int default_size_width = 16;
    private final int default_size = 1;
    private final HashMap<Integer, Integer> default_sizes;{
        default_sizes = new HashMap<>();

        default_sizes.put(1, default_size_width * 1);
        default_sizes.put(2, default_size_width * 2);
        default_sizes.put(3, default_size_width * 3);
        default_sizes.put(4, default_size_width * 4);
        default_sizes.put(5, default_size_width * 5);
        default_sizes.put(6, default_size_width * 6);
    }

    private HashMap<String, HashMap<Integer, String[]>> objects;





    // Конструктор класса
    public DreamMap(JSONObject map_data, int dream_id, Context context){
        this.activity = (Activity) context;
        this.context = context;
        this.dream_id = dream_id;



        // Данные карты
        if (map_data.length() > 0)
            {this.map_data = map_data;}
        else
            {this.map_data = new JSONObject();}


        // Поворот карты
        try {
            this.orig_rotate = this.map_data.getInt("rotate");
            this.orig_rotate = this.orig_rotate>=1 & this.orig_rotate<=4? this.orig_rotate: this.default_rotate;
        }
        catch(JSONException e)
            {this.orig_rotate = this.default_rotate;}


        // Ширина карты
        try {
            this.width = this.map_data.getInt("width");
        }
        catch(JSONException e)
            {this.width = 0;}


        // Высота карты
        try {
            this.height = this.map_data.getInt("height");
        }
        catch(JSONException e)
            {this.height = 0;}


        // Количество непустых фонов
        try {
            this.back_count = map_data.getJSONObject("counts").getInt("object");
        }
        catch(JSONException e)
            {this.back_count = 0;}


        // Количество непустых объектов
        try {
            this.object_count = map_data.getJSONObject("counts").getInt("object");
        }
        catch(JSONException e)
            {this.object_count = 0;}


        DreamsObjects dreamsObjects = new DreamsObjects();
        this.objects = dreamsObjects.get_objects();

        fileHashMap = new FileHashMap("", context);



        // Получение кэша
        this.cache_backs = fileHashMap.read("dream_map_cache_backs_"+dream_id);
        this.cache_objects = fileHashMap.read("dream_map_cache_objects_"+dream_id);
        this.cache_3dhs = fileHashMap.read("dream_map_cache_3dhs_"+dream_id);
        this.cache_borders_up = fileHashMap.read("dream_map_cache_borders_up_"+dream_id);
        this.cache_borders_right = fileHashMap.read("dream_map_cache_borders_right_"+dream_id);
        this.cache_borders_down = fileHashMap.read("dream_map_cache_borders_down_"+dream_id);
        this.cache_borders_left = fileHashMap.read("dream_map_cache_borders_left_"+dream_id);

    }





    // Сохранить кэш
    public void saveCache(){
        if( save_chache ){
            fileHashMap.save(cache_backs, "dream_map_cache_backs_" + dream_id);
            fileHashMap.save(cache_objects, "dream_map_cache_objects_" + dream_id);
            fileHashMap.save(cache_3dhs, "dream_map_cache_3dhs_" + dream_id);
            fileHashMap.save(cache_borders_up, "dream_map_cache_borders_up_" + dream_id);
            fileHashMap.save(cache_borders_right, "dream_map_cache_borders_right_" + dream_id);
            fileHashMap.save(cache_borders_down, "dream_map_cache_borders_down_" + dream_id);
            fileHashMap.save(cache_borders_left, "dream_map_cache_borders_left_" + dream_id);
        }
    }





    // Кооректировка координат
    private int[] correctCoords(int y, int x){
        int[] ret = {y, x};
        int w = getWidth() - 1;
        int h = getHeight() - 1;


        // Поворот на 0
        if(rotate==1)
            {}

        // Поворот на 90
        else if(rotate==2){
            ret[0] = w-x;
            ret[1] = y;
        }

        // Поворот на 180
        else if(rotate==3){
            ret[0] = h-y;
            ret[1] = w-x;
        }

        // Поворот на 270
        else if(rotate==4){
            ret[0] = x;
            ret[1] = h-y;
        }


        return ret;
    }

    // Ключ для кэша
    private int keyFromCoords(int y, int x){
        return (rotate*100000000) + (y*10000) + x;
    }


    // Получить количество блоков фона
    public int getBackCount(){
        return back_count;
    }

    // Получить количество объектов
    public int getObjectCount(){
        return object_count;
    }

    // Получить ширину карты
    public int getWidth(){
        return width;
    }

    // Получить высоту карты
    public int getHeight(){
        return height;
    }

    // Получить поворот карты
    public int getRotate(){
        return rotate;
    }

    // Получить поворот карты
    public int getOrigRotate(){
        return orig_rotate;
    }

    // Получить размер ячейки
    public int getSize(){
        size = size>=1 & size<=6? size: default_size;

        return default_sizes.get(size);
    }

    // Получить размер ячейки
    public int getRealSize(){
        size = size>=1 & size<=6? size: default_size;

        return size;
    }

    // Получить шаг для высоты блока
    public int get3DHeightStep(){
        return getSize()/4;
    }





    // Получить данные блока
    private JSONObject getBlock(int y, int x){
        JSONObject ret = new JSONObject();

        String back = "empty";
        String object = "empty";
        int z3d = 0;

        JSONArray border_ = new JSONArray();
        try {
            border_.put(0, "empty");
            border_.put(1, "t1");
        }
        catch (JSONException e){}
        JSONArray border_u = border_;
        JSONArray border_r = border_;
        JSONArray border_d = border_;
        JSONArray border_l = border_;



        // Проверка самого блока
        try{
            ret = map_data.getJSONArray("blocks").getJSONArray(y).getJSONObject(x);
        }
        catch (JSONException e){}

        // Проверка типа местности
        try{
            back = ret.getString("back");
            int resID = context.getResources().getIdentifier(
                "map_data_backs_"+back,
                "drawable",
                context.getPackageName()
            );
            back = resID==0? "empty": back;
        }
        catch (JSONException e){}

        // Проверка типа объекта
        try{
            object = ret.getString("object");

            if(!object.equals("empty")) {
                if(objects.get(object) != null){
                    String[] object_a = objects.get(object).get(getOrigRotate());
                    int resID = context.getResources().getIdentifier(
                            "map_data_object_" + object_a[1],
                            "drawable",
                            context.getPackageName()
                    );
                    object = resID == 0 ? "empty" : object;
                }
            }
        }
        catch (JSONException e){}

        // Проверка высоты по оси Z
        try{
            z3d = ret.getInt("h");
            z3d = z3d>=-6 & z3d<=12? z3d: 0;
        }
        catch (JSONException e){}

        // Проверка границы сверху
        try{
            border_u = ret.getJSONObject("border").getJSONArray("up");
            border_u.put(1, border_u.getString(0).equals("shadow")? "t1": border_u.getString(1));
            int resID = context.getResources().getIdentifier(
                    "map_data_border_"+border_u.getString(0)+"_"+border_u.getString(1),
                    "drawable",
                    context.getPackageName()
            );
            border_u = resID==0? border_: border_u;
        }
        catch (JSONException e){}

        // Проверка границы справа
        try{
            border_r = ret.getJSONObject("border").getJSONArray("right");
            border_r.put(1, border_r.getString(0).equals("shadow")? "t1": border_r.getString(1));
            int resID = context.getResources().getIdentifier(
                    "map_data_border_"+border_r.getString(0)+"_"+border_r.getString(1),
                    "drawable",
                    context.getPackageName()
            );
            border_r = resID==0? border_: border_r;
        }
        catch (JSONException e){}

        // Проверка границы снизу
        try{
            border_d = ret.getJSONObject("border").getJSONArray("down");
            border_d.put(1, border_d.getString(0).equals("shadow")? "t1": border_d.getString(1));
            int resID = context.getResources().getIdentifier(
                    "map_data_border_"+border_d.getString(0)+"_"+border_d.getString(1),
                    "drawable",
                    context.getPackageName()
            );
            border_d = resID==0? border_: border_d;
        }
        catch (JSONException e){}

        // Проверка границы слева
        try{
            border_l = ret.getJSONObject("border").getJSONArray("left");
            border_l.put(1, border_l.getString(0).equals("shadow")? "t1": border_l.getString(1));
            int resID = context.getResources().getIdentifier(
                    "map_data_border_"+border_l.getString(0)+"_"+border_l.getString(1),
                    "drawable",
                    context.getPackageName()
            );
            border_l = resID==0? border_: border_l;
        }
        catch (JSONException e){}



        // Обновление данных
        try{
            ret.put("back", back);
            ret.put("object", object);
            ret.put("h", String.valueOf(z3d));
            ret.put("border", new JSONObject());
            ret.getJSONObject("border").put("up",border_u);
            ret.getJSONObject("border").put("right",border_r);
            ret.getJSONObject("border").put("down",border_d);
            ret.getJSONObject("border").put("left",border_l);
        }
        catch (JSONException e){}



        return ret;
    }


    // Получить тип местности блока
    public String getBlockBack(int y, int x){
        String ret = "empty";
        int[] coords = correctCoords(y,x);
        y = coords[0];
        x = coords[1];
        int key = keyFromCoords(y,x);


        if(cache_backs.get(key) == null) {
            JSONObject block = getBlock(y, x);

            try
                {ret = block.getString("back");}
            catch (JSONException e)
                {}

            cache_backs.put(key,ret);
            save_chache = true;
        }
        else
            {ret = cache_backs.get(key);}


        return ret;
    }

    // Получить объект блока
    public String getBlockObject(int y, int x){
        String ret = "empty";
        int[] coords = correctCoords(y,x);
        y = coords[0];
        x = coords[1];
        int key = keyFromCoords(y,x);


        if(cache_objects.get(key) == null) {
            JSONObject block = getBlock(y, x);

            try
                {ret = block.getString("object");}
            catch (JSONException e)
                {}

            cache_objects.put(key,ret);
            save_chache = true;
        }
        else
            {ret = cache_objects.get(key);}


        return ret;
    }

    // Получить высоту блока по оси Z
    public int getBlock3DHeight(int y, int x){
        int ret = default_3dh;
        int[] coords = correctCoords(y,x);
        int o_y = y;
        int o_x = x;
        y = coords[0];
        x = coords[1];
        int key = keyFromCoords(y,x);


        if(cache_3dhs.get(key) == null) {
            JSONObject block = getBlock(y, x);

            if(getBlockBack(o_y,o_x).equals("empty"))
                {ret = 0;}
            else{
                try {ret = block.getInt("h");}
                catch (JSONException e) {}
            }

            cache_3dhs.put(key,ret);
            save_chache = true;
        }
        else
            {ret = cache_3dhs.get(key);}


        return ret+6;
    }


    // Получить верхнюю границу блока
    public String[] getBlockBorderUP(int y, int x){
        String[] ret = new String[]{"empty","t1"};
        int[] coords = correctCoords(y,x);
        int o_y = y;
        int o_x = x;
        y = coords[0];
        x = coords[1];
        int key = keyFromCoords(y,x);


        if(cache_borders_up.get(key) == null) {
            JSONObject block = getBlock(y, x);

            try{
                ret[0] = block.getJSONObject("border").getJSONArray("up").getString(0);
                ret[1] = block.getJSONObject("border").getJSONArray("up").getString(1);
            }
            catch (JSONException e) {}

            cache_borders_up.put(key,ret);
            save_chache = true;
        }
        else
            {ret = cache_borders_up.get(key);}


        return ret;
    }

    // Получить правую границу блока
    public String[] getBlockBorderRIGHT(int y, int x){
        String[] ret = new String[]{"empty","t1"};
        int[] coords = correctCoords(y,x);
        int o_y = y;
        int o_x = x;
        y = coords[0];
        x = coords[1];
        int key = keyFromCoords(y,x);


        if(cache_borders_right.get(key) == null) {
            JSONObject block = getBlock(y, x);

            try{
                ret[0] = block.getJSONObject("border").getJSONArray("right").getString(0);
                ret[1] = block.getJSONObject("border").getJSONArray("right").getString(1);
            }
            catch (JSONException e) {}

            cache_borders_right.put(key,ret);
            save_chache = true;
        }
        else
            {ret = cache_borders_right.get(key);}


        return ret;
    }

    // Получить нижнюю границу блока
    public String[] getBlockBorderDOWN(int y, int x){
        String[] ret = new String[]{"empty","t1"};
        int[] coords = correctCoords(y,x);
        int o_y = y;
        int o_x = x;
        y = coords[0];
        x = coords[1];
        int key = keyFromCoords(y,x);


        if(cache_borders_down.get(key) == null) {
            JSONObject block = getBlock(y, x);

            try{
                ret[0] = block.getJSONObject("border").getJSONArray("down").getString(0);
                ret[1] = block.getJSONObject("border").getJSONArray("down").getString(1);
            }
            catch (JSONException e) {}

            cache_borders_down.put(key,ret);
            save_chache = true;
        }
        else
            {ret = cache_borders_down.get(key);}


        return ret;
    }

    // Получить левую границу блока
    public String[] getBlockBorderLEFT(int y, int x){
        String[] ret = new String[]{"empty","t1"};
        int[] coords = correctCoords(y,x);
        int o_y = y;
        int o_x = x;
        y = coords[0];
        x = coords[1];
        int key = keyFromCoords(y,x);


        if(cache_borders_left.get(key) == null) {
            JSONObject block = getBlock(y, x);

            try{
                ret[0] = block.getJSONObject("border").getJSONArray("left").getString(0);
                ret[1] = block.getJSONObject("border").getJSONArray("left").getString(1);
            }
            catch (JSONException e) {}

            cache_borders_left.put(key,ret);
            save_chache = true;
        }
        else
            {ret = cache_borders_left.get(key);}


        return ret;
    }





    // Изменить масштаб блоков
    public boolean zoomOUT(){

        if(size > 1){
            size = size-1;
            return true;
        }


        return false;
    }

    // Изменить масштаб блоков
    public boolean zoomIN(){

        if(size < 6){
            size = size+1;
            return true;
        }


        return false;
    }

    // Изменить масштаб блоков
    public boolean rotateRIGHT(){
        rotate = rotate<4? rotate+1: 1;
        orig_rotate = orig_rotate<4? orig_rotate+1: 1;

        return true;
    }

    // Изменить масштаб блоков
    public boolean rotateLEFT(){
        rotate = rotate>1? rotate-1: 4;
        orig_rotate = orig_rotate>1? orig_rotate-1: 4;

        return true;
    }


}
