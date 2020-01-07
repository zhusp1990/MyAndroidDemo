package com.zhusp.androiddemo.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.activity.SocketTest.SocketUseTestActivity;
import com.zhusp.androiddemo.activity.assistuse.AssistUseActivity;
import com.zhusp.androiddemo.activity.extend.WidgetExtendActivity;
import com.zhusp.androiddemo.activity.materialdesign.CollapsingToolBarActivity;
import com.zhusp.androiddemo.activity.materialdesign.MaterialDesignActivity;
import com.zhusp.androiddemo.activity.selfwidget.SelfDefindViewActivity;
import com.zhusp.androiddemo.activity.service.CheckAppStateService;
import com.zhusp.androiddemo.utils.ExceptionCrashHandler;
import com.zhusp.androiddemo.utils.FixDexManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, CheckAppStateService.class));
        initViews();
        initData();
    }

    private void initData(){
        handleCrashException();
//        int i = 1/0;
    }

    /**
     * 自己的热修复方式
     */
    private void fixDexBug() throws Exception{
        File fixFile = new File(Environment.getExternalStorageDirectory(),"fix.dex");
        if (fixFile.exists()){
            FixDexManager fixDexManager = new FixDexManager(this);
            fixDexManager.fixDexBug(fixFile.getAbsolutePath());
        }
    }

    private void handleCrashException() {
        //TODO 获取上次的崩溃信息上传到服务器
        //获取崩溃文件
        File crashFile = ExceptionCrashHandler.getInstance().getCrashFile();
        if (crashFile.exists()){
            //上传到服务器


            //打印崩溃信息
            try {
                InputStreamReader fileReader = new InputStreamReader(new FileInputStream(crashFile));
                char[] buffer = new char[1024];
                int len = 0;
                while ((len = fileReader.read(buffer))!= -1){
                    String str = new String(buffer, 0, len);
                    Log.d(TAG,str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initViews() {

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_define_view:
                startActivity(new Intent(this, SelfDefindViewActivity.class));
                break;
            case R.id.btn_assist:
                startActivity(new Intent(this, AssistUseActivity.class));
                break;
            case R.id.btn_widget_extend:
                startActivity(new Intent(this, WidgetExtendActivity.class));
                break;
            case R.id.btn_material_design:
                startActivity(new Intent(this, MaterialDesignActivity.class));
                break;

            case R.id.btn_socket_test:
                startActivity(new Intent(this, SocketUseTestActivity.class));
                break;
        }
    }
}
