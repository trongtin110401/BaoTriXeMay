package com.example.lib.Repository;

import com.example.lib.model.Loaixe;
import com.example.lib.model.test;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
    @GET("api/loaixe")
    Call<Loaixe[]> getdata();
}
