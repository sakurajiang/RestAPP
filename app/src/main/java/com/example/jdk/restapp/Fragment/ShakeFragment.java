package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.ShakeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShakeFragment extends Fragment implements ShakeUtils.onMySensorChangedListener{
    @Bind(R.id.myViewPager)
    ViewPager myViewPager;
    @Bind(R.id.watch_tl)
    TabLayout myTabLayout;
    Fragment shakeMeiziFragment;
    Fragment shakeAndroidFragment;
    Fragment shakeFrontFragment;
    List<Fragment> fragmentList=new ArrayList<Fragment>(3);;
    private List<String> mTitleList = new ArrayList<>(3);
    private int page;
    private static Context mContext;
    private ShakeUtils shakeUtils;
    private boolean isCreateView;
    private View v;
    static ShakeFragment shakeFragment;
    int t;
    public static ShakeFragment getInstance(Context context){
        if( shakeFragment==null){
            shakeFragment=new ShakeFragment();
        }
        mContext=context;
        return shakeFragment;
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
        isCreateView=true;
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(getActivity()!=null) {
            if (isVisibleToUser) {
                shakeUtils = new ShakeUtils(getActivity());
                shakeUtils.setOnMySensorChangedListener(this);
                shakeUtils.registerSensor();
            } else {
                if(shakeUtils!=null) {
                    shakeUtils.unRegisterSensor();
                }
            }
    }
            if(isVisibleToUser&&isCreateView){
                InitVariable();
                MyAdapter myAdapter = new MyAdapter(getChildFragmentManager());
                myAdapter.notifyDataSetChanged();
                myViewPager.setOffscreenPageLimit(2);
                myViewPager.setCurrentItem(0);
                myViewPager.setAdapter(myAdapter);
                myTabLayout.setTabMode(TabLayout.MODE_FIXED);
                myTabLayout.setupWithViewPager(myViewPager);
                isCreateView=false;
            }
            super.setUserVisibleHint(isVisibleToUser);

    }
    @Override
    public void onPause() {
        super.onPause();
    }
    public void InitVariable(){
        mTitleList.add("Picture");
        mTitleList.add("Android");
        mTitleList.add("Front");
        shakeMeiziFragment=ShakeMeiziFragment.newInstance(getActivity());
        shakeAndroidFragment=ShakeAndroidFragment.newInstance(getActivity());
        shakeFrontFragment=ShakeFrontFragment.newInstance(getActivity());
        fragmentList.add(shakeMeiziFragment);
        fragmentList.add(shakeAndroidFragment);
        fragmentList.add(shakeFrontFragment);
    }
    @Override
    public void onMySensorChanged() {
        Toast.makeText(mContext,getString(R.string.shake_and_shake),Toast.LENGTH_SHORT).show();
        if(shakeMeiziFragment.getUserVisibleHint()){
            ((ShakeMeiziFragment) shakeMeiziFragment).setProgressSubscriber(page);
        }
        if(shakeAndroidFragment.getUserVisibleHint()){
            ((ShakeAndroidFragment)shakeAndroidFragment).setProgressSubscribler(page);
        }
        if(shakeFrontFragment.getUserVisibleHint()){
            ((ShakeFrontFragment)shakeFrontFragment).setProgressSubscribler(page);
        }
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
