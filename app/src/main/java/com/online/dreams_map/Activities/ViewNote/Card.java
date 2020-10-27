package com.online.dreams_map.Activities.ViewNote;





public class Card {

    private String content;
    private int type;





    // Конструктор
    public Card( int type, String content ){
        this.type = type;
        this.content = content;
    }





    // Получить тип содержимого
    public int getType(){
        return this.type;
    }


    // Получить содержимое
    public String getContent(){
        return this.content;
    }
}
