package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.baotrixemay.adapter.adapterMessage;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.MessageModel;
import com.example.lib.request.rqChat;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterMessage adapterMessege1;
    int idcuahang,iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        recyclerView = findViewById(R.id.listMS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        idcuahang = intent.getIntExtra("key_1",-1);
        iduser = intent.getIntExtra("key_2",-1);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        rqChat temp = new rqChat();
        temp.setIduser(iduser);
        temp.setIdcuahang(idcuahang);
        Call<MessageModel[]> call = methods.getChat(temp);
        Log.v("aaaaaa",new Gson().toJson(temp));
        call.enqueue(new Callback<MessageModel[]>() {
            @Override
            public void onResponse(Call<MessageModel[]> call, Response<MessageModel[]> response) {
                MessageModel[] data = response.body();
                ArrayList<MessageModel> temp1 = new ArrayList<>();
                for (MessageModel dt:data
                ) {
                    Log.v("aaaaaaaaaaaaaaaaa",dt.getNoidung());
                    temp1.add(dt);
                }
                adapterMessege1 = new adapterMessage(Chat.this,temp1);
                recyclerView.setAdapter(adapterMessege1);
                recyclerView.setVisibility(View.VISIBLE);
                adapterMessege1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MessageModel[]> call, Throwable t) {

            }
        });
    }
}