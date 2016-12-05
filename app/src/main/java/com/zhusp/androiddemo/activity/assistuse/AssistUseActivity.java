package com.zhusp.androiddemo.activity.assistuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.activity.selfwidget.CircleScaleViewActivity;
import com.zhusp.androiddemo.activity.selfwidget.LineChartActivity;
import com.zhusp.androiddemo.activity.selfwidget.MyWatchViewActivity;
import com.zhusp.androiddemo.activity.selfwidget.ViewPagerIndicatorActivity;

public class AssistUseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assits_use);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_font_set:
                startActivity(new Intent(this,FontSetActivity.class));
                break;
            case R.id.btn_my_watch:
                startActivity(new Intent(this,MyWatchViewActivity.class));
                break;
            case R.id.btn_line_chart:
                startActivity(new Intent(this,LineChartActivity.class));
                break;
            case R.id.btn_circle_scale:
                startActivity(new Intent(this,CircleScaleViewActivity.class));
                break;
        }
    }
}
