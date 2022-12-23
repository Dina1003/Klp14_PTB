package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.datamodel.ProfilResponse;
import com.example.myapplication.retrofit.StoryClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    TextView name, nim2;
    ImageView profile;
    String token;


    private static final String TAG = "ProfileActivity-Debug";
    private static final String CHANNEL_ID = "Text Channel";
    private ImageButton gantipass;
    private NotificationManagerCompat notificationManager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN","");

        getProfile();

//        profile = findViewById (R.id.profil);

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


                NotificationCompat.Builder builder = new NotificationCompat.Builder(ProfileActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notifikasi)
                        .setContentTitle("Perubahan Password")
                        .setContentText("tindakn Perubahan Password, apakah itu anda? tekan  untuk melanjutkan")
                        .addAction(R.drawable.ic_notifikasi, "Selengkapnya", resultPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                notificationManager.notify(101, builder.build());
            }
        });
    }

    private void getProfile() {
        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);


        Call<ProfilResponse> call = client.profill("Bearer " + token );
        call.enqueue(new Callback<ProfilResponse>() {
            @Override
            public void onResponse(Call<ProfilResponse> call, Response<ProfilResponse> response) {
                if (!response.isSuccessful()) {
                    name.setText("Nama Mahasiswa");
                    nim2.setText("Nim Mahasiswa");
                } else {
                    ProfilResponse user = response.body();

//                    Log.d("YYPROFILEACTIVITY", "Hasilnya: " + response.body().getEmail());
                    String nama = user.getName();
                    String nim = user.getUsername();
                    String email = user.getEmail();

                    TextView castName = findViewById(R.id.name);
                    TextView castNim = findViewById(R.id.nim2);
                    TextView castEmail = findViewById(R.id.email);

                    castName.setText(nama);
                    castNim.setText(nim);
                    castEmail.setText(email);

                    ImageView imageView = (ImageView) findViewById(R.id.profict);

                    Glide.with(ProfileActivity.this).load(user.getProfilePhotoPath()).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<ProfilResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cekprofile() {
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

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