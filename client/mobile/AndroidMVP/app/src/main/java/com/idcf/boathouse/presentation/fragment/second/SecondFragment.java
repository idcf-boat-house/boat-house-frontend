package com.idcf.boathouse.presentation.fragment.second;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class SecondFragment extends MvpFragment<SecondContract.Presenter> implements SecondContract.View, SwipeRefreshLayout.OnRefreshListener {

    private Toolbar mToolbar;
    private SwipeRefreshLayout mRefreshView;
    private RecyclerView mRecyclerView;

    private SecondAdapter mAdapter;

    private List<String> mdatas = new ArrayList<>();

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public SecondContract.Presenter createPresenter() {
        return new SecondPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Must use
         * 编码规范，必须使用
         */
        getPresenter().start();

        initView(view);

        /**
         * If you want load data on init
         *
         * 如果你想在初始化的时候加载数据
         */
        // getPresenter().getDatas();
    }

    private void initView(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.fragment_nooning);

        mRefreshView = view.findViewById(R.id.refresh);
        mRecyclerView = view.findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        mAdapter = new SecondAdapter(getContext(), mdatas);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshView.setOnRefreshListener(this);
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

    @Override
    public void onRefresh() {
        getPresenter().getDatas();
    }
}
