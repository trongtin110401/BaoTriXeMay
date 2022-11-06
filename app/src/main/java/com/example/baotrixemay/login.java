package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baotrixemay.R;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.userModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    Button btnLogin,btnDK;
    EditText acc,pas;
    TextView thongbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        acc = findViewById(R.id.edtUsername);
        pas = findViewById(R.id.edtPassword);
        thongbao = findViewById(R.id.txtThongBao);
        btnDK = findViewById(R.id.btnDK);
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,DangKi.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
                Call<userModel[]> call = methods.getUser();
                call.enqueue(new Callback<userModel[]>() {
                    @Override
                    public void onResponse(Call<userModel[]> call, Response<userModel[]> response) {
                        userModel[] data = response.body();
                        int count=0;
                        for (userModel dt:data
                             ) {
                            if(dt.account.equals(acc.getText().toString()) && dt.password.equals(pas.getText().toString())){
                                count=1;

                            }
                        }
                        if(count==1){
                            Intent intent = new Intent(login.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            thongbao.setText("Tài khoản hoặc mật khẩu không chính xác");
                        }
                    }

                    @Override
                    public void onFailure(Call<userModel[]> call, Throwable t) {

                    }
                });
            }
        });
    }
}