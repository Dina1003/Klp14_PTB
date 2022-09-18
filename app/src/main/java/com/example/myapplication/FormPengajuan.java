package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class FormPengajuan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengajuan);
    }

    public void pindah(View view) {
        Intent intent = new Intent(FormPengajuan.this, MainActivity.class);
        startActivity(intent);
    }

    public void ajukan(View view) {
        Intent intent = new Intent(FormPengajuan.this, PopUp.class);
        startActivity(intent);
    }
}