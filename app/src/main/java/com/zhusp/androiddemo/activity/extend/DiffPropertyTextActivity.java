package com.zhusp.androiddemo.activity.extend;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhusp.androiddemo.R;

public class DiffPropertyTextActivity extends AppCompatActivity {

    private EditText etStyle;
    private TextView tvStyle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_property_text);
        tvStyle1 = findViewById(R.id.tv_style1);
        String style1Text = "今天<font color='#FF0000'>天气不错</font>";
        tvStyle1.setText(Html.fromHtml(style1Text));

        TextView tvStyle2 = findViewById(R.id.tv_style2);
        String style2Text = "今天<font color='#FF0000'><small>天气不错</small></font>";
        tvStyle2.setText(Html.fromHtml(style2Text));

        TextView tvStyle3 = findViewById(R.id.tv_style3);
        SpannableString str = new SpannableString("邀请好友可得5元现金");
        str.setSpan(new RelativeSizeSpan(1.5f),6,7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE );
        tvStyle3.setText(str);

        etStyle = findViewById(R.id.tv_style4);
        etStyle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())){
                    etStyle.setTypeface(etStyle.getTypeface(),Typeface.BOLD);
                }else{
                    etStyle.setTypeface(tvStyle1.getTypeface());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onClick(View view){

    }
}
