package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

                "12 Oktober 2022",
                "Bimbingan 1"

        ));
        listLogbook.add(new logbook(

                "13 Oktober 2022",
                "Bimbingan 2"

        ));
        listLogbook.add(new logbook(

                "14 Oktober 2022",
                "Bimbingan 3"

        ));
        listLogbook.add(new logbook(

                "15 Oktober 2022",
                "Bimbingan 4"

        ));
        listLogbook.add(new logbook(

                "16 Oktober 2022",
                "Bimbingan terakhir"

        ));
        listLogbook.add(new logbook(

                "17 Oktober 2022",
                "Bimbingan terakhir tp boong"

        ));
        listLogbook.add(new logbook(

                "18 Oktober 2022",
                "Bimbingan terakhir gakdeng"

        ));
        listLogbook.add(new logbook(

                "19 Oktober 2022",
                "Bimbingan terakhir beneran"

        ));
        return listLogbook;
    }

    @Override
    public void onItemLogbookClick(logbook logbook) {
        Intent detailIntent = new Intent(this, Logbook_Detail.class);
//        detailIntent.putExtra("tanggal", agenda1.getTanggal());
        startActivity(detailIntent);

    }
}