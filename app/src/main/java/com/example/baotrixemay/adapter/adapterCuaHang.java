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

import java.util.List;
import java.util.zip.Inflater;

public class adapterCuaHang extends ArrayAdapter<CuaHangModel> {

    public adapterCuaHang(@NonNull Context context, int resource, @NonNull List<CuaHangModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView tvselected = convertView.findViewById(R.id.txtSelected);
        CuaHangModel cuahang = this.getItem(position);

        if(cuahang!=null){
            tvselected.setText(cuahang.getTencuahang());
        }
        return convertView ;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuahang,parent,false);
        TextView textView = convertView.findViewById(R.id.tv_cuahang);
        CuaHangModel cuahang = this.getItem(position);

        if(cuahang!=null){
            textView.setText(cuahang.getTencuahang());
        }
        return convertView ;
    }
}
