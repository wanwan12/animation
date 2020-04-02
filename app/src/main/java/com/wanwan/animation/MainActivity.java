package com.wanwan.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private View vBg;
    private TextView tvOriginal;

    private Context mContext;
    /**
     * View Animation视图动画 -- 补间动画
     */
    private Animation mAlphaAnimation, mRotateAnimation, mScaleAnimation, mSetAnimation, mTranslateAnimation;
    /**
     * 插槽值，其实就是动画速率曲线
     * 即可用于视图动画，又可用于属性动画
     */
    private Animation mInterpolatorAnimation;
    /**
     * Property Animator属性动画 -- 值动画
     * 支持 ofInt(),ofFloat(),ofObject() 方式
     */
    private ValueAnimator valueAnimator;
    /**
     * Property Animator属性动画 -- 对象动画
     * 支持 ofInt(),ofFloat(),ofObject() 方式
     */
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initAnimation();
        initPropertyAnimator();
    }

    private void initAnimation() {
        mAlphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        mSetAnimation = AnimationUtils.loadAnimation(this, R.anim.set);
        mTranslateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);

        mInterpolatorAnimation = AnimationUtils.loadAnimation(this, R.anim.interpolator);
    }

    private void initView() {
        vBg = findViewById(R.id.v_bg);
        tvOriginal = findViewById(R.id.tv_original);

        findViewById(R.id.tv_original).setOnClickListener(this);

        findViewById(R.id.tv_alpha).setOnClickListener(this);
        findViewById(R.id.tv_rotate).setOnClickListener(this);
        findViewById(R.id.tv_scale).setOnClickListener(this);
        findViewById(R.id.tv_set).setOnClickListener(this);
        findViewById(R.id.tv_translate).setOnClickListener(this);

        findViewById(R.id.tv_interpolator_accelerate_decelerate).setOnClickListener(this);
        findViewById(R.id.tv_interpolator_accelerate).setOnClickListener(this);
        findViewById(R.id.tv_interpolator_anticipate).setOnClickListener(this);
        findViewById(R.id.tv_interpolator_bounce).setOnClickListener(this);
        findViewById(R.id.tv_interpolator_linear).setOnClickListener(this);
        findViewById(R.id.tv_interpolator_overshoot).setOnClickListener(this);
        findViewById(R.id.tv_value_animator).setOnClickListener(this);
    }

    private void initPropertyAnimator() {
        valueAnimator = ValueAnimator.ofInt(0, 200, 50, 100);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new AnticipateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                tvOriginal.setScaleX(curValue / 100f);
                Log.e("TAG", "curValue = " + curValue);
            }
        });


//        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvOriginal,)

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
            case R.id.tv_interpolator_accelerate_decelerate:
                mInterpolatorAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                tvOriginal.startAnimation(mInterpolatorAnimation);
                break;
            case R.id.tv_interpolator_accelerate:
                mInterpolatorAnimation.setInterpolator(new AccelerateInterpolator());
                tvOriginal.startAnimation(mInterpolatorAnimation);
                break;
            case R.id.tv_interpolator_anticipate:
                mInterpolatorAnimation.setInterpolator(new AnticipateInterpolator());
                tvOriginal.startAnimation(mInterpolatorAnimation);
                break;
            case R.id.tv_interpolator_bounce:
                mInterpolatorAnimation.setInterpolator(new BounceInterpolator());
                tvOriginal.startAnimation(mInterpolatorAnimation);
                break;
            case R.id.tv_interpolator_linear:
                mInterpolatorAnimation.setInterpolator(new LinearInterpolator());
                tvOriginal.startAnimation(mInterpolatorAnimation);
                break;
            case R.id.tv_interpolator_overshoot:
                mInterpolatorAnimation.setInterpolator(new OvershootInterpolator());
                tvOriginal.startAnimation(mInterpolatorAnimation);
                break;
            case R.id.tv_value_animator:
                valueAnimator.start();
                break;

        }
    }
}
