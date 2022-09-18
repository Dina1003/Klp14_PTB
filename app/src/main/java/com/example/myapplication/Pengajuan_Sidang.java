package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pengajuan_Sidang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_sidang);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Pengajuan_Sidang.this, Sidang_Awal.class);
        startActivity(intent);
    }

    public void ajukan(View view) {
        Intent intent = new Intent(Pengajuan_Sidang.this, PopUp_Sidang.class);
        startActivity(intent);
    }
}