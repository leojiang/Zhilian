package com.zhi.gui.guide.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.view.HomePageViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentHomePage extends Fragment {

    private ViewPager mViewPager;
    private HomePageViewAdapter mViewPagerAdapter;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_homepage, null);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.view_pager);
        initView();
        mViewPager.setCurrentItem(0);
        return mRootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new FragmentLookAround());
        mFragmentList.add(new FragmentCompetence());

        mTitleList = new ArrayList<String>();
        mTitleList.add(getResources().getString(R.string.homepage_lookaround));
        mTitleList.add(getResources().getString(R.string.homepage_competence));

        mViewPagerAdapter = new HomePageViewAdapter(getChildFragmentManager(), mFragmentList,
                mTitleList);

        mViewPager.setAdapter(mViewPagerAdapter);
    }
}
