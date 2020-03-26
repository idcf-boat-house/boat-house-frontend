package com.idcf.boathouse.presentation.login;

import android.util.Log;

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
        return loginUrl;//String.valueOf(new LoginTask().execute(loginUrl, username, password));
    }

    @Override
    public String register(String webURI, String username, String password) {
        String loginUrl = webURI + "/signUp";
        return loginUrl;//String.valueOf(new LoginTask().execute(loginUrl, username, password));
    }
}
