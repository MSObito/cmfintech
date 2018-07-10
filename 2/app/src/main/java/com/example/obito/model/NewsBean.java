package com.example.obito.model;

import java.io.Serializable;
import java.util.List;

public class NewsBean implements Serializable{
    private String id;

    private String titile;

    private String source;

    private String dateTme;

    private boolean like;

    private List<Content> content;

    private List<Comments> comments;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setTitile(String titile){
        this.titile = titile;
    }
    public String getTitile(){
        return this.titile;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setDateTme(String dateTme){
        this.dateTme = dateTme;
    }
    public String getDateTme(){
        return this.dateTme;
    }
    public void setLike(boolean like){
        this.like = like;
    }
    public boolean getLike(){
        return this.like;
    }
    public void setContent(List<Content> content){
        this.content = content;
    }
    public List<Content> getContent(){
        return this.content;
    }
    public void setComments(List<Comments> comments){
        this.comments = comments;
    }
    public List<Comments> getComments(){
        return this.comments;
    }

    public class Content implements Serializable
    {
        private String type;

        private String text;

        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
        public void setText(String text){
            this.text = text;
        }
        public String getText(){
            return this.text;
        }
    }

    public class Comments implements Serializable
    {
        private String icon;

        private String name;

        private String text;

        private int thumbUp;

        private String dateTime;

        public void setIcon(String icon){
            this.icon = icon;
        }
        public String getIcon(){
            return this.icon;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setText(String text){
            this.text = text;
        }
        public String getText(){
            return this.text;
        }
        public void setThumbUp(int thumbUp){
            this.thumbUp = thumbUp;
        }
        public int getThumbUp(){
            return this.thumbUp;
        }
        public void setDateTime(String dateTime){
            this.dateTime = dateTime;
        }
        public String getDateTime(){
            return this.dateTime;
        }
    }

}
