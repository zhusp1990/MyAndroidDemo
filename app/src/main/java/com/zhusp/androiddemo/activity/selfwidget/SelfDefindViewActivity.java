package com.zhusp.androiddemo.activity.selfwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;

public class SelfDefindViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_defind_view);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_view_pager_indicator:
                startActivity(new Intent(this,ViewPagerIndicatorActivity.class));
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
            case R.id.btn_top_slide_menu:
                startActivity(new Intent(this,DropdownViewActivity.class));
                break;
        }
    }
}
