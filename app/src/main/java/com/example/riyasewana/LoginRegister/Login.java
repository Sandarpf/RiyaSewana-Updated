package com.example.riyasewana.LoginRegister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.riyasewana.ForgotPassword.ForgotPasswordEnterNumber;
import com.example.riyasewana.Fragments.MainScreen;
import com.example.riyasewana.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    //Variables
    TextView txtRegister,txtForgotPass;
    TextInputLayout uname,password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        txtRegister = findViewById(R.id.registerActivityMover);
        txtForgotPass = findViewById(R.id.txtForgotPassword);
        uname = findViewById(R.id.LoginUserName);
        password = findViewById(R.id.LoginUserPassword);
        loginBtn = findViewById(R.id.btnLogin);


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

    public void validateLogins(View view){

        if(!isConnected(this)){
            showCustomDialog();
        }else{
            //validate username and password fields
            if(!validateUserName() | !validatePassword()){
                return;
            }else{
                Intent i = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(i);
            }
        }



    }
    //custom dialog box
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setMessage("Please Connect to internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),Welcome.class));
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //checking connection
    private boolean isConnected(Login login) {
        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean validateUserName(){
        String value = uname.getEditText().getText().toString().trim();

        if(value.isEmpty()){
            uname.setError("User Name field cannot be empty!");
            return false;
        }
        else{
            uname.setError(null);
            uname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){
        String value = password.getEditText().getText().toString().trim();

        if(value.isEmpty()){
            password.setError("Password field cannot be empty!");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}