package com.idcf.boathouse.presentation.login;

import com.idcf.boathouse.mvp.MvpBasePresenter;

/**
 * Created by xiaweicai on 2020/3/15.
 */
public class LoginPresenter extends MvpBasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    @Override
    public void start() {
        super.start();
        /**
         * You can init action here
         *
         * 你可以在这里进行一些初始操作
         */
    }

    @Override
    public void login() {

    }
}
