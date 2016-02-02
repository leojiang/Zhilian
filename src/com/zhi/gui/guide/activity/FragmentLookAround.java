package com.zhi.gui.guide.activity;

import com.zhi.gui.guide.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentLookAround extends Fragment {

    private ListView mListIndustry;
    private ListView mListRanking;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lookaround, null);
        mListIndustry = (ListView) root.findViewById(R.id.list_industry);
        mListRanking = (ListView) root.findViewById(R.id.list_ranking);
        initView();
        return root;
    }

    private void initView() {
        mListIndustry.setAdapter(null);
        mListRanking.setAdapter(null);
    }
}