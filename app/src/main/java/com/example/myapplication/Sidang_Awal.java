package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.adapter.SidangAdapter;
import com.example.myapplication.models.Sidang;

import java.util.ArrayList;

public class Sidang_Awal extends AppCompatActivity implements SidangAdapter.ItemSidangCLickListener{

    private RecyclerView rvSidang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidang_awal);

        rvSidang = findViewById(R.id.rvSidang);

        SidangAdapter adapter = new SidangAdapter(getSidang());
        adapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvSidang.setLayoutManager(layoutManager);
        rvSidang.setAdapter(adapter);
    }
    public ArrayList<Sidang> getSidang(){
        ArrayList<Sidang> listSidang = new ArrayList<>();

        listSidang.add(new Sidang(
           "Jum'at/ 01-08-2023"
        ));

        listSidang.add(new Sidang(
                "Kamis/ 10-08-2023"
        ));
        listSidang.add(new Sidang(
                "Selasa/ 20-08-2023"
        ));
        listSidang.add(new Sidang(
                "Senin/ 25-08-2023"
        ));
        listSidang.add(new Sidang(
                "Rabu/ 01-09-2023"
        ));
        listSidang.add(new Sidang(
                "Kamis/ 10-09-2023"
        ));

        listSidang.add(new Sidang(
                "Selasa/ 25-09-2023"
        ));

        listSidang.add(new Sidang(
                "Rabu/ 31-09-2023"
        ));
        listSidang.add(new Sidang(
                "Rabu/ 01-10-2023"
        ));
        listSidang.add(new Sidang(
                "Kamis/ 10-10-2023"
        ));
        listSidang.add(new Sidang(
                "Jum'at/ 21-10-2023"
        ));



        return listSidang;
    }


    public void form(View view) {
        Intent intent = new Intent(Sidang_Awal.this, Pengajuan_Sidang.class);
        startActivity(intent);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Sidang_Awal.this, DetailSidang.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(Sidang_Awal.this, HomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSidangClickListener(Sidang sidang) {
        Intent intent = new Intent(this, DetailSidang.class);
        intent.putExtra("waktu", sidang.getWaktu());
        startActivity(intent);
    }
}