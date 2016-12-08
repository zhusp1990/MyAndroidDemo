package com.zhusp.androiddemo.activity.extend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.activity.extend.dragRecyclerView.RecyclerViewDragActivity;

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
        }
    }
}
