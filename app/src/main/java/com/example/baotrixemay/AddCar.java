package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baotrixemay.adapter.adapterLoaiXe;
import com.example.baotrixemay.adapter.adapterLuuBT;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.Loaixe;
import com.example.lib.model.PhieuLuuModel;
import com.example.lib.request.RqPhieuLuu;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCar extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterLoaiXe adtLoaoXe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView =findViewById(R.id.listLoaiXe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        int iduser = intent.getIntExtra("iduser",-1);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<Loaixe[]> call = methods.getLoaiXe();
        call.enqueue(new Callback<Loaixe[]>() {
            @Override
            public void onResponse(Call<Loaixe[]> call, Response<Loaixe[]> response) {
                Loaixe[] dta = response.body();
                ArrayList<Loaixe> temp = new ArrayList<>();
                for (Loaixe dt:dta
                ) {
                    temp.add(dt);
                }
                adtLoaoXe= new adapterLoaiXe(AddCar.this,temp,iduser);
                recyclerView.setAdapter(adtLoaoXe);
                recyclerView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Call<Loaixe[]> call, Throwable t) {

            }
        });

    }
}