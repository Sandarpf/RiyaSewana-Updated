package com.example.riyasewana.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.riyasewana.Adapters.DonateAdapter;
import com.example.riyasewana.R;

import java.util.ArrayList;
import java.util.List;

public class DonateUs extends AppCompatActivity {

    ViewPager viewPager;
    Button btnBack;
    DonateAdapter donateAdapter;
    List<Donate> donates;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_us);

        donates = new ArrayList<>();
        donates.add(new Donate(R.drawable.basic,"LKR 250.00","We can do no great things, only small things with great love"));
        donates.add(new Donate(R.drawable.standard,"LKR 500.00","Your greatness is not what you have, It's what you give"));
        donates.add(new Donate(R.drawable.premier,"LKR 1000.00","Giving is not just about make a donation, It's about making a difference"));

        donateAdapter = new DonateAdapter(donates,this);

        btnBack = findViewById(R.id.btnBack);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(donateAdapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3)
        };

        colors = colors_temp;

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonateUs.this, MainScreen.class);
                startActivity(i);
                finish();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (donateAdapter.getCount() -1)  && position <(colors.length - 1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

}