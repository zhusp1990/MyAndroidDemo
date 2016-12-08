package com.zhusp.androiddemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.activity.assistuse.AssistUseActivity;
import com.zhusp.androiddemo.activity.extend.WidgetExtendActivity;
import com.zhusp.androiddemo.activity.selfwidget.SelfDefindViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_define_view:
                startActivity(new Intent(this,SelfDefindViewActivity.class));
                break;
            case R.id.btn_assist:
                startActivity(new Intent(this,AssistUseActivity.class));
                break;
            case R.id.btn_widget_extend:
                startActivity(new Intent(this,WidgetExtendActivity.class));
                break;
        }
    }
}
