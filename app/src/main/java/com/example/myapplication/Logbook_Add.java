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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.datamodel.TambahLogbookResponse;
import com.example.myapplication.datamodel.UbahPassword;
import com.example.myapplication.retrofit.StoryClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Logbook_Add extends AppCompatActivity {

    //variabel tambah logbook
    EditText supervisor_id, date, progress, problem;
    Button add;
    String token;

    //notifikasi manual
    //1. deklarasi objek
    private static final String CHANNEL_ID= "Text Channel";
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_add);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN", "");

        supervisor_id = findViewById(R.id.supervisor_id);
        date = findViewById(R.id.date);
        progress = findViewById(R.id.progress);
        problem = findViewById(R.id.problem);

        // 1. Ambil notification manager
        notificationManager = NotificationManagerCompat.from(this);
        //2. panggil objek
        add = findViewById(R.id.tambah_lb);
        //creat channel
        createNotificationChannel();
        //3. buat onclick listenernya
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahLogbook();

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

            }

        });



    }

    private void tambahLogbook() {
        Integer id_supervisor = Integer.valueOf(supervisor_id.getText().toString());
        String tanggal = date.getText().toString();
        String prog = progress.getText().toString();
        String prob = problem.getText().toString();

//        if (id_supervisor.isEmpty()) {
//            supervisor_id.setError("fill this field");
//            supervisor_id.requestFocus();
//            return;
//        }
        if (tanggal.isEmpty()) {
            date.setError("fill this field");
            date.requestFocus();
            return;
        }
        if (prog.isEmpty()) {
            progress.setError("fill this field");
            progress.requestFocus();
            return;
        }
        if (prob.isEmpty()) {
            problem.setError("fill this field");
            problem.requestFocus();
            return;
        }


        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);

        Call<TambahLogbookResponse> call = client.tambah_logbook("Bearer " + token, id_supervisor, tanggal, prog, prob);

        call.enqueue(new Callback<TambahLogbookResponse>() {
            @Override
            public void onResponse(Call<TambahLogbookResponse> call, Response<TambahLogbookResponse> response) {
                if (response.isSuccessful()) {
                    TambahLogbookResponse ganti = response.body();
                    if (ganti != null) {
                        Toast.makeText(Logbook_Add.this, ganti.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Logbook_Add.this, logbookList.class);
                        startActivity(intent);
                    }
                }  else {
                    Toast.makeText(Logbook_Add.this, "Gagal tambah logbook", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TambahLogbookResponse> call, Throwable t) {
                Toast.makeText(Logbook_Add.this, t.getMessage(), Toast.LENGTH_LONG).show();
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



    public void pindah(View view) {
        Intent intent = new Intent(Logbook_Add.this, HomeScreenActivity.class);
        startActivity(intent);
    }
}