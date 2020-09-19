package com.example.riyasewana.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.riyasewana.R;

public class ResetPassword extends AppCompatActivity {

    Button updatePassBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        updatePassBtn = findViewById(R.id.updatePasswordBtn);
        updatePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResetPassword.this,ResetPasswordSuccess.class);
                startActivity(i);
            }
        });
    }
}