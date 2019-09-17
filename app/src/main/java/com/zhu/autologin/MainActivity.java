package com.zhu.autologin;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.os.PersistableBundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private WiFiReceiver wifiLoginChangeReceiver = null;
    private IntentFilter intentFilter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        //注册“开机变化”的广播接收器
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");// 连上热点与否
        wifiLoginChangeReceiver = new WiFiReceiver();
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                PackageManager pm = context.getPackageManager();    //包管理者
                //意图
                Intent it = pm.getLaunchIntentForPackage("com.zhu.autologin");   //值为应用的包名
                if (null != it) {
                    context.startActivity(it);         //启动意图
                }
            }
        }, intentFilter);

        // wifiLogin wifiLoginwait=new wifiLogin(); 不是实例化开启动的 tmd
        // 启动服务

////用于启动和停止service的Intent
//        final Intent it = new Intent("android.mu.action.music");
//　　在运行的时候就出了问题，经百度，是Android5.0以后不能这么写了，所以挂了（看来书要读新啊，技术变化太快，旧书过时了）
////用于启动和停止service的Intent
//        final Intent it = new Intent(当前类名.this,要调用的Service类名.class);
//　　这样显式声明就可以了。
//
//　　再运行，OK。
        final Intent intent = new Intent(MainActivity.this, wifiLogin.class); //5.0后要这么写
        startService(intent);
//                onCreate()是回调方法，是被动的
        //nox_adb.exe connect 127.0.0.1:5037  先查看端口号 nox-adb的端口号


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您已成功登录", Toast.LENGTH_LONG).show();
                okhttp();
            }
        });

    }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
