package com.zhusp.androiddemo.activity.selfwidget;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.zhusp.androiddemo.R;

public class SelfViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_view_test);
        initView();
    }

    private void initView() {
        WindowManager.LayoutParams mFloatParams = new WindowManager.LayoutParams();
        mFloatParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mFloatParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mFloatParams.type =WindowManager.LayoutParams.TYPE_APPLICATION;
        mFloatParams.gravity = Gravity.RIGHT | Gravity.TOP;
        mFloatParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        Button floatBut = new Button(this);
        floatBut.setText("应用悬浮窗");
        WindowManager manager = getWindowManager();
        manager.addView(floatBut,mFloatParams);
//        EditText
    }


}
