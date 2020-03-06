package com.idcf.boathouse.presentation.fragment.third;

import com.idcf.boathouse.mvp.MvpPresenter;
import com.idcf.boathouse.mvp.MvpView;

import java.util.List;

/**
 * Created by JaneConan on 2020/3/3.
 */
public interface ContentChildContract {

    interface View extends MvpView {

        void setRefresh(boolean refresh);

        void showDatas(List<String> datas);
    }

    interface Presenter extends MvpPresenter<View> {

        void getDatas();
    }
}
