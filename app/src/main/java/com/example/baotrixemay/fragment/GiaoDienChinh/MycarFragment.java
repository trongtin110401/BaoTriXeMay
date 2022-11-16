package com.example.baotrixemay.fragment.GiaoDienChinh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baotrixemay.AddCar;
import com.example.baotrixemay.R;
import com.example.baotrixemay.adapter.adapterGroupChat;
import com.example.baotrixemay.adapter.adapterMycar;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.ChatModel;
import com.example.lib.model.GroupChatModel;
import com.example.lib.model.XeCaNhanModel;
import com.example.lib.request.RqGroupChat;
import com.example.lib.request.RqXeCaNhan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MycarFragment extends Fragment {
    ImageButton add;
    RecyclerView recyclerView;
    ArrayList<XeCaNhanModel> t;
    adapterMycar adapterMycar;
    public MycarFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mycar,container,false);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        add = view.findViewById(R.id.add);
        recyclerView =view.findViewById(R.id.listmycar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("id",-1);
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        RqXeCaNhan rqGroupChat = new RqXeCaNhan();
        rqGroupChat.setIduser(id);
        Call<XeCaNhanModel[]> call = methods.getXeCaNhan(rqGroupChat);
        call.enqueue(new Callback<XeCaNhanModel[]>() {
            @Override
            public void onResponse(Call<XeCaNhanModel[]> call, Response<XeCaNhanModel[]> response) {
                XeCaNhanModel[] dta = response.body();
                ArrayList<XeCaNhanModel> temp = new ArrayList<>();
                for (XeCaNhanModel dt:dta
                ) {
                    temp.add(dt);
                }
                adapterMycar = new adapterMycar(getActivity(),temp,id);
                recyclerView.setAdapter(adapterMycar);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<XeCaNhanModel[]> call, Throwable t) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddCar.class);
                intent.putExtra("iduser",id);
                startActivity(intent);
            }
        });
        return view;
    }
}