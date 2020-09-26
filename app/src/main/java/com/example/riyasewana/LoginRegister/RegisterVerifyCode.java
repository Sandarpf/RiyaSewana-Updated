package com.example.riyasewana.LoginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.riyasewana.Fragments.MainScreen;
import com.example.riyasewana.Models.UsersModel;
import com.example.riyasewana.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class RegisterVerifyCode extends AppCompatActivity {

    PinView mobilePin;
    TextView code,number,resendCode;
    String codeBySystem;
    ProgressBar progressBar;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_verify_code);

        mobilePin = findViewById(R.id.pinViewRegister);
        code = findViewById(R.id.countryCodeRegisterText);
        number = findViewById(R.id.mobileNumberRegisterText);
        progressBar = findViewById(R.id.progressBarRegister2);
        resendCode = findViewById(R.id.textViewResendCode);

        final String mNumber = getIntent().getStringExtra("phoneNo");


        String num = getIntent().getStringExtra("enteredNumber");
        String ccode = getIntent().getStringExtra("countryCode");
        code.setText(ccode);
        number.setText(num);

        sendVerificationCodeToUser(mNumber);

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCodeToUser(mNumber);
                Toast.makeText(getApplicationContext(), "Resend Code Request Sent!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validatePin(View view){
        progressBar.setVisibility(View.VISIBLE);
        String code = mobilePin.getText().toString();
        if(!code.isEmpty()){
            verifyCode(code);
        }
    }

    private void sendVerificationCodeToUser(String mNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mNumber,        // Phone number to verify
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
        if(!codeBySystem.isEmpty()){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
            signInWithPhoneAuthCredential(credential);
        }else{
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Code didn't Sent To You Yet!", Toast.LENGTH_SHORT).show();
        }

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {


        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            linkEmail();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Authentication failed. :"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void linkEmail() {
        final String cusName = getIntent().getStringExtra("customerName");
        final String cusUName = getIntent().getStringExtra("customerUserName");
        final String cusMail = getIntent().getStringExtra("customerEmail");
        final String mNumber = getIntent().getStringExtra("phoneNo");
        String cusPass = getIntent().getStringExtra("customerPassword");


        AuthCredential credential = EmailAuthProvider.getCredential(cusMail, cusPass);

        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            UsersModel usersModel = new UsersModel(cusUName,cusName,cusMail,mNumber);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(usersModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Data Added To Database.", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(),MainScreen.class);
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(i);
                                        finish();
                                    }else{
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "Data Not Added To Database.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show();

                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String mNumber = getIntent().getStringExtra("phoneNo");
        sendVerificationCodeToUser(mNumber);
    }
}