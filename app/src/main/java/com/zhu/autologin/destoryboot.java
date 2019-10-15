package com.zhu.autologin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class destoryboot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        final Intent iin = new Intent(context, wifiLogin.class); //5.0后要这么写
        context.startService(iin);
        myLog.i("destory", "destorybootservices");

    }
}
