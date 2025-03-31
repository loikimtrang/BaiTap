package com.example.viewflipper_cricleindicator;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator3;

public class ViewPager2Activity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator;
    private List<ImageModel> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        viewPager2 = findViewById(R.id.viewPager2);
        circleIndicator = findViewById(R.id.circle_indicator);

        images = new ArrayList<>();
        images.add(new ImageModel(R.drawable.image1));
        images.add(new ImageModel(R.drawable.image2));
        images.add(new ImageModel(R.drawable.image3));
        images.add(new ImageModel(R.drawable.image4));

        ImageAdapter adapter = new ImageAdapter(images);
        viewPager2.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager2);
    }
}