package com.example.myapplication.db;

import android.app.usage.UsageEvents;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {


    @Insert
    void addPosition(SavedPositions position);

    @Delete
    void removePosition(SavedPositions position);

    @Update
    void updatePosition(SavedPositions position);

    @Query("SELECT * FROM SavedPositions WHERE id =:id")
    LiveData<SavedPositions> getTask(int id);

    @Query("select * from SavedPositions")
    List<SavedPositions> getPositions();

    @Query("SELECT * FROM SavedPositions WHERE id = :ArticleID")
    SavedPositions getById(String ArticleID);

}
