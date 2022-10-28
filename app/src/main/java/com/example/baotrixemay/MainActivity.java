package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.Loaixe;
import com.example.lib.model.test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<Loaixe[]> call = methods.getdata();
        call.enqueue(new Callback<Loaixe[]>() {
            @Override
            public void onResponse(Call<Loaixe[]> call, Response<Loaixe[]> response) {
                Loaixe[] data = response.body();
                for (Loaixe dt:data
                     ) {
                    Log.v("............",dt.tenxe);
                }
            }

            @Override
            public void onFailure(Call<Loaixe[]> call, Throwable t) {
                Log.v("............","aaaaaaaaaaaaaaa");
            }
        });
    }
}