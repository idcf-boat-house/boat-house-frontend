package com.idcf.boathouse.http;



import com.idcf.boathouse.R;
import com.idcf.boathouse.bean.CurrencyBean;
import com.idcf.boathouse.presentation.bean.FoodBean;
import com.idcf.boathouse.presentation.bean.FoodCategoryBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author JaneConan
 * 描述：  接口
 * 创建日期：2020/03/21
 *
 */
public interface ApiService {


    String BASE_URL = "http://d-docker.southeastasia.cloudapp.azure.com:7001";


    //网络请求时长
    int HTTP_TIME =0;

    /***
     * 获取菜品分类列表
     * @return
     */
    @GET("/api/v1.0/BoatHouse/FoodCategories")
    Observable<BaseResponse<List<FoodCategoryBean.DataBean>>> getFoodCategories();


    /***
     * 根据菜品分类id获取菜品列表
     *
     * ?categoryId={categoryId}
     * 形如?t=1&p=2&size=3的url链接不能用@PATH注解
     * https://www.cnblogs.com/krislight1105/p/5452202.html
     * 参考：
     * http://stackoverflow.com/questions/24610243/retrofit-error-url-query-string-must-not-have-replace-block
     * @param categoryId
     * @return
     */
    @GET("/api/v1.0/BoatHouse/Foods")
    Observable<BaseResponse<FoodBean.DataBean>> getFoodsListByCategoryId(@Query("categoryId") int categoryId);


}
