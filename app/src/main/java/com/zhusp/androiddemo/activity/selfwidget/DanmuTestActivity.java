package com.zhusp.androiddemo.activity.selfwidget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.utils.ViewUtils;
import com.zhusp.androiddemo.views.DanmuLayout;
import com.zhusp.androiddemo.views.ExToast;
import com.zhusp.androiddemo.views.WTAdToast;

public class DanmuTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv_dammu;
    private HorizontalScrollView mHsv_dammu;
    private Button mBtn_show;
    private ObjectAnimator offsetXAnimation;
    private int dammuLength;
    private String TAG = "DanmuTestActivity";
    private DanmuLayout mDl_danmu;
    private WTAdToast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmu_test);
        initView();
//        initDanmuAnimation();
    }

    private void initView() {
        mHsv_dammu = (HorizontalScrollView) findViewById(R.id.hsv_dammu);
        mHsv_dammu.setVisibility(View.INVISIBLE);
        mTv_dammu = (TextView) findViewById(R.id.tv_dammu);
        mTv_dammu.setOnClickListener(this);
        mBtn_show = (Button) findViewById(R.id.btn_show_danmu);
        mBtn_show.setOnClickListener(this);
        mDl_danmu = (DanmuLayout) findViewById(R.id.dl_danmu);
        mDl_danmu.setOnClickListener(this);
    }

    private void initDanmuAnimation() {
        float currentPosition = mTv_dammu.getTranslationX();
        dammuLength = mTv_dammu.getWidth();
        Log.i(TAG, "currentPosition:" + currentPosition);
        Log.i(TAG, "dammuLength:" + dammuLength);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        Log.i(TAG, "screenWidth:" + width);
        offsetXAnimation = ObjectAnimator.ofFloat(mTv_dammu, "translationX", width, width - dammuLength - 200);
        offsetXAnimation.setDuration(2000);

        offsetXAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHsv_dammu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dammu:
                Toast.makeText(this, "跳转弹幕事件", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_show_danmu:
//                showDanmu();
//                mDl_danmu.show();
//                ViewUtils.showAdFloatView(this);
                DanmuLayout danmuLayout = new DanmuLayout(this);
                danmuLayout.setDanmuContent("油菜打赏了戏精100000书豆，获得本萌金牌盟主头衔");
                if (toast == null) {
                    toast = new WTAdToast(this, danmuLayout);
                }
                toast.setDuration(12);
                danmuLayout.show(12);
                toast.show();
                break;
            case R.id.dl_danmu:

                break;
        }
    }

    private void showDanmu() {
        mHsv_dammu.setVisibility(View.VISIBLE);
        initDanmuAnimation();
        offsetXAnimation.start();
    }
}
