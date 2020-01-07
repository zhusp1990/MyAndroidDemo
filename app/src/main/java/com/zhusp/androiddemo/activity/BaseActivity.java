package com.zhusp.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhusp.androiddemo.R;

public abstract class BaseActivity extends AppCompatActivity {
    private ViewGroup mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base,null);
        setContentView(mRootView);
        View activityLayout = LayoutInflater.from(this).inflate(getActivityLayoutView(),null);
        if (activityLayout != null){
            mRootView.addView(activityLayout);
            initView(activityLayout);
        }

        initData();

    }

    protected abstract void initView(View rootView);

    protected abstract void initData();

    protected abstract int getActivityLayoutView();

    protected <T extends View> T getViewId(int viewId){
        return (T) findViewById(viewId);
    }
}
