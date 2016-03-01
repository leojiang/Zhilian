package com.zhi.gui.guide.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.adapter.CompetenceListAdapter;
import com.zhi.gui.guide.adapter.IndustryListAdapter;
import com.zhi.gui.guide.data.Competence;
import com.zhi.gui.guide.view.RefreshableView;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentLookAround extends Fragment implements RefreshableView.PullToRefreshListener {

    private ListView mListIndustry;
    private ListView mListRanking;
    private List<String> mIndustryList;
    private List<Competence> mCompetenceList;
    private RefreshableView mRefreshableView;
    private CompetenceListAdapter mCompetenceListAdapter;
    private Handler mHandler = new Handler() {
    };

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_lookaround, null);
            mListIndustry = (ListView) mRootView.findViewById(R.id.list_industry);
            mListRanking = (ListView) mRootView.findViewById(R.id.list_ranking);
            mRefreshableView = (RefreshableView) mRootView.findViewById(R.id.refreshable_view);
            mRefreshableView.setOnRefreshListener(this, 0);

            initListView();
        }

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    private void initListView() {
        mIndustryList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mIndustryList.add("leo" + i);
        }
        mListIndustry.setAdapter(new IndustryListAdapter(getActivity(), mIndustryList));
        mCompetenceList = new ArrayList<Competence>();
        for (int j = 0; j < 30; j++) {
            mCompetenceList.add(new Competence(j, "leo" + j, (20 - j) * 100));
        }

        mCompetenceListAdapter = new CompetenceListAdapter(getActivity(), mCompetenceList);
        mListRanking.setAdapter(mCompetenceListAdapter);
    }

    @Override
    public void onRefresh() {
        try {
            Thread.sleep(3000);
            mCompetenceList.add(new Competence(31, "leo" + 31, (20 - 31) * 100));

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCompetenceListAdapter.notifyDataSetChanged();
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}