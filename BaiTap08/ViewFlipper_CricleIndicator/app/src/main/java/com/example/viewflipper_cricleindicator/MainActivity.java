package com.example.viewflipper_cricleindicator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnViewFlipper, btnViewpager, btnViewpager2, btnSliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnViewFlipper = findViewById(R.id.btnViewFlipper);
        btnViewFlipper.setOnClickListener(v -> {
            Intent it = new Intent(this, ViewFlipperActivity.class);
            startActivity(it);
        });

        btnViewpager = findViewById(R.id.btnViewpager);
        btnViewpager.setOnClickListener(v -> {
            Intent it = new Intent(this, ViewPagerActivity.class);
            startActivity(it);
        });

        btnViewpager2 = findViewById(R.id.btnViewpager2);
        btnViewpager2.setOnClickListener(v -> {
            Intent it = new Intent(this, ViewPager2Activity.class);
            startActivity(it);
        });

        btnSliderView = findViewById(R.id.btnSliderView);
        btnSliderView.setOnClickListener(v -> {
            Intent it = new Intent(this, SlideShowActivity.class);
            startActivity(it);
        });
    }
}