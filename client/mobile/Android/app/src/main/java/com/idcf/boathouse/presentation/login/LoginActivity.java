package com.idcf.boathouse.presentation.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

        // 启用WebView访问文件数据
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowContentAccess(true);
        // 设置支持JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 设置渲染效果优先级，高
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // also check this line of code for appCachePath
        String cacheDirPath = APP_CACAHE_DIRNAME;
        // 设置数据库缓存路径
        mWebView.getSettings().setDatabasePath(cacheDirPath);
        // 设置 应用 缓存目录
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        // 开启 DOM 存储功能
        mWebView.getSettings().setDomStorageEnabled(true);
        // 开启 数据库 存储功能
        mWebView.getSettings().setDatabaseEnabled(true);
        // 开启 应用缓存 功能
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.loadUrl(webURI);
    }

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void onClick(View v) {
        String username = (String) mTextUserName.getText();
        String password = (String) mTextPassword.getText();
        switch (v.getId()) {
            case R.id.btn_login:
                getPresenter().login(webURI, username, password);
                break;
            case R.id.btn_register:
                getPresenter().register(webURI, username, password);
                break;
        }
    }
}
