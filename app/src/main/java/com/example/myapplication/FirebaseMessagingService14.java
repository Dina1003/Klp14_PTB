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
    private NotificationManagerCompat notificationManager;

    public FirebaseMessagingService14() {
    }

    @Override
    public void onNewToken(@NonNull String token){
        Log.d("newtoken", "refresh Token"+ token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        displayNotification(remoteMessage.getNotification());

    }

    private void displayNotification(RemoteMessage.Notification notification) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Permintaan ganti password", importance);
            channel.setDescription("Anda mencoba mengubah password");
            notificationManager.createNotificationChannel(channel);

        }

        Intent resultIntent = new Intent(this, EditProfileActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifikasi)
                .setContentTitle("Perubahan Password")
                .setContentText("tindakan Perubahan Password, apakah itu anda? tekan  untuk melanjutkan")
                .addAction(R.drawable.ic_notifikasi, "Selengkapnya", resultPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(101,builder.build());
    }

}
