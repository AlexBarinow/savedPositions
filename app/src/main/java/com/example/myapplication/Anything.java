package com.example.myapplication;

import org.w3c.dom.Text;

public class Anything {

    private String text;
    private String name;

    public Anything(String text, String name){
        this.name = name;
        this.text = text;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
