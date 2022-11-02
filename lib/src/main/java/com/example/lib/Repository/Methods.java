package com.example.lib.Repository;

import com.example.lib.model.Loaixe;
import com.example.lib.model.otpModel;
import com.example.lib.model.test;
import com.example.lib.model.userModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {
    @GET("api/loaixe")
    Call<Loaixe[]> getdata();
    @POST("api/user")
    Call<userModel> postUser(@Body userModel data);
    @GET("api/user")
    Call<userModel[]> getUser();
    @GET("otp")
    Call<otpModel> getOTP();

}
