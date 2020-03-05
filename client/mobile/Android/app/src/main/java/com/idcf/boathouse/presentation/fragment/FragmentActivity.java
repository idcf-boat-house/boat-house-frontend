package com.idcf.boathouse.presentation.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.idcf.boathouse.R;
import com.idcf.boathouse.mvp.MvpActivity;
import com.idcf.boathouse.presentation.fragment.first.FirstFragment;
import com.idcf.boathouse.presentation.fragment.second.SecondFragment;
import com.idcf.boathouse.presentation.fragment.third.ThirdFragment;

import me.yokeyword.fragmentation.SupportFragment;

public class FragmentActivity extends MvpActivity<FragmentContract.Presenter> implements FragmentContract.View, BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "FragmentActivity";
    private static final String K_EXTRA_FRAGMENT = "extra_fragment";

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private BottomNavigationBar mBottomNavigationBar;

    private String mData;

    /**
     * Current fragment
     *
     * 当前的fragment
     */
    private int mCurrentFrm = FIRST;

    public static Intent newIntent(Context context, String data) {
        Intent intent = new Intent(context, FragmentActivity.class);
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
        setContentView(R.layout.activity_fragment);
        getPresenter().start();

        initView();

        /**
         * Fragment init
         *
         * Fragment初始化
         */
        SupportFragment firstFragment = findFragment(FirstFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = FirstFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            mFragments[THIRD] = ThirdFragment.newInstance();

            loadMultipleRootFragment(R.id.framen_root, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(SecondFragment.class);
            mFragments[THIRD] = findFragment(ThirdFragment.class);
        }
    }

    private void initView() {
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_brightness_morning_black_24dp, R.string.fragment_morning))
                .addItem(new BottomNavigationItem(R.drawable.ic_brightness_nooning_black_24dp, R.string.fragment_nooning))
                .addItem(new BottomNavigationItem(R.drawable.ic_brightness_evening_black_24dp, R.string.fragment_evening))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    @NonNull
    @Override
    public FragmentContract.Presenter createPresenter() {
        return new FragmentPresenter();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case FIRST:
                showHideFragment(mFragments[FIRST], mFragments[mCurrentFrm]);
                mCurrentFrm = FIRST;
                break;
            case SECOND:
                showHideFragment(mFragments[SECOND], mFragments[mCurrentFrm]);
                mCurrentFrm = SECOND;
                break;
            case THIRD:
                showHideFragment(mFragments[THIRD], mFragments[mCurrentFrm]);
                mCurrentFrm = THIRD;
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
