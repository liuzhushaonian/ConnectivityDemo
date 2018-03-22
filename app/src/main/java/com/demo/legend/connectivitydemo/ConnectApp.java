package com.demo.legend.connectivitydemo;

import android.app.Application;
import android.content.Context;

/**
 *
 * Created by legend on 2018/3/21.
 */

public class ConnectApp extends Application {

    private static Context context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}
