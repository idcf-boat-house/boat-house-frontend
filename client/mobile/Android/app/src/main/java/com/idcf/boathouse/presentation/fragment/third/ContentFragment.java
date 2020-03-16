package com.idcf.boathouse.presentation.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.idcf.boathouse.R;
import com.idcf.boathouse.base.BaseFragment;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class ContentFragment extends BaseFragment {

    private static final String TAG = "ContentFragment";
    public static final String K_CONTENT = "k_content";

    private String mData;

    public static ContentFragment newInstance(String billId) {
        Bundle args = new Bundle();
        args.putString(K_CONTENT, billId);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third_content;
    }

    private void init() {
        mData = getArguments().getString(K_CONTENT);
        Log.d(TAG, "init: " + mData);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    /**
     * Lazy initial，Called when fragment is first called.
     *
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(ContentChildFragment.class) == null) {
            loadRootFragment(R.id.fl_container, ContentChildFragment.newInstance(mData));
        }
    }
}
