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
            CareerTarget target = new CareerTarget(j, "career " + j, (39 - j) + "%");
            if (j == 0) {
                target.setCareerLevel(CareerTarget.LEVEL_PRIOR);
            } else if (j == 1) {
                target.setCareerLevel(CareerTarget.LEVEL_SECONDARY);
            }
            mData.add(target);
        }

        mListView.setAdapter(new CareerTargetAdapter(this, mData));
    }
}
