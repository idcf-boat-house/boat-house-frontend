package com.idcf.boathouse.base;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by JaneConan on 2020/3/3.
 * Providing functionality for all activity
 * <p>
 * 为所有的activity封装功能
 */
public class BaseActivity extends BaseSuperActivity {

    private static final String TAG = "BaseActivity";

    public void bindOnClickLister(View rootView, View.OnClickListener listener, @IdRes int... ids) {
        for (int id : ids) {
            View view = rootView.findViewById(id);
            if (view != null) {
                view.setOnClickListener(listener);
            }
        }
    }

    public void bindOnClickLister(View.OnClickListener listener, @IdRes int... ids) {
        for (int id : ids) {
            View view = findViewById(id);
            if (view != null) {
                view.setOnClickListener(listener);
            }
        }
    }

    public void bindOnClickLister(View.OnClickListener listener, View... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }
}
