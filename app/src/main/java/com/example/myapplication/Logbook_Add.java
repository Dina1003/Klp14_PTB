package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Logbook_Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_add);
    }

    public void add(View view) {
        Intent intent = new Intent(Logbook_Add.this, HomeScreenActivity.class);
        startActivity(intent);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Logbook_Add.this, HomeScreenActivity.class);
        startActivity(intent);
    }
}