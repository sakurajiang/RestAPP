package com.example.jdk.restapp.Fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WatchFragment extends Fragment{
    @Bind(R.id.myViewPager)
    ViewPager myViewPager;
    @Bind(R.id.watch_tl)
    TabLayout tabLayout;
    Fragment meiziFragment;
    Fragment androidFragment;
    Fragment frontFragment;
    List<Fragment> fragmentList;
    private List<String> mTitleList = new ArrayList<>(3);
    View v;
    static WatchFragment watchFragment;
    public static WatchFragment getInstance(){
        if( watchFragment ==null){
            watchFragment =new WatchFragment();
        }
        return watchFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v!=null){
            ButterKnife.bind(this,v);
            return v;
        }
        v=inflater.inflate(R.layout.paf_fragment_layout,container,false);
        ButterKnife.bind(this,v);
        InitVariable();
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager());
        myAdapter.notifyDataSetChanged();
        myViewPager.setAdapter(myAdapter);
        myViewPager.setOffscreenPageLimit(2);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(myViewPager);
        return v;
    }

    public void InitVariable(){
        mTitleList.add("Picture");
        mTitleList.add("Android");
        mTitleList.add("Front");
        meiziFragment=MeiziFragment.newInstance(getActivity());
        androidFragment=AndroidFragment.newInstance(getActivity());
        frontFragment=FrontFragment.newInstance(getActivity());
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(meiziFragment);
        fragmentList.add(androidFragment);
        fragmentList.add(frontFragment);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }


}
