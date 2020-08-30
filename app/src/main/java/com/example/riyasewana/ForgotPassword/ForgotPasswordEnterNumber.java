package com.example.riyasewana.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.riyasewana.R;

public class ForgotPasswordEnterNumber extends AppCompatActivity {

    //Variables
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_enter_number);

        //Hooks
        btnNext = findViewById(R.id.btnForgotPassNumVerify);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgotPasswordEnterNumber.this,ForgotPasswordVeriyCode.class);
                startActivity(i);
            }
        });
    }
}