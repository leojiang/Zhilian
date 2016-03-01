package com.zhi.gui.guide.activity;

import com.zhi.gui.guide.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentFriends extends FragmentBase {

    @Override
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_friends, null);
    }
}