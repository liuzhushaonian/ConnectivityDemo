package com.demo.legend.connectivitydemo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * socket管理
 * 连接好之后
 * Created by legend on 2018/3/21.
 */

public class SocketManager {


    private BluetoothSocket socket;
    private static volatile SocketManager socketManager;
    private ConnectThread connectThread;
    private Activity activity;
    private MessageAdapter adapter;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public static SocketManager getSocketManager(){

        if (socketManager==null) {

            synchronized (SocketManager.class) {
                socketManager=new SocketManager();
            }
        }

        return socketManager;

    }


    public BluetoothSocket getSocket() {
        return socket;
    }


    public void setSocket(BluetoothSocket socket) {

        this.socket = socket;

        startConnection();
    }

    private void startConnection(){
        connectThread=new ConnectThread(this.socket,handler,activity);

        connectThread.setSTATE(ConnectThread.CONNECT);
        connectThread.start();
    }

    public void stopConnection(){

        if (this.connectThread!=null){
            connectThread.cancel();
        }

    }


    /**
     * 设置为服务端
     */
    public void makeServerSocket(BluetoothAdapter adapter){
        ServerThread serverThread=new ServerThread(adapter);
        serverThread.start();

    }

    /**
     * 设置为客户端
     */
    public void makeClientSocket(BluetoothDevice device) {

        ClientThread clientThread = new ClientThread(device);
        clientThread.start();
    }


    public void sendMessage(String msg){

        if (this.connectThread==null){

            Log.d("conn--->>",""+msg);
            return;
        }

        connectThread.sendMessage(msg);

    }

    private Handler handler=new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Msg msg1= (Msg) msg.obj;

            int count=msg1.getCount();
            byte[] bytes=msg1.getBytes();

            String s=new String(bytes,0,count);

//            Toast.makeText(ConnectApp.getContext(), ""+s, Toast.LENGTH_SHORT).show();

            if (adapter!=null){

                com.demo.legend.connectivitydemo.Message message=new com.demo.legend.connectivitydemo.Message(s,1);
                adapter.addMsg(message);
            }

        }
    };

    public void setAdapter(MessageAdapter adapter) {
        this.adapter = adapter;
    }
}
