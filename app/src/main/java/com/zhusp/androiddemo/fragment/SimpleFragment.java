package com.zhusp.androiddemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * -----------------------------------------------------
 * 项目： ProPertyAnimatorDemo
 * 作者： wd_zhu
 * 日期： 2016/7/29 15:24
 * 邮箱： zhushengping@wdit.com.cn
 * 描述：
 * ------------------------------------------------------
 */
public class SimpleFragment extends Fragment {

    private static final String KEY_TITLE = "title";
    private String mTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (null != bundle){
            mTitle = bundle.getString(KEY_TITLE);
        }
        TextView tv = new TextView(getActivity());
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public static SimpleFragment newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE,title);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
