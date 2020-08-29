package com.example.riyasewana.Extra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.riyasewana.LoginRegister.Welcome;
import com.example.riyasewana.R;

public class TermsAndConditions extends AppCompatActivity {

    //Variables
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        //Hooks
        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TermsAndConditions.this, Welcome.class);
                startActivity(i);
                finish();
            }
        });
    }
}