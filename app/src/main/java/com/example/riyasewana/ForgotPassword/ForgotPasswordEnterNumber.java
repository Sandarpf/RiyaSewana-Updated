package com.example.riyasewana.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.riyasewana.R;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordEnterNumber extends AppCompatActivity {

    //Variables
    Button btnNext;
    TextInputLayout mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_enter_number);

        //Hooks
        btnNext = findViewById(R.id.btnForgotPassNumVerify);
        mobileNumber = findViewById(R.id.forgotPasswordMobileNumber);

    }

    public void checkNumber(View view){
        if(!validateNumber()){
            return;
        }
        else{
            Intent i = new Intent(getApplicationContext(),ForgotPasswordVeriyCode.class);
            startActivity(i);
        }
    }

    private boolean validateNumber(){
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