package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UbahEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_email);
    }

    public void pindah(View view) {
        Intent intent = new Intent(UbahEmailActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(UbahEmailActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}