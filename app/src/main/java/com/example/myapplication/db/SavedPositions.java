package com.example.myapplication.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class SavedPositions {

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

}