package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.adapter.AgendaAdapter1;
import com.example.myapplication.models.Agenda1;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity implements AgendaAdapter1.itemAgenda1ClickListener{

    private RecyclerView rvAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        rvAgenda = findViewById(R.id.rvlogbook);

        AgendaAdapter1 adapter1 = new AgendaAdapter1(getAgenda1());
        adapter1.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvAgenda.setLayoutManager(layoutManager);
        rvAgenda.setAdapter(adapter1);

    }

    public ArrayList<Agenda1> getAgenda1(){
        ArrayList<Agenda1> listAgenda = new ArrayList<>();

        listAgenda.add(new Agenda1(
                "Selasa/12-10-2023",
                "Bimbingan Bab 2"
        ));
        listAgenda.add(new Agenda1(
                "Rabu/20-10-2023",
                "Bimbingan Revisi Bab 2"
        ));
        listAgenda.add(new Agenda1(
                "Kamis/25-10-2023",
                "Bimbingan Bab 3"
        ));
        listAgenda.add(new Agenda1(
                "Jum'at/30-10-2023",
                "Bimbingan Revisi Bab 3"
        ));
        listAgenda.add(new Agenda1(
                "Senin/10-11-2023",
                "Bimbingan Revisi 2 Bab 3"
        ));

        return listAgenda;
    }

    public void profil(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void out(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void semhas(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, Semhas_Awal.class);
        startActivity(intent);
    }

    public void sidang(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, Sidang_Awal.class);
        startActivity(intent);
    }

    public void detail(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, Logbook_Detail.class);
        startActivity(intent);
    }

    public void add(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, Logbook_Add.class);
        startActivity(intent);
    }



    @Override
    public void onItemAgenda1Click(Agenda1 agenda1) {
        Intent detailIntent = new Intent(this, Logbook_Detail.class);
//        detailIntent.putExtra("tanggal", agenda1.getTanggal());
        startActivity(detailIntent);
    }
}