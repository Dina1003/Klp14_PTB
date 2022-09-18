package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetailSemHas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sem_has);
    }

    public void back(View view) {
        Intent intent = new Intent(DetailSemHas.this, Semhas_Awal.class);
        startActivity(intent);
    }

    public void kembali(View view) {
        Intent intent = new Intent(DetailSemHas.this, Semhas_Awal.class);
        startActivity(intent);
    }

    public void peserta(View view) {
        Intent intent = new Intent(DetailSemHas.this, Peserta.class);
        startActivity(intent);
    }
}