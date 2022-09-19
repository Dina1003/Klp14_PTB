package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class Semhas_Awal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semhas_awal);


    }

    public void form(View view) {
        Intent intent = new Intent(Semhas_Awal.this, FormPengajuan.class);
        startActivity(intent);
    }


    public void pindah(View view) {
        Intent intent = new Intent(Semhas_Awal.this, DetailSemHas.class);
        startActivity(intent);
    }

    public void sidang(View view) {
        Intent intent = new Intent(Semhas_Awal.this, Sidang_Awal.class);
        startActivity(intent);
    }


}
