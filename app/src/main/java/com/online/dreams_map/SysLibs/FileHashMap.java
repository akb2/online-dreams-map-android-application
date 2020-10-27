package com.online.dreams_map.SysLibs;


import android.content.Context;

import android.app.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;





public class FileHashMap{


    private Activity activity;
    private Context context;
    private String file_name;

    private File dir;





    // Конструктор
    public FileHashMap(String file_name, Context context){
        this.activity = (Activity) context;
        this.context = context;
        this.file_name = file_name;

        this.dir = context.getDir("", context.MODE_PRIVATE);
    }





    // Сохранить в файл
    public void save(HashMap map){
        try {
            File file = new File(dir, file_name);
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(map);
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e){}
    }
    public void save(HashMap map, String file_name){
        change_name(file_name);
        save(map);
    }


    // Получить из файла
    public HashMap read(){
        try {
            File file = new File(dir, file_name);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            HashMap result = (HashMap) ois.readObject();

            return result;
        }
        catch (Exception e){}


        return new HashMap();
    }
    public HashMap read(String file_name){
        change_name(file_name);
        return read();
    }


    // Изменить имя файла
    public void change_name(String file_name){
        this.file_name = file_name;
    }



}
