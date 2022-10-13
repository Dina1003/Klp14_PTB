package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UbahNoTlpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_no_tlp);
    }

    public void back(View view) {
        Intent intent = new Intent(UbahNoTlpActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(UbahNoTlpActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}