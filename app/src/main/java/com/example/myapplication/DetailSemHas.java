package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailSemHas extends AppCompatActivity {
    String coba;
    String namaAgenda;
    TextView text_jadwal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sem_has);

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            namaAgenda = detailIntent.getStringExtra("Jadwal");
        }

        text_jadwal = findViewById(R.id.text_jadwal);
        text_jadwal.setText(namaAgenda);
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