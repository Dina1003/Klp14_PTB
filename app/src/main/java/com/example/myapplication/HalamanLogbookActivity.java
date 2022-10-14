package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.logbookAdapter;
import com.example.myapplication.models.logbook;

import java.util.ArrayList;



public class HalamanLogbookActivity extends AppCompatActivity{

   private RecyclerView rvlogbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_logbook);

        rvlogbook =findViewById(R.id.rvlogbook);
        logbookAdapter adapter = new logbookAdapter(getLogbook());
        LinearLayoutManager layoutManager = new LinearLayoutManager((this));

        rvlogbook.setLayoutManager(layoutManager);
        rvlogbook.setAdapter(adapter);

    }

    public ArrayList<logbook>getLogbook(){

        ArrayList<logbook>listLogbook = new ArrayList<>();
        listLogbook.add(new logbook(
                "selasa, 15 maret 2023",
                "Bimbingan Bab 2"
        ));
        listLogbook.add(new logbook(
                "kamis, 12 April 2023",
                "Bimbingan Revisi BAB 2 dan BAB 3"

        ));
        listLogbook.add(new logbook(
                "kamis, 12 April 2023",
                "Bimbingan Revisi BAB 2 dan BAB 3"

        ));
        listLogbook.add(new logbook(
                "kamis, 12 April 2023",
                "Bimbingan Revisi BAB 2 dan BAB 3"

        ));
        return listLogbook;
    }

    public void home(View view) {
        Intent intent = new Intent(HalamanLogbookActivity.this, HomeScreenActivity.class);
        startActivity(intent);
    }

}