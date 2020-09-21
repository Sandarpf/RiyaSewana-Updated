package com.example.riyasewana.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.riyasewana.R;
import com.google.android.material.textfield.TextInputLayout;

public class MobileNumberVerificationRegister extends AppCompatActivity {

    //Variables
    Button btnNextToVerifyNumber;
    TextInputLayout mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mobile_number_verification_register);

        //Hooks
        btnNextToVerifyNumber = findViewById(R.id.btnNextToEVerifyMobileNumber);
        mobileNumber = findViewById(R.id.registerMobileNumber);
    }
    public void mobileNumberVerify(View view){
        if(!verifyMobile()){
            return;
        }
        else{
            Intent i = new Intent(getApplicationContext(),RegisterVerifyCode.class);
            startActivity(i);
        }
    }

    private boolean verifyMobile(){
        String value = mobileNumber.getEditText().getText().toString().trim();

        if(value.isEmpty()){
            mobileNumber.setError("Mobile Number field cannot be empty!");
            return false;
        }
        else{
            mobileNumber.setError(null);
            mobileNumber.setErrorEnabled(false);
            return true;
        }
    }
}