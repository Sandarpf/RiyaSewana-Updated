package com.example.riyasewana.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.riyasewana.R;

public class MobileNumberVerificationRegister extends AppCompatActivity {

    //Variables
    Button btnNextToVerifyNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mobile_number_verification_register);

        //Hooks
        btnNextToVerifyNumber = findViewById(R.id.btnNextToEVerifyMobileNumber);

        btnNextToVerifyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MobileNumberVerificationRegister.this,RegisterVerifyCode.class);
                startActivity(i);
            }
        });
    }
}