package com.example.riyasewana.BordingScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.riyasewana.Adapters.SliderAdapter;
import com.example.riyasewana.LoginRegister.Welcome;
import com.example.riyasewana.R;
import com.example.riyasewana.SplashScreen;

public class BordingScreen extends AppCompatActivity {

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    Button getStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bording_screen);


        viewPager = findViewById(R.id.sliderScreens);
        getStartedBtn = findViewById(R.id.boardingScreenButton);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        viewPager.addOnPageChangeListener(changeListener);


        //give custom position to button
        getStartedBtn.setText(R.string.next);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = 0;
                viewPager.setCurrentItem(currentPosition + 1);
            }
        });
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {
            if (position == 0) {
                getStartedBtn.setText(R.string.next);
                getStartedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentPos = 0;
                        viewPager.setCurrentItem(currentPos + 1);
                    }
                });

            } else if (position == 1) {
                getStartedBtn.setText(R.string.next);
                getStartedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(position + 1);
                    }
                });

            } else {
                getStartedBtn.setText(R.string.letsStart);
                getStartedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Welcome.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), Welcome.class));
        finish();
    }


}