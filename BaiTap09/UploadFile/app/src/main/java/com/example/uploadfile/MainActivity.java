package com.example.uploadfile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnEdit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnEdit = findViewById(R.id.btnEditProfile);

        btnEdit.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, UploadFileActivity.class);
            startActivity(it);
        });
    }
}