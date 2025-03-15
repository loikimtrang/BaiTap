package com.example.girdview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String[] names = {"Java", "Kotlin", "Python", "PHP", "JavaScript", "C#"};
    int[] images = {R.drawable.java, R.drawable.kotlin, R.drawable.python, R.drawable.php, R.drawable.javascript, R.drawable.c_sharp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        LanguageAdapter adapter = new LanguageAdapter(this, names, images);
        gridView.setAdapter(adapter);
    }
}
