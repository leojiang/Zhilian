package com.zhi.gui.guide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhi.gui.guide.R;

public class ActivityRegister extends BaseActivity {
    private View mBtnRegister;
    private EditText mEditTextUserName;
    private EditText mEditTextPasswd;
    private EditText mEditTextConfPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEditTextUserName = (EditText) findViewById(R.id.text_username);
        mEditTextPasswd = (EditText) findViewById(R.id.text_password);
        mEditTextConfPasswd = (EditText) findViewById(R.id.text_confirm_password);

        mBtnRegister = findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityAddBasicInfo.class));
            }
        });
    }
}
