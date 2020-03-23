package com.idcf.boathouse.presentation.fragment.third;

import android.os.Handler;

import com.idcf.boathouse.base.OnLoadDatasListener;
import com.idcf.boathouse.mvp.MvpBasePresenter;
import com.idcf.boathouse.mvp.model.IFrProjectModel;
import com.idcf.boathouse.mvp.model.impl.FrProjectModelImpl;
import com.idcf.boathouse.presentation.bean.FoodBean;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class ContentChildPresenter extends MvpBasePresenter<ContentChildContract.View> implements ContentChildContract.Presenter {


    private IFrProjectModel iFrProjectModel;

    public ContentChildPresenter() {
        this.iFrProjectModel = new FrProjectModelImpl();
    }
    /***
     * 获取菜品分类列表
     */
    public  void  getFoodsListByCategoryId(int categoryId){
        if (!isViewAttached()) return;
        iFrProjectModel.handleFoodsListByCategoryId(categoryId, new OnLoadDatasListener<List<FoodBean.DataBean>>() {
            @Override
            public void onSuccess(List<FoodBean.DataBean> dataBean) {
                if (!isViewAttached()) return;

                int valueCount = dataBean.size();
                List<String> datas = new ArrayList<>();
                for (int i = 0; i <= valueCount; i++) {
                    datas.add("菜品" + i);
                }
                getView().showDatas(datas);
                getView().setRefresh(false);
            }

            @Override
            public void onFailure(String error) {
                getView().showData("获取菜单数据失败！\n原因:" + error + "\n故切换至假数据");
                if (!isViewAttached()) return;

                int valueCount = 5;
                List<String> datas = new ArrayList<>();
                for (int i = 0; i <= valueCount; i++) {
                    datas.add("菜品" + i);
                }
                getView().showDatas(datas);
                getView().setRefresh(false);
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
    public void getDatas() {
        getView().setRefresh(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFoodsListByCategoryId(1);
            }
        }, 1000);
    }
}
