package com.example.riyasewana.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.riyasewana.R;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    //Variables
    TextView txtLogin;
    TextInputLayout name, uname, email, password;
    Button nextToEnterMobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        //Hooks
        txtLogin = findViewById(R.id.loginActivityMover);
        nextToEnterMobileNumber = findViewById(R.id.btnNextToEnterMobileNumber);
        name = findViewById(R.id.registerCustomerName);
        uname = findViewById(R.id.registerUserName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });
    }

    public void callNextActivityMobile(View view){
        Intent i = new Intent(getApplicationContext(),MobileNumberVerificationRegister.class);

        if(!validateName() | !validateUserName() | !validateEmail() | !validatePassword()){
            return;
        }
        else {
            startActivity(i);
        }
    }

    private boolean validateName() {
        String value = name.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            name.setError("Name Field cannot be empty!");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String value = uname.getEditText().getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z"; //white spaces check

        if (value.isEmpty()) {
            uname.setError("User Name Field cannot be empty!");
            return false;
        }else if(value.length()>20){
            uname.setError("User Name must be less than 20 characters!");
            return false;
        }
        else if(!value.matches(checkSpaces)){
            uname.setError("No White Spaces are allowed!");
            return false;
        }

        else {
            uname.setError(null);
            uname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String value = email.getEditText().getText().toString().trim();
        String checkemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //email check

        if(value.isEmpty()){
            email.setError("Email field cannot be empty!");
            return false;
        }
        else if(!value.matches(checkemail)){
            email.setError("Invalid Email Address!");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){
        String value = password.getEditText().getText().toString().trim();
        String chackpassword = "^"+
                "(?=.*[0-9])"+          //at least one digit
                "(?=.*[a-z])"+          //at least lower character letter
                "(?=.*[A-Z])"+          //at least Upper character letter
                "(?=.*[a-zA-Z])" +      // any letter
                "(?=.*[@#$%^&+=])"+     //any special character
                "(?=\\S+$)"+            //no white spaces
                ".{4,}"+                //at least four characters
                "$";
        if(value.isEmpty()){
            password.setError("Password field cannot be empty!");
            return false;
        }
        else if(!value.matches(chackpassword)){
            password.setError("Password is too weak!");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}