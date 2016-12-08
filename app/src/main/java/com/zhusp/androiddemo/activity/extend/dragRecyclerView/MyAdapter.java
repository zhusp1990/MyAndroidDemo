package com.zhusp.androiddemo.activity.extend.dragRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhusp.androiddemo.R;

import java.util.Collections;
import java.util.List;

/**
 * -----------------------------------------------------
 * 项目： MyAndroidDemo
 * 作者： wd_zhu
 * 日期： 2016/12/8 16:19
 * 邮箱： zhushengping@wdit.com.cn
 * 描述：
 * ------------------------------------------------------
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements MyItemTouchCallBack.ItemTouchAdapter{

    private List<String> datas;
    private Context mContext;

    public MyAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.datas = datas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_layout,null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition==datas.size()-1 || toPosition==datas.size()-1){
            return;
        }
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(datas, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(datas, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwiped(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
