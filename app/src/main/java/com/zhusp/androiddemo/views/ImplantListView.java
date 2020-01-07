package com.zhusp.androiddemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ImplantListView extends ListView {
    public ImplantListView(Context context) {
        super(context);
    }

    public ImplantListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImplantListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
