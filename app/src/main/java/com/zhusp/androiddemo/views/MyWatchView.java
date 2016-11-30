package com.zhusp.androiddemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.utils.CommonUtil;

import java.util.Calendar;

/**
 * -----------------------------------------------------
 * 项目： ProPertyAnimatorDemo
 * 作者： wd_zhu
 * 日期： 2016/11/1 14:15
 * 邮箱： zhushengping@wdit.com.cn
 * 描述：<attr name="watchRadius" format="dimension"/>
 * ------------------------------------------------------
 */
public class MyWatchView extends View {
    /**表盘边距*/
    private float mWatchPadding = 5;
    /**表盘与刻度边距*/
    private float mWatchScalePadding = 5;
    /**表盘半径*/
    private float mWatchRadius = 250;
    /**表盘刻度长度*/
    private float mWatchScaleLength;
    /**表盘刻度颜色*/
    private int mWatchScaleColor = Color.BLACK;
    /**表盘整点刻度长度*/
    private float mHourScaleLength = 8;
    /**表盘整点刻度颜色*/
    private int mHourScaleColor = Color.BLUE;
    /**表盘时针颜色*/
    private int mHourPointColor = Color.BLACK;
    /**表盘时针长度*/
    private float mHourPointLength = 100;
    /**表盘分针颜色*/
    private int mMinutePointColor = Color.BLACK;
    /**表盘分针长度*/
    private float mMinutePointLength = 130;
    /**表盘秒针颜色*/
    private int mSecondPointColor = Color.RED;
    /**表盘秒针长度*/
    private float mSecondPointLength = 160;
    /**表盘尾部指针长度*/
    private float mEndPointLength;
    /**表盘数字颜色*/
    private int mTimeTextColor = Color.BLACK;
    /**表盘数字大小*/
    private int mTimeTextSize = 15;

    private Paint mHourPaint;
    private Paint mMinutePaint;
    private Paint mSecondPaint;

    private String[] mTimes = {"XII","Ⅰ","Ⅱ","Ⅲ","Ⅳ","Ⅴ","Ⅵ","Ⅶ","Ⅷ","Ⅸ","Ⅹ","XI"};

    private Paint mPaint;
    public MyWatchView(Context context) {
        this(context,null);
    }

    public MyWatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WatchView);
        int n = array.getIndexCount();
        for (int i = 0;i<n;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.WatchView_watchRadius:
                    mWatchRadius = array.getDimensionPixelSize(attr, CommonUtil.dip2px(context,60));
                    break;
                case R.styleable.WatchView_watchPadding:
                    mWatchPadding = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,5));
                    break;
                case R.styleable.WatchView_watchScalePadding:
                    mWatchScalePadding = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,3));
                    break;
                case R.styleable.WatchView_watchScaleLength:
                    mWatchScaleLength = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,5));
                    break;
                case R.styleable.WatchView_watchScaleColor:
                    mWatchScaleColor = array.getColor(attr, Color.parseColor("#50000000"));
                    break;
                case R.styleable.WatchView_watchHourScaleLength:
                    mHourScaleLength = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,10));
                    break;
                case R.styleable.WatchView_watchHourScaleColor:
                    mHourScaleColor = array.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.WatchView_hourPointLength:
                    mHourPointLength = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,35));
                    break;
                case R.styleable.WatchView_hourPointColor:
                    mHourPointColor = array.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.WatchView_minutePointLength:
                    mMinutePointLength = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,40));
                    break;
                case R.styleable.WatchView_minutePointColor:
                    mMinutePointColor = array.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.WatchView_secondPointLength:
                    mSecondPointLength = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,50));
                    break;
                case R.styleable.WatchView_secondPointColor:
                    mSecondPointColor = array.getColor(attr,Color.BLUE);
                    break;
                case R.styleable.WatchView_timeTextSize:
                    mTimeTextSize = array.getDimensionPixelSize(attr,CommonUtil.dip2px(context,15));
                    break;
                case R.styleable.WatchView_timeTextColor:
                    mTimeTextColor = array.getColor(attr,Color.BLACK);
                    break;
            }

        }
        array.recycle();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth()/2,getHeight()/2);
        paintWatchBoard(canvas); //画表盘
        paintScale(canvas); //画刻度
        paintPoint(canvas);
        canvas.drawCircle(0,0,15,mSecondPaint);
        postInvalidateDelayed(1000);
    }

    /**
     * 画刻度及整点数字
     * @param canvas
     */
    private void paintScale(Canvas canvas){
        int lineLength;
        canvas.save();
        for (int i = 0;i<60;i++){
            if (i%5 == 0){
                mPaint.setStrokeWidth(CommonUtil.dip2px(getContext(),1.5f));
                mPaint.setColor(mHourScaleColor);
                lineLength = CommonUtil.dip2px(getContext(),8);
                canvas.drawLine(0,-mWatchRadius+mWatchScalePadding,0,-mWatchRadius+mWatchScalePadding+lineLength,mPaint);
                mPaint.setColor(mTimeTextColor);
                mPaint.setTextSize(mTimeTextSize);
                canvas.drawText(mTimes[i/5],-mTimeTextSize/2,-mWatchRadius+mWatchScalePadding + lineLength+mTimeTextSize,mPaint);
            }else {
                mPaint.setStrokeWidth(CommonUtil.dip2px(getContext(),0.8f));
                mPaint.setColor(mWatchScaleColor);
                lineLength = CommonUtil.dip2px(getContext(),5);
                canvas.drawLine(0,-mWatchRadius+mWatchScalePadding,0,-mWatchRadius+mWatchScalePadding+lineLength,mPaint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }

    /**
     * 画表盘
     * @param canvas
     */
    private void paintWatchBoard(Canvas canvas){
        initPaint();
        canvas.save();
        canvas.drawCircle(0,0,mWatchRadius,mPaint); //画圆盘
        canvas.restore();
    }

    /**
     * 画指针
     * @param canvas
     */
    private void paintPoint(Canvas canvas){
        initPointPaint();
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        //绘制时针
        canvas.save();
        canvas.rotate(hour*30);
        canvas.drawLine(0,0,0,-mHourPointLength,mHourPaint);
        canvas.drawLine(0,0,0,mEndPointLength,mHourPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(minute*6);
        canvas.drawLine(0,0,0,-mMinutePointLength,mMinutePaint);
        canvas.drawLine(0,0,0,mEndPointLength,mMinutePaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(second*6);
        canvas.drawLine(0,0,0,-mSecondPointLength,mSecondPaint);
        canvas.drawLine(0,0,0,mEndPointLength,mSecondPaint);
        canvas.restore();
    }

    /**
     * 初始化指针
     */
    private void initPointPaint(){
        mHourPaint = new Paint();
        mHourPaint.setAntiAlias(true);
        mHourPaint.setStyle(Paint.Style.FILL);
        mHourPaint.setStrokeWidth(16);
        mHourPaint.setColor(mHourPointColor);

        mMinutePaint = new Paint();
        mMinutePaint.set(mHourPaint);
        mMinutePaint.setStrokeWidth(12);
        mMinutePaint.setColor(mMinutePointColor);

        mSecondPaint = new Paint();
        mSecondPaint.set(mHourPaint);
        mSecondPaint.setStrokeWidth(7);
        mSecondPaint.setColor(mSecondPointColor);
        mEndPointLength = mWatchRadius/6;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wrapContentSize = 1000;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.UNSPECIFIED && heightMode == MeasureSpec.UNSPECIFIED){
            wrapContentSize = (int) Math.max(wrapContentSize,mWatchRadius+mWatchPadding);
            setMeasuredDimension(wrapContentSize,wrapContentSize);
        }else {
            setMeasuredDimension(widthSize,heightSize);
        }
    }

    public class MeasureSizeException extends Exception{
        public MeasureSizeException(String exceptionMsg){
            super(exceptionMsg);
        }
    }
}
