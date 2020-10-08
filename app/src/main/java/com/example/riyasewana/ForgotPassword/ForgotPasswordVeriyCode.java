package com.example.riyasewana.ForgotPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.riyasewana.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ForgotPasswordVeriyCode extends AppCompatActivity {
    Button verifyBtn;
    TextView countryCode, Mobile,resendCode;
    ProgressBar progressBar;
    PinView mobilePin;
    String codeBySystem;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_veriy_code);

        String num = getIntent().getStringExtra("enteredNum");
        String ccode = getIntent().getStringExtra("countryCode");
        final String cusNumber = getIntent().getStringExtra("mNumber");

        verifyBtn = findViewById(R.id.verifyCodeForgotPass);
        countryCode = findViewById(R.id.countryCodeMobile);
        Mobile = findViewById(R.id.mobileNumberForgotPass);
        progressBar = findViewById(R.id.progressBarForgotPassVerifyCode);
        mobilePin = findViewById(R.id.pinViewForgotPassword);
        resendCode = findViewById(R.id.forgotPassVerifyCode);

        countryCode.setText(ccode);
        Mobile.setText(num);

        sendVerificationCodeToUser(cusNumber);

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String code = mobilePin.getText().toString();
                if(!code.isEmpty()){
                    verifyCode(code);
                }
            }
        });
        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCodeToUser(cusNumber);
            }
        });

    }

    private void sendVerificationCodeToUser(String cusNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                cusNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeBySystem = s;
            Toast.makeText(getApplicationContext(), "Code Send To You!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                mobilePin.setText(code);
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(),"Error : "+ e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {
        if(codeBySystem != null){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
            signInWithPhoneAuthCredential(credential);
        }else{
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Code didn't Sent To You Yet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(getApplicationContext(),ResetPassword.class);
                    startActivity(i);
                    finish();
                }else{
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        FirebaseAuth.getInstance().signOut();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Authentication failed. :"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String cusNumber = getIntent().getStringExtra("mNumber");
        sendVerificationCodeToUser(cusNumber);
    }
}