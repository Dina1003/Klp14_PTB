package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PopUp_Sidang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_sidang);
    }

    public void ok(View view) {
        Intent intent = new Intent(PopUp_Sidang.this, Sidang_Awal.class);
        startActivity(intent);
    }
}