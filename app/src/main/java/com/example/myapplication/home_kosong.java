package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home_kosong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_kosong);
    }

    public void out(View view) {
        Intent intent = new Intent(home_kosong.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void daftar(View view) {
        Intent intent = new Intent(home_kosong.this, FormPendaftaranTActivity.class);
        startActivity(intent);
    }
}