package com.example.firebasevideo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            } else if (id == R.id.menu_upload) {
                startActivity(new Intent(this, UploadActivity.class));
            } else if (id == R.id.menu_web) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new WebViewFragment())
                        .commit();
            }
            return true;
        });


        // Default tab
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.menu_home);
        }
    }
}