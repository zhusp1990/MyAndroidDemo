package com.zhusp.androiddemo.activity.selfwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.views.ExpandableTextView;

public class ExpandTextViewActivity extends AppCompatActivity {

    private ExpandableTextView mEtv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text_view);
        initView();
    }

    private void initView(){
        mEtv_test = (ExpandableTextView)findViewById(R.id.etv_test_text);
        String text = "这是一个\n好可以扩展,的啊如,果限 定行而 数字数   超出\n的话即可在后面添加省略号的一个很牛逼的TextView自定义控件";
        mEtv_test.setText(text);
        mEtv_test.setExpandListener(new ExpandableTextView.OnExpandListener() {
            @Override
            public void onExpand(ExpandableTextView view) {
                mEtv_test.setMaxLines(Integer.MAX_VALUE);
            }

            @Override
            public void onShrink(ExpandableTextView view) {

            }
        });
    }
}
