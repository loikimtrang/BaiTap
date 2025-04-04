package com.example.blue;

import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.io.OutputStream;

public class BlueControl {
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;

    public BlueControl(BluetoothSocket socket) {
        this.bluetoothSocket = socket;
        try {
            this.outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        try {
            outputStream.write(command.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

