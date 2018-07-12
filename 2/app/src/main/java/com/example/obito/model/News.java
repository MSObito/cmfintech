package com.example.obito.model;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable{
    private String id;

    private String titile;

    private String source;

    private String dateTme;

    private Image image;

    private boolean top;

    private List<Comments> comments ;

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
    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage(){
        return this.image;
    }
    public void setTop(boolean top){
        this.top = top;
    }
    public boolean getTop(){
        return this.top;
    }
    public void setComments(List<Comments> comments){
        this.comments = comments;
    }
    public List<Comments> getComments(){
        return this.comments;
    }

}
