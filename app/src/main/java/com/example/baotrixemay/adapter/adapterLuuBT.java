package com.example.baotrixemay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baotrixemay.ChiTietPhieuLuu;
import com.example.baotrixemay.R;
import com.example.lib.model.PhieuLuuModel;
import com.example.lib.model.XeCaNhanModel;

import java.util.ArrayList;

public class adapterLuuBT extends  RecyclerView.Adapter<adapterLuuBT.ViewHolder>{
    private Context mContext;
    private ArrayList<PhieuLuuModel> mHeros;
    int id;

    public adapterLuuBT(Context mContext, ArrayList<PhieuLuuModel> mHeros,int id) {
        this.mContext = mContext;
        this.mHeros = mHeros;
        this.id = id;
    }
    @NonNull
    @Override
    public adapterLuuBT.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.itemluuthongtin, parent, false);
        adapterLuuBT.ViewHolder viewHolder = new adapterLuuBT.ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterLuuBT.ViewHolder holder, int position) {
        PhieuLuuModel hero = mHeros.get(position);
        String ngay = "Ngày bảo trì: " + hero.getThoigian();
        holder.Name.setText(ngay);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ChiTietPhieuLuu.class);
                intent.putExtra("idphieuluu",hero.getIdphieuluu());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeros.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.ngayBT);

        }
    }
}
