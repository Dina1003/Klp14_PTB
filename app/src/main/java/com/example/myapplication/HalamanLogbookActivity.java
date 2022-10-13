package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.LogbookAdapter;
import com.example.myapplication.models.Logbook;

import java.util.ArrayList;



public class HalamanLogbookActivity extends AppCompatActivity{

   private RecyclerView rvlogbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_logbook);

        rvlogbook =findViewById(R.id.rvlogbook);
        LogbookAdapter adapter = new LogbookAdapter(getLogbook());
        LinearLayoutManager layoutManager = new LinearLayoutManager((this));

        rvlogbook.setLayoutManager(layoutManager);
        rvlogbook.setAdapter(adapter);

    }

    public ArrayList<Logbook>getLogbook(){

        ArrayList<Logbook>listLogbook = new ArrayList<>();
        listLogbook.add(new Logbook(
                "selasa, 15 maret 2023",
                1
        ));
        listLogbook.add(new Logbook(
                "kamis, 12 April 2023",
                1

        ));
        return listLogbook;
    }

    public void home(View view) {
        Intent intent = new Intent(HalamanLogbookActivity.this, HomeScreenActivity.class);
        startActivity(intent);
    }

}