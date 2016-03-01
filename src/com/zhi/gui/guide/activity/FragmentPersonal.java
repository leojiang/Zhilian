package com.zhi.gui.guide.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.zhi.gui.guide.R;

public class FragmentPersonal extends FragmentBase {

    @Override
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_personal, null);
    }
}