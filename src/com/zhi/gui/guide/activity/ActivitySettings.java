package com.zhi.gui.guide.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.common.Preferences;
import com.zhi.gui.guide.network.NetRequestListener;
import com.zhi.gui.guide.network.NetworkTask;
import com.zhi.gui.guide.network.ThreadPoolManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivitySettings extends BaseActivity implements OnClickListener {
    private View mBtnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings);
        ((TextView)findViewById(R.id.navigation_bar).findViewById(R.id.title)).setText("我的设置");
        mBtnLogOut = findViewById(R.id.btn_log_out);
        mBtnLogOut.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_log_out:
                Preferences.setUserLoggedInState(getApplicationContext(), false);
                sendBroadcast(new Intent(FragmentBase.BROADCAST_USER_LOG_OUT));
                finish();
                break;
        }
    }

    public void logout() {
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
}
