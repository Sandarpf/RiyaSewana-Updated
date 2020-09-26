package com.example.riyasewana.ForgotPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.riyasewana.LoginRegister.RegisterVerifyCode;
import com.example.riyasewana.Models.UsersModel;
import com.example.riyasewana.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class ForgotPasswordEnterNumber extends AppCompatActivity {

    //Variables
    Button btnNext;
    TextInputLayout mobileNumber;
    CountryCodePicker countryCodePicker;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_enter_number);
        FirebaseAuth.getInstance().signOut();

        //Hooks
        btnNext = findViewById(R.id.btnForgotPassNumVerify);
        mobileNumber = findViewById(R.id.forgotPasswordMobileNumber);
        countryCodePicker = findViewById(R.id.countryCodePickerForgotPassword);
        progressBar = findViewById(R.id.progressBarForgotPassMobile);

    }

    public void checkNumber(View view) {
        if (!validateNumber()) {
            return;
        } else {
            progressBar.setVisibility(View.VISIBLE);
            final String enteredMobileNumber = mobileNumber.getEditText().getText().toString().trim();
            final String mNumber = "+" + countryCodePicker.getFullNumber() + enteredMobileNumber;

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();


            rootRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> userChildren = dataSnapshot.getChildren();

                    for (DataSnapshot user: userChildren) {
                        UsersModel u = user.getValue(UsersModel.class);      //make a model User with necessary fields

                        if(u.getMobileNumber().equalsIgnoreCase(mNumber)){
                            Intent i = new Intent(getApplicationContext(), ForgotPasswordVeriyCode.class);
                            i.putExtra("enteredNum", enteredMobileNumber);
                            i.putExtra("countryCode", "+" + countryCodePicker.getFullNumber());
                            i.putExtra("mNumber", mNumber);
                            progressBar.setVisibility(View.GONE);
                            startActivity(i);

                        }else{
                            progressBar.setVisibility(View.GONE);
                            mobileNumber.setError("No Such Mobile Number Exist!");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Error! : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private boolean validateNumber() {
        String value = mobileNumber.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            mobileNumber.setError("Mobile Number field cannot be empty!");
            return false;
        } else if (value.length() != 10) {
            mobileNumber.setError("Invalid Mobile Number!");
            return false;
        } else {
            mobileNumber.setError(null);
            mobileNumber.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth.getInstance().signOut();
    }
}