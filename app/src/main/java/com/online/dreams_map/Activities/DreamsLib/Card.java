package com.online.dreams_map.Activities.DreamsLib;





public class Card {
    private String id;
    private String server_id;
    private String title;
    private String content;
    private String keywords;
    private String cover;
    private String date;
    private String category;
    private String mood;

    public Card(
            String id,
            String server_id,
            String title,
            String content,
            String keywords,
            String cover,
            String date,
            String category,
            String mood
    ) {
        this.id = id;
        this.server_id = server_id;
        this.title = title;
        this.content = content;
        this.keywords = keywords;
        this.cover = cover;
        this.date = date;
        this.category = category;
        this.mood = mood;
    }

    public String getID(){
        return this.id;
    }


    public String getServerID(){
        if(this.server_id == null)
            {this.server_id = "0";}

        return this.server_id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public String getKeywords(){
        return this.keywords;
    }

    public String getCover(){
        return this.cover;
    }

    public String getDate(){
        return this.date;
    }

    public String getCategory(){
        return this.category;
    }

    public String getMood(){
        return this.mood;
    }

}