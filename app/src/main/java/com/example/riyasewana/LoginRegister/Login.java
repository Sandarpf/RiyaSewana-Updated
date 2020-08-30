package com.example.riyasewana.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.riyasewana.ForgotPassword.ForgotPasswordEnterNumber;
import com.example.riyasewana.R;

public class Login extends AppCompatActivity {

    //Variables
    TextView txtRegister,txtForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        txtRegister = findViewById(R.id.registerActivityMover);
        txtForgotPass = findViewById(R.id.txtForgotPassword);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });
        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ForgotPasswordEnterNumber.class);
                startActivity(i);
            }
        });
    }
}