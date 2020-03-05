package com.idcf.boathouse.presentation.fragment.third;

import android.support.annotation.NonNull;

import com.idcf.boathouse.mvp.MvpPresenter;
import com.idcf.boathouse.mvp.MvpView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by JaneConan on 2020/3/3.
 */
public interface ThirdContract {

    interface View extends MvpView {

        void setTabContent(@NonNull SupportFragment[] fragments, @NonNull String[] titles);
    }

    interface Presenter extends MvpPresenter<View> {

        void loadData();
    }
}
