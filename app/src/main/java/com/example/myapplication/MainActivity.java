package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.animation.AnimatorInflater;
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

public class MainActivity extends AppCompatActivity {


    MyDatabase db;
    MyDao dao;
    SavedPositions savedPositions;
     static int position = 0;
     static  int opened_article = 1;
    final String ID = "1";
    DataAdapter dataAdapter;
    List<Anything> anythings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "db")
                    .allowMainThreadQueries().build();
            dao = db.getMyDao();

            savedPositions = new SavedPositions(ID, position);try {
                dao.addPosition(savedPositions);
            }
            catch (Exception ex){
            }

        setInitialData();
        final RecyclerView recyclerView = findViewById(R.id.rv);
        dataAdapter = new DataAdapter(this, anythings);
        recyclerView.setAdapter(dataAdapter);
        restorePosition();
    }

    private void restorePosition() {
        try {
            int a = dao.getPositions().get(0).getPosition();
            RecyclerView rv = findViewById(R.id.rv);
            rv.scrollToPosition(a);
        }
        catch (Exception ex)
        {}
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



        RecyclerView recyclerView = findViewById(R.id.rv);
        position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        savedPositions = new SavedPositions(ID, position);
        dao.updatePosition(savedPositions);

            savedPositions = new SavedPositions("last_saved", opened_article); // Saving opened article
            try{
                dao.addPosition(savedPositions);
            }
            catch (Exception ex) {
                dao.updatePosition(savedPositions);
            }
    }

    public void makeToast(String string){

        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

}