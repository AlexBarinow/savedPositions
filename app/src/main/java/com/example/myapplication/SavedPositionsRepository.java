package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.myapplication.db.MyDatabase;
import com.example.myapplication.db.SavedPositions;

import java.util.List;

public class SavedPositionsRepository {


    private String DB_NAME = "db";

    private MyDatabase myDatabase;
    public SavedPositionsRepository(Context context) {
        myDatabase = Room.databaseBuilder(context, MyDatabase.class, DB_NAME).build();
    }

    /*public void insertTask(String title,
                            String description) {

        insertTask(title, description, false, null);
    }*/

    public void insertPosition(String id,
                               int  position) {

        SavedPositions savedPositions = new SavedPositions(id, position);
        savedPositions.setId(id);
        savedPositions.setPosition(position);

        insertPosition(savedPositions);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertPosition(final SavedPositions savedPositions) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                myDatabase.getMyDao().addPosition(savedPositions);
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

    @SuppressLint("StaticFieldLeak")
    public void delTask(final int id) {
        final LiveData<SavedPositions> task = getTask(id);
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    myDatabase.getMyDao().removePosition(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

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

    public LiveData<SavedPositions> getTask(int id) {
        return myDatabase.getMyDao().getTask(id);
    }

    /*public LiveData<List<ContactsContract.CommonDataKinds.Note>> getTasks() {
        return myDatabase.getMyDao().fetchAllTasks();
    }*/
}
