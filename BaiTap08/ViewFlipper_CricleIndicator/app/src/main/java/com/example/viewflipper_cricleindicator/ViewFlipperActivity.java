package com.example.viewflipper_cricleindicator;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ViewFlipperActivity extends AppCompatActivity {
    ViewFlipper viewFlipperMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        viewFlipperMain = findViewById(R.id.viewFlipperMain);
        ActionViewFlipperMain();
    }

    private void ActionViewFlipperMain() {
        List<Integer> arrayListFlipper = new ArrayList<>();
        arrayListFlipper.add(R.drawable.image1);
        arrayListFlipper.add(R.drawable.image2);
        arrayListFlipper.add(R.drawable.image3);
        arrayListFlipper.add(R.drawable.image4);

        for (Integer resId : arrayListFlipper) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resId);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);
        }

        viewFlipperMain.setFlipInterval(3000);
        viewFlipperMain.setAutoStart(true);
    }
}
