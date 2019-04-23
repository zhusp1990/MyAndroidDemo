package com.zhusp.androiddemo.activity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.utils.ViewUtils;
import com.zhusp.androiddemo.views.ExToast;

public class ShowToastService extends Service {

    private ToastBinder mBinder;
    private final int STATE_ON = 0;
    private final int STATE_OFF = 1;
    private int mServiceState = STATE_ON;

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }


    @Override
    public void onCreate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (mServiceState == STATE_ON){
                        Thread.sleep(5000);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (mServiceState == STATE_ON){
                                    ExToast exToast = ExToast.makeText(getBaseContext(),"message",5);
//                                    exToast.setAnimations(R.style.anim_view);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        exToast.show();
                                    }
                                    //使用LENGTH_ALWAYS注意在合适的时候调用hide()
//                                    exToast.hide();
//                                    ViewUtils.showAdFloatView(getApplicationContext());
                                }
                            }
                        });
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public class ToastBinder extends Binder{
        public ShowToastService getService(){
            return ShowToastService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Service","onDestroy");
        mServiceState = STATE_OFF;
    }
}
