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

public class DetailSidang extends AppCompatActivity {

    String jadwal;
    TextView waktu;

    //notifikasi
    private static final String CHANNEL_ID= "Kelompok 14";
    private Button buttonShow;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sidang);



        Intent intent = getIntent();
        if(intent != null){
            jadwal = intent.getStringExtra("waktu");
        }

        waktu = findViewById(R.id.text_jadwal);
        waktu.setText(jadwal);

        //notifikasi
        buttonShow = findViewById(R.id.btnNotif);
        notificationManager = NotificationManagerCompat.from(this);
        createNotificationChannel();

        buttonShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //inten notifikasi ke activity yang diinginkan
                Intent resultIntent = new Intent(DetailSidang.this, DetailSidang.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(DetailSidang.this);
                stackBuilder.addNextIntentWithParentStack(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0,
                                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(DetailSidang.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_tanggal)
                        .setContentTitle("Informasi Terbaru Nih!!")
                        .setContentText("Berikut informasi terkait SEMHAS anda. Klik Show dan lihat selengkapnya 😊")
                        .addAction(R.drawable.ic_tanggal, "Show", resultPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                //6.buat objek notifikasi
                Notification notification = builder.build();
                //7.tampilkan notifikasi
                notificationManager.notify(101, notification);
            }
        });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           CharSequence name = "Contoh Notifikasi";
            String description = "ini contoh notifikasi manual :v";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "kanal", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("contoh");
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void back(View view) {
        Intent intent = new Intent(DetailSidang.this, Sidang_Awal.class);
        startActivity(intent);
    }

    public void kembali(View view) {
        Intent intent = new Intent(DetailSidang.this, Sidang_Awal.class);
        startActivity(intent);
    }
}