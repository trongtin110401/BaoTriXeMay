package com.example.baotrixemay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baotrixemay.R;
import com.example.lib.model.CuaHangModel;
import com.example.lib.model.PhuTungModel;

import java.util.List;

public class adapterPhuTung extends ArrayAdapter<PhuTungModel> {

    public adapterPhuTung(@NonNull Context context, int resource, @NonNull List<PhuTungModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView tvselected = convertView.findViewById(R.id.txtSelected);
        PhuTungModel cuahang = this.getItem(position);

        if(cuahang!=null){
            tvselected.setText(cuahang.getTenphutung());
        }
        return convertView ;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuahang,parent,false);
        TextView textView = convertView.findViewById(R.id.tv_cuahang);
        PhuTungModel cuahang = this.getItem(position);

        if(cuahang!=null){
            textView.setText(cuahang.getTenphutung());
        }
        return convertView ;
    }
}
