package com.zhusp.androiddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.views.DanmuLayout;

/**
 * Created by i on 2018/10/11.
 */

public class ViewUtils {
    public static final int LENGTH_ALWAYS = 0;
    public static final int LENGTH_SHORT = 2;
    public static final int LENGTH_LONG = 4;
    public static void showFloatView(Activity context) {
        FrameLayout mFloatBtn;
        WindowManager mWindowManager;
        WindowManager.LayoutParams mLayoutParams;
        mFloatBtn = new FrameLayout(context);
        mFloatBtn.setBackgroundColor(Color.RED);

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

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

//        mLayoutParams.token = context.getWindow().getDecorView().getWindowToken();

        mWindowManager.addView(mFloatBtn, mLayoutParams);
//        mWindowManager.removeViewImmediate(mFloatBtn);
    }

    public static void showToastView(Context context){
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP,20,20);
        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setDuration(5000);
//        View toastView = LayoutInflater.from(context).inflate(R.layout.danmu_layout,null);
        DanmuLayout danmuLayout = new DanmuLayout(context);
        toast.setView(danmuLayout);
        toast.show();
        danmuLayout.show(8);
    }

    /**
     * @param context
     */
    public static void showAdFloatView(Context context){
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;//@deprecated for non-system apps. Use {@link #TYPE_APPLICATION_OVERLAY} instead.
        DanmuLayout danmuLayout = new DanmuLayout(context);
        windowManager.addView(danmuLayout,layoutParams);
    }
}
