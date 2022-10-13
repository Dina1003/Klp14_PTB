package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FormPendaftaranTActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pendaftaran_tactivity);
    }


    public void daftar(View view) {
        Intent intent = new Intent(FormPendaftaranTActivity.this, HomeScreenActivity.class);
        startActivity(intent);
    }
}