package com.demo.legend.connectivitydemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 蓝牙连接线程
 * Created by legend on 2018/3/19.
 */

public class ServerThread extends Thread {


    private BluetoothServerSocket serverSocket;
    private OutputStream outputStream;
    private static final String ID = "fa87c0d0-afac-11de-8a39-0800200c9a66";

    public ServerThread(BluetoothAdapter adapter) {

        try {
            UUID uuid = UUID.fromString(ID);
            serverSocket = adapter.listenUsingRfcommWithServiceRecord("test", uuid);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        super.run();

        BluetoothSocket socket = null;

        while (true) {

            try {
                socket = this.serverSocket.accept();
                outputStream = socket.getOutputStream();

            } catch (IOException e) {

                e.printStackTrace();
            }


            if (socket != null) {

                try {
                    serverSocket.close();

                    SocketManager.getSocketManager().setSocket(socket);//将生成的socket交给manager


                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            }

        }
    }


    public void cancel() {

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
