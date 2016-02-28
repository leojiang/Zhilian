package com.zhi.gui.guide.activity;

import com.zhi.gui.guide.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ActivityAddBasicInfo extends BaseActivity implements OnClickListener{
    private View mNextStep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basic_info);

        mNextStep = findViewById(R.id.button_next_step);
        mNextStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        startActivity(new Intent(this, ActivityChooseIndustry.class));
    }
}
