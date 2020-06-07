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

    // Четыре метода выполняющие операции вставки, удаления, обновления и чтения записей
    @Insert
    void addPosition(SavedPositions person);

    @Delete
    void removePosition(SavedPositions person);

    @Update
    void updatePosition(SavedPositions person);

    @Query("select * from SavedPositions")
    List<SavedPositions> getPositions();

    @Query("SELECT * FROM SavedPositions WHERE id = :ArticleID")
    SavedPositions getById(String ArticleID);

}
