package com.idcf.boathouse.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by JaneConan on 2020/3/3.
 * Manage all Fragment lifecycle, such as statistics and recovery.
 *
 * 管理所有Fragment生命周期，如：统计、恢复
 */
public class BaseSuperFragment extends SupportFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Statsu recovery
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Status save
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }
}
