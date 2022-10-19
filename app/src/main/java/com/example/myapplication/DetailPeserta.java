package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailPeserta extends AppCompatActivity {

    String peserta;
    TextView textpeserta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peserta);

        Intent intent = getIntent();
        if(intent != null){
         peserta= intent.getStringExtra("peserta");
        }

        textpeserta = findViewById(R.id.nama_peserta);
        textpeserta.setText(peserta);
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
        Intent intent = new Intent(DetailPeserta.this, Peserta.class);
        startActivity(intent);
    }
}