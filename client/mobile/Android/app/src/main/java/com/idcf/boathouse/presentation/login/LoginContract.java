package com.idcf.boathouse.presentation.login;

import com.idcf.boathouse.mvp.MvpPresenter;
import com.idcf.boathouse.mvp.MvpView;

/**
 * Created by xiaweicai on 2020/3/15.
 */
public interface LoginContract {

    interface View extends MvpView {

    }

    interface Presenter extends MvpPresenter<View> {

        String login(String webURI, String username, String password);

        String register(String webURI, String username, String password);
    }
}
