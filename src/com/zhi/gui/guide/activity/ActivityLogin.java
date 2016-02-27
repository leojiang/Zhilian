package com.zhi.gui.guide.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.zhi.gui.guide.R;

public class ActivityLogin extends BaseActivity implements OnClickListener {
    private View mBtnLookRound;
    private View mBtnRegister;
    private EditText mEditTextUsername;
    private EditText mEditTextPasswd;
    private View mBtnLogin;
    private View mBtnForgotPasswd;
    private View mBtnShareWeixin;
    private View mBtnShareQQ;
    private View mBtnShareWeibo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextUsername = (EditText) findViewById(R.id.text_username);
        mEditTextPasswd = (EditText) findViewById(R.id.text_password);
        mBtnLogin = findViewById(R.id.button_login);
        mBtnLogin.setOnClickListener(this);
        mBtnForgotPasswd = findViewById(R.id.forgot_password);
        mBtnForgotPasswd.setOnClickListener(this);
        mBtnShareWeixin = findViewById(R.id.btn_share_weixin);
        mBtnShareWeixin.setOnClickListener(this);
        mBtnShareQQ = findViewById(R.id.btn_share_qq);
        mBtnShareQQ.setOnClickListener(this);
        mBtnShareWeibo = findViewById(R.id.btn_share_weibo);
        mBtnShareWeibo.setOnClickListener(this);
        mBtnLookRound = findViewById(R.id.text_lookaround);
        mBtnLookRound.setOnClickListener(this);
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_login:
                break;
            case R.id.forgot_password:
                break;
            case R.id.btn_register:
                startActivity(new Intent(getApplicationContext(), ActivityRegister.class));
                break;
            case R.id.text_lookaround:
                startActivity(new Intent(getApplicationContext(), ActivityMainTab.class));
                break;
            case R.id.btn_share_weixin:
                break;
            case R.id.btn_share_qq:
                break;
            case R.id.btn_share_weibo:
                break;
        }
    }
}
