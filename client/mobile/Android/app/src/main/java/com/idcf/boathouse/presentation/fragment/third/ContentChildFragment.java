package com.idcf.boathouse.presentation.fragment.third;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpFragment;
import com.idcf.boathouse.presentation.fragment.second.SecondAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class ContentChildFragment extends MvpFragment<ContentChildContract.Presenter> implements ContentChildContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "ContentChildFragment";
    public static final String K_CONTENT_CHILD = "k_content_child";

    private String mData;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshView;

    private SecondAdapter mAdapter;

    private List<String> mdatas = new ArrayList<>();

    public static ContentChildFragment newInstance(String billId) {
        Bundle args = new Bundle();
        args.putString(ContentChildFragment.K_CONTENT_CHILD, billId);
        ContentChildFragment fragment = new ContentChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third_content_child;
    }

    private void init() {
        mData = getArguments().getString(K_CONTENT_CHILD);
        Log.d(TAG, "init: " + mData);
        showToast("您选择了: " + mData);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView(view);
        getPresenter().start();
        getPresenter().getDatas();
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mRefreshView = view.findViewById(R.id.refresh);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        mAdapter = new SecondAdapter(getContext(), mdatas);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshView.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getPresenter().getDatas();
    }

    @NonNull
    @Override
    public ContentChildContract.Presenter createPresenter() {
        return new ContentChildPresenter();
    }

    @Override
    public void setRefresh(boolean refresh) {
        mRefreshView.setRefreshing(refresh);
    }

    @Override
    public void showDatas(List<String> datas) {
        mdatas.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }
}
