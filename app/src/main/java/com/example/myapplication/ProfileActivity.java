package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity-Debug";
    private static final String CHANNEL_ID = "Text Channel";
    private ImageButton gantipass;
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d(TAG, token);
                        Toast.makeText(ProfileActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        notificationManager = NotificationManagerCompat.from(this);

        createNotificationChannel();

        gantipass = findViewById(R.id.gantipass);
        gantipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent resultIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(ProfileActivity.this);
                stackBuilder.addNextIntentWithParentStack(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0,
                                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(ProfileActivity.this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notifikasi)
                        .setContentTitle("Perubahan Password")
                        .setContentText("tindakn Perubahan Password, apakah itu anda? tekan  untuk melanjutkan")
                        .addAction(R.drawable.ic_notifikasi, "Selengkapnya", resultPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                notificationManager.notify(101,builder.build());
            }
        });
    }
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Permintaan ganti password", importance);
            channel.setDescription("Anda mencoba mengubah password");
            notificationManager.createNotificationChannel(channel);

        }
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