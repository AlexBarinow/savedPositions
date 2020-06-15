package com.example.myapplication;

public class Anything  {

    private String text;
    private String name;

    public Anything(){}
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
