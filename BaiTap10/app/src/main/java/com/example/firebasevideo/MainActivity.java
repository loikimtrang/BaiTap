package com.example.firebasevideo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        checkUserLoginStatus();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            } else if (id == R.id.menu_upload) {
                if (checkUserLoginStatus()) {
                    startActivity(new Intent(this, UploadActivity.class));
                }
            } else if (id == R.id.menu_web) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new WebViewFragment())
                        .commit();
            } else if (id == R.id.logout) {
                mAuth.signOut();
                Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            return true;
        });

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.menu_home);
        }
    }

    private boolean checkUserLoginStatus() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserLoginStatus();
    }
}
