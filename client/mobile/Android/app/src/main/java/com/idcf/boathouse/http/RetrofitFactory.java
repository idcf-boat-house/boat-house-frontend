package com.idcf.boathouse.http;


import com.idcf.boathouse.bean.CurrencyBean;
import com.idcf.boathouse.presentation.bean.FoodBean;
import com.idcf.boathouse.presentation.bean.FoodCategoryBean;


import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private final ApiService mApiService;

    private static RetrofitFactory mRetrofitFactory;

    private RetrofitFactory() {
        //创建日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设定日志级别
        //创建OkHttpClient
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)//添加拦截器
                .build();

        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava2转换器
                .client(mOkHttpClient)
                .build();

        //创建接口实现类
        mApiService = retrofit.create(ApiService.class);

    }




    public static RetrofitFactory getInstence() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null)
                    mRetrofitFactory = new RetrofitFactory();
            }
        }
        return mRetrofitFactory;
    }

    public ApiService API() {
        return mApiService;
    }

    public ObservableTransformer threadTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    public void getFoodCategoriesTitle( BaseObserver<List<FoodCategoryBean.DataBean>> scheduler) {
        API()
                .getFoodCategories()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }



    public void getFoodList(int categoryId, BaseObserver<List<FoodBean.DataBean>> scheduler) {
        API()
                .getFoodsListByCategoryId(categoryId)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

}
