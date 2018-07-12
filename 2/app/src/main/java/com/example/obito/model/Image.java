package com.example.obito.model;


import java.io.Serializable;

public class Image implements Serializable {

    private String url;
    private String position;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

}

