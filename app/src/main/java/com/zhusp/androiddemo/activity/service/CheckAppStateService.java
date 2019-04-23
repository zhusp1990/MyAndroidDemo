package com.zhusp.androiddemo.activity.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by i on 2018/10/12.
 */

public class CheckAppStateService extends Service {

    private ActivityManager activityManager;
    private String packageName;
    private boolean stop = false;

    @Override
    public void onCreate() {
        super.onCreate();
        activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        packageName = this.getPackageName();
        new Thread(new AppStatus()).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private boolean appOnForeground() {
        if (activityManager == null || TextUtils.isEmpty(packageName)) return false;
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    private class AppStatus implements Runnable {

        @Override
        public void run() {
            stop = false;

            while (!stop) {
                try {
                    if (appOnForeground()) {
//                        System.out.println("当前App处于前台");
                    } else {
//                        System.out.println("当前App处于后台");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
