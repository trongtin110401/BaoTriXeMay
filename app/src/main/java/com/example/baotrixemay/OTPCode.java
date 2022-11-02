package com.example.baotrixemay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.otpModel;
import com.example.lib.model.userModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPCode extends AppCompatActivity {
Button btnXN;
String OTP;
EditText maOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpcode);
        btnXN = findViewById(R.id.btnXacNhan);
        maOTP = findViewById(R.id.editTextTextPersonName);
        Intent intent = getIntent();
        String acc = intent.getStringExtra("key_1");
        String pass = intent.getStringExtra("key_2");
        String email = intent.getStringExtra("key_3");
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<otpModel> call = methods.getOTP();
        call.enqueue(new Callback<otpModel>() {
            @Override
            public void onResponse(Call<otpModel> call, Response<otpModel> response) {
                otpModel data = response.body();
                OTP = data.getMessage();
            }

            @Override
            public void onFailure(Call<otpModel> call, Throwable t) {

            }
        });
        btnXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("temp",maOTP.getText().toString());
                Log.v("otp",OTP);
                if(maOTP.getText().toString().equals(OTP.toString())){
                    Log.v("otp",OTP);
                    Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
                    userModel temp = new userModel();
                    temp.setAccount(acc);
                    temp.setPassword(pass);
                    temp.setPhone(email);
                    Call<userModel> call = methods.postUser(temp);
                    call.enqueue(new Callback<userModel>() {
                        @Override
                        public void onResponse(Call<userModel> call, Response<userModel> response) {
                            Intent intent = new Intent(OTPCode.this,MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<userModel> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
}