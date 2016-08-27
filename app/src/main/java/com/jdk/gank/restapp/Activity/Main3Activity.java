package com.jdk.gank.restapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdk.gank.restapp.Fragment.AndroidFragment;
import com.jdk.gank.restapp.Fragment.FrontFragment;
import com.jdk.gank.restapp.Fragment.MeiziFragment;
import com.jdk.gank.restapp.Fragment.UserFragment;
import com.jdk.gank.restapp.R;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends Fragment{
//    Fragment meiziFragment;
//    Fragment androidFragment;
//    Fragment frontFragment;
//    Fragment userFragment;
//    List<Fragment> fragmentList;
//    private List<String> mTitleList = new ArrayList<>(3);
//    ViewPager myViewPager3;
//    TabLayout myTabLayout;
//    View v;
//    static Main3Activity main1Activity;
//    public static Main3Activity getInstance(){
//        if( main1Activity==null){
//            main1Activity=new Main3Activity();
//        }
//        return main1Activity;
//    }
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if(v!=null){
//            return v;
//        }
//        v=inflater.inflate(R.layout.activity_main1,container,false);
//        myTabLayout= (TabLayout) v.findViewById(R.id.watch_tab);
//        myViewPager3= (ViewPager) v.findViewById(R.id.myViewPager);
//        InitListener();
//        InitVariable();
//        MyAdapter myAdapter = new MyAdapter(getFragmentManager());
//        myAdapter.notifyDataSetChanged();
//        myViewPager3.setAdapter(myAdapter);
//        myViewPager3.setOffscreenPageLimit(2);
//        myTabLayout.setTabMode(TabLayout.MODE_FIXED);
//        myTabLayout.setupWithViewPager(myViewPager3);
//        return v;
//    }
//
//    public void InitVariable(){
//        mTitleList.add("MEIZI");
//        mTitleList.add("Android");
//        mTitleList.add("Front");
//        meiziFragment=MeiziFragment.newInstance(getActivity());
//        androidFragment=AndroidFragment.newInstance(getActivity());
//        frontFragment=FrontFragment.newInstance(getActivity());
//        userFragment=new UserFragment();
//        fragmentList=new ArrayList<Fragment>();
//        fragmentList.add(meiziFragment);
//        fragmentList.add(androidFragment);
//        fragmentList.add(frontFragment);
//    }
//   public void InitListener(){
//
//
//   }
//    class MyAdapter extends FragmentPagerAdapter {
//
//        public MyAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitleList.get(position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//        }
//    }

}
