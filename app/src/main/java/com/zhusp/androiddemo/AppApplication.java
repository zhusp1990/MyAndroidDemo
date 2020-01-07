package com.zhusp.androiddemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.zhusp.androiddemo.utils.ExceptionCrashHandler;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by i on 2019/5/6.
 */

public class AppApplication extends Application {
    private static final String TAG = "AppApplication";
    private ApplicationInfo appInfo;
    private String channelName;
    private int channelNum;

    @Override
    public void onCreate() {
        super.onCreate();
        String channelInfo = getChannelInfo(this);
        Log.d("channelInfo",channelInfo);
        //设置全局异常捕捉类
        ExceptionCrashHandler.getInstance().init(this);
//        appInfo.
    }

    private void initAppdataInfo(){
        PackageManager pm = getPackageManager();
        if (null != pm){
            try {
                appInfo = pm.getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String getChannelInfo(Context context){
        try {
            InputStream in = context.getAssets().open("channel.txt");
            int size = in.available();//获取文件内容大小
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            String channelInfo = new String(buffer,"utf-8");
            String[] channelInfos = channelInfo.split("\n");
            for (String info :channelInfos){
                if (info.contains("channelName")){
                    channelName = info.substring("channelName=".length());
                    Log.d(TAG,"channelName:"+channelName);
                }else if (info.contains("channelNum")){
                    channelNum = Integer.parseInt(info.substring("channelNum=".length()));
                    Log.d(TAG,"channelNum:"+channelNum);
                }
            }
            return channelInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "unKnow channel";
    }
}
