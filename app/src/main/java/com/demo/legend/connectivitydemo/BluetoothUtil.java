package com.demo.legend.connectivitydemo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import java.util.List;
import java.util.Set;

/**
 *
 * Created by legend on 2018/3/21.
 */

public class BluetoothUtil {

    private Activity activity;

    private static volatile BluetoothUtil util;

    public static BluetoothUtil getUtil(){

        if (util==null){
            synchronized (BluetoothUtil.class){

                util=new BluetoothUtil();
            }
        }

        return util;
    }

    public BluetoothAdapter getAdapter(Activity activity){
        BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();
        if (!adapter.isEnabled()){
            openBluetooth(activity);
        }

        return adapter;

    }


    private void discoverable(Activity activity) {

        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);//0到3600秒，0表示永远可以被检测到，不在这个范围内的都会被设置为120
        activity.startActivity(discoverableIntent);

    }

    private void openBluetooth(Activity activity) {

        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, 300);

    }


}
