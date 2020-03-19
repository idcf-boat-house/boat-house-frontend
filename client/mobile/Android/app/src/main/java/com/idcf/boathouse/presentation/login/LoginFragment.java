package com.idcf.boathouse.presentation.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpFragment;

/**
 * Created by xiaweicai on 2020/3/3.
 */
public class LoginFragment extends MvpFragment<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    private Toolbar mToolbar;
    private Button mBtnLogin;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int getLayoutId() {
        return 0;//R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Must use
         * 编码规范，必须使用
         */
        getPresenter().start();

        initView(view);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        // TODO: onSupportVisible
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        // TODO: onSupportInvisible
    }

    private void initView(View view) {
    //    mToolbar = view.findViewById(R.id.toolbar);
    //    mToolbar.setTitle(R.string.fragment_morning);
    //    mBtnGetData = view.findViewById(R.id.btn_get_data);
    //    bindOnClickLister(this, mBtnGetData);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
          //      getPresenter().login(username, password);
                break;
        }
    }

}
