package com.zhi.gui.guide.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.adapter.CareerTargetAdapter;
import com.zhi.gui.guide.data.CareerTarget;

import android.os.Bundle;
import android.widget.ListView;

public class ActivityChooseIndustry extends BaseActivity {
    private ListView mListView;
    private List<CareerTarget> mData = new ArrayList<CareerTarget>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_career_target);
        mListView = (ListView) findViewById(R.id.list);
        initListView();
    }


    private void initListView() {
        for (int j = 0; j < 30; j++) {
            mData.add(new CareerTarget(j, "ÐÐÒµ" + j, (39 - j) + "%"));
        }

        mListView.setAdapter(new CareerTargetAdapter(this, mData));
    }
}
