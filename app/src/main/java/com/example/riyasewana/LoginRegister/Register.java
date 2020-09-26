package com.example.riyasewana.LoginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riyasewana.Models.UsersModel;
import com.example.riyasewana.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    //Variables
    TextView txtLogin;
    TextInputLayout name, uname, email, password;
    Button nextToEnterMobileNumber;
    ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBarRegister1);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });
    }

    public void callNextActivityMobile(View view){


        if(!validateName() | !validateUserName() | !validateEmail() | !validatePassword()){
            return;
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            final Intent i = new Intent(Register.this,MobileNumberVerificationRegister.class);
            final String cusName = name.getEditText().getText().toString().trim();
            final String cusUName = uname.getEditText().getText().toString().trim();
            final String cusMail = email.getEditText().getText().toString().trim();
            final String cusPass = password.getEditText().getText().toString().trim();


            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

            rootRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> userChildren = dataSnapshot.getChildren();

                    for (DataSnapshot user: userChildren) {
                        UsersModel u = user.getValue(UsersModel.class);      //make a model User with necessary fields

                        if(u.getEmail().equalsIgnoreCase(cusMail)){
                            progressBar.setVisibility(View.GONE);
                            email.setError("Email Already Exist!");
                        }else{
                            i.putExtra("customerName",cusName);
                            i.putExtra("customerUserName",cusUName);
                            i.putExtra("customerEmail",cusMail);
                            i.putExtra("customerPassword",cusPass);
                            progressBar.setVisibility(View.GONE);
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