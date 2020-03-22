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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpActivity;
import com.idcf.boathouse.presentation.fragment.FragmentActivity;
import com.idcf.boathouse.presentation.main.MainActivity;

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
    public static String app_token;
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

        mTextUserName = findViewById(R.id.editText_username);
        mTextPassword = findViewById(R.id.editText_password);
        initView();

        getPresenter().start();

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
                break;
            case R.id.btn_register:
                new LoginTask().execute(webURI+ "/signUp", username, password);
                break;
        }
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        private String command;
        private LoginPostMsg loginPostMsg = new LoginPostMsg();
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = getUrl(params);
                return loginPostMsg.postMsg(url);
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

        private void getCommand(String url) {
            if (url.contains("login")){
                command = getResources().getString(R.string.login);
            }
            else if (url.contains("signUp")){
                command = getResources().getString(R.string.register);
            }
        }

        private URL getUrl(String[] params) throws MalformedURLException {
            getCommand(params[0]);
            String data = "username="+String.valueOf(params[1])+"&password="+String.valueOf(params[2]);
            return new URL(String.valueOf(params[0]) + "?"+ data);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            if (result != null && result.contains("\"code\":200,")){
                updateToken(result);
                showToast(command + "成功");
                turnToMainFragment(command);
            }
            else{
                showToast(command + "失败");
            }
        }
    }

    private void updateToken(String result) {
        String token = (new LoginMsgParse()).getToken(result);
        if (token != null && token.length() > 0)
        {
            app_token = token;
        }
    }

    public void turnToMainFragment(String command) {
        if (command.equals(getResources().getString(R.string.login))){
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("token", app_token);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
