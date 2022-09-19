package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetailPeserta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peserta);
    }

    public void pindah(View view) {
        Intent intent = new Intent(DetailPeserta.this, Peserta.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(DetailPeserta.this, Semhas_Awal.class);
        startActivity(intent);
    }

    public void input(View view) {
        Intent intent = new Intent(DetailPeserta.this, InputPeserta.class);
        startActivity(intent);
    }
}