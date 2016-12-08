package com.zhusp.androiddemo.activity.extend.dragRecyclerView;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------
 * 项目： MyAndroidDemo
 * 作者： wd_zhu
 * 日期： 2016/12/8 16:06
 * 邮箱： zhushengping@wdit.com.cn
 * 描述：
 * ------------------------------------------------------
 */
public class GridRecyclerFragment extends Fragment implements MyItemTouchCallBack.OnDragListener {

    private List<String> mDatas;
    private Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mDatas = new ArrayList<>();
        for (int i = 0;i<9;i++){
            mDatas.add("item:"+i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return new RecyclerView(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MyAdapter adapter = new MyAdapter(mContext,mDatas);
        RecyclerView recyclerView = (RecyclerView) view;
        GridLayoutManager lm = new GridLayoutManager(mContext,4);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);

        final ItemTouchHelper itemHelper = new ItemTouchHelper(new MyItemTouchCallBack(adapter).setOnDragListener(this));
        itemHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView){
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Toast.makeText(mContext,"click Item:"+vh.getLayoutPosition(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition() != mDatas.size()-1){
                    itemHelper.startDrag(vh);
                }
            }
        });
    }

    @Override
    public void onFinishDrag() {

    }
}
