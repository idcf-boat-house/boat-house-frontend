package com.idcf.boathouse.presentation.fragment;

import com.idcf.boathouse.mvp.MvpPresenter;
import com.idcf.boathouse.mvp.MvpView;

/**
 * Created by JaneConan on 2020/3/3.
 */
public interface FragmentContract {

    interface View extends MvpView {

    }

    interface Presenter extends MvpPresenter<View> {

    }
}
