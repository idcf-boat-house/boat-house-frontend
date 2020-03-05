package com.idcf.boathouse;

import android.content.Context;

import com.idcf.boathouse.presentation.fragment.FragmentActivity;


/**
 * Created by JaneConan on 2020/3/3.
 * App Router
 * APP路由，Activity跳转等操作在这包装好方法
 */
public class AppRouter {

    public static void showFragmentActivity(Context context) {
        showFragmentActivity(context, null);
    }

    public static void showFragmentActivity(Context context, String data) {
        context.startActivity(FragmentActivity.newIntent(context, data));
    }
}
