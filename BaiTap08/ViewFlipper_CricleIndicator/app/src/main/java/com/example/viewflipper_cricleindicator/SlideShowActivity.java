package com.example.viewflipper_cricleindicator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class SlideShowActivity extends AppCompatActivity {

    private SliderView sliderView;
    private List<ImageModel> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);

        sliderView = findViewById(R.id.imageSlider);

        images = new ArrayList<>();
        images.add(new ImageModel(R.drawable.image1));
        images.add(new ImageModel(R.drawable.image2));
        images.add(new ImageModel(R.drawable.image3));
        images.add(new ImageModel(R.drawable.image4));

        SliderAdapter adapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(adapter);
        sliderView.setAutoCycle(true);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(3);
        sliderView.startAutoCycle();
    }
}
