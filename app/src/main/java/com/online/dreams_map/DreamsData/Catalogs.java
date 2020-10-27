package com.online.dreams_map.DreamsData;


import java.util.HashMap;
import java.util.StringTokenizer;





public class Catalogs {


    private final HashMap<String, HashMap<String, String>> catalogs;
    {
        catalogs = new HashMap<>();

        catalogs.put("1",new HashMap<String, String>());
        catalogs.get("1").put("name","Обычный сон");
        catalogs.get("1").put("image","dreams_catalog_1");

        catalogs.put("2",new HashMap<String, String>());
        catalogs.get("2").put("name","Сплошная болтовня");
        catalogs.get("2").put("image","dreams_catalog_2");

        catalogs.put("3",new HashMap<String, String>());
        catalogs.get("3").put("name","Полнейший бред");
        catalogs.get("3").put("image","dreams_catalog_3");

        catalogs.put("4",new HashMap<String, String>());
        catalogs.get("4").put("name","Экшен (эпичное)");
        catalogs.get("4").put("image","dreams_catalog_4");

        catalogs.put("5",new HashMap<String, String>());
        catalogs.get("5").put("name","Осознанное сновидение");
        catalogs.get("5").put("image","dreams_catalog_5");

        catalogs.put("6",new HashMap<String, String>());
        catalogs.get("6").put("name","Внетелесный опыт");
        catalogs.get("6").put("image","dreams_catalog_6");

        catalogs.put("7",new HashMap<String, String>());
        catalogs.get("7").put("name","Радость полные штаны");
        catalogs.get("7").put("image","dreams_catalog_7");

        catalogs.put("8",new HashMap<String, String>());
        catalogs.get("8").put("name","Заставил задуматься");
        catalogs.get("8").put("image","dreams_catalog_8");

        catalogs.put("9",new HashMap<String, String>());
        catalogs.get("9").put("name","Непримечательный сон");
        catalogs.get("9").put("image","dreams_catalog_9");

        catalogs.put("10",new HashMap<String, String>());
        catalogs.get("10").put("name","Кошмарный сон");
        catalogs.get("10").put("image","dreams_catalog_10");

        catalogs.put("11",new HashMap<String, String>());
        catalogs.get("11").put("name","Мрачный сон");
        catalogs.get("11").put("image","dreams_catalog_11");

        catalogs.put("12",new HashMap<String, String>());
        catalogs.get("12").put("name","Грустный сон");
        catalogs.get("12").put("image","dreams_catalog_12");

    }



    // Создание класса
    public Catalogs(){

    }




    // Информация о категориях и настроениях по ID
    public HashMap<String, String> getCatalog(String id){
        if(catalogs.get(id) != null){
            return catalogs.get(id);
        }

        return new HashMap<>();
    }


    // Список категорий
    public HashMap<String, HashMap<String, String>> getCatalogs(){
        HashMap<String, HashMap<String, String>> ret = new HashMap<>();

        for( int i=1; i<=6; i++ ){
            String key = String.valueOf(i);
            ret.put(key,catalogs.get(key));
        }

        return ret;
    }


    // Список настроений
    public HashMap<String, HashMap<String, String>> getMoods(){
        HashMap<String, HashMap<String, String>> ret = new HashMap<>();

        for( int i=7; i<=12; i++ ){
            String key = String.valueOf(i);
            ret.put(key,catalogs.get(key));
        }

        return ret;
    }


}
