package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeScreenActivity extends AppCompatActivity {

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

    public void logbook(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, logbookList.class);
        startActivity(intent);
    }
}