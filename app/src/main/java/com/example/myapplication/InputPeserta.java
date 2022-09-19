package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InputPeserta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_peserta);
    }

    public void pindah(View view) {
        Intent intent = new Intent(InputPeserta.this, Peserta.class);
        startActivity(intent);
    }

    public void Input(View view) {
        Intent intent = new Intent(InputPeserta.this, Peserta.class);
        startActivity(intent);
    }
}