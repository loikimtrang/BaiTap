package com.example.socket;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class BlueActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private TextView tvDeviceName, tvConnectionStatus;
    private Spinner spinnerDevices;
    private Button btnConnectDevice, btnTurnOn, btnTurnOff;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String selectedDeviceAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue);

        tvDeviceName = findViewById(R.id.tvDeviceName);
        tvConnectionStatus = findViewById(R.id.tvConnectionStatus);
        spinnerDevices = findViewById(R.id.spinnerDevices);
        btnConnectDevice = findViewById(R.id.btnConnectDevice);
        btnTurnOn = findViewById(R.id.btnTurnOn);
        btnTurnOff = findViewById(R.id.btnTurnOff);

        btnTurnOn.setEnabled(false);
        btnTurnOff.setEnabled(false);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        checkBluetoothPermissions();
        loadPairedDevices();

        btnConnectDevice.setOnClickListener(v -> connectBluetooth());
        btnTurnOn.setOnClickListener(v -> sendSignal("ON"));
        btnTurnOff.setOnClickListener(v -> sendSignal("OFF"));
    }

    private void checkBluetoothPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.BLUETOOTH_CONNECT, android.Manifest.permission.BLUETOOTH_SCAN},
                        1);
            }
        }
    }

    private void loadPairedDevices() {
        if (bluetoothAdapter == null) {
            tvConnectionStatus.setText("Trạng thái: Thiết bị không hỗ trợ Bluetooth");
            Toast.makeText(this, "Thiết bị không hỗ trợ Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            tvConnectionStatus.setText("Trạng thái: Bluetooth chưa bật");
            Toast.makeText(this, "Hãy bật Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Quyền Bluetooth chưa được cấp", Toast.LENGTH_SHORT).show();
            return;
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList<String> deviceNames = new ArrayList<>();
        ArrayList<String> deviceAddresses = new ArrayList<>();

        for (BluetoothDevice device : pairedDevices) {
            deviceNames.add(device.getName());
            deviceAddresses.add(device.getAddress());
        }

        if (deviceNames.isEmpty()) {
            Toast.makeText(this, "Không có thiết bị nào được ghép đôi", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deviceNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevices.setAdapter(adapter);

        spinnerDevices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDeviceAddress = deviceAddresses.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedDeviceAddress = null;
            }
        });
    }

    private void connectBluetooth() {
        try {
            if (selectedDeviceAddress == null) {
                Toast.makeText(this, "Vui lòng chọn một thiết bị để kết nối", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                tvConnectionStatus.setText("Trạng thái: Quyền Bluetooth chưa được cấp");
                Toast.makeText(this, "Quyền Bluetooth chưa được cấp", Toast.LENGTH_SHORT).show();
                return;
            }

            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(selectedDeviceAddress);
            tvDeviceName.setText("Tên thiết bị: " + device.getName());

            bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            bluetoothSocket.connect();
            outputStream = bluetoothSocket.getOutputStream();

            tvConnectionStatus.setText("Trạng thái: Kết nối thành công");
            Toast.makeText(this, "Kết nối Bluetooth thành công", Toast.LENGTH_SHORT).show();

            btnTurnOn.setEnabled(true);
            btnTurnOff.setEnabled(true);
        } catch (Exception e) {
            tvConnectionStatus.setText("Trạng thái: Kết nối thất bại");
            Toast.makeText(this, "Kết nối Bluetooth thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSignal(String signal) {
        try {
            if (outputStream != null) {
                outputStream.write(signal.getBytes());
                Toast.makeText(this, "Gửi tín hiệu: " + signal, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Không thể gửi tín hiệu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadPairedDevices();
            } else {
                Toast.makeText(this, "Quyền Bluetooth bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
