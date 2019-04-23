package com.zhusp.androiddemo.activity.materialdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhusp.androiddemo.R;

public class MaterialDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        initViews();
    }

    private void initViews() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_collapsing_tool_bar:
                startActivity(new Intent(this, CollapsingToolBarActivity.class));
                break;
            case R.id.btn_coordinate_use:
                startActivity(new Intent(this, SimpleCoordinatorLayoutUseActivity.class));
                break;
        }
    }
}
