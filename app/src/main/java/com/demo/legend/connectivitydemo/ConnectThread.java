package com.demo.legend.connectivitydemo;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 连接线程，负责传输信息
 * Created by legend on 2018/3/21.
 */

public class ConnectThread extends Thread {

    private InputStream inputStream;
    private OutputStream outputStream;
    private BluetoothSocket socket;
    private int STATE=-1;
    public static final int CONNECT=0x000100;
    public static final int DISCONNECT=0x000200;
    private Handler handler;

    public ConnectThread(BluetoothSocket socket, Handler handler, Activity activity) {
        this.socket = socket;
        this.handler=handler;
        try {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            Intent intent=new Intent(activity,ChatActivity.class);
            activity.startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSTATE(int STATE) {
        this.STATE = STATE;
    }

    @Override
    public void run() {
        super.run();
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes;
        while (STATE==CONNECT){
            try {
                bytes=inputStream.read(buffer);

                Msg msg=new Msg(buffer,bytes);

                handler.obtainMessage(10,msg).sendToTarget();

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 发送信息
     */
    public void sendMessage(String msg){
        if (this.outputStream==null){
            return;
        }
        if (STATE!=CONNECT){
            return;
        }

        byte[] bytes=msg.getBytes();

        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            cancel();
            e.printStackTrace();
        }
    }

    public void cancel(){

        try {
            this.STATE=DISCONNECT;
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
