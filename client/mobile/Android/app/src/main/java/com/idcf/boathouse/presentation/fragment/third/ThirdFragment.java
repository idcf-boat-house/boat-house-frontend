package com.idcf.boathouse.presentation.fragment.third;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class ThirdFragment extends MvpFragment<ThirdContract.Presenter> implements ThirdContract.View {

    private Toolbar mToolbar;
    private TabLayout mTab;
    private ViewPager mPager;

    public static ThirdFragment newInstance() {
        Bundle args = new Bundle();
        ThirdFragment fragment = new ThirdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public ThirdContract.Presenter createPresenter() {
        return new ThirdPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Must use
        getPresenter().start();

        initView(view);

        getPresenter().loadData();
    }

    private void initView(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.fragment_evening);

        mTab = view.findViewById(R.id.tab);
        mPager = view.findViewById(R.id.pager);
    }

    private void setAdapter(final PagerAdapter adapter) {
        mPager.setOffscreenPageLimit(adapter.getCount() - 1);
        mPager.setAdapter(adapter);
        mTab.setupWithViewPager(mPager);
    }

    @Override
    public void setTabContent(@NonNull SupportFragment[] fragments, @NonNull String[] titles) {
        if (mPager.getAdapter() == null) {
            BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager());
            adapter.setFragmentPages(fragments);
            adapter.setPageTitles(titles);
            setAdapter(adapter);
        } else {
            BaseFragmentAdapter adapter = (BaseFragmentAdapter) mPager.getAdapter();
            adapter.setFragmentPages(fragments);
            adapter.setPageTitles(titles);
            mPager.setOffscreenPageLimit(adapter.getCount() - 1);
            adapter.notifyDataSetChanged();
        }
    }
}
