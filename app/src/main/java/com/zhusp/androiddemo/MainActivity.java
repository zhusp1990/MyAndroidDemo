package com.zhusp.androiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_define_view:
                startActivity(new Intent(this,SelfDefindViewActivity.class));
                break;
        }
    }
}
