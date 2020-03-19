package com.idcf.boathouse.presentation.login;

import com.idcf.boathouse.mvp.MvpBasePresenter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by xiaweicai on 2020/3/15.
 */
public class LoginPresenter extends MvpBasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

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
    public String login(String webURI, String username, String password) {
        String loginUrl = webURI + "/login";
        return post(loginUrl, username, password);
    }

    public String post(String webUri, String username, String password){
        try {
            URL url = new URL(webUri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");

            //数据准备
            String data = "username="+username+"&password="+password;
            //至少要设置的两个请求头
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", data.length()+"");

            //post的方式提交实际上是留的方式提交给服务器
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());

            //获得结果码
            int responseCode = connection.getResponseCode();
            if(responseCode ==200){
                //请求成功
                InputStream is = connection.getInputStream();
                return is.toString();//IOSUtil.inputStream2String(is);
            }else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String register(String webURI, String username, String password) {
        String loginUrl = webURI + "/signUp";
        return post(loginUrl, username, password);
    }
}
