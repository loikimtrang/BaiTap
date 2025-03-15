package com.example.btimageview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Switch;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainLayout;
    private Switch switchChangeBackground;

    private final int[] backgrounds = {
            R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
            R.drawable.bg4,
            R.drawable.bg5
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        switchChangeBackground = findViewById(R.id.swBg);

        changeBackground();

        switchChangeBackground.setOnCheckedChangeListener((buttonView, isChecked) -> changeBackground());
    }

    private void changeBackground() {
        Random random = new Random();
        int randomIndex = random.nextInt(backgrounds.length);

        Drawable drawable = ContextCompat.getDrawable(this, backgrounds[randomIndex]);
        mainLayout.setBackground(drawable);
    }
}
