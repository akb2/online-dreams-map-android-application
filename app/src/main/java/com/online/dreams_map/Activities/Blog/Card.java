package com.online.dreams_map.Activities.Blog;





public class Card {
    private String id;
    private String title;
    private String description;
    private String keywords;
    private String cover;
    private String date;

    public Card(
            String id,
            String title,
            String description,
            String keywords,
            String cover,
            String date
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.cover = cover;
        this.date = date;
    }





    public String getID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
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

}
