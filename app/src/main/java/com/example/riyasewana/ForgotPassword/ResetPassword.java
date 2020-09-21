package com.example.riyasewana.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.riyasewana.R;
import com.google.android.material.textfield.TextInputLayout;

public class ResetPassword extends AppCompatActivity {

    Button updatePassBtn;
    TextInputLayout password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        updatePassBtn = findViewById(R.id.updatePasswordBtn);
        password = findViewById(R.id.resetPassNewPassword);
        confirmPassword = findViewById(R.id.resetPassConfirmPassword);


    }
    public void resetPassword(View view){
        if(!validatePassword() | !validateConfirmPassword()){
            return;
        }
        else{
            Intent i = new Intent(getApplicationContext(),ResetPasswordSuccess.class);
            startActivity(i);
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
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword(){
        String value = confirmPassword.getEditText().getText().toString().trim();
        String value2 = password.getEditText().getText().toString().trim();
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
            confirmPassword.setError("Password field cannot be empty!");
            return false;
        }

        else if(!value.matches(chackpassword)){
            confirmPassword.setError("Password is too weak!");
            return false;
        }
        else if(!value.matches(value2)){
            confirmPassword.setError("Passwords doesn't match!");
            return false;
        }
        else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
            return true;
        }
    }
}