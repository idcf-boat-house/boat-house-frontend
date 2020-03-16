package com.idcf.boathouse.presentation.main;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.idcf.boathouse.AppRouter;
import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpActivity;

import java.io.File;

public class MainActivity extends MvpActivity<MainContract.Presenter> implements MainContract.View, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static String APP_CACAHE_DIRNAME;

    private Toolbar mToolbar;
    private Button mBtnGetData;
    private Button mBtnFragment;
    private WebView mWebView;
    private String webURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        webURI = getResources().getString(R.string.app_uri);
        mWebView = (WebView)findViewById(R.id.webview_index);
        APP_CACAHE_DIRNAME = this.getCacheDir().getAbsolutePath();

        initView();
        initWebView();

        /**
         * Must use
         * 编码规范，必须使用
         */
        getPresenter().start();
    }

    private void initView() {
//        mBtnGetData = findViewById(R.id.btn_get_data);
        mBtnFragment = findViewById(R.id.btn_fragment);
        bindOnClickLister(this, mBtnFragment);
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
    public MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setBtnGetDataEnabled(boolean enabled) {
        mBtnGetData.setEnabled(enabled);
    }

    @Override
    public void showData(String data) {
        showToast(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_get_data:
//                getPresenter().getData();
//                break;
            case R.id.btn_fragment:
                AppRouter.showFragmentActivity(this, "欢迎来到船屋餐厅！\n点餐系统正在开发中...");
                break;
        }
    }

    /**
     * 清理Webview缓存数据库，缓存文件由程序自动生成
     * /data/data/package_name/database/webview.db
     * /data/data/package_name/database/webviewCache.db
     **/
    public void clearWebViewCache() {
        try {
            //因为他们都是文件，所以可以用io方式删除，具体方法可以自己写
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView 缓存文件
        File webviewCacheDir = new File(APP_CACAHE_DIRNAME);
        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            //具体的方法自己写
            //deleteFile(webviewCacheDir);
        }
    }
}
