package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void home(View view) {
        Intent intent = new Intent(ProfileActivity.this, HomeScreenActivity.class);
        startActivity(intent);
    }

    public void edit_nohp(View view) {
        Intent intent = new Intent(ProfileActivity.this, UbahNoTlpActivity.class);
        startActivity(intent);
    }

    public void ubah_email(View view) {
        Intent intent = new Intent(ProfileActivity.this, UbahEmailActivity.class);
        startActivity(intent);
    }

    public void pass(View view) {
        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(intent);
    }
}