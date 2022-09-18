package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sidang_Awal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidang_awal);
    }

    public void form(View view) {
        Intent intent = new Intent(Sidang_Awal.this, Pengajuan_Sidang.class);
        startActivity(intent);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Sidang_Awal.this, DetailSidang.class);
        startActivity(intent);
    }

    public void semhaskembali(View view) {
        Intent intent = new Intent(Sidang_Awal.this, Semhas_Awal.class);
        startActivity(intent);
    }
}