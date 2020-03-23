package com.idcf.boathouse.mvp.model;

import com.idcf.boathouse.base.OnLoadDatasListener;
import com.idcf.boathouse.presentation.bean.FoodBean;
import com.idcf.boathouse.presentation.bean.FoodCategoryBean;

import java.util.List;

/**
 * @author JaneConan
 * 描述：
 * 创建日期：2020/03/22
 *
 */
public interface IFrProjectModel {

    void handleFoodCategoryTitle(OnLoadDatasListener<List<FoodCategoryBean.DataBean>> onLoadDatasListener);

    void handleFoodsListByCategoryId(int categoryId, OnLoadDatasListener<List<FoodBean.DataBean>> onLoadDatasListener);

}
