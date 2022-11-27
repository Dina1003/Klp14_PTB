package com.example.myapplication;

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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DetailSemHas extends AppCompatActivity {
    //notifikasi
    //1. deklarasi objek
    private static final String CHANNEL_ID= "Kelompok 14";

    private Button buttonShow;
    private NotificationManagerCompat notificationManager;
    String coba;
    String namaAgenda;
    TextView text_jadwal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sem_has);

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            namaAgenda = detailIntent.getStringExtra("Jadwal");
        }

        text_jadwal = findViewById(R.id.text_jadwal);
        text_jadwal.setText(namaAgenda);

        //2. panggil objek
        buttonShow = findViewById(R.id.notifikasi);
        notificationManager = NotificationManagerCompat.from(this);
        //creat channel
        createNotificationChannel();

        //3. buat onclick listenernya
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inten notifikasi ke activity yang diinginkan
                Intent resultIntent = new Intent(DetailSemHas.this, DetailSemHas.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(DetailSemHas.this);
                stackBuilder.addNextIntentWithParentStack(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0,
                                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                //5. buat builder varnya

                NotificationCompat.Builder builder = new NotificationCompat.Builder(DetailSemHas.this, CHANNEL_ID)
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

    //4. buat dulu channel idnya
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Contoh Notifikasi";
            String description = "ini contoh notifikasi manual :v";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void back(View view) {
        Intent intent = new Intent(DetailSemHas.this, Semhas_Awal.class);
        startActivity(intent);
    }

    public void kembali(View view) {
        Intent intent = new Intent(DetailSemHas.this, Semhas_Awal.class);
        startActivity(intent);
    }

    public void peserta(View view) {
        Intent intent = new Intent(DetailSemHas.this, Peserta.class);
        startActivity(intent);
    }


}