package com.zhi.gui.guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.adapter.ImageListAdapter;
import com.zhi.gui.guide.adapter.InternshipFullAdapter;
import com.zhi.gui.guide.data.InternshipFull;
import com.zhi.gui.guide.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class CompanyActivity extends BaseActivity implements View.OnClickListener {
    private HorizontalListView mGallery;
    private ListView mJobLIstView;
    private InternshipFullAdapter mFullAdapter;
    private List<InternshipFull> mInternshipFullList;

    private TextView mTabCompany;
    private TextView mTabJobs;
    private View mCompanInfo;
    private View mJobsInfo;
    private List<String> mImageURLs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_detail);

        mGallery = (HorizontalListView) findViewById(R.id.gallery);
        mJobLIstView = (ListView) findViewById(R.id.job_list);
        mTabCompany = (TextView)findViewById(R.id.company);
        mTabJobs = (TextView)findViewById(R.id.jobs);
        mCompanInfo = findViewById(R.id.company_info);
        mJobsInfo = findViewById(R.id.jobs_info);
        mTabCompany.setOnClickListener(this);
        mTabJobs.setOnClickListener(this);

        initGallery();
        initListView();
    }

    private void initGallery() {
        for (int i = 0; i < 10; i++) {
            mImageURLs.add(" ");
        }
        mGallery.setAdapter(new ImageListAdapter(this, mImageURLs));
    }

    private void initListView() {
        initViewWhenNotLoggedIn();
    }


    private void initViewWhenNotLoggedIn() {
        mInternshipFullList = new ArrayList<InternshipFull>();
        for (int j = 0; j < 30; j++) {
            mInternshipFullList.add(new InternshipFull("company" + j, "job" + j, "location" + j,
                    (20 - j) * 100, "test_url", "15-25K"));
        }
        mFullAdapter = new InternshipFullAdapter(this, mInternshipFullList);
        mJobLIstView.setAdapter(mFullAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.company:
                selectCompanInfo();
                break;
            case R.id.jobs:
                selectJobsInfo();
                break;
        }
    }

    private void selectCompanInfo() {
        mTabCompany.setBackgroundColor(getResources().getColor(R.color.color_shllow_blue));
        mTabCompany.setTextColor(getResources().getColor(R.color.color_bg_white));
        mTabJobs.setBackgroundColor(getResources().getColor(R.color.color_bg_white));
        mTabJobs.setTextColor(getResources().getColor(R.color.text_black));
        mCompanInfo.setVisibility(View.VISIBLE);
        mJobsInfo.setVisibility(View.GONE);
    }

    private void selectJobsInfo() {
        mTabCompany.setBackgroundColor(getResources().getColor(R.color.color_bg_white));
        mTabCompany.setTextColor(getResources().getColor(R.color.text_black));
        mTabJobs.setBackgroundColor(getResources().getColor(R.color.color_shllow_blue));
        mTabJobs.setTextColor(getResources().getColor(R.color.color_bg_white));
        mCompanInfo.setVisibility(View.GONE);
        mJobsInfo.setVisibility(View.VISIBLE);
    }
}
