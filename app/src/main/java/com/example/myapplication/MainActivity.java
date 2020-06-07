package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.db.SavedPositions;
import com.example.myapplication.db.MyDao;
import com.example.myapplication.db.MyDatabase;
import com.example.myapplication.DataAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnRecycleListener {


    MyDatabase db;
    MyDao dao;
    static SavedPositions savedPositions;
    int position = 0;
    public String ArticleID;
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    DataAdapter dataAdapter;
    List<Anything> anythings = new ArrayList<>();


    @Override
    public void onRecycleClick(int position) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        ArticleID = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);


        databaseInitiation();
        setInitialData();

        final RecyclerView recyclerView = findViewById(R.id.rv);
        dataAdapter = new DataAdapter(this, anythings, this);
        recyclerView.setAdapter(dataAdapter);

        restorePosition();

    }

    private void databaseInitiation() {
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "db")
                .allowMainThreadQueries().build();
        dao = db.getMyDao();

        savedPositions = new SavedPositions(ArticleID, position);
        try {
            dao.addPosition(savedPositions);
        }

        catch (Exception ex){
        }
    }

    private void restorePosition() {
        try {
            RecyclerView rv = findViewById(R.id.rv);
            rv.scrollToPosition(dao.getById(ArticleID).getPosition());
        }
        catch (Exception ex){}
    }

    private void setInitialData(){

        for(int i = 0;i<=300;i++) {

            String a = String.valueOf(i);
            anythings.add(new Anything("Hello, friend? That's lame. Maybe I should give you a name. But thats a slippery slope. Youre only in my head. We have to remember that. Shit. Its actually happened. Im talking to an imaginary person. What Im about to tell you is top secret. A conspiracy bigger than all of us.", a));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        save();
    }

    private void save() {
        RecyclerView recyclerView = findViewById(R.id.rv);
        position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();


        savedPositions = new SavedPositions(ArticleID, position);
        dao.updatePosition(savedPositions);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
    }
}