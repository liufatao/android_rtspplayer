package com.example.administrator.cartspclient.rtsp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import com.example.administrator.cartspclient.R;
import com.example.administrator.cartspclient.rtsp.clinet.RtspClient;

public class RtspPlayActivity extends AppCompatActivity {
    private final static String TAG="RtspPlayActivity";
    private RtspClient rtspClient;
    private SurfaceView surfaceView;
    private final static String url = "rtsp://192.168.43.227:8554/video";
    private final static String url2 = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";
    private String adder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtsp_play);
        surfaceView=findViewById(R.id.surfaceView1);
        adder=getIntent().getStringExtra("url");
        if (adder==null){
            adder=url;
        }
        Log.d(TAG,"RTSP:"+adder);
        rtspClient=new RtspClient(adder);
        rtspClient.setSurfaceView(surfaceView);
        rtspClient.start();
    }

    @Override
    protected void onDestroy() {
        rtspClient.shutdown();
        super.onDestroy();
    }
}
