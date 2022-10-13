package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapter.logbookAdapter;
import com.example.myapplication.models.logbook;

import java.util.ArrayList;

public class logbookList extends AppCompatActivity implements logbookAdapter.itemLogbookClickListener{

    private RecyclerView rvLogbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        rvLogbook = findViewById(R.id.rv_logbook);

        logbookAdapter adapter = new logbookAdapter(getlogbook());
        adapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvLogbook.setLayoutManager(layoutManager);
        rvLogbook.setAdapter(adapter);
    }
    public ArrayList<logbook> getlogbook(){
        ArrayList<logbook> listLogbook = new ArrayList<>();
        listLogbook.add(new logbook(
                null,
                "12 Oktober 2022",
                "Bimbingan 1",
                1
        ));
        listLogbook.add(new logbook(
                null,
                "13 Oktober 2022",
                "Bimbingan 2",
                1
        ));
        listLogbook.add(new logbook(
                null,
                "14 Oktober 2022",
                "Bimbingan 3",
                1
        ));
        listLogbook.add(new logbook(
                null,
                "15 Oktober 2022",
                "Bimbingan 4",
                1
        ));
        listLogbook.add(new logbook(
                null,
                "16 Oktober 2022",
                "Bimbingan terakhir",
                1
        ));
        listLogbook.add(new logbook(
                null,
                "17 Oktober 2022",
                "Bimbingan terakhir tp boong",
                1
        ));
        listLogbook.add(new logbook(
                null,
                "18 Oktober 2022",
                "Bimbingan terakhir gakdeng",
                1
        ));
        listLogbook.add(new logbook(
                null,
                "19 Oktober 2022",
                "Bimbingan terakhir beneran",
                1
        ));
        return listLogbook;
    }

    @Override
    public void onItemLogbookClick(logbook logbook) {

    }
}