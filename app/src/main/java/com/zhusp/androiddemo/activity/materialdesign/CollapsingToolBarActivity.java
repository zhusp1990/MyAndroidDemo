package com.zhusp.androiddemo.activity.materialdesign;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.zhusp.androiddemo.R;

public class CollapsingToolBarActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapsingToolBar;
    private AppBarLayout mAppBarLayout;
    private int mOffsetHeight;
    private ImageView mIv_titleIcon;
    private Toolbar mTb_toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_tool_bar);
        initViews();
        initData();
    }

    private void initData() {
//        mOffsetHeight = mAppBarLayout.getMeasuredHeight();
    }

    private void initViews() {
        mCollapsingToolBar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);
        mAppBarLayout = (AppBarLayout)findViewById(R.id.abl_title_bar_layout);
        mIv_titleIcon = (ImageView)findViewById(R.id.iv_head_icon);
        mTb_toolBar = (Toolbar)findViewById(R.id.toolbar);
        mAppBarLayout.post(new Runnable() {
            @Override
            public void run() {
                mOffsetHeight = mAppBarLayout.getHeight()-mTb_toolBar.getHeight();
                Log.i("BarActivity","mOffsetHeight:"+ mOffsetHeight);
            }
        });
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float scaleData = Math.abs((float) verticalOffset/(float) mOffsetHeight);
                mIv_titleIcon.setAlpha(scaleData);
                mIv_titleIcon.setScaleX(scaleData);
                mIv_titleIcon.setScaleY(scaleData);
            }
        });
    }
}
