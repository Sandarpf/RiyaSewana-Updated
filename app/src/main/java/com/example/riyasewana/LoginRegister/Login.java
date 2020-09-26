package com.example.riyasewana.LoginRegister;

import androidx.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riyasewana.ForgotPassword.ForgotPasswordEnterNumber;
import com.example.riyasewana.Fragments.MainScreen;
import com.example.riyasewana.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    //Variables
    TextView txtRegister, txtForgotPass;
    TextInputLayout mail, password;
    Button loginBtn;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        txtRegister = findViewById(R.id.registerActivityMover);
        txtForgotPass = findViewById(R.id.txtForgotPassword);
        mail = findViewById(R.id.LoginUserName);
        password = findViewById(R.id.LoginUserPassword);
        loginBtn = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBarLogin);


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
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

    public void validateLogins(View view) {
        if (!isConnected(this)) {
            showCustomDialog();
        } else {
            //validate username and password fields
            if (!validateEmailName() | !validatePassword()) {
                return;
            } else {
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth = FirebaseAuth.getInstance();
                String email = mail.getEditText().getText().toString().trim();
                String pass = password.getEditText().getText().toString().trim();

                //logging in the user
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //if the task is successfull
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(new Intent(getApplicationContext(), MainScreen.class));
                                    finish();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Error! :" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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
                        startActivity(new Intent(getApplicationContext(), Welcome.class));
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

        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateEmailName() {
        String value = mail.getEditText().getText().toString().trim();
        String checkemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //email check


        if (value.isEmpty()) {
            mail.setError("Email field cannot be empty!");
            return false;
        }else if(!value.matches(checkemail)){
            mail.setError("Invalid Email Address!");
            return false;
        } else {
            mail.setError(null);
            mail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String value = password.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            password.setError("Password field cannot be empty!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}