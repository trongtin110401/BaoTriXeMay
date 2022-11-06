package com.example.lib.Repository;

import com.example.lib.model.ChatModel;
import com.example.lib.model.GroupChatModel;
import com.example.lib.model.Loaixe;
import com.example.lib.model.MessageModel;
import com.example.lib.model.otpModel;
import com.example.lib.model.test;
import com.example.lib.model.userModel;
import com.example.lib.request.RqGroupChat;
import com.example.lib.request.rqChat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @POST("api/chat")
    Call<MessageModel[]> getChat(@Body rqChat data);
    @POST("api/doanchatuser")
    Call<GroupChatModel[]> getGroupChat(@Body RqGroupChat data);

}
