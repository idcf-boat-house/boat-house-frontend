package com.idcf.boathouse.presentation.fragment.third;

import android.os.Handler;

import com.idcf.boathouse.mvp.MvpBasePresenter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class ThirdPresenter extends MvpBasePresenter<ThirdContract.View> implements ThirdContract.Presenter {

    private static final String TAG = "SecondPresenter";

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
    public void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isViewAttached()) return;

                // mData.size()
                int valueCount = 6;

                SupportFragment[] fragments = null;
                String[] titles = null;
                fragments = new SupportFragment[valueCount];
                titles = new String[valueCount];
                for (int i = 0; i < valueCount; i++) {
                    String title = "菜品" + i;
                    fragments[i] = ContentFragment.newInstance(title);
                    titles[i] = title;
                }

                getView().setTabContent(fragments, titles);
            }
        }, 1000);
    }
}
