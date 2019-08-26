package com.example.chachacha_dory;

import android.graphics.drawable.Drawable;

public class ChaClass {
    private String name, mood, desc;
    private int backImg;
    private boolean select;

    public ChaClass(){}

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getBackImg() {
        return backImg;
    }

    public void setBackImg(int backImg) {
        this.backImg = backImg;
    }
}
