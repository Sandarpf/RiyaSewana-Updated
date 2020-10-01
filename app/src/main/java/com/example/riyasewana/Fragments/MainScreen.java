package com.example.riyasewana.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.riyasewana.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_screen);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new Home()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment = null;

                    switch (item.getItemId())
                    {
                        case R.id.bottom_home:
                            fragment = new Home();
                            break;

                        case R.id.bottom_search:
                            fragment = new Search();
                            break;

                        case R.id.bottom_favourites:
                            fragment = new Favourites();
                            break;

                        case R.id.bottom_add:
                            fragment = new Add();
                            break;

                        case R.id.bottom_profile:
                            fragment = new Profile();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,fragment).commit();

                    return true;
                }
            };
}