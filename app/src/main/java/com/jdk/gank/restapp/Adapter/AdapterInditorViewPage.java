package com.jdk.gank.restapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JDK on 2016/8/4.
 */
public class AdapterInditorViewPage extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    String [] TITLEARRAY;
    public AdapterInditorViewPage(FragmentManager fm,List<Fragment> fragmentList,String[] TITLEARRAY) {
        super(fm);
        this.fragmentList=fragmentList;
        this.TITLEARRAY=TITLEARRAY;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return TITLEARRAY.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLEARRAY[position % TITLEARRAY.length];
    }
}
