package com.idcf.boathouse.presentation.fragment;

import com.idcf.boathouse.mvp.MvpBasePresenter;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class FragmentPresenter extends MvpBasePresenter<FragmentContract.View> implements FragmentContract.Presenter {

    private static final String TAG = "FragmentPresenter";

    @Override
    public void start() {
        super.start();
        /**
         * You can init action here
         *
         * 你可以在这里进行一些初始操作
         */
    }
}
