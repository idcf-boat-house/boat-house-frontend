package com.idcf.boathouse.presentation.fragment.first;

import com.idcf.boathouse.mvp.MvpPresenter;
import com.idcf.boathouse.mvp.MvpView;

/**
 * Created by JaneConan on 2020/3/3.
 */
public interface FirstContract {

    interface View extends MvpView {

        void setBtnGetDataEnabled(boolean enabled);

        void showData(String data);
    }

    interface Presenter extends MvpPresenter<View> {

        void getData();
    }
}
