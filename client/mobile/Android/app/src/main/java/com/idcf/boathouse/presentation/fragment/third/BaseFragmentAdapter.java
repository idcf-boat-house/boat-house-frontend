package com.idcf.boathouse.presentation.fragment.third;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {

    private SupportFragment[] FragmentPages;
    private String[] PageTitles;
    private FragmentManager fragmentManager;

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
    }

    @Override
    public SupportFragment getItem(int position) {
        return FragmentPages[position];
    }

    @Override
    public int getCount() {
        return FragmentPages != null ? FragmentPages.length : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragmentPages(List<SupportFragment> FragmentPages) {
        setFragmentPages((SupportFragment[]) FragmentPages.toArray());
    }

    public void setFragmentPages(SupportFragment[] FragmentPages) {
        if (this.FragmentPages != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            for (SupportFragment f : this.FragmentPages) {
                ft.remove(f);
            }
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        this.FragmentPages = FragmentPages;
        notifyDataSetChanged();
    }

    public void setPageTitles(ArrayList<String> PageTitles) {
        setPageTitles((String[]) PageTitles.toArray());
    }

    public void setPageTitles(String[] PageTitles) {
        this.PageTitles = PageTitles;

    }

    public String[] getPageTitles() {
        return PageTitles;
    }

    public SupportFragment[] getFragmentPages() {
        return FragmentPages;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (PageTitles != null && position < PageTitles.length) {
            return PageTitles[position];
        }
        return super.getPageTitle(position);
    }
}
