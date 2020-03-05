package com.idcf.boathouse.mvp;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by JaneConan on 2020/3/3.
 */
public interface MvpView {

    void showToast(String message);

    void showToast(@StringRes int id);

    Context provideContext();

    void loading();

    void succeed();

    void error();

    void empty();

    void complete();
}
