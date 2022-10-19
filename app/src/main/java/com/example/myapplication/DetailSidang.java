package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailSidang extends AppCompatActivity {

    String jadwal;
    TextView waktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sidang);

        Intent intent = getIntent();
        if(intent != null){
            jadwal = intent.getStringExtra("waktu");
        }

        waktu = findViewById(R.id.text_jadwal);
        waktu.setText(jadwal);

    }


    public void back(View view) {
        Intent intent = new Intent(DetailSidang.this, Sidang_Awal.class);
        startActivity(intent);
    }

    public void kembali(View view) {
        Intent intent = new Intent(DetailSidang.this, Sidang_Awal.class);
        startActivity(intent);
    }
}