package com.zhu.autologin;

import android.app.Service;
import android.content.ComponentName;
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
        Toast.makeText(this, "服务创建时", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent("com.zhu.autologin.destorybootbroadcast");
        //安桌9而要如果是再同一个包内接收广播，在发送广播时需要添加接收的广播的完整路径和类名，
        //查看ComponentName类的源码，该类的介绍上说明要设置接收类所在的包名和类名如果是需要在不同的包里接收，两个及以上的module，需要修改代码如下：
        //
        // @Override
        // public void onClick(View view) {
        //       Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
        //       if(Build.VERSION.SDK_INT >= 26) {
        //            intent.addFlags(0x01000000);
        //       }
        //       sendBroadcast(intent);
        //
        //————————————————
        //版权声明：本文为CSDN博主「XingTina」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/XingTina/article/details/101304580
        intent.setComponent(new ComponentName("com.zhu.autologin",
                "com.zhu.autologin.destorybootbroadcast.destoryboot"));
        sendBroadcast(intent);
        Intent sevice = new Intent(this, wifiLogin.class);
        this.startService(sevice);
        super.onDestroy();
        myLog.i("wang", "onDestroy 服务关闭时mylog");
        Log.v("wang", "onDestroy 服务关闭时");
    }


}







