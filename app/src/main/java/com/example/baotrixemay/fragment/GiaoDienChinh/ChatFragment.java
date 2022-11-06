package com.example.baotrixemay.fragment.GiaoDienChinh;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baotrixemay.R;
import com.example.baotrixemay.adapter.adapterGroupChat;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.ChatModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ChatModel> t;
    adapterGroupChat adtGroupChat;
    public ChatFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerView =view.findViewById(R.id.listChat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<ChatModel[]> call = methods.getGroudChat();
        call.enqueue(new Callback<ChatModel[]>() {
            @Override
            public void onResponse(Call<ChatModel[]> call, Response<ChatModel[]> response) {
                ChatModel[] dta = response.body();
                ArrayList<ChatModel> temp = new ArrayList<>();
                for (ChatModel dt:dta
                ) {
                    temp.add(dt);
                    Log.v("............",dt.getAvatar());
                }
                adtGroupChat = new adapterGroupChat(getActivity(),temp);
                recyclerView.setAdapter(adtGroupChat);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ChatModel[]> call, Throwable t) {

            }
        });
        return view;
    }
    public void reloadData(){

    }
}