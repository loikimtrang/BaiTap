package com.example.socket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnBluetooth, btnSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnBluetooth = findViewById(R.id.blue);
        btnSocket = findViewById(R.id.socket);
        btnBluetooth.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, BlueActivity.class);
            startActivity(it);
        });
        btnSocket.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, SocketActivity.class);
            startActivity(it);
        });
    }
}