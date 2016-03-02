package com.zhi.gui.guide.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.adapter.CompetenceListAdapter;
import com.zhi.gui.guide.adapter.IndustryListAdapter;
import com.zhi.gui.guide.data.Competence;
import com.zhi.gui.guide.view.RefreshableView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class FragmentLookAround extends FragmentBase implements RefreshableView.RefreshAndLoadListener {

    private ListView mListIndustry;
    private ListView mListRanking;
    private List<String> mIndustryList;
    private List<Competence> mCompetenceList;
    private CompetenceListAdapter mCompetenceListAdapter;
    private RefreshableView mRefreshableView;
    private Handler mHandler = new Handler();

    @Override
    protected View createView(LayoutInflater inflater) {
        View root = inflater.inflate(R.layout.fragment_lookaround, null);
        mListIndustry = (ListView) root.findViewById(R.id.list_industry);
        mListRanking = (ListView) root.findViewById(R.id.list_ranking);
        mRefreshableView = (RefreshableView) root.findViewById(R.id.refreshable_view);
        mRefreshableView.setOnRefreshListener(this, 0);

        initListView();

        return root;
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
            for(int i = 0; i < 2; i++) {
                mCompetenceList.add(0, new Competence(31, "leo" + 31, (20 - 31) * 100));
            }

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

    @Override
    public void onLoad() {
        try {
            Thread.sleep(3000);
            for(int i = 0; i < 5; i++) {
                mCompetenceList.add(new Competence(2, "leo" + 31, (20 - 31) * 100));
            }

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