package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.db.SavedPositions;
import com.example.myapplication.db.MyDao;
import com.example.myapplication.db.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnRecycleListener {


    MyDatabase db;
    MyDao dao;
     SavedPositions savedPositions;
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


       // databaseInitiation();
        setInitialData();

        final RecyclerView recyclerView = findViewById(R.id.rv);
        dataAdapter = new DataAdapter(this, anythings, this);
        recyclerView.setAdapter(dataAdapter);

        //restorePosition();



        SavedPositionsRepository savedPositionsRepository =
                new SavedPositionsRepository(getApplicationContext());



        SavedPositions savedPositions1 = new SavedPositions(ArticleID, 555);

        int a = 0;


        for (int i =0; i <300;i++){
            savedPositions1 = new SavedPositions("test", i);

        savedPositionsRepository.insertPosition(savedPositions1);}



        int ab  = -1;



        savedPositions = savedPositionsRepository.getPosition("test").getValue();
        //ab = savedPositions.getPosition();

        makeToast(String.valueOf(ab));




    }

    private void databaseInitiation() {

        /*
        SavedPositionsRepository savedPositionsRepository = new
                SavedPositionsRepository(getApplicationContext());

        savedPositions = new SavedPositions(ArticleID, position);
            savedPositionsRepository.insertPosition(savedPositions);



            savedPositions = savedPositionsRepository.getTask(ArticleID).getValue();

            String a = String.valueOf(savedPositions.getPosition());
            Toast.makeText(this, a, Toast.LENGTH_LONG);*/

    }

    private void restorePosition() {
        try {
            SavedPositionsRepository savedPositionsRepository = new
                    SavedPositionsRepository(getApplicationContext());
            RecyclerView rv = findViewById(R.id.rv);

            //savedPositions = savedPositionsRepository.getTask(ArticleID).getValue();
            //Toast.makeText(this, String.valueOf(savedPositions.getPosition()), Toast.LENGTH_SHORT);
          //  rv.scrollToPosition(/*dao.getById(ArticleID).getPosition()*/ savedPositionsRepository.getTask(ArticleID).getValue().getPosition());   // here async
        }
        catch (Exception ex){

            Toast.makeText(this, "Err in restorePosition" + ex.getMessage(), Toast.LENGTH_LONG).show();

        }
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
        try{
            save();

        }
        catch (Exception ex){

            Toast.makeText(this, "Err in onSaveInstanceState" + ex.getMessage(), Toast.LENGTH_LONG).show();

        }


    }

    private void save() {
        RecyclerView recyclerView = findViewById(R.id.rv);
        position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();


        savedPositions = new SavedPositions(ArticleID, position);
        //dao.updatePosition(savedPositions);
        SavedPositionsRepository savedPositionsRepository = new
                SavedPositionsRepository(getApplicationContext());
        savedPositionsRepository.updPosition(savedPositions);




    }

    public void makeToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            save();

        }
        catch (Exception ex){

            Toast.makeText(this, "Err in save" + ex.getMessage(), Toast.LENGTH_LONG).show();

        }

    }
}