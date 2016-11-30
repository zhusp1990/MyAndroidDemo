package com.zhusp.androiddemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhusp.androiddemo.R;

/**
 * -----------------------------------------------------
 * 项目： helifeAndroid
 * 作者： wd_zhu
 * 日期： 2016/10/23 17:06
 * 邮箱： zhushengping@wdit.com.cn
 * 描述：
 * ------------------------------------------------------
 */
public class CircleScaleView extends View {
    private static final String TAG = "CircleScaleView";
    private int mRadius;
    private int mLifeCostColor;
    private int trafficCostColor;
    private int mEntertainmentColor;
    private int mCommunicationCostColor;
    private Paint mPaint;
    private float mCircleWidth;
    private int mWidth;
    private int mHeight;
    private RectF mRectF;
    private float lifeSweepAngle;
    private float communicateSweep;
    private float trafficSweep;
    private float entertainmentSweep;
    private float lifePercent;
    private float trafficPercent;
    private float communicatePercent;
    private float entertainmentPercent;

    public CircleScaleView(Context context) {
        this(context,null);
    }

    public CircleScaleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleScaleView,defStyleAttr,0 );
        int n = array.getIndexCount();
        for (int i = 0;i<n;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.CircleScaleView_lifeCostColor:
                    mLifeCostColor = array.getColor(attr, Color.parseColor("#fac62d"));
                    break;
                case R.styleable.CircleScaleView_trafficCostColor:
                    trafficCostColor = array.getColor(attr, Color.parseColor("#65cff6"));
                    break;
                case R.styleable.CircleScaleView_communicateCostColor:
                    mCommunicationCostColor = array.getColor(attr, Color.parseColor("#fe9a9c"));
                    break;
                case R.styleable.CircleScaleView_entertainmentCostColor:
                    mEntertainmentColor = array.getColor(attr, Color.parseColor("#a286da"));
                    break;
                case R.styleable.CircleScaleView_circleWidth:
                    mCircleWidth = array.getDimensionPixelSize(attr,dip2px(context,20));
                    break;
                case R.styleable.CircleScaleView_radius:
                    mRadius = array.getDimensionPixelSize(attr,dip2px(context,20));
                    break;

            }
        }

        array.recycle();
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setCostPercent(float liftCost,float trafficCost,float communicateCost,float entertainmentCost){
        float total = liftCost + trafficCost + communicateCost + entertainmentCost;
        lifePercent = liftCost/total;
        trafficPercent = trafficCost/total;
        communicatePercent = communicateCost/total;
        entertainmentPercent = entertainmentCost/total;
        invalidate();//重绘
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(mCircleWidth);
        mRectF = new RectF(mCircleWidth/2,mCircleWidth/2,mRadius*2-mCircleWidth/2,mRadius*2-mCircleWidth/2);
        float startAngle = -90;
        lifeSweepAngle = 360*lifePercent;
        Log.i(TAG,"lifePercent:"+lifeSweepAngle);
        mPaint.setColor(mLifeCostColor);
        canvas.drawArc(mRectF,startAngle,lifeSweepAngle,false,mPaint);

        startAngle = startAngle + lifeSweepAngle;
        communicateSweep = 360*communicatePercent;
        Log.i(TAG,"communicateSweep:"+communicateSweep);
        mPaint.setColor(mCommunicationCostColor);
        canvas.drawArc(mRectF,startAngle,communicateSweep,false,mPaint);

        startAngle = startAngle + communicateSweep;
        trafficSweep = 360*trafficPercent;
        Log.i(TAG,"trafficSweep:"+trafficSweep);
        mPaint.setColor(trafficCostColor);
        canvas.drawArc(mRectF,startAngle,trafficSweep,false,mPaint);

        startAngle = startAngle + trafficSweep;
        entertainmentSweep = 360*(1-lifePercent-communicatePercent-trafficPercent);
        Log.i(TAG,"entertainmentSweep:"+entertainmentSweep);
        mPaint.setColor(mEntertainmentColor);
        canvas.drawArc(mRectF,startAngle,entertainmentSweep,false,mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            Log.e("xxx", "EXACTLY");
            mWidth = (int) (specSize + mCircleWidth);
        } else
        {

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mWidth = (int) (mRadius*2);
                Log.e("xxx", "AT_MOST");
            }
        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = (int) (specSize+mCircleWidth);
        } else
        {
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mHeight = (int) (mRadius*2);
            }
        }
        setMeasuredDimension(mWidth+10, mHeight+10);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
