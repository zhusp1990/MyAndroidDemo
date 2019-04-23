package com.zhusp.androiddemo.activity.svguse;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhusp.androiddemo.R;

public class SVGUseActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView svgImage;
    private Button btnStart;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svguse);
        svgImage = (ImageView)findViewById(R.id.iv_svg);
        btnStart = (Button)findViewById(R.id.btn_donghua);
        final AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.svg_animator);
        svgImage.setImageDrawable(drawable);
        drawable.start();
        btnStart.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_donghua:
                final AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.svg_animator);
                svgImage.setImageDrawable(drawable);
                drawable.start();
                break;
        }
    }
}
