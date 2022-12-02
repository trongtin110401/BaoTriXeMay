package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.baotrixemay.adapter.adaperPhuTung1;
import com.example.baotrixemay.adapter.adapterCuaHang;
import com.example.baotrixemay.adapter.adapterPhuTung;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.CuaHangModel;
import com.example.lib.model.PhieuLuuModel;
import com.example.lib.model.PhuTungModel;
import com.example.lib.model.otpModel;
import com.example.lib.model.reponsethemxe;
import com.example.lib.request.RqLuuBT;
import com.example.lib.request.RqPhieuLuu;
import com.example.lib.request.idphutung;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LuuThongTinBaoTri extends AppCompatActivity {
    EditText DTPicker;
    Button btnLBT;
    Spinner spinnerCH,spinnerPT;
    RecyclerView recyclerView;
    adapterCuaHang adapterCH;
    adapterPhuTung adapterPT;
    adaperPhuTung1 adapterPT1;
    ArrayList<PhuTungModel> listPT = new ArrayList<>();
    DatePickerDialog picker;
    ArrayList<PhuTungModel> temp= new ArrayList<>();
    public ArrayList<idphutung> listdata = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_thong_tin_bao_tri);
        Intent intent = getIntent();
        int idxe = intent.getIntExtra("iduser",-1);
        btnLBT = findViewById(R.id.buttonLBT);
        DTPicker =  findViewById(R.id.DTPicker);
        DTPicker.setInputType(InputType.TYPE_NULL);
        DTPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(LuuThongTinBaoTri.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                DTPicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView =findViewById(R.id.listPTLichHen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapterPT1 = new adaperPhuTung1(LuuThongTinBaoTri.this,listPT);
        recyclerView.setAdapter(adapterPT1);
        recyclerView.setVisibility(View.VISIBLE);
        adapterPT1.notifyDataSetChanged();
        spinnerCH = findViewById(R.id.spinnercuahang);
        spinnerPT = findViewById(R.id.spinnerPT);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);;
        Call<CuaHangModel[]> call = methods.getCuaHang();
        call.enqueue(new Callback<CuaHangModel[]>() {
            @Override
            public void onResponse(Call<CuaHangModel[]> call, Response<CuaHangModel[]> response) {
                CuaHangModel[] data=response.body();
                ArrayList<CuaHangModel> temp1= new ArrayList<>();
                for (CuaHangModel dt:data
                ) {
                    temp1.add(dt);
                }

                adapterCH = new adapterCuaHang(LuuThongTinBaoTri.this,R.layout.item_selected,temp1);
                spinnerCH.setAdapter(adapterCH);
            }

            @Override
            public void onFailure(Call<CuaHangModel[]> call, Throwable t) {

            }
        });
        Call<PhuTungModel[]> call1 = methods.getPhuTung();
        call1.enqueue(new Callback<PhuTungModel[]>() {
            @Override
            public void onResponse(Call<PhuTungModel[]> call, Response<PhuTungModel[]> response) {
                PhuTungModel[] data=response.body();

                for (PhuTungModel dt:data
                ) {
                    temp.add(dt);
                }

                adapterPT = new adapterPhuTung(LuuThongTinBaoTri.this,R.layout.item_selected,temp);

                spinnerPT.setAdapter(adapterPT);
            }

            @Override
            public void onFailure(Call<PhuTungModel[]> call, Throwable t) {

            }
        });
        spinnerPT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(DatLichBaoTri.this,adapterPT.getItem(i).getTenphutung(),Toast.LENGTH_LONG).show();
                listPT.add(0,adapterPT.getItem(i));
                for (PhuTungModel pt:listPT
                     ) {
                    for (PhuTungModel ptlist:temp
                         ) {
                        if(pt.getTenphutung().equals(ptlist.getTenphutung())){
                            idphutung idtemp = new idphutung();
                            idtemp.setPhutungxe_idphutungxe(ptlist.getIdphutungxe());
                            listdata.add(0,idtemp);
                        }
                    }
                }

                adapterPT1.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnLBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods1 = RetrofitClient.getRetrofit().create(Methods.class);;
                RqLuuBT rqLuuBT = new RqLuuBT();
                rqLuuBT.setListdata(listdata);
                rqLuuBT.setIdcuahang(1);
                rqLuuBT.setIdxe(idxe);
                rqLuuBT.setThoigian(DTPicker.getText().toString());
                Call<otpModel> call2 = methods1.themPhieuLuu(rqLuuBT);
                Log.v(".....",new Gson().toJson(rqLuuBT));
                call2.enqueue(new Callback<otpModel>() {
                    @Override
                    public void onResponse(Call<otpModel> call, Response<otpModel> response) {

                    }

                    @Override
                    public void onFailure(Call<otpModel> call, Throwable t) {

                    }
                });

            }
        });
    }
}