package com.zhusp.androiddemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhusp.androiddemo.R;

import java.util.List;

/**
 * -----------------------------------------------------
 * 项目： ProPertyAnimatorDemo
 * 作者： wd_zhu
 * 日期： 2016/7/29 14:32
 * 邮箱： zhushengping@wdit.com.cn
 * 描述： 自定义ViewPager头部指示器
 * ------------------------------------------------------
 */
public class ViewPagerIndicator extends HorizontalScrollView {
    private final int NORMAL_TEXT_COCLR = 0x77ffffff;
    private final int HIGHTLIAGT_TEXT_COCLR = 0xffffffff;
    private final int DEFAULT_VISIABLE_ITEM = 4;
    private int mVisiableItemCount;
    private float mScreenWidth;
    private List<String> mTitles;

    private Paint mPaint;
    private Path mPath;
    private int mTriangleWidth;
    private int mTriangleHeight;
    private int mInitTranslationX;
    private int mTranslationX;
    private OnPageChangeListener mListener;

    private final float RADIO_TRIANGLE_WIDTH = 1 / 6f;
    private int mTabWidth;
    private ViewPager mViewPager;
    private LinearLayout linearLayout;


    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setHorizontalScrollBarEnabled(false);
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setPathEffect(new CornerPathEffect(3));
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mVisiableItemCount = a.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, DEFAULT_VISIABLE_ITEM);
        a.recycle();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();
//        canvas.translate(mTranslationX, getHeight());
        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (mScreenWidth / mVisiableItemCount * RADIO_TRIANGLE_WIDTH);
        mInitTranslationX = (int) (mScreenWidth / mVisiableItemCount / 2 - mTriangleWidth / 2);
//        mTabWidth = (int) (mScreenWidth/mVisiableItemCount);
//        initLines();
        initTriangle();

    }

    /**
     * 设置应用的ViewPager
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager, int position) {
        this.mViewPager = viewPager;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffset 偏移量
                scroll(position, positionOffset);
                if (mVisiableItemCount != 1) {
                    if (position >= mVisiableItemCount - 2 && position < mTitles.size() - 2) {
                        scrollTo((int) ((position - (mVisiableItemCount - 2)) * mTabWidth + mTabWidth * positionOffset), 0);
                    }
                } else {
                    scrollTo((int) (position * mTabWidth + mTabWidth * positionOffset), 0);
                }
                if (mListener != null){
                    mListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                setHightLightText(position);
                if (mListener != null){
                    mListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null){
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        viewPager.setCurrentItem(position);
        setHightLightText(position);
    }

    /**
     * 三角形跟随手指进行滚动
     *
     * @param pos    当前所在位置
     * @param offset 偏移量
     */
    private void scroll(int pos, float offset) {
        mTabWidth = (int) (mScreenWidth / mVisiableItemCount);
        mTranslationX = (int) (pos * mTabWidth + offset * mTabWidth);
        invalidate();//重绘控件
    }

    /**
     * 初始化三角形
     */
    private void initTriangle() {
        mTriangleHeight = mTriangleWidth / 2;
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    private void initLines(){
        mPath = new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTabWidth,0);
        mPath.lineTo(mTabWidth,-10);
        mPath.lineTo(0,-10);
        mPath.close();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    /**
     * 动态添加TitleView
     * @param titles
     */
    public void addIndicatorItem(List<String> titles) {
        removeAllViews();
        mScreenWidth = getScreenWidth();
        if (null != titles) {
            this.mTitles = titles;
            linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0;i<titles.size();i++) {
                String title = titles.get(i);
                TextView tv = new TextView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                lp.weight = 0;
                lp.width = (int) (mScreenWidth / mVisiableItemCount);
                tv.setText(title);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tv.setTextColor(NORMAL_TEXT_COCLR);
                tv.setLayoutParams(lp);
                final int j = i;
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOnItemClickEvent(j);
                    }
                });
                linearLayout.addView(tv);
            }
            addView(linearLayout);
        }
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    private float getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 设置可见Tab数量
     * @param count
     */
    public void setVisibleCount(int count){
        this.mVisiableItemCount = count;
    }

    /**
     * 设置高亮文本
     * @param position
     */
    private void setHightLightText(int position){
        reSetTextColor();
        View view = linearLayout.getChildAt(position);
        if (view instanceof TextView){
            ((TextView) view).setTextColor(HIGHTLIAGT_TEXT_COCLR);
        }
    }

    /**
     * 重置指示器文本颜色
     */
    private void reSetTextColor(){
        int n = linearLayout.getChildCount();
        for (int i = 0;i<n;i++){
            View view = linearLayout.getChildAt(i);
            if (view instanceof TextView){
                ((TextView) view).setTextColor(NORMAL_TEXT_COCLR);
            }
        }
    }

    /**
     * 设置Tab点击事件
     * @param position
     */
    private void setOnItemClickEvent(int position){
        if (null != mViewPager){
            mViewPager.setCurrentItem(position);
        }
    }

    /**
     * 给用户提供被Indicator占用的ViewPager的接口
     */
    public interface OnPageChangeListener{
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    public void setOnPageChangeListener(OnPageChangeListener listener){
        this.mListener = listener;
    }
}
