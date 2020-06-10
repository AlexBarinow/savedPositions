package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;



import com.example.myapplication.db.MyDatabase;
import com.example.myapplication.db.SavedPositions;

public class SavedPositionsRepository {


    private String DB_NAME = "bech";

    private MyDatabase myDatabase;
    public SavedPositionsRepository(Context context) {
        myDatabase = Room.databaseBuilder(context, MyDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertPosition(final SavedPositions savedPositions) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    myDatabase.getMyDao().addPosition(savedPositions);
                }
                catch (Exception ex){
                    updPosition(savedPositions);
                }
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updPosition(final SavedPositions savedPositions) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                myDatabase.getMyDao().updatePosition(savedPositions);
                return null;
            }
        }.execute();
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

    public  LiveData<SavedPositions> getPosition(String ArticleID) {
        try{

                    return myDatabase.getMyDao().getTask(ArticleID);



        }
        catch (Exception ex){
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public SavedPositions getById(final String ArticleID){
                return  myDatabase.getMyDao().getById(ArticleID);
    }

    /*public LiveData<List<ContactsContract.CommonDataKinds.Note>> getTasks() {
        return myDatabase.getMyDao().fetchAllTasks();
    }*/
}
