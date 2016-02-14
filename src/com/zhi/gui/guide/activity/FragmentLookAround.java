package com.zhi.gui.guide.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.adapter.CompetenceListAdapter;
import com.zhi.gui.guide.adapter.IndustryListAdapter;
import com.zhi.gui.guide.data.Competence;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentLookAround extends Fragment {

    private ListView mListIndustry;
    private ListView mListRanking;
    private List<String> mIndustryList;
    private List<Competence> mCompetenceList;

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
        mIndustryList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mIndustryList.add("leo" + i);
        }
        mListIndustry.setAdapter(new IndustryListAdapter(getActivity(), mIndustryList));
        mCompetenceList = new ArrayList<Competence>();
        for (int j = 0; j < 30; j++) {
            mCompetenceList.add(new Competence(j, "leo" + j, (20 - j) * 100));
        }

        mListRanking.setAdapter(new CompetenceListAdapter(getActivity(), mCompetenceList));
    }
}