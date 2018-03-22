package com.demo.legend.connectivitydemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 *
 * Created by legend on 2018/3/20.
 */

public class ClientThread extends Thread {

    private BluetoothSocket socket;
    private BluetoothDevice device;

    public ClientThread(BluetoothDevice device) {

        this.device = device;

        UUID uuid=UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
        try {
            this.socket=device.createRfcommSocketToServiceRecord(uuid);

            Log.d("uuid--->>",uuid.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        super.run();

        try {
            socket.connect();

            SocketManager.getSocketManager().setSocket(socket);

        } catch (IOException e) {

            e.printStackTrace();

            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Log.d("client--->>","faild");

            return;

        }



        //写其他逻辑

    }


    public void cancel(){

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
