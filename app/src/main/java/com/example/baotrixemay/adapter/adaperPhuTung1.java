package com.example.baotrixemay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baotrixemay.R;
import com.example.lib.model.GroupChatModel;
import com.example.lib.model.PhuTungModel;

import java.util.ArrayList;

public class adaperPhuTung1 extends
        RecyclerView.Adapter<adaperPhuTung1.ViewHolder>{
    private Context mContext;
    private ArrayList<PhuTungModel> mHeros;

    public adaperPhuTung1(Context mContext, ArrayList<PhuTungModel> mHeros) {
        this.mContext = mContext;
        this.mHeros = mHeros;
    }
    @NonNull
    @Override
    public adaperPhuTung1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.itemphutung, parent, false);
        adaperPhuTung1.ViewHolder viewHolder = new adaperPhuTung1.ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adaperPhuTung1.ViewHolder holder, int position) {
        PhuTungModel hero = mHeros.get(position);
        int stt = position+1;
        String tenPT = String.valueOf(stt)+": "+hero.getTenphutung();
        holder.Name.setText(tenPT);
    }
    @Override
    public int getItemCount() {
        return mHeros.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.txtPhuTung);

        }
    }
}
