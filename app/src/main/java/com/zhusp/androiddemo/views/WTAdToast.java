package com.zhusp.androiddemo.views;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

/**
 * Created by i on 2018/12/18.
 */

public class WTAdToast {
    private Toast mToast;
    private Context mContext;
    private boolean isShow = false;

    private View mView;
    private Object mTN;
    private Method show;
    private Method hide;
    private Handler handler = new Handler();
    private int mDuration = 3;
//    private WindowManager.LayoutParams mLayoutParams;


    public WTAdToast(Context context,View toastView) {
        this.mContext = context;
        if (mToast == null) {
            mToast = new Toast(mContext);
        }
        mView = toastView;
        mToast.setView(mView);
    }

    public void show() {
        if (isShow)
            return;
        initTN();
        try {
            if (Build.VERSION.SDK_INT <= 23) {
                show.invoke(mTN);
            } else {
                show.invoke(mTN, mView.getWindowToken());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isShow = true;
        Log.i("czc", "SettingSlideLockToast is showing");
        handler.postDelayed(hideRunnable, mDuration * 1000);
    }

    private Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    public void setDuration(int duration){
        this.mDuration = duration;
    }

    public void hide() {
        if (!isShow)
            return;
        try {
            hide.invoke(mTN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isShow = false;
//        mView.stopTimeCountDown();
        Log.i("czc", "SettingSlideLockToast is hided");
    }

    private void initTN() {
        try {
            Field tnField = mToast.getClass().getDeclaredField("mTN");
            tnField.setAccessible(true);
            mTN = tnField.get(mToast);

            Field tnParamsField = mTN.getClass().getDeclaredField("mParams");
            tnParamsField.setAccessible(true);
            WindowManager.LayoutParams mLayoutParams = (WindowManager.LayoutParams) tnParamsField.get(mTN);
//            mLayoutParams.format = PixelFormat.TRANSLUCENT;
            //this flag set view don't interrupt touch event
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//            mLayoutParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING;
//            mLayoutParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//            mLayoutParams.windowAnimations = R.style.SettingClickToastAnim;
            //设置viewgroup宽高
            mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; //设置Toast宽度为屏幕宽度
            mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; //设置高度
            mLayoutParams.x = 0;
            mLayoutParams.y = 0;


            /**调用tn.show()之前一定要先设置mNextView*/
            Field tnNextViewField = mTN.getClass().getDeclaredField("mNextView");
            tnNextViewField.setAccessible(true);
            tnNextViewField.set(mTN, mView);

            if (Build.VERSION.SDK_INT <= 23) {
                show = mTN.getClass().getMethod("show");
            } else {
                show = mTN.getClass().getMethod("show", IBinder.class);
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
            hide = mTN.getClass().getMethod("hide");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mToast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);
    }
}
