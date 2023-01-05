package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.PengujiAdapter;
import com.example.myapplication.adapter.SidangAdapter;
import com.example.myapplication.datamodel.AmbilSemhasResponse;
import com.example.myapplication.datamodel.AmbilSidangResponse;
import com.example.myapplication.datamodel.ExaminersItem;
import com.example.myapplication.datamodel.LogbooksItem;
import com.example.myapplication.datamodel.PembuktianRVResponse;
import com.example.myapplication.retrofit.StoryClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailSidang extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    TextView text_jadwal, file_report, file_slide, file_journal, startend_at, room_id, online_url, score, grade;
    String gettoken, token;
    private RecyclerView rvSidang;
    private PengujiAdapter adapter;

    SharedPrefManager sharedPrefManager;


    //notifikasi
    private static final String CHANNEL_ID= "Kelompok 14";
    private Button buttonShow;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sidang);

        SharedPreferences sharedPref = getSharedPreferences("prefs", MODE_PRIVATE);
        token = sharedPref.getString("TOKEN","");
        getDetailSidang();
        datarv();

        sharedPrefManager = new SharedPrefManager(this);


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

    public void datarv(){

        rvSidang = findViewById(R.id.rvpenguji);
        rvSidang.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PengujiAdapter();
        rvSidang.setAdapter(adapter);

        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<AmbilSidangResponse> call = client.sidang("Bearer " + token);
        call.enqueue(new Callback<AmbilSidangResponse>() {
            @Override
            public void onResponse(Call<AmbilSidangResponse> call, Response<AmbilSidangResponse> response) {
                Log.d("ListLogbook-Debug", response.toString());
                AmbilSidangResponse getLogbookResponse = response.body();
                if(getLogbookResponse != null){
                    List<ExaminersItem> logbooks = getLogbookResponse.getExaminers();
                    Log.d("ListLogbook-Debug", String.valueOf(logbooks.size()));
                    adapter.setItemList(logbooks);
                }
            }

            @Override
            public void onFailure(Call<AmbilSidangResponse> call, Throwable t) {

            }
        });

    }

    public void getDetailSidang(){
        text_jadwal = findViewById(R.id.text_jadwal);
        file_report = findViewById(R.id.file_report);
        file_slide = findViewById(R.id.file_slide);
        file_journal = findViewById(R.id.file_journal);
        startend_at = findViewById(R.id.startend_at);
        room_id = findViewById(R.id.room_id);
        online_url = findViewById(R.id.online_url);
        score = findViewById(R.id.score);
        grade = findViewById(R.id.grade);


        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);

        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<AmbilSidangResponse> call = client.sidang("Bearer "+token);
        call.enqueue(new Callback<AmbilSidangResponse>() {
            @Override
            public void onResponse(Call<AmbilSidangResponse> call, Response<AmbilSidangResponse> response) {
                if(response.isSuccessful()){
                    if(!response.isSuccessful()){
                        text_jadwal.setText("04 Januari 2023");
                        return;
                    } else {
                        AmbilSidangResponse ambilSemhasResponse = response.body();

                        String tanggal = ambilSemhasResponse.getRegisteredAt();
                        String fileReport = (String) ambilSemhasResponse.getFileReport();
                        String fileSlide = (String) ambilSemhasResponse.getFileSlide();
                        String fileJurnal = (String) ambilSemhasResponse.getFileJournal();
                        String waktu = ambilSemhasResponse.getStartAt();
                        String waktu1 = ambilSemhasResponse.getEndAt();
                        String ruang = (String) ambilSemhasResponse.getRoomId();
                        String url = (String) ambilSemhasResponse.getOnlineUrl();
                        String skor = (String) ambilSemhasResponse.getScore();
                        String nilai = ambilSemhasResponse.getGrade();

                        text_jadwal.setText("Date: "+tanggal);
                        file_report.setText("File Report: "+String.valueOf(fileReport));
                        file_slide.setText("File Slide: "+String.valueOf(fileSlide));
                        file_journal.setText("File Jurnal: "+String.valueOf(fileJurnal));
                        startend_at.setText("Start "+waktu+" End"+waktu1);
                        room_id.setText("Room: "+String.valueOf(ruang));
                        online_url.setText("URL: "+String.valueOf(url));
                        score.setText("Score: "+String.valueOf(skor));
                        grade.setText("Nilai: "+nilai);

                    }
                }
            }

            @Override
            public void onFailure(Call<AmbilSidangResponse> call, Throwable t) {
                Toast.makeText(DetailSidang.this, "ERROR", Toast.LENGTH_SHORT).show();

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