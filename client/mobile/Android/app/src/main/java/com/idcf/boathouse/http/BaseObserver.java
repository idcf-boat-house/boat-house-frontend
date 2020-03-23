package com.idcf.boathouse.http;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private static final String tag = BaseObserver.class.getSimpleName();


    private Context mContext;


    //对应HTTP的状态码
    private static final int UNAU = 402;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    //出错提示
    private final String networkMsg = "网络错误";
    private final String cookieOutMsg = "登录过期，请重新登录";
    private final String parseMsg = "服务器数据解析错误";
    private final String unknownMsg = "未知错误";
    private final String connectMsg = "连接服务器错误,请检查网络";
    private final String connectOutMsg = "连接服务器超时,请检查网络";



    protected BaseObserver(Context context) {
        this.mContext = context;

    }
    protected BaseObserver(){    }


    @Override
    public void onNext(BaseResponse<T> tBaseEntity) {


        if (tBaseEntity.isSuccess()) {
            try {
                onSuccees(tBaseEntity.data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
//                LogUtils.d("3333333333333333333    "+tBaseEntity.getCode()+"   "+tBaseEntity.getMsg());
//                if (tBaseEntity.getCode()==-1001){
//                    LogUtils.d("777777777777777777777777777");
//                    startLogin();
//
//                }
                onCodeError(tBaseEntity.getCode());
                onError(new Throwable(tBaseEntity.getMsg()));
            } catch (Exception e) {
                e.printStackTrace();
                //LogUtils.d("4444444444444444444444");
            }
        }
    }

    /**
     * 返回成功
     *
     * @param t
     */
    protected abstract void onSuccees(T t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param error
     */
    protected void onCodeError(int error) { }

    @Override
    public void onComplete() { }


    @Override
    public void onError(Throwable e) {

        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        String error = null;
        if (e instanceof ConnectException) {
            Log.d(tag, "ConnectException");
            error = connectMsg;
        } else if (e instanceof HttpException) {             //HTTP错误
            Log.d(tag, "HttpException");
            HttpException httpException = (HttpException) e;
            error=e.getLocalizedMessage()+"";

            //      switch (httpException.code()) {
//                case UNAU:
//                    return;
//                case UNAUTHORIZED:
//                case FORBIDDEN:
//                    //                    onPermissionError(ex);          //权限错误，需要实现
//                    //                    break;
//                case NOT_FOUND:
//                case REQUEST_TIMEOUT:
//                case GATEWAY_TIMEOUT:
//                case INTERNAL_SERVER_ERROR:
//                case BAD_GATEWAY:
//                case SERVICE_UNAVAILABLE:
//                default:
//                    error = networkMsg + "错误代码:" + httpException.code();
//                    break;
            //       }
        } else if (e instanceof ApiException) {    //服务器返回的错误
            ApiException apiException = (ApiException) e;
            switch (apiException.getErrorCode()) {


                case "10007":
                    error = parseMsg;
                    break;
                case "10008":
                    error = cookieOutMsg;
                    //       IntentUtil.showLoginActivity(AppActivityManager.getInstance().getTopActivity());
                    break;
                case "11111":
                    error = cookieOutMsg;
                    //       IntentUtil.showLoginActivity(AppActivityManager.getInstance().getTopActivity());
                    break;
                default:
                    error = e.getLocalizedMessage();
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            Log.d(tag, "JsonParseException JSONException");
            error = parseMsg;
        } else if (e instanceof IOException) {
            Log.d(tag, "IOException");
            if (e instanceof SocketTimeoutException) {
                Log.d(tag, "SocketTimeoutException");
                error = connectOutMsg;
            } else {
                if ("Canceled".equals(e.getMessage()) || "Socket closed".equals(e.getMessage()))
                    return;
                else
                    error = connectMsg;
            }
        } else {
            error = e.getLocalizedMessage();
        }

        try {
            //CommonUtil.showToast(error);
            onFailure(error,false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }




    /**
     * 返回失败
     *
     * @param error
     * @param isNetWorkError 是否是网络错误
     */
    protected abstract void onFailure(String error, boolean isNetWorkError) throws Exception;
    public  static  void startLogin(){
        //Intent intent=new Intent(MyApplication.getContext(), LoginActivity.class);
        //MyApplication.getContext().startActivity(intent);
    }

    @Override
    public void onSubscribe(Disposable d) { }
}
