package com.zhusp.androiddemo.activity.selfwidget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.fragment.SimpleFragment;
import com.zhusp.androiddemo.views.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerIndicatorActivity extends AppCompatActivity {

    private ViewPager mVp_content;
    private ViewPagerIndicator mIndicator;
    private List<String> mTitles = Arrays.asList("标题1","标题2","标题3","标题4","标题5","标题6","标题7","标题8","标题9");
    private List<SimpleFragment> mFragmentContens = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_indicator);
        initViews();
        initDatas();
    }

    private void initDatas() {
        mIndicator.addIndicatorItem(mTitles);
        for (String title : mTitles){
            SimpleFragment fragment = SimpleFragment.newInstance(title);
            mFragmentContens.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentContens.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentContens.size();
            }
        };
        mVp_content.setAdapter(mAdapter);
        mIndicator.setViewPager(mVp_content,0);
    }

    private void initViews(){
        mVp_content = (ViewPager)findViewById(R.id.vp_content);
        mIndicator = (ViewPagerIndicator)findViewById(R.id.indicator);
    }
}
