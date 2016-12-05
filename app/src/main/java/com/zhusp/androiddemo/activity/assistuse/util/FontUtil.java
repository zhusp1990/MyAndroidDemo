package com.zhusp.androiddemo.activity.assistuse.util;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * -----------------------------------------------------
 * 项目： PengpaiNews
 * 作者： wd_zhu
 * 日期： 2016/12/5 14:28
 * 邮箱： zhushengping@wdit.com.cn
 * 描述：
 * ------------------------------------------------------
 */
public class FontUtil {
    public static final String FONTS_DIR = "fonts/";
    public static final String DEF_FONT = FONTS_DIR+"FZBWKSJW.TTF";
    public static final void injectFont(View rootView){
        injectFont(rootView,Typeface.createFromAsset(rootView.getContext().getAssets(),DEF_FONT));
    }

    public static final void injectFont(View rootView, Typeface tf){
        if (rootView instanceof ViewGroup){
            ViewGroup group = (ViewGroup) rootView;
            int childCount = group.getChildCount();
            for (int i = 0;i<childCount;i++){
                injectFont(group.getChildAt(i),tf);
            }
        }else if (rootView instanceof TextView){
            ((TextView)rootView).setTypeface(tf);
        }
    }

    public static void injectFont(View rootView,String fontName){
        injectFont(rootView,Typeface.createFromAsset(rootView.getContext().getAssets(),FONTS_DIR+fontName));
    }
}
