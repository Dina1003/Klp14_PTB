package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class home_kosong extends AppCompatActivity {

//    private static final String TAG = "Token Firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_kosong);

        //notifikasi firebase
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//
//                        // Log and toast
//
//                        Log.d(TAG, token);
//                        Toast.makeText(home_kosong.this, token, Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    public void out(View view) {
        Intent intent = new Intent(home_kosong.this, LoginActivity.class);
        startActivity(intent);
    }

    public void daftar(View view) {
        Intent intent = new Intent(home_kosong.this, FormPendaftaranTActivity.class);
        startActivity(intent);
    }

    public void profil(View view) {
        Intent intent = new Intent(home_kosong.this, ProfileActivity.class);
        startActivity(intent);
    }
}