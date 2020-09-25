package com.example.riyasewana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.riyasewana.BordingScreen.BordingScreen;
import com.example.riyasewana.LoginRegister.Welcome;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    //Variables
    Animation topAnimation;
    ImageView image;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Hooks
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        image = findViewById(R.id.CompanyLogo);

        image.setAnimation(topAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);

                if(isFirstTime){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent intent = new Intent(SplashScreen.this, BordingScreen.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashScreen.this, Welcome.class);
                    startActivity(intent);
                    finish();
                }


            }
        },SPLASH_SCREEN);
    }
}