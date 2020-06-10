package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.MyDao;
import com.example.myapplication.db.MyDatabase;
import com.example.myapplication.db.SavedPositions;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends AppCompatActivity implements DataAdapter.OnRecycleListener {



    MyDatabase db;
    MyDao dao;
    SavedPositions savedPositions;

    SavedPositionsRepository savedPositionsRepository = new
            SavedPositionsRepository(getApplicationContext());
    List<Anything> listOfArtilcles = new ArrayList<>();
    DataAdapter listOfArtilclesAdapter;
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public String ArticleID ;

    @Override
    public void onRecycleClick(int position) {

        ArticleID = String.valueOf(position);
        sendArticleID();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        initiateList();
        listOfArtilclesAdapter = new DataAdapter(this, listOfArtilcles, this);

        recyclerView.setAdapter(listOfArtilclesAdapter);
    }

    private void initiateList() {
        listOfArtilcles.add(new Anything("Here will be first article", String.valueOf(1)));
        listOfArtilcles.add(new Anything("Second is here ", String.valueOf(2)));
        listOfArtilcles.add(new Anything("And third here", String.valueOf(3)));
    }

    public void sendArticleID(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(  EXTRA_MESSAGE, ArticleID + "kjd");
        startActivity(intent);
    }


    public void transferData(View view) {
        sendArticleID();
    }

    @Override
    public void onBackPressed() {

        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "db")
                .allowMainThreadQueries().build();
        dao = db.getMyDao();
        savedPositions = dao.getById(ArticleID);
    }




}
