package com.zhusp.androiddemo.activity.selfwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.views.DropdownLayout;

public class DropdownViewActivity extends AppCompatActivity {

    private DropdownLayout mDropdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_slide_menu);
        mDropdownView = (DropdownLayout) findViewById(R.id.dropdown_layout);
    }

    public void onClick(View v){
        mDropdownView.toggle();
    }
}
