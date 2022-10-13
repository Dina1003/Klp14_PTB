package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Logbook_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_detail);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Logbook_Detail.this, HomeScreenActivity.class);
        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(Logbook_Detail.this, Logbook_edit.class);
        startActivity(intent);
    }
}