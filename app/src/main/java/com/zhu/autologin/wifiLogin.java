package com.zhu.autologin;

import android.app.Service;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;

public class wifiLogin extends Service {
    private WiFiReceiver wifiLoginChangeReceiver = null;
    private IntentFilter intentFilter = null;


    public wifiLogin() {  //构造函数

//    服务会结束？

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("wang", "OnCreate 服务启动时调用");

        //注册“网络变化”的广播接收器
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        wifiLoginChangeReceiver = new WiFiReceiver();
        registerReceiver(wifiLoginChangeReceiver, intentFilter);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //服务被关闭时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("wang", "onDestroy 服务关闭时");
    }


}







