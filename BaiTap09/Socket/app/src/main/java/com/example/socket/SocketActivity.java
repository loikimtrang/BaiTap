package com.example.socket;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.PrintWriter;
import java.net.Socket;

public class SocketActivity extends AppCompatActivity {

    private final String SERVER_IP = "192.168.1.100";
    private final int SERVER_PORT = 12345;

    Button btnTurnOn, btnTurnOff;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        btnTurnOn = findViewById(R.id.btnTurnOn);
        btnTurnOff = findViewById(R.id.btnTurnOff);

        btnTurnOn.setOnClickListener(v -> sendSignal("ON"));

        btnTurnOff.setOnClickListener(v -> sendSignal("OFF"));
    }

    private void sendSignal(final String signal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println(signal); // Gửi tín hiệu
                    writer.close();
                    socket.close();

                    runOnUiThread(() -> Toast.makeText(SocketActivity.this, "Gửi tín hiệu: " + signal, Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(SocketActivity.this, "Không thể gửi tín hiệu", Toast.LENGTH_SHORT).show());
                }
            }
        }).start();
    }
}

