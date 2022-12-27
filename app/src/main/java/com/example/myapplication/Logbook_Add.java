package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class Logbook_Add extends AppCompatActivity {

    //notifikasi
    //1. deklarasi objek
    private static final String CHANNEL_ID= "Text Channel";

    private Button add;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_add);

        // 1. Ambil notification manager
        notificationManager = NotificationManagerCompat.from(this);


        //2. panggil objek
        add = findViewById(R.id.add);

        //creat channel
        createNotificationChannel();

        //3. buat onclick listenernya
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inten notifikasi ke activity yang diinginkan
                Intent resultIntent = new Intent(Logbook_Add.this, logbookList.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(Logbook_Add.this);
                stackBuilder.addNextIntentWithParentStack(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0,
                                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                //5. buat builder varnya
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Logbook_Add.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_book)
                        .setContentTitle("WAW LOGBOOK MU BERTAMBAH")
                            .setContentText("Selamat Berprogress ya(!) Yuk lihat sudah sejauh mana perjalananmu")
                        .addAction(R.drawable.ic_book, "Lihat List", resultPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                //6.buat objek notifikasi
             //   Notification notification = builder.build();

                //7.tampilkan notifikasi
                notificationManager.notify(102, builder.build());

//                Intent intent = new Intent(Logbook_Add.this, HomeScreenActivity.class);
//                startActivity(intent);
            }

        });



    }

    //4. buat dulu channel idnya
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Contoh Notifikasi";
            String description = "ini contoh notifikasi manual";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void add(View view) {
        Intent intent = new Intent(Logbook_Add.this, HomeScreenActivity.class);
        startActivity(intent);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Logbook_Add.this, HomeScreenActivity.class);
        startActivity(intent);
    }
}