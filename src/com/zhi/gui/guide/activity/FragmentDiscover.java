package com.zhi.gui.guide.activity;

import com.zhi.gui.guide.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDiscover extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.i("leojiang", "onCreateView");
        return inflater.inflate(R.layout.fragment_discovery, null);
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
}