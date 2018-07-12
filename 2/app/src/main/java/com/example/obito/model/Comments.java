package com.example.obito.model;

import java.io.Serializable;

public class Comments implements Serializable {

    private int thumbUp;
    private int count;
    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }
    public int getThumbUp() {
        return thumbUp;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

}
