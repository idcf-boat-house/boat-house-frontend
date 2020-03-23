package com.idcf.boathouse.presentation.fragment.third;

import android.os.Bundle;
import android.os.Handler;

import com.idcf.boathouse.base.OnLoadDatasListener;
import com.idcf.boathouse.http.ApiService;
import com.idcf.boathouse.http.BaseObserver;
import com.idcf.boathouse.http.BaseResponse;
import com.idcf.boathouse.http.RetrofitFactory;
import com.idcf.boathouse.mvp.MvpBasePresenter;
import com.idcf.boathouse.mvp.model.IFrProjectModel;
import com.idcf.boathouse.mvp.model.impl.FrProjectModelImpl;
import com.idcf.boathouse.presentation.bean.FoodCategoryBean;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class ThirdPresenter extends MvpBasePresenter<ThirdContract.View> implements ThirdContract.Presenter {

    private static final String TAG = "SecondPresenter";

    private List<FoodCategoryBean.DataBean> foodCategoryList=new ArrayList<>();

    private IFrProjectModel iFrProjectModel;

    public ThirdPresenter() {
        this.iFrProjectModel = new FrProjectModelImpl();
    }

    /***
     * 获取菜品分类列表
     */
    public  void  getFoodCategoryTitle(){
        if (!isViewAttached()) return;
        iFrProjectModel.handleFoodCategoryTitle(new OnLoadDatasListener<List<FoodCategoryBean.DataBean>>() {
            @Override
            public void onSuccess(List<FoodCategoryBean.DataBean> dataBean) {
                if (!isViewAttached()) return;

                // mData.size()
                int valueCount = dataBean.size();

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

            @Override
            public void onFailure(String error) {
                getView().showData("获取菜品种类失败！\n原因:" + error + "\n故切换至假数据");

                int valueCount = 3;

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
        });
    }


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
                getFoodCategoryTitle();
            }
        }, 1000);
    }
}
