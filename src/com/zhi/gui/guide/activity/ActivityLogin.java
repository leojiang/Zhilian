package com.zhi.gui.guide.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.common.Constants;
import com.zhi.gui.guide.common.Preferences;
import com.zhi.gui.guide.network.NetRequestListener;
import com.zhi.gui.guide.network.NetworkTask;
import com.zhi.gui.guide.network.ThreadPoolManager;

import org.json.JSONException;
import org.json.JSONObject;

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
    private boolean mIsFromMain = false;
    private int mBackKeyPressedCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsFromMain = getIntent().getBooleanExtra(Constants.KEY_IS_LOGIN_FROM_MAIN, false);

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

        setViewVisibility();
    }

    private void setViewVisibility() {
        if (mIsFromMain) {
            mBtnLookRound.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_login:
//                login();
                if (!TextUtils.isEmpty(mEditTextUsername.getText().toString())) {
                    Preferences.setUserLoggedInState(getApplicationContext(), true);
                } else {
                    Preferences.setUserLoggedInState(getApplicationContext(), false);
                }
                if (mIsFromMain) {
                    sendBroadcast(new Intent(FragmentBase.BROADCAST_USER_LOG_IN));
                } else {
                    startActivity(new Intent(this, ActivityMainTab.class));
                }
                finish();
                break;
            case R.id.forgot_password:
                break;
            case R.id.btn_register:
                startActivity(new Intent(getApplicationContext(), ActivityRegister.class));
                break;
            case R.id.text_lookaround:
                Preferences.setUserLoggedInState(getApplicationContext(), false);
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

    public void login() {
        try {
            JSONObject object = new JSONObject();
            object.put("ACTION", "fuck zhuangzhuang");
            NetworkTask task = new NetworkTask("http://139.196.240.222:55500/", object, null);
            task.setNetRequestListener(new NetRequestListener() {
                @Override
                public void onSucceed() {

                }

                @Override
                public void onFail() {

                }

                @Override
                public void onTimeout() {

                }

                @Override
                public void onError() {

                }
            });
            ThreadPoolManager.getInstance().runTask(task);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackKeyPressedCount == 0) {
            mBackKeyPressedCount++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBackKeyPressedCount = 0;
                }
            }, 2000);
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}
