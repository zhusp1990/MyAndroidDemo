package com.zhusp.androiddemo.activity.extend.dragRecyclerView;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;

public class RecyclerViewDragActivity extends AppCompatActivity {

    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_drag);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_list:
                fragment = new ListRecyclerFragment();
                break;
            case R.id.btn_grid:
                fragment = new GridRecyclerFragment();
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
    }
}
