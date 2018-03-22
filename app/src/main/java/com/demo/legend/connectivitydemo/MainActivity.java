package com.demo.legend.connectivitydemo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity{

    Button server,client;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DeviceAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent();

        initList();
        click();
    }

    private void getComponent(){
        server=findViewById(R.id.server);
        client=findViewById(R.id.client);
        recyclerView=findViewById(R.id.device_list);
    }

    private void initList(){

        linearLayoutManager=new LinearLayoutManager(this);
        adapter=new DeviceAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void click(){

        server.setOnClickListener(v -> {
            setServer();
        });

        client.setOnClickListener(v -> {
            getDevice();
        });

    }

    private void setServer(){

        SocketManager.getSocketManager().setActivity(this);

        BluetoothAdapter adapter=BluetoothUtil.getUtil().getAdapter(this);
        if (adapter==null||!adapter.isEnabled()){
            return;
        }
        SocketManager.getSocketManager().makeServerSocket(adapter);

    }

    private void getDevice(){

        SocketManager.getSocketManager().setActivity(this);

        BluetoothAdapter adapter=BluetoothUtil.getUtil().getAdapter(this);

        if (adapter==null||!adapter.isEnabled()){
            return;
        }

        Set<BluetoothDevice> deviceSet=adapter.getBondedDevices();

        List<BluetoothDevice> deviceList=new ArrayList<>();

        deviceList.addAll(deviceSet);

        this.adapter.setDeviceList(deviceList);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 300:

                switch (resultCode){
                    case RESULT_OK:



                        break;
                    case RESULT_CANCELED:



                        break;
                }

                break;
        }

    }
}
