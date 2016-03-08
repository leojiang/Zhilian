package com.zhi.gui.guide.activity;

import com.zhi.gui.guide.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentFriends extends FragmentBase {

    @Override
    protected View createView(LayoutInflater inflater) {
        View root= inflater.inflate(R.layout.fragment_friends, null);
        ((TextView)root.findViewById(R.id.navigation_bar).findViewById(R.id.title)).setText("圈圈");
        return root;
    }

    @Override
    protected void onUserLogIn() {

    }

    @Override
    protected void onUserLogOut() {

    }
}