package com.zhi.gui.guide.activity;

import com.zhi.gui.guide.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDiscover extends FragmentBase {

    @Override
    protected View createView(LayoutInflater inflater) {
        View root= inflater.inflate(R.layout.fragment_discovery, null);
        ((TextView)root.findViewById(R.id.navigation_bar).findViewById(R.id.title)).setText("发现");
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("leojiang", "onActivityCreated");
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("leojiang", "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("leojiang", "onDetach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("leojiang", "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("leojiang", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("leojiang", "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("leojiang", "onStop");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("leojiang", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("leojiang", "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("leojiang", "onDestroyView");
    }

    @Override
    protected void onUserLogIn() {

    }

    @Override
    protected void onUserLogOut() {

    }
}