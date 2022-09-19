package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Peserta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Peserta.this, DetailSemHas.class);
        startActivity(intent);
    }

    public void input(View view) {
        Intent intent = new Intent(Peserta.this, InputPeserta.class);
        startActivity(intent);
    }

    public void deatil(View view) {
        Intent intent = new Intent(Peserta.this, DetailPeserta.class);
        startActivity(intent);
    }
}