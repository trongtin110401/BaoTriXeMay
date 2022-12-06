package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baotrixemay.adapter.adaperPhuTung1;
import com.example.baotrixemay.adapter.adapterLuuBT;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.CuaHangModel;
import com.example.lib.model.PhieuLuuModel;
import com.example.lib.model.PhuTungModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietPhieuLuu extends AppCompatActivity {
    RecyclerView recyclerView;
    adaperPhuTung1 adapterPT1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phieu_luu);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView =findViewById(R.id.list_pt);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Intent intent = getIntent();
        int idphieuluu = intent.getIntExtra("idphieuluu",-1);
        Log.v("idphieuluu",String.valueOf(idphieuluu));
        PhieuLuuModel phieuLuuModel = new PhieuLuuModel();
        phieuLuuModel.setIdthongtinbaotrixe(idphieuluu);
        Log.v("........",new Gson().toJson(phieuLuuModel));
        Call<PhieuLuuModel[]> call = methods.chitietphieuluu(phieuLuuModel);
        call.enqueue(new Callback<PhieuLuuModel[]>() {
            @Override
            public void onResponse(Call<PhieuLuuModel[]> call, Response<PhieuLuuModel[]> response) {
                PhieuLuuModel[] phieuLuuModels = response.body();
                ArrayList<PhuTungModel> listtemp= new ArrayList<>();
                for (PhieuLuuModel pt:phieuLuuModels
                     ) {
                    PhuTungModel temp = new PhuTungModel();
                    temp.setTenphutung(pt.getTenphutung());
                    listtemp.add(temp);
                }
                adapterPT1 = new adaperPhuTung1(ChiTietPhieuLuu.this,listtemp);
                recyclerView.setAdapter(adapterPT1);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<PhieuLuuModel[]> call, Throwable t) {

            }
        });
    }
}