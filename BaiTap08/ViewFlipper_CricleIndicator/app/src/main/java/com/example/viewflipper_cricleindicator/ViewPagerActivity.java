package com.example.viewflipper_cricleindicator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;

public class ViewPagerActivity extends AppCompatActivity {
    ViewPager viewPager;
    CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circleIndicator);

        List<Integer> imageIds = new ArrayList<>();
        imageIds.add(R.drawable.image1);
        imageIds.add(R.drawable.image2);
        imageIds.add(R.drawable.image3);
        imageIds.add(R.drawable.image4);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageIds);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
    }
}