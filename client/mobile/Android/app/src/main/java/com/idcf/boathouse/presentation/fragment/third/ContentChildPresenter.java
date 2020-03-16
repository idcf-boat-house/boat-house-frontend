package com.idcf.boathouse.presentation.fragment.third;

import android.os.Handler;

import com.idcf.boathouse.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class ContentChildPresenter extends MvpBasePresenter<ContentChildContract.View> implements ContentChildContract.Presenter {

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
    public void getDatas() {
        getView().setRefresh(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isViewAttached()) return;

                List<String> datas = new ArrayList<>();
                for (int i = 0; i < 16; i++) {
                    datas.add("菜名" + i);
                }
                getView().showDatas(datas);
                getView().setRefresh(false);
            }
        }, 1000);
    }
}
