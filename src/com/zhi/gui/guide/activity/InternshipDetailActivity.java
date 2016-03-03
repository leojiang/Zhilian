package com.zhi.gui.guide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhi.gui.guide.R;

public class InternshipDetailActivity extends BaseActivity implements View.OnClickListener {

    private View mCompanyDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_detail);

        mCompanyDescription = findViewById(R.id.company_description);
        mCompanyDescription.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.company_description:
                startActivity(new Intent(this, CompanyActivity.class));
                break;
        }

    }
}
