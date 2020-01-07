package com.zhusp.androiddemo.activity.extend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.activity.extend.dragRecyclerView.RecyclerViewDragActivity;
import com.zhusp.androiddemo.activity.selfwidget.DanmuTestActivity;
import com.zhusp.androiddemo.activity.selfwidget.SelfViewTestActivity;
import com.zhusp.androiddemo.activity.svguse.SVGUseActivity;

public class WidgetExtendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_extend);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_recycler_drag:
                startActivity(new Intent(this,RecyclerViewDragActivity.class));
                break;
            case R.id.btn_danmu_test:
                startActivity(new Intent(this,DanmuTestActivity.class));
                break;
            case R.id.btn_diff_text:
                startActivity(new Intent(this,DiffPropertyTextActivity.class));
                break;
            case R.id.btn_svg_use:
                startActivity(new Intent(this,SVGUseActivity.class));
                break;
            case R.id.btn_float_btn:
                startActivity(new Intent(this, SelfViewTestActivity.class));
                break;
        }
    }
}
