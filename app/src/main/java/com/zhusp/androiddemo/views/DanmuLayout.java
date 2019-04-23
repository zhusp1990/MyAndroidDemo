package com.zhusp.androiddemo.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhusp.androiddemo.R;

/**
 * Created by i on 2018/12/12.
 */

public class DanmuLayout extends RelativeLayout {

    Context mContext;
    private String TAG = "DanmuLayout";
    private TextView mTv_dammu;
    private int dammuLength;
    private ObjectAnimator offsetXAnimation;
    private HorizontalScrollView mHsv_dammu;

    public DanmuLayout(Context context) {
        this(context, null);
    }

    public DanmuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanmuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        mContext = getContext();
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.danmu_layout, null);
        mHsv_dammu = (HorizontalScrollView) rootView.findViewById(R.id.hsv_dammu);
        mTv_dammu = (TextView) rootView.findViewById(R.id.tv_dammu);
        addView(rootView);
        this.setVisibility(View.INVISIBLE);
    }

    public void setDanmuContent(String content) {
        this.setVisibility(VISIBLE);
        if (!TextUtils.isEmpty(content)) {
            mTv_dammu.setText(content);
        }
    }

    public void show(int duration) {
        this.setVisibility(VISIBLE);
        float currentPosition = mTv_dammu.getTranslationX();
        dammuLength = mTv_dammu.getWidth();
        Log.i(TAG, "currentPosition:" + currentPosition);
        Log.i(TAG, "dammuLength:" + dammuLength);
        int width = 720;
        Log.i(TAG, "screenWidth:" + width);
        TranslateAnimation animation = new TranslateAnimation(width,-dammuLength,0,0);
        animation.setDuration(duration*1000);
        mTv_dammu.setAnimation(animation);
        animation.start();
//        offsetXAnimation = ObjectAnimator.ofFloat(mTv_dammu, "translationX", width,  - 200);
//        offsetXAnimation.setDuration(8000);
//        offsetXAnimation.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                DanmuLayout.this.setVisibility(GONE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        offsetXAnimation.start();
    }
}
