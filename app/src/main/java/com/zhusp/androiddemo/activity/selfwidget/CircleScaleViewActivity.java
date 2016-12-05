package com.zhusp.androiddemo.activity.selfwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.views.CircleScaleView;

public class CircleScaleViewActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private CircleScaleView mScaleView;
    private SeekBar sbOne;
    private SeekBar sbTwo;
    private SeekBar sbThree;
    private SeekBar sbFour;
    private float lifeCost;
    private float trafficCost;
    private float communicateCost;
    private float entertainmentCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circlr_scale_view);
        initViews();
        initData();
    }

    private void initViews() {
        mScaleView = (CircleScaleView) findViewById(R.id.cs_my_scale);
        sbOne = (SeekBar) findViewById(R.id.sb_one);
        sbOne.setOnSeekBarChangeListener(this);

        sbTwo = (SeekBar) findViewById(R.id.sb_two);
        sbTwo.setOnSeekBarChangeListener(this);

        sbThree = (SeekBar) findViewById(R.id.sb_three);
        sbThree.setOnSeekBarChangeListener(this);

        sbFour = (SeekBar) findViewById(R.id.sb_four);
        sbFour.setOnSeekBarChangeListener(this);

    }

    private void initData() {
        lifeCost = sbOne.getProgress();
        trafficCost = sbTwo.getProgress();
        communicateCost = sbThree.getProgress();
        entertainmentCost = sbFour.getProgress();
        mScaleView.setCostPercent(lifeCost, trafficCost, communicateCost, entertainmentCost);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float values = (float) (seekBar.getProgress());
        switch (seekBar.getId()) {
            case R.id.sb_one:
                lifeCost = values;
                break;
            case R.id.sb_two:
                trafficCost = values;
                break;
            case R.id.sb_three:
                communicateCost = values;
                break;
            case R.id.sb_four:
                entertainmentCost = values;
                break;
        }
        mScaleView.setCostPercent(lifeCost, trafficCost, communicateCost, entertainmentCost);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
