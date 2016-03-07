package com.zhi.gui.guide.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.adapter.IndustryListAdapter;
import com.zhi.gui.guide.adapter.InternshipBriefAdapter;
import com.zhi.gui.guide.adapter.InternshipFullAdapter;
import com.zhi.gui.guide.data.InternshipFull;
import com.zhi.gui.guide.view.RefreshableView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FragmentInternship extends FragmentBase
        implements RefreshableView.RefreshAndLoadListener, AdapterView.OnItemClickListener {

    private ListView mListInternships;
    private List<InternshipFull> mInternshipFullList;
    private InternshipBriefAdapter mBriefAdapter;
    private InternshipFullAdapter mFullAdapter;
    private IndustryListAdapter mIndustryAdapter;
    private RefreshableView mRefreshableView;
    private View mComptenceView;
    private View mAdvertisement;
    private View mNavigation;
    private View mSearchBar;
    private View mFull;
    private boolean mIsCompetenceEnough = true;

    @Override
    protected View createView(LayoutInflater inflater) {
        View root = inflater.inflate(R.layout.fragment_internship, null);
        mNavigation = root.findViewById(R.id.navigation);
        mFull = root.findViewById(R.id.intership_full);
        mComptenceView = mFull.findViewById(R.id.competence_view);
        mSearchBar = mFull.findViewById(R.id.search_bar);
        mAdvertisement = mFull.findViewById(R.id.advertisement);
        mListInternships = (ListView) mFull.findViewById(R.id.list_internships);
        mRefreshableView = (RefreshableView) mFull.findViewById(R.id.refreshable_view);
        mListInternships.setOnItemClickListener(this);
        mRefreshableView.setOnRefreshListener(this, 0);
        showView();
        initListView();
        return root;
    }

    private void showView() {
        if (mIsCompetenceEnough) {
            mRefreshableView.setVisibility(View.VISIBLE);
            mSearchBar.setVisibility(View.VISIBLE);
            mNavigation.setVisibility(View.GONE);
            mComptenceView.setVisibility(View.GONE);
            mAdvertisement.setVisibility(View.VISIBLE);
        } else {
            mRefreshableView.setVisibility(View.GONE);
            mSearchBar.setVisibility(View.GONE);
            mAdvertisement.setVisibility(View.GONE);
            mNavigation.setVisibility(View.VISIBLE);
            mComptenceView.setVisibility(View.VISIBLE);
        }
    }

    private void initListView() {
        mInternshipFullList = new ArrayList<InternshipFull>();
        for (int j = 0; j < 30; j++) {
            if(j < 5) {
                mInternshipFullList.add(new InternshipFull("company" + j, "job" + j, "location" + j,
                        (20 - j) * 100, "test_url", "15-25K", true));
            } else {
                mInternshipFullList.add(new InternshipFull("company" + j, "job" + j, "location" + j,
                        (20 - j) * 100, "test_url", "15-25K"));
            }
        }
        mFullAdapter = new InternshipFullAdapter(getActivity(), mInternshipFullList);
        mFullAdapter.setmIsCompetenceEnough(mIsCompetenceEnough);
        mListInternships.setAdapter(mFullAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), ActivityInternshipDetail.class));
    }

    @Override
    public void onRefresh() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad() {

    }

    @Override
    protected void onUserLogOut() {
        showView();
    }

    @Override
    protected void onUserLogIn() {
        showView();
    }
}