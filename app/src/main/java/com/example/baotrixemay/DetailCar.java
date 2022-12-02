package com.example.baotrixemay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baotrixemay.adapter.adapterGroupChat;
import com.example.baotrixemay.adapter.adapterLuuBT;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.GroupChatModel;
import com.example.lib.model.PhieuLuuModel;
import com.example.lib.request.RqGroupChat;
import com.example.lib.request.RqPhieuLuu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCar extends AppCompatActivity {
    Button btnDatLichHen,btnLuuBT;
    RecyclerView recyclerView;
    adapterLuuBT adtGroupChat;
    TextView txtDTTenXe,hangxe1;
    ImageView logoxe,imgDTxe;
    RecyclerView listBaoTri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_car);
        btnDatLichHen = findViewById(R.id.btnDatLichHen);
        btnLuuBT = findViewById(R.id.button);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView =findViewById(R.id.listBaoTri);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        txtDTTenXe = findViewById(R.id.txtDTTenXe);
        logoxe = findViewById(R.id.logoxe);
        hangxe1 = findViewById(R.id.hangxe);
        imgDTxe = findViewById(R.id.imgDTxe);
        listBaoTri = findViewById(R.id.listBaoTri);
        Intent intent = getIntent();
        int iduser = intent.getIntExtra("key_0",-1);
        int idxe = intent.getIntExtra("key_1",-1);
        String imgxe = intent.getStringExtra("key_2");
        String imglogo = intent.getStringExtra("key_3");
        String tenxe = intent.getStringExtra("key_4");
        String hangxe = intent.getStringExtra("key_5");
        txtDTTenXe.setText(tenxe);
        hangxe1.setText(hangxe);
        Glide.with(this)
                .load(imgxe)
                .into(imgDTxe);
        Glide.with(this)
                .load(imglogo)
                .into(logoxe);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        RqPhieuLuu rqGroupChat = new RqPhieuLuu();
        rqGroupChat.setIdxe(idxe);
        Call<PhieuLuuModel[]> call = methods.getPhieuLuu(rqGroupChat);
        call.enqueue(new Callback<PhieuLuuModel[]>() {
            @Override
            public void onResponse(Call<PhieuLuuModel[]> call, Response<PhieuLuuModel[]> response) {
                PhieuLuuModel[] dta = response.body();
                ArrayList<PhieuLuuModel> temp = new ArrayList<>();
                for (PhieuLuuModel dt:dta
                ) {
                    temp.add(dt);
                }
                adtGroupChat = new adapterLuuBT(DetailCar.this,temp,iduser);
                recyclerView.setAdapter(adtGroupChat);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<PhieuLuuModel[]> call, Throwable t) {

            }
        });
        btnDatLichHen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailCar.this,DatLichBaoTri.class);
                startActivity(intent);
            }
        });
        btnLuuBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailCar.this,LuuThongTinBaoTri.class);
                intent.putExtra("iduser",idxe);
                startActivity(intent);
            }
        });
    }
}