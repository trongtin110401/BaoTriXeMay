package com.example.baotrixemay.fragment.GiaoDienChinh;

import android.content.Intent;
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
import com.example.lib.model.GroupChatModel;
import com.example.lib.request.RqGroupChat;

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
        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("id",-1);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        RqGroupChat rqGroupChat = new RqGroupChat();
        rqGroupChat.setIdcuahang(id);
        Call<GroupChatModel[]> call = methods.getGroupChat(rqGroupChat);
        call.enqueue(new Callback<GroupChatModel[]>() {
            @Override
            public void onResponse(Call<GroupChatModel[]> call, Response<GroupChatModel[]> response) {
                GroupChatModel[] dta = response.body();
                ArrayList<GroupChatModel> temp = new ArrayList<>();
                for (GroupChatModel dt:dta
                ) {
                    temp.add(dt);
                    Log.v("............",dt.getAvatar());
                }
                adtGroupChat = new adapterGroupChat(getActivity(),temp);
                recyclerView.setAdapter(adtGroupChat);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<GroupChatModel[]> call, Throwable t) {

            }
        });
        return view;
    }
    public void reloadData(){

    }
}