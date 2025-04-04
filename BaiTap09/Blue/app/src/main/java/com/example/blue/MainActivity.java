package com.example.blue;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private TextView txtStatus;
    private static BlueControl blueControl;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public static BlueControl getBlueControlInstance() {
        return blueControl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConnect = findViewById(R.id.btnConnect);
        txtStatus = findViewById(R.id.txtStatus);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnConnect.setOnClickListener(v -> connectBluetooth());
    }

    private void connectBluetooth() {
        if (bluetoothAdapter == null) {
            txtStatus.setText("Bluetooth không khả dụng trên thiết bị này.");
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 1);
            }
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        } else {
            txtStatus.setText("Bluetooth đã được bật.");

            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    try {
                        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(MY_UUID);
                        socket.connect();

                        blueControl = new BlueControl(socket);

                        txtStatus.setText("Kết nối thành công với " + device.getName());

                        Intent intent = new Intent(MainActivity.this, ControlActivity.class);
                        startActivity(intent);
                        break;
                    } catch (Exception e) {
                        txtStatus.setText("Không thể kết nối tới " + device.getName());
                        e.printStackTrace();
                    }
                }
            } else {
                txtStatus.setText("Không có thiết bị đã ghép đôi.");
            }
        }
    }
}
