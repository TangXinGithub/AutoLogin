package com.zhu.autologin;

import android.app.Service;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

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
        Toast.makeText(this, "服务创建", Toast.LENGTH_LONG).show();
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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub我对这个函数的理解是：当服务被异常终止时，是否重启服务?
        return START_STICKY;
    }
    //服务被关闭时调用
    @Override
    public void onDestroy() {
//        发送广播不让关掉
        stopForeground(true);
        Intent intent = new Intent("com.zhu.autologin.destroy");
        sendBroadcast(intent);
//        重启服务，不让杀掉
        Intent sevice = new Intent(this, wifiLogin.class);
        this.startService(sevice);
        super.onDestroy();
        Log.v("wang", "onDestroy 服务关闭时");
    }


}







