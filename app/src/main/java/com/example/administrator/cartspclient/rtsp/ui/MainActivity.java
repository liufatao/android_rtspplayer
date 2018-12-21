/*
* To do:
* 1. ByteBuffer.allocateDirect()
* 2. Byte.get change to ByteBuffer.put(ByteBuffer)
*
*
*
* */

package com.example.administrator.cartspclient.rtsp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.cartspclient.R;
import com.example.administrator.cartspclient.rtsp.clinet.RtspClient;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_adder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findViewById(R.id.BtPlay).setOnClickListener(this);
        findViewById(R.id.buttonStop).setOnClickListener(this);
        et_adder=findViewById(R.id.et_adder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtPlay:
                Intent intent=new Intent(MainActivity.this,RtspPlayActivity.class);
                intent.putExtra("url",et_adder.getText().toString());
                startActivity(intent);
                Toast.makeText(MainActivity.this,"开始播放流",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 先判断网络情况是否良好
     * */
    private String displayIpAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String ipaddress = "";
        if (info!=null && info.getNetworkId()>-1) {
            int i = info.getIpAddress();
            String ip = String.format(Locale.ENGLISH,"%d.%d.%d.%d", i & 0xff, i >> 8 & 0xff,i >> 16 & 0xff,i >> 24 & 0xff);
            ipaddress += "rtsp://";
            ipaddress += ip;
            ipaddress += ":";
            ipaddress += 8854;
            ipaddress +="/video";
        }
        return ipaddress;
    }
}

