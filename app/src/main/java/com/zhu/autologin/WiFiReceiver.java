package com.zhu.autologin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.net.wifi.WifiManager.WIFI_STATE_DISABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_DISABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_UNKNOWN;

public class WiFiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");   这个相当于是return 了，所以后面有语句会有错误
//网络打开与否
        Log.i("broadcastOpen", "广播触发");


        Toast.makeText(context, "广播触发", Toast.LENGTH_LONG).show();

        //网络改变
        if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {  //打开wifi会触发这么多次吗

//       intent.getExtras();
            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            int b = intent.getExtras().getInt("previous_wifi_state");
            int c = intent.getExtras().getInt("wifi_state");
//        Bundle cc = intent.getExtras();
//        for (Iterator<Map.Entry<String, Integer>> it = cc.mMap.entrySet().iterator() ; it.hasNext();){
//            Map.Entry<String, Integer> item = it.next();
//            Log.i("aaa", String.valueOf(item.getValue()));
//        }
            if (b == 2 && c == 3) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //解决在子线程中调用Toast的异常情况处理
                        Looper.prepare();
                        Toast.makeText(context, "wifi开始登录", Toast.LENGTH_LONG).show();
                        Looper.loop();
                        okhttp();
                    }
                }).start();

            }
//            // 获取联网状态的NetWorkInfo对象
//            NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
//            //获取的State对象则代表着连接成功与否等状态
//            NetworkInfo.State state = networkInfo.getState();
//            Log.e("TAG", String.valueOf(networkInfo.getState()));
//            //判断网络是否已经连接
//            boolean isConnected = state == NetworkInfo.State.CONNECTED;
//            Log.e("TAG", "isConnected:" + isConnected);
//
//            if (isConnected) {
//                Log.e("TAG","已连接和连接中");
//                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo netInfo = manager.getActiveNetworkInfo();
////————————————————
////                版权声明：本文为CSDN博主「程大龙」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
////                原文链接：https://blog.csdn.net/qq_25749749/article/details/82344463
//                if (networkInfo.isConnected()) {
//                    String type = networkInfo.getTypeName();
//                    Log.i("debug",type);
//                    if (type.equalsIgnoreCase("WIFI")) {
//                        //openapp(context); //打开外部应用
//                        if("金色农家".equals(getWIFISSID(context)))
//                            okhttp();
//
//                    }}
//            } else {
//
//            }
        }

//开机启动

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d("BootBroadcastReceiver", "the program has received BOOT_BROADCAST");
            //Toast.makeText(context, "Boot complete!!!", Toast.LENGTH_SHORT).show();

            PackageManager pm = context.getPackageManager();    //包管理者
            Intent it = new Intent();                           //意图
            it = pm.getLaunchIntentForPackage("com.zhu.autologin");   //值为应用的包名
            if (null != it) {
                context.startActivity(it);         //启动意图
            }
        }

    }

    public void openapp(Context context) {
        //获取wifi信息


//        //获取当前wifi名称
        //System.out.println("getwifissid值是"+ getWIFISSID(context));

//        Log.e("相似度",String.valueOf(compare(getWIFISSID(context),"金色农家")));//越大越相似

        //if("金色农家".equals(getWIFISSID(context))) {//compare(getWIFISSID(context),"金色农家")<2
//             Uri uri = Uri.parse("http://202.193.80.124/a70.htm");
//             Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//             // Intent intent=new Intent(context,AnotherActivity.class);
//             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//             context.startActivity(intent);//有上一个context才能打开activity
        //}
        //else {Log.e("名称","名称不同？");};
        //.startActivity(intent);
//         * 1 在普通情况下,必须要有前一个Activity的Context,才能启动后一个Activity
//                * 2 但是在BroadcastReceiver里面是没有Activity的Context的
//                * 3 对于startActivity()方法,源码中有这么一段描述:
// *   Note that if this method is being called from outside of an
// *   {@link android.app.Activity} Context, then the Intent must include
// *   the {@link Intent#FLAG_ACTIVITY_NEW_TASK} launch flag.  This is because,
// *   without being started from an existing Activity, there is no existing
//                *   task in which to place the new activity and thus it needs to be placed
//                *   in its own separate task.
// *   说白了就是如果不加这个flag就没有一个Task来存放新启动的Activity.
//                https://www.cnblogs.com/bigben0123/p/4305307.html
    }


    public String getWIFISSID(Context context) {
        String ssid = "unknown id";

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O || Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {

            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            assert mWifiManager != null;
            WifiInfo info = mWifiManager.getConnectionInfo();

            return info.getSSID().replace("\"", "");
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) {

            ConnectivityManager connManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connManager != null;
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo.isConnected()) {
                if (networkInfo.getExtraInfo() != null) {
                    return networkInfo.getExtraInfo().replace("\"", "");
                }
            }
        }
        return ssid;
    }

//    https://blog.csdn.net/gengkui9897/article/details/82863966  高版本的要用https解决，或者在manifest中加入。。。。。。
//

//https://blog.csdn.net/u010687392/article/details/43705927

    public void okhttp() {
        String data = "DDDDD=s3172052051626&upass=123456&R1=0&R2=&R3=0&R6=0&para=00&0MKKey=123456&buttonClicked=&redirect_url=&err_flag=&username=&password=&user=&cmd=&Login=&v6ip=";
        //  http://202.193.80.124/a70.htm
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("http://202.193.80.124/a70.htm").post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"), data)).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String postcontent;
                if (response.body() != null)
                    postcontent = response.body().string();
                else postcontent = "";
                System.out.println("postcontent" + postcontent);
            }
        });


    }


}
