package com.example.myapplication.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity()
public class SavedPositions implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;

    private int position;

    public SavedPositions(String id, int position) {
        this.position = position;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}