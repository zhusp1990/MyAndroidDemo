package com.zhusp.androiddemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ExceptionCrashHandler implements Thread.UncaughtExceptionHandler {

    private static ExceptionCrashHandler mInstance;
    //系统默认异常处理对象
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;
    private Context mContext;
    private static final String TAG = "ExceptionCrashHandler";

    public static ExceptionCrashHandler getInstance() {
        if (null == mInstance) {
            mInstance = new ExceptionCrashHandler();
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        //设置全局的异常处理类为本类
        Thread.currentThread().setUncaughtExceptionHandler(this);
        //设置系统默认处理类对象
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.d(TAG,"程序出现异常");
        //TODO 在这里处理异常信息及异常操作
        //将异常写入到本地文件
        //1.崩溃的详细信息
        String crashFileName = saveInfoToSD(e);
        //2.应用信息、包名、版本号

        //3.手机信息

        //4.保存当前文件，等应用再次启动再上传（上传文件不在这里处理）
        cacheCrashFile(crashFileName);

        //让系统默认处理异常
        mDefaultExceptionHandler.uncaughtException(t, e);
    }

    /**
     * 缓存崩溃日志文件路径
     * @param fileName 文件路径
     */
    private void cacheCrashFile(String fileName) {
        SharedPreferences sp = mContext.getSharedPreferences("crash",Context.MODE_PRIVATE);
        sp.edit().putString("CRASH_FILE_NAME",fileName).commit();
    }

    /**
     * 获取设备信息
     * @return 设备信息
     */
    private String getMobileInfo() {
        StringBuffer sb = new StringBuffer();
        try {
            //利用反射获取Build所有属性信息
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);//设置可获取该属性
                String name = field.getName();
                String value = field.get(null).toString();
                sb.append(name+"="+value+"\n");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取系统未捕捉的错误信息
     * @param throwable
     * @return
     */
    private String obtainExceptionInfo(Throwable throwable){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    private String saveInfoToSD(Throwable ex){
        String fileName = null;
        StringBuffer stringBuffer = new StringBuffer();
        //1.手机信息 + 应用信息  -->obtainSimpleInfo()
        for (Map.Entry<String,String> entry : obtainSimpleInfo(mContext).entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuffer.append(key).append("=").append(value).append("\n");
        }

        stringBuffer.append(obtainExceptionInfo(ex));

        //保存文件  手机应用的目录，并没有拿手机sdCard目录 6.0以上需要动态申请权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File dir = new File(mContext.getFilesDir() + File.separator+"crash"+File.separator);
            Log.i(TAG,dir.getAbsolutePath());
            //先删除之前的异常信息
            if (dir.exists()){
                //删除该目录下的所有子文件
                deleteDir(dir);
            }

            //再重新创建文件夹
            if (!dir.exists()){
                dir.mkdir();
            }

            fileName = dir.toString()+File.separator+getAssignTime("yyyy_mm_dd_HH_mm") + ".txt";
            try {
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(stringBuffer.toString().getBytes());
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /**
     * 删除整个该目录下的文件
     * @param dir
     */
    private boolean deleteDir(File dir) {
        if (dir.isDirectory()){
            File[] children = dir.listFiles();
            //删除目录中的子目录内容
            for (File child : children) {
                child.delete();
            }
        }
        return true;
    }

    private String getAssignTime(String dataFormatStr){
        DateFormat dateFormat = new SimpleDateFormat(dataFormatStr);
        long currentTime = System.currentTimeMillis();
        return dateFormat.format(currentTime);
    }

    /**
     * 获取一些简单的信息，软件版本，手机版本，型号等信息存放在HashMap中
     * @return
     */
    private Map<String,String> obtainSimpleInfo(Context context) {
        HashMap<String,String> map = new HashMap<>();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(),PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        map.put("versionName",packageInfo.versionName);
        map.put("versionCode",packageInfo.versionCode+"");
        map.put("MODEL",Build.MODEL);
        map.put("SDK_INT",Build.VERSION.SDK_INT+"");
        map.put("PRODUCT",Build.PRODUCT);
        map.put("MOBILE_INFO",getMobileInfo());
        return map;
    }

    /**
     * 获取崩溃文件
     */
    public File getCrashFile() {
        String crashFileName = mContext.getSharedPreferences("crash",Context.MODE_PRIVATE).getString("CRASH_FILE_NAME","");
        return new File(crashFileName);
    }
}
