package com.example.blue;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ControlActivity extends AppCompatActivity {
    private BlueControl blueControl;
    private TextView txtDeviceStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        Button btnOn = findViewById(R.id.btnOn);
        Button btnOff = findViewById(R.id.btnOff);
        txtDeviceStatus = findViewById(R.id.txtDeviceStatus);

        blueControl = MainActivity.getBlueControlInstance();

        btnOn.setOnClickListener(v -> {
            if (blueControl != null) {
                blueControl.sendCommand("ON");
                txtDeviceStatus.setText("Trạng thái thiết bị: Đã bật");
            } else {
                txtDeviceStatus.setText("Không thể kết nối thiết bị.");
            }
        });

        btnOff.setOnClickListener(v -> {
            if (blueControl != null) {
                blueControl.sendCommand("OFF");
                txtDeviceStatus.setText("Trạng thái thiết bị: Đã tắt");
            } else {
                txtDeviceStatus.setText("Không thể kết nối thiết bị.");
            }
        });
    }
}

