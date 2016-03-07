package com.zhi.gui.guide.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.common.Constants;

public class FragmentPersonal extends FragmentBase implements View.OnClickListener {
    private View mViewUserInfo;
    private View mLonginPrompt;
    private View mEmptyText;
    private View mItemsVIew;
    private View mLoginBtn;


    @Override
    protected View createView(LayoutInflater inflater) {
        View root = inflater.inflate(R.layout.fragment_personal, null);
        mViewUserInfo = root.findViewById(R.id.user_info);
        mLonginPrompt = root.findViewById(R.id.login_prompt);
        mEmptyText = root.findViewById(R.id.empty_text);
        mItemsVIew = root.findViewById(R.id.items);
        mLoginBtn = root.findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(this);
        showView();
        return root;
    }

    private void showView() {
        if (isLoggedIn) {
            mViewUserInfo.setVisibility(View.VISIBLE);
            mLonginPrompt.setVisibility(View.GONE);
            mEmptyText.setVisibility(View.GONE);
            mItemsVIew.setVisibility(View.VISIBLE);
        } else {
            mViewUserInfo.setVisibility(View.GONE);
            mLonginPrompt.setVisibility(View.VISIBLE);
            mEmptyText.setVisibility(View.VISIBLE);
            mItemsVIew.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onUserLogOut() {
        showView();
    }

    @Override
    protected void onUserLogIn() {
        showView();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login_btn:
                Intent intent = new Intent(getActivity(), ActivityLogin.class);
                intent.putExtra(Constants.KEY_IS_LOGIN_FROM_MAIN, true);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}