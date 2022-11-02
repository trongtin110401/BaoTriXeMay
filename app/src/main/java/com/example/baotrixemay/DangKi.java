package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.userModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKi extends AppCompatActivity {

    EditText account,password,rePassword,phone;
    Button Dangki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        account = findViewById(R.id.TK);
        password = findViewById(R.id.MK);
        rePassword = findViewById(R.id.MKnhaplai);
        phone = findViewById(R.id.Email);
        Dangki = findViewById(R.id.btnDangKi);
        Dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userModel temp = new userModel();
                temp.setAccount(account.getText().toString());
                temp.setPassword(password.getText().toString());
                temp.setPhone(phone.getText().toString());
                Intent intent = new Intent(DangKi.this,OTPCode.class);
                intent.putExtra("key_1",temp.getAccount());
                intent.putExtra("key_2",temp.getPassword());
                intent.putExtra("key_3",temp.getPhone());
                startActivity(intent);
            }

        });
    }

}