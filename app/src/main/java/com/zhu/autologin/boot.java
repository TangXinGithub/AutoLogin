package com.zhu.autologin;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.util.Log;

import java.util.List;

public class boot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        myLog.i("bootcomplete", "bootcomplete启动了");
        Log.i("bootlog", "bootlogbroact");

        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        PackageManager pm = context.getPackageManager();    //包管理者
//                开机也启动一下服务
        //  final Intent in = new Intent(context, wifiLogin.class); //5.0后要这么写
        //  context.startService(in);
        //意图


        Intent it = pm.getLaunchIntentForPackage("com.zhu.autologin");   //值为应用的包名
        if (null != it) {

            context.startActivity(it);         //启动意图
        }
    }

}


