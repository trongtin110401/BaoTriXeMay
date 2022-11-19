package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.CuaHangModel;
import com.example.lib.model.reponsethemxe;
import com.example.lib.request.RqThemXe;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemXeMoi extends AppCompatActivity {
    ImageView imageView2;
    TextView txt24,txt25;
    EditText editTextTextPersonName2;
    DatePickerDialog picker;
    Button themxe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_xe_moi);
        imageView2 = findViewById(R.id.imageView2);
        txt24 = findViewById(R.id.textView23);
        txt25 = findViewById(R.id.textView24);
        themxe = findViewById(R.id.button3);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName2.setInputType(InputType.TYPE_NULL);
        Intent intent = getIntent();
        int iduser = intent.getIntExtra("key_0",-1);
        int idxe = intent.getIntExtra("key_1",-1);
        String imgxe = intent.getStringExtra("key_2");
        String imglogo = intent.getStringExtra("key_3");
        String tenxe = intent.getStringExtra("key_4");
        String hangxe = intent.getStringExtra("key_5");
        txt24.setText(tenxe);
        txt25.setText(hangxe);
        Glide.with(this)
                .load(imgxe)
                .into(imageView2);
        editTextTextPersonName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ThemXeMoi.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextTextPersonName2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        themxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
                RqThemXe rqThemXe = new RqThemXe();
                rqThemXe.setIduser(iduser);
                rqThemXe.setLoaixe_idloaixe(idxe);
                rqThemXe.setNgaymua(editTextTextPersonName2.getText().toString());
                Call<reponsethemxe[]> call = methods.ThemXe(rqThemXe);
               call.enqueue(new Callback<reponsethemxe[]>() {
                   @Override
                   public void onResponse(Call<reponsethemxe[]> call, Response<reponsethemxe[]> response) {

                   }

                   @Override
                   public void onFailure(Call<reponsethemxe[]> call, Throwable t) {

                   }
               });
                Intent intent = new Intent(ThemXeMoi.this,MainActivity.class);
                intent.putExtra("id",iduser);
                startActivity(intent);
            }
        });
    }

}