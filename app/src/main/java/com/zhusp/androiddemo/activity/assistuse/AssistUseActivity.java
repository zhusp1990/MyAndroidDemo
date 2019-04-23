package com.zhusp.androiddemo.activity.assistuse;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.activity.selfwidget.CircleScaleViewActivity;
import com.zhusp.androiddemo.activity.selfwidget.LineChartActivity;
import com.zhusp.androiddemo.activity.selfwidget.MyWatchViewActivity;
import com.zhusp.androiddemo.activity.selfwidget.ViewPagerIndicatorActivity;
import com.zhusp.androiddemo.activity.service.ShowToastService;
import com.zhusp.androiddemo.utils.ViewUtils;

public class AssistUseActivity extends AppCompatActivity {

    private FrameLayout mFloatBtn;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private ShowToastService mToastService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assits_use);
    }

    private void initFloatView(){
        mFloatBtn = new FrameLayout(getApplicationContext());
        mFloatBtn.setBackgroundColor(Color.RED);

        mWindowManager = (WindowManager)getApplication().getSystemService(Context.WINDOW_SERVICE);

        //设置WindowManager参数
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.format = PixelFormat.RGBA_8888;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
        mLayoutParams.width = 200;
        mLayoutParams.height = 200;
        mLayoutParams.x = 100;
        mLayoutParams.y = 100;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;

        mLayoutParams.token = getWindow().getDecorView().getWindowToken();

        mWindowManager.addView(mFloatBtn,mLayoutParams);
//        mWindowManager.removeViewImmediate(mFloatBtn);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_font_set:
                startActivity(new Intent(this,FontSetActivity.class));
                break;
            case R.id.btn_float_show:
//                initFloatView();
//                ViewUtils.showFloatView(this);
                Intent service = new Intent(this, ShowToastService.class);
                bindService(service,mServiceConnection,Context.BIND_AUTO_CREATE);
                break;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mToastService = ((ShowToastService.ToastBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Activity","onStop");
//        mToastService.stopSelf();
//        unbindService(mServiceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Activity","onDestroy");
        unbindService(mServiceConnection);
    }
}
