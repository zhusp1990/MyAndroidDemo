package com.zhusp.androiddemo.activity.assistuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.activity.assistuse.util.FontUtil;

public class FontSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_set);
        TextView tv1 = (TextView) findViewById(R.id.tv_test1);
        FontUtil.injectFont(tv1,"fzgl.ttf");
        TextView tv2 = (TextView) findViewById(R.id.tv_test2);
        FontUtil.injectFont(tv2,"hkwwt.TTF");

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_group);
        FontUtil.injectFont(ll,"pop.ttf");
    }
}
