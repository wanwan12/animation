package com.wanwan.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View vBg;
    private TextView tvOriginal;
    private TextView tvAlpha,tvRotate, tvScale, tvSet, tvTranslate;

    private Context mContext;
    private Animation mAlphaAnimation, mRotateAnimation, mScaleAnimation, mSetAnimation, mTranslateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initAnimation();
    }

    private void initAnimation() {
        mAlphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        mSetAnimation = AnimationUtils.loadAnimation(this, R.anim.set);
        mTranslateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
    }

    private void initView() {
        vBg = findViewById(R.id.v_bg);
        tvOriginal = findViewById(R.id.tv_original);
        tvAlpha = findViewById(R.id.tv_alpha);
        tvRotate = findViewById(R.id.tv_rotate);
        tvScale = findViewById(R.id.tv_scale);
        tvSet = findViewById(R.id.tv_set);
        tvTranslate = findViewById(R.id.tv_translate);

        tvOriginal.setOnClickListener(this);
        tvAlpha.setOnClickListener(this);
        tvRotate.setOnClickListener(this);
        tvScale.setOnClickListener(this);
        tvSet.setOnClickListener(this);
        tvTranslate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_original:
                Toast.makeText(mContext, "click this original", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_alpha:
                tvOriginal.startAnimation(mAlphaAnimation);
                break;
            case R.id.tv_rotate:
                tvOriginal.startAnimation(mRotateAnimation);
                break;
            case R.id.tv_scale:
                tvOriginal.startAnimation(mScaleAnimation);
                break;
            case R.id.tv_set:
                tvOriginal.startAnimation(mSetAnimation);
                break;
            case R.id.tv_translate:
                tvOriginal.startAnimation(mTranslateAnimation);
                break;

        }
    }
}
