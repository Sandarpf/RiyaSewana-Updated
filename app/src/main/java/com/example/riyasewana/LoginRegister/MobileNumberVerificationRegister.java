package com.example.riyasewana.LoginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.riyasewana.Models.UsersModel;
import com.example.riyasewana.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class MobileNumberVerificationRegister extends AppCompatActivity {

    //Variables
    Button btnNextToVerifyNumber;
    TextInputLayout mobileNumber;
    CountryCodePicker countryCodePicker;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mobile_number_verification_register);

        //Hooks
        btnNextToVerifyNumber = findViewById(R.id.btnNextToEVerifyMobileNumber);
        mobileNumber = findViewById(R.id.registerMobileNumber);
        countryCodePicker = findViewById(R.id.countryCodePickerRegister);
        progressBar = findViewById(R.id.progressBarRegisterMobile);
    }
    public void mobileNumberVerify(View view){
        if(!verifyMobile()){
            return;
        }
        else{

            final String cusName = getIntent().getStringExtra("customerName");
            final String cusUName = getIntent().getStringExtra("customerUserName");
            final String cusMail = getIntent().getStringExtra("customerEmail");
            final String cusPass = getIntent().getStringExtra("customerPassword");

            final String enteredMobileNumber = mobileNumber.getEditText().getText().toString().trim();
            final String mNumber = "+"+countryCodePicker.getFullNumber()+enteredMobileNumber;

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();


            rootRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> userChildren = dataSnapshot.getChildren();

                    for (DataSnapshot user: userChildren) {
                        UsersModel u = user.getValue(UsersModel.class);      //make a model User with necessary fields

                        if(u.getMobileNumber().equalsIgnoreCase(mNumber)){
                            progressBar.setVisibility(View.GONE);
                            mobileNumber.setError("Mobile Number Already Exist!");
                        }else{
                            Intent i = new Intent(getApplicationContext(),RegisterVerifyCode.class);
                            i.putExtra("customerName",cusName);
                            i.putExtra("customerUserName",cusUName);
                            i.putExtra("customerEmail",cusMail);
                            i.putExtra("customerPassword",cusPass);
                            i.putExtra("phoneNo",mNumber);
                            i.putExtra("countryCode",'+'+countryCodePicker.getFullNumber());
                            i.putExtra("enteredNumber",enteredMobileNumber);
                            startActivity(i);
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

    private boolean verifyMobile(){
        String value = mobileNumber.getEditText().getText().toString().trim();

        if(value.isEmpty()){
            mobileNumber.setError("Mobile Number field cannot be empty!");
            return false;
        }else if(value.length() != 10){
            mobileNumber.setError("Invalid Mobile Number!");
            return false;
        }
        else{
            mobileNumber.setError(null);
            mobileNumber.setErrorEnabled(false);
            return true;
        }
    }
}