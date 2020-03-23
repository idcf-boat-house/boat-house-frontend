package com.idcf.boathouse.mvp.model.impl;

import com.idcf.boathouse.base.OnLoadDatasListener;
import com.idcf.boathouse.http.BaseObserver;
import com.idcf.boathouse.http.BaseResponse;
import com.idcf.boathouse.http.RetrofitFactory;
import com.idcf.boathouse.presentation.bean.FoodBean;
import com.idcf.boathouse.presentation.bean.FoodCategoryBean;
import com.idcf.boathouse.base.OnLoadDatasListener;
import com.idcf.boathouse.http.BaseObserver;
import com.idcf.boathouse.http.RetrofitFactory;
import com.idcf.boathouse.mvp.model.IFrProjectModel;

import java.util.List;

/**
 * @author JaneConan
 * 描述：
 * 创建日期：2020/03/22
 *
 */
public class FrProjectModelImpl implements IFrProjectModel {
    @Override
    public void handleFoodCategoryTitle(final OnLoadDatasListener<List<FoodCategoryBean.DataBean>> onLoadDatasListener) {
        RetrofitFactory
                .getInstence()
                .getFoodCategoriesTitle( new BaseObserver<List<FoodCategoryBean.DataBean>>() {
                    protected void onSuccees(List<FoodCategoryBean.DataBean> dataBean) throws Exception {
                        onLoadDatasListener.onSuccess(dataBean);
                    }
                    protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                        onLoadDatasListener.onFailure(error);
                    }
                });
    }

    @Override
    public void handleFoodsListByCategoryId(int categoryId, final OnLoadDatasListener<List<FoodBean.DataBean>> onLoadDatasListener) {
        RetrofitFactory
                .getInstence()
                .getFoodList( categoryId,new BaseObserver<List<FoodBean.DataBean>>() {
                    @Override
                    protected void onSuccees(List<FoodBean.DataBean> dataBean) throws Exception {
                        onLoadDatasListener.onSuccess(dataBean);
                    }

                    @Override
                    protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                        onLoadDatasListener.onFailure(error);
                    }
                });
    }
}
