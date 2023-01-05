package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.datamodel.DetailSemhasResponse;
import com.example.myapplication.retrofit.StoryClient;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailSeminarHasilActivity extends AppCompatActivity {


//    private RecyclerView rvseminar;
//
//    String SeminarAt, CreatedAt, FileReport, RegisteredAt;
////    int roomID;
//    TextView SeminarAtDetail;
//    TextView CreatedAtDetail;
//    TextView FileReportDetail;
//    TextView RegisteredAtDetail;
////    TextView roomIDDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_seminar_hasil);
//
//        Intent detailIntent = getIntent();
//        if(detailIntent != null){
////            roomID = detailIntent.getIntExtra("DATE");
//            SeminarAt = detailIntent.getStringExtra("Tempat");
//            CreatedAt = detailIntent.getStringExtra("Dibuat");
//            FileReport = detailIntent.getStringExtra("File");
//            RegisteredAt = detailIntent.getStringExtra("Terdaftar");
//
//        }
//        SeminarAtDetail = findViewById(R.id.textView_getSeminarAt);
//        if(SeminarAt == null){
//            SeminarAt = "Hari, Tanggal"; //Assign default string
//        }
//        SeminarAtDetail.setText(SeminarAt);
//
//        CreatedAtDetail = findViewById(R.id.textView_getCreatedAt);
//        if(CreatedAt == null){
//            CreatedAt = "Kegiatan"; //Assign default string
//        }
//        CreatedAtDetail.setText(CreatedAt);
//
//        FileReportDetail = findViewById(R.id.textView_getFileReport);
//        if(FileReport == null){
//            FileReport = "Kegiatan"; //Assign default string
//        }
//        FileReportDetail.setText(FileReport);
//
//        RegisteredAtDetail = findViewById(R.id.textView_getRegisteredAt);
//        if(RegisteredAt == null){
//            RegisteredAt = "Kegiatan"; //Assign default string
//        }
//        RegisteredAtDetail.setText(RegisteredAt);
//
////        roomIDDetail = findViewById(R.id.textView_getRoomId);
////        if(roomID == null){
////            roomID = "Kegiatan"; //Assign default string
////        }
////        roomIDDetail.setText(roomID);

        getData();

    }

    public void getData(){

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String token = sharedPref.getString("TOKEN", "");
        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        StoryClient client = RetrofitClientInstance.getRetrofitInstance().create(StoryClient.class);
        Call<DetailSemhasResponse> call = client.detailseminar("Bearer "+ token);

        call.enqueue(new Callback<DetailSemhasResponse>()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<DetailSemhasResponse> call, Response<DetailSemhasResponse> response)
            {

                DetailSemhasResponse seminarsItems = response.body();
//                Log.d("hasilnya", String.valueOf(data.getReviewers(getPackageName())));
                if(response.isSuccessful())
                {
                    String register = seminarsItems.getRegisteredAt();
                    TextView tvregister = findViewById(R.id.textView_getRegisteredAt);
                    tvregister.setText("Mendaftar pada : "+register );

                    Integer thesis = seminarsItems.getThesisId();
                    TextView tvthesis = findViewById(R.id.textView_getCreatedAt);
                    tvthesis.setText("Kode Tesis : "+thesis.toString());

                    Integer fileRepor = seminarsItems.getRoomId();
                    TextView tvdata = findViewById(R.id.textView_getRoomId);
                    tvdata.setText("Ruangan : "+fileRepor.toString());

                    String fileReport = seminarsItems.getFileReport();
                    TextView tvTest = findViewById(R.id.textView_getFileReport);
                    tvTest.setText("File Laporan : "+fileReport);


                    String seminarat = seminarsItems.getSeminarAt();
                    TextView tanggaltv = findViewById(R.id.textView_getSeminarAt);
                    tanggaltv.setText("Seminar pada : "+seminarat);





//                    List<ReviewersItem> file = data.getReviewers(getPackageName());
//                    TextView pengujitv = findViewById(R.id.penguji); pengujitv.setText(file.toString());
//
//                    List<ReviewersItem> filee = data.getReviewers(getPackageName());
//                    TextView pengujiitv = findViewById(R.id.pengujii); pengujiitv.setText(filee.toString());

//                    List<ReviewersItem> reviewersItems = DetailSeminarResponse.getreviewers();
//                    Log.d("ListLogbook-Debug", reviewersItems.toString());
//                    adapter.setItemList(reviewersItems);
//                    }
                }

            }

            @Override
            public void onFailure(Call<DetailSemhasResponse> call, Throwable t) {
                Toast.makeText(DetailSeminarHasilActivity.this, "GAGAL", Toast.LENGTH_SHORT).show();
            };


        });

    }

}