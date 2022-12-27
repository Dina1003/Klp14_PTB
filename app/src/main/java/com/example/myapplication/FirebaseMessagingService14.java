package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService14 extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "Text Channel";
    private static final String TAG = "Service";
    private NotificationManagerCompat notificationManager;


    @Override
    public void onNewToken(@NonNull String token){
        Log.d(TAG,"refresh Token"+ token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Check if message contains a data payload
        displayNotification(remoteMessage.getNotification());



    }

    private void displayNotification(RemoteMessage.Notification notification) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notifikasi";
            String description = "Jadwal seminar telah keluar bestiee";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription("Anda mencoba mengubah password");
            NotificationManagerCompat.from(this).createNotificationChannel(channel);

        }

        Intent resultIntent = new Intent(this, Semhas_Awal.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = null;
        builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifikasi)
                .setContentTitle("Notifikasi")
                .setContentText("Jadwal seminar telah keluar bestiee")
                .addAction(R.drawable.ic_notifikasi, "Selengkapnya", resultPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat.from(this).notify(112,builder.build());
        
    }

}
