package com.zhusp.androiddemo.utils;

import android.content.Context;

import com.zhusp.androiddemo.activity.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;

/**
 * 自定义热修复
 */
public class FixDexManager {


    private final Context mContext;
    private File mDexDir;//系统可以访问的dex目录

    public FixDexManager(Context context) {
        this.mContext = context;
        //获取应用可以访问的Dex目录
        this.mDexDir = context.getDir("odex",Context.MODE_PRIVATE);
    }

    public void fixDexBug(String fixDexPath) throws Exception{
        //1. 先获取已经运行的 dexElement
        ClassLoader appClassLoader = mContext.getClassLoader();

        Object dexElements = getDexElementsByClassLoader(appClassLoader);

        //2. 获取下载好的补丁的 dexElement
        //2.1 移动到系统能够访问的 dex目录下 ClassLoader

        File srcFile = new File(fixDexPath);
        if (!srcFile.exists()){
            throw new FileNotFoundException(fixDexPath);
        }

        File targetFile = new File(mDexDir,srcFile.getName());
        if (targetFile.exists()){
            return;
        }
        //2.2 ClassLoader读取fixDex路径

        //3. 把补丁的 dexElement 插到已经运行的 dexElement的最前面
    }

    /**
     * 从ClassLoader中获取 dexElements
     * @param classLoader
     * @return
     */
    private Object getDexElementsByClassLoader(ClassLoader classLoader) throws Exception{
        //1. 先获取存储dexElement的pathList
        Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
        //
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);

        //2. 获取pathList里面的dexElements
        Field dexElements = pathList.getClass().getDeclaredField("dexElements");
        dexElements.setAccessible(true);

        return dexElements.get(pathList);
    }
}
