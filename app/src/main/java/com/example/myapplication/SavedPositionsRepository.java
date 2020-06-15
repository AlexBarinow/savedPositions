package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Room;



import com.example.myapplication.db.MyDatabase;
import com.example.myapplication.db.SavedPositions;

public class SavedPositionsRepository {


    private String DB_NAME = "yeaahhh";

    private MyDatabase myDatabase;
    public SavedPositionsRepository(Context context) {
        myDatabase = Room.databaseBuilder(context, MyDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

   // @SuppressLint("StaticFieldLeak")
    public void insertPosition(final SavedPositions savedPositions) {
       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {*/
                    try{
                        myDatabase.getMyDao().addPosition(savedPositions);
                    }
                    catch (Exception ex){
                        updPosition(savedPositions);
                    }
                    //return null;
                }/*
            }.execute();
        }*/
    //}

    @SuppressLint("StaticFieldLeak")
    public void updPosition(final SavedPositions savedPositions) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    myDatabase.getMyDao().updatePosition(savedPositions);
                    return null;
                }
            }.execute();
        }
    }

    /*@SuppressLint("StaticFieldLeak")
    public void delTask(final String id) {
        final LiveData<SavedPositions> task = getPosition(id);
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    myDatabase.getMyDao().removePosition(task.getValue());
                    return null;
                }
            }.execute();
        }
    }*/

    @SuppressLint("StaticFieldLeak")
    public void delTask(final SavedPositions savedPositions) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                myDatabase.getMyDao().removePosition(savedPositions);
                return null;
            }
        }.execute();
    }

    public  LiveData<SavedPositions> getPosition(String ArticleID, Context context) {
        try{

                    return myDatabase.getMyDao().getTask(ArticleID);
        }
        catch (Exception ex){


            makeToast(ex.getMessage(), context);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public SavedPositions getById(final String ArticleID){
                return  myDatabase.getMyDao().getById(ArticleID);
    }

    public void makeToast(String string, Context context){
        Toast.makeText(context, string, Toast.LENGTH_LONG).show();
    }

    /*public LiveData<List<ContactsContract.CommonDataKinds.Note>> getTasks() {
        return myDatabase.getMyDao().fetchAllTasks();
    }*/
}
