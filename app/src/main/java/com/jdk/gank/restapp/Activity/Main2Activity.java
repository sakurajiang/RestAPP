package com.jdk.gank.restapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdk.gank.restapp.Adapter.AdapterInditorViewPage;
import com.jdk.gank.restapp.Fragment.AndroidFragment;
import com.jdk.gank.restapp.Fragment.FrontFragment;
import com.jdk.gank.restapp.Fragment.MeiziFragment;
import com.jdk.gank.restapp.Fragment.UserFragment;
import com.jdk.gank.restapp.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Fragment{
    Fragment meiziFragment;
    Fragment androidFragment;
    Fragment frontFragment;
    Fragment userFragment;
    List<Fragment> fragmentList;
    String [] TITLE;
    ViewPager myViewPager;
    TabPageIndicator myTabPageIndicator;
    View v;
    static Main2Activity main1Activity;
    public static Main2Activity getInstance(){
        if( main1Activity==null){
            main1Activity=new Main2Activity();
        }
        return main1Activity;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v!=null){
            return v;
        }
        v=inflater.inflate(R.layout.activity_main1,container,false);
        myTabPageIndicator= (TabPageIndicator) v.findViewById(R.id.myIndicatorViewPage);
        myViewPager= (ViewPager) v.findViewById(R.id.myViewPager);
        InitListener();
        InitVariable();
        myViewPager.setAdapter(new AdapterInditorViewPage(getFragmentManager(), fragmentList, TITLE));
        myViewPager.setOffscreenPageLimit(3);
        myTabPageIndicator.setViewPager(myViewPager);
        return v;
    }

    public void InitVariable(){
        meiziFragment=MeiziFragment.newInstance(getActivity());
        androidFragment=AndroidFragment.newInstance(getActivity());
        frontFragment=FrontFragment.newInstance(getActivity());
        userFragment=new UserFragment();
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(meiziFragment);
        fragmentList.add(androidFragment);
        fragmentList.add(frontFragment);
        fragmentList.add(userFragment);
        TITLE=new String[]{"MEIZI","Android","Front","User"};
    }
   public void InitListener(){
                 myTabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

               }

               @Override
               public void onPageSelected(int position) {
               }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
//       ((UserFragment)userFragment).setCollectionInUserFragmentOnClick(new UserFragment.collectionInUserFragmentOnClick() {
//           @Override
//           public void collectionOnClick() {
//               Intent intent=new Intent();
//               intent.setClass(Main1Activity.this,UserActivity.class);
//               startActivity(intent);
//           }
//       });
   }

}
