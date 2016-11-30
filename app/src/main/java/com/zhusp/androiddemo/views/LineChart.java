package com.zhusp.androiddemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhusp.androiddemo.R;
import com.zhusp.androiddemo.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------
 * 项目： MyColorRateView
 * 作者： wd_zhu
 * 日期： 2016/10/24 14:21
 * 邮箱： zhushengping@wdit.com.cn
 * 描述： 折线图
 * ------------------------------------------------------
 */
public class LineChart extends View {
    private Paint mLinePaint;
    private Paint mDotPoint;
    private Paint mMonthPoint;
    private Paint mSumPoint;
    private int dotNum;
    private int lineColor;
    private int mWidth;
    private int mHeight;
    private float dotRadius;
    private List<Float> mSumData;
    private float maxData;
    private float singleWidth;
    private List<String> mSelectMonth;

    public LineChart(Context context) {
        this(context, null);
    }

    public LineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LineChart, defStyleAttr, 0);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.LineChart_dotNum:
                    dotNum = array.getInteger(attr, 4);
                    break;
                case R.styleable.LineChart_lineColor:
                    lineColor = array.getColor(attr, Color.YELLOW);
                    break;
                case R.styleable.LineChart_dotRadius:
                    dotRadius = array.getDimensionPixelSize(attr, CommonUtil.dip2px(context, 5));
                    break;
            }
        }
        array.recycle();
        initPaint();
        initData();
    }

    private void initData() {
        mSumData = new ArrayList<>();
        dotNum = mSumData.size();
    }

    private void initPaint() {
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(lineColor);
        mLinePaint.setStrokeWidth(dotRadius / 4 * 3);
        mLinePaint.setStyle(Paint.Style.STROKE);

        mDotPoint = new Paint();
        mDotPoint.setAntiAlias(true);
        mDotPoint.setStrokeWidth(dotRadius);
        mDotPoint.setColor(lineColor);

        mMonthPoint = new Paint();
        mMonthPoint.setAntiAlias(true);
        mMonthPoint.setStrokeWidth(dotRadius);
        mMonthPoint.setColor(Color.parseColor("#6b6b6b"));
        mMonthPoint.setTextSize(CommonUtil.dip2px(getContext(), 15));

        mSumPoint = new Paint();
        mSumPoint.setAntiAlias(true);
        mSumPoint.setStrokeWidth(dotRadius);
        mSumPoint.setColor(Color.parseColor("#a4a4a4"));
        mSumPoint.setTextSize(CommonUtil.dip2px(getContext(), 12));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (dotNum > 0) {
            singleWidth = mWidth / dotNum;
            Path path = new Path();
            float hight = (float) (mHeight * 0.6);
            path.moveTo(0,getHeight()/2);
            for (int i = 0; i < dotNum; i++) {
                float width = singleWidth * i + singleWidth / 2;
                if (maxData > 0){
                   hight = (float) (mHeight * 0.6 * (1.3 - mSumData.get(i) / maxData));
                }
                path.lineTo(width, hight);
                canvas.drawCircle(width, hight, dotRadius, mDotPoint);
                if (null != mSelectMonth){
                    canvas.drawText(mSelectMonth.get(i), width-dotRadius*2, mHeight - dotRadius * 2, mMonthPoint);
                }
                canvas.drawText(mSumData.get(i) + "", width-dotRadius*2, hight - dotRadius * 2, mSumPoint);
            }
            canvas.drawPath(path, mLinePaint);
        }
    }

    public void setSumData(List<Float> sumData, List<String> seletcMonths) {
        if (null != sumData && null != seletcMonths) {
            mSelectMonth = seletcMonths;
            this.mSumData.clear();
            this.mSumData.addAll(sumData);
            this.dotNum = mSumData.size();
            for (Float value : sumData) {
                maxData = value > maxData ? value : maxData;
            }
            invalidate();
        }
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
            mWidth = specSize;
        } else {

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
//                mWidth = (int) (mRadius*2);
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
            mHeight = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
//                mHeight = (int) (mRadius*2);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

}
