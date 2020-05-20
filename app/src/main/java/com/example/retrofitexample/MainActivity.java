package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitexample.adapters.RecyclerAdapter;
import com.example.retrofitexample.network.RetrofitClientClass;
import com.example.retrofitexample.network.services.dataServices;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDoalog;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        RetrofitClientClass clientClass = new RetrofitClientClass();
        dataServices service = clientClass.getRetrofitInstance().create(dataServices.class);

        Call<List<RetrofitClass>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetrofitClass>>() {
            @Override
            public void onResponse(Call<List<RetrofitClass>> call, Response<List<RetrofitClass>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<RetrofitClass>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void generateDataList(List<RetrofitClass>  photoList) {
        recyclerView = findViewById(R.id.recyclerviewlist);
        adapter = new RecyclerAdapter(this,photoList);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
