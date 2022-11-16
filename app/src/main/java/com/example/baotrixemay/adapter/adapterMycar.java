package com.example.baotrixemay.adapter;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baotrixemay.Chat;
import com.example.baotrixemay.DetailCar;
import com.example.baotrixemay.R;
import com.example.lib.model.GroupChatModel;
import com.example.lib.model.XeCaNhanModel;

import java.util.ArrayList;

public class adapterMycar extends
        RecyclerView.Adapter<adapterMycar.ViewHolder>{
    private Context mContext;
    private ArrayList<XeCaNhanModel> mHeros;
    int id;

    public adapterMycar(Context mContext, ArrayList<XeCaNhanModel> mHeros,int id) {
        this.mContext = mContext;
        this.mHeros = mHeros;
        this.id = id;
    }
    @NonNull
    @Override
    public adapterMycar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.itemxe, parent, false);
        adapterMycar.ViewHolder viewHolder = new adapterMycar.ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterMycar.ViewHolder holder, int position) {
        XeCaNhanModel hero = mHeros.get(position);
        holder.Name.setText(hero.getTenxe());
        Glide.with(mContext)
                .load(hero.getHinhanh())
                .into(holder.mImageHero);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailCar.class);
                intent.putExtra("key_0",id);
                intent.putExtra("key_1",hero.getIdxe());
                intent.putExtra("key_2",hero.getHinhanh());
                intent.putExtra("key_3",hero.getImglogo());
                intent.putExtra("key_4",hero.getTenxe());
                intent.putExtra("key_5",hero.getHangxe());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeros.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageHero;
        private TextView Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageHero = itemView.findViewById(R.id.imgxecanhan);
            Name = itemView.findViewById(R.id.txtTenxe);

        }
    }
}
