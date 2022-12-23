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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

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
        Intent intent = new Intent(HomeScreenActivity.this, DetailSemHas.class);
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


    public void logbook(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, logbookList.class);
        startActivity(intent);
    }

    public void image_detaillog(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, logbookList.class);
        startActivity(intent);
    }

    public void image_detailperjalanan(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, logbookList.class);
        startActivity(intent);
    }

    public void image_detailsemhas(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, DetailSemHas.class);
        startActivity(intent);
    }

    public void detailsidang(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, DetailSidang.class);
        startActivity(intent);
    }


    @Override
    public void onItemAgenda1Click(Agenda1 agenda1) {
        Intent detailIntent = new Intent(this, Logbook_Detail.class);
//        detailIntent.putExtra("tanggal", agenda1.getTanggal());
        startActivity(detailIntent);
    }
}