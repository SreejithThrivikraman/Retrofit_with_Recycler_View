package com.example.retrofitexample.network.services;

import com.example.retrofitexample.RetrofitClass;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface dataServices {

    @GET("/photos")
    Call<List<RetrofitClass>> getAllPhotos();
}
