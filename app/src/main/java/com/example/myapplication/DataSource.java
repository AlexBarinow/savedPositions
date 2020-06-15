package com.example.myapplication;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Anything> createAnythingList(){
        try{
        List<Anything> anythingList = new ArrayList<>();
        anythingList.add(new Anything("Here`s Title", "Here`s Text"));
        anythingList.add(new Anything("Title two", "And da text ova it again"));
        return anythingList;}
        catch (Exception e){
           return null;
        }
    }
}
