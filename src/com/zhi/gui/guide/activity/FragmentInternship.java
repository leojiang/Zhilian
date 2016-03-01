package com.zhi.gui.guide.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.adapter.IndustryListAdapter;
import com.zhi.gui.guide.adapter.InternshipBriefAdapter;
import com.zhi.gui.guide.adapter.InternshipFullAdapter;
import com.zhi.gui.guide.data.InternshipBrief;
import com.zhi.gui.guide.data.InternshipFull;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentInternship extends FragmentBase {

    private ListView mListIndustry;
    private ListView mListInternships;
    private List<String> mIndustryList;
    private List<InternshipBrief> mInternshipBriefList;
    private List<InternshipFull> mInternshipFullList;

    private boolean isLoggedIn = false;

    @Override
    protected View createView(LayoutInflater inflater) {
        int flag = (int)System.currentTimeMillis() % 2;

        View root;
        if (flag == 0) {
            root = inflater.inflate(R.layout.fragment_internship_brief, null);
            mListIndustry = (ListView) root.findViewById(R.id.list_industry);
            mListInternships = (ListView) root.findViewById(R.id.list_internships);
            initViewWhenLoggedIn();
        } else {
            root = inflater.inflate(R.layout.fragment_internship_full, null);
            mListInternships = (ListView) root.findViewById(R.id.list_internships);
            initViewWhenNotLoggedIn();
        }

        return root;
    }

    private void initViewWhenLoggedIn() {
        mIndustryList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mIndustryList.add("leo" + i);
        }
        mListIndustry.setAdapter(new IndustryListAdapter(getActivity(), mIndustryList));
        mInternshipBriefList = new ArrayList<InternshipBrief>();
        for (int j = 0; j < 30; j++) {
            mInternshipBriefList.add(
                    new InternshipBrief("company" + j, "job" + j, "location" + j, (20 - j) * 100));
        }

        mListInternships
                .setAdapter(new InternshipBriefAdapter(getActivity(), mInternshipBriefList));
    }

    private void initViewWhenNotLoggedIn() {
        mInternshipFullList = new ArrayList<InternshipFull>();
        for (int j = 0; j < 30; j++) {
            mInternshipFullList.add(new InternshipFull("company" + j, "job" + j, "location" + j,
                    (20 - j) * 100, "test_url", "15-25K"));
        }
        mListInternships.setAdapter(new InternshipFullAdapter(getActivity(), mInternshipFullList));
    }
}