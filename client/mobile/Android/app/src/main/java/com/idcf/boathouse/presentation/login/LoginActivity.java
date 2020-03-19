package com.idcf.boathouse.presentation.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;


import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import me.yokeyword.fragmentation.SupportFragment;

public class LoginActivity extends MvpActivity<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private static final String K_EXTRA_FRAGMENT = "extra_fragment";
    private static String APP_CACAHE_DIRNAME;

    private SupportFragment mFragment = new SupportFragment();
    private Toolbar mToolbar;
    private String mData;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private WebView mWebView;
    private String webURI;
    private TextView mTextUserName;
    private TextView mTextPassword;

    public static Intent newIntent(Context context, String data) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(K_EXTRA_FRAGMENT, data);
        return intent;
    }

    private void initExtra() {
        mData = getIntent().getStringExtra(K_EXTRA_FRAGMENT);
        Log.d(TAG, "initExtra: " + mData);
        showToast(mData);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initExtra();
        setContentView(R.layout.activity_login);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        webURI = getResources().getString(R.string.app_login_uri);
        mWebView = (WebView)findViewById(R.id.webview_index);
        APP_CACAHE_DIRNAME = this.getCacheDir().getAbsolutePath();
        mTextUserName = findViewById(R.id.editText_username);
        mTextPassword = findViewById(R.id.editText_password);
        initView();
//        initWebView();
        getPresenter().start();
//        mFragment = LoginFragment.newInstance();
        /**
         * login init
         *
         * Fragment初始化
         */

    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.login);
        mBtnLogin = findViewById(R.id.btn_login);
        bindOnClickLister(this, mBtnLogin);
        mBtnRegister = findViewById(R.id.btn_register);
        bindOnClickLister(this, mBtnRegister);
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void onClick(View v) {
        String username = mTextUserName.getText().toString();
        String password = mTextPassword.getText().toString();
        switch (v.getId()) {
            case R.id.btn_login:
                new LoginTask().execute(webURI + "/login", username, password);
                //getPresenter().login(webURI, username, password);
                break;
            case R.id.btn_register:
                new LoginTask().execute(webURI+ "/signUp", username, password);
                break;
        }
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d("username=", String.valueOf(params[1]));
            Log.d("password=", String.valueOf(params[2]));
            try {
                String data = "username="+String.valueOf(params[1])+"&password="+String.valueOf(params[2]);
                URL url = new URL(String.valueOf(params[0]) + "?"+ data);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("POST");

                //数据准备

                //至少要设置的两个请求头
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", "0");

                //post的方式提交实际上是留的方式提交给服务器
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
//                outputStream.write(data.getBytes());

                //获得结果码
                int responseCode = connection.getResponseCode();
                if(responseCode ==200){
                    //请求成功
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String msg = br.readLine();
                    Log.d("result=",msg);
                    return msg;//IOSUtil.inputStream2String(is);
                }else {
                    //请求失败
                    return String.valueOf(responseCode);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                Log.e("login error:", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            //updateLoginStatus();
            Log.d("return:", result);
            if (result.contains("success")){
                showToast("请求成功");
            }
            else{
                showToast("请求失败");
            }
        }
    }

}
