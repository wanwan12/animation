package com.wanwan.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.egret.egretnativeandroid.EgretNativeAndroid;
import org.egret.runtime.launcherInterface.INativePlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    private EgretNativeAndroid nativeAndroid;
    private View rootView;
    private FrameLayout flAnimation;
    private int bgIndex = 0;
    private int[] colorIds={R.color.red,R.color.blue,R.color.black,R.color.white};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flAnimation = findViewById(R.id.fl_animation);
        rootView = findViewById(R.id.root_view);

        findViewById(R.id.btn_think).setOnClickListener(this);
        findViewById(R.id.btn_email).setOnClickListener(this);
        findViewById(R.id.btn_music).setOnClickListener(this);
        findViewById(R.id.btn_nod).setOnClickListener(this);
        findViewById(R.id.btn_repair).setOnClickListener(this);
        findViewById(R.id.img_mammon).setOnClickListener(this);
        findViewById(R.id.img_christmas).setOnClickListener(this);
        findViewById(R.id.img_cowboy).setOnClickListener(this);
        findViewById(R.id.img_winter).setOnClickListener(this);
        findViewById(R.id.img_repair).setOnClickListener(this);

        nativeAndroid = new EgretNativeAndroid(this);
        if (!nativeAndroid.checkGlEsVersion()) {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        nativeAndroid.config.showFPS = false;
        nativeAndroid.config.fpsLogTime = 15;
        nativeAndroid.config.disableNativeRender = false;
        nativeAndroid.config.clearCache = false;
        nativeAndroid.config.loadingTimeout = 0;
        nativeAndroid.config.transparentGameView=true;
        setExternalInterfaces();

        if (!nativeAndroid.initialize("http://game/index.html")) {
            Toast.makeText(this, "Initialize native failed.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        flAnimation.removeAllViews();
        flAnimation.addView(nativeAndroid.getRootFrameLayout());

    }

    @Override
    protected void onPause() {
        super.onPause();
        nativeAndroid.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nativeAndroid.resume();
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            nativeAndroid.exitGame();
        }

        return super.onKeyDown(keyCode, keyEvent);
    }

    private void setExternalInterfaces() {
        nativeAndroid.setExternalInterface("sendToNative", new INativePlayer.INativeInterface() {
            @Override
            public void callback(String message) {
                String str = "Native get message: ";
                str += message;
                Log.d(TAG, str);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        rootView.setBackgroundResource(colorIds[bgIndex]);
        bgIndex++;
        if(bgIndex>=colorIds.length){
            bgIndex = 0;
        }
        if(v.getId() == R.id.btn_think){
            nativeAndroid.callExternalInterface("changeAnimation", "think");
        }else if(v.getId() == R.id.btn_email){
            nativeAndroid.callExternalInterface("changeAnimation", "email");
        }else if(v.getId() == R.id.btn_music){
            nativeAndroid.callExternalInterface("changeAnimation", "music");
        }else if(v.getId() == R.id.btn_nod){
            nativeAndroid.callExternalInterface("changeAnimation", "nod");
        }else if(v.getId() == R.id.btn_repair){
            nativeAndroid.callExternalInterface("changeAnimation", "repair");
        }else if(v.getId() == R.id.img_repair){
            nativeAndroid.callExternalInterface("changeHat", "repair_hat");
        }else if(v.getId() == R.id.img_christmas){
            nativeAndroid.callExternalInterface("changeHat", "hat_christmas");
        }else if(v.getId() == R.id.img_cowboy){
            nativeAndroid.callExternalInterface("changeHat", "hat_cowboy");
        }else if(v.getId() == R.id.img_mammon){
            nativeAndroid.callExternalInterface("changeHat", "hat_mammon");
        }else if(v.getId() == R.id.img_winter){
            nativeAndroid.callExternalInterface("changeHat", "hat_winter");
        }
    }

    private void sendTexPng(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        nativeAndroid.callExternalInterface("sendToJS", bitmap.toString());
    }
}
