package com.zhusp.androiddemo.utils;

import android.content.Context;
import android.util.Log;

/**
 * -----------------------------------------------------
 * 项目： ProPertyAnimatorDemo
 * 作者： wd_zhu
 * 日期： 2016/11/1 15:03
 * 邮箱： zhushengping@wdit.com.cn
 * 描述：
 * ------------------------------------------------------
 */
public class CommonUtil {
    private static final String TAG = "CommonUtil";

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int parseInt(String strData){
        int data = 0;
        try {
            int i = Integer.parseInt(strData);
            data = i;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"strData cannot parse to int");
        }
        return data;
    }
}
