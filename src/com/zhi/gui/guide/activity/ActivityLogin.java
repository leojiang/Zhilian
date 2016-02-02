package com.zhi.gui.guide.activity;


import com.zhi.gui.guide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ActivityLogin extends BaseActivity {
    private TextView mTextLookAround;
    private TextView mTextRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextLookAround = (TextView) findViewById(R.id.text_lookaround);
        mTextLookAround.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(getApplicationContext(), ActivityMainTab.class));
                finish();

            }
        });

        mTextRegister = (TextView) findViewById(R.id.text_register);
        mTextRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityRegister.class));
            }
        });
    }
}
