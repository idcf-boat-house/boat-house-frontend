package com.idcf.boathouse.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idcf.boathouse.util.ToastUtil;

/**
 * Created by JaneConan on 2020/3/3.
 * Providing functionality for all fragment
 *
 * 为所有的fragment封装功能
 */
public abstract class BaseFragment extends BaseSuperFragment {

    private static final String TAG = "BaseFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    public abstract int getLayoutId();

    public void showToast(String msg) {
        ToastUtil.showToast(msg);
    }

    public void showToast(int id) {
        ToastUtil.showToast(id);
    }

    public void bindOnClickLister(View rootView, View.OnClickListener listener, @IdRes int... ids) {
        for (int id : ids) {
            View view = rootView.findViewById(id);
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
