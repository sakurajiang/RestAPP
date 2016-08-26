package com.jdk.gank.restapp.Activity;

import android.content.Intent;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.ImageView;

import com.jdk.gank.restapp.Adapter.AdapterInditorViewPage;
import com.jdk.gank.restapp.Fragment.AndroidFragment;
import com.jdk.gank.restapp.Fragment.FrontFragment;
import com.jdk.gank.restapp.Fragment.MeiziFragment;
import com.jdk.gank.restapp.Fragment.UserFragment;
import com.jdk.gank.restapp.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    ImageView imageView;
    Fragment meiziFragment;
    Fragment androidFragment;
    Fragment frontFragment;
    Fragment userFragment;
    List<Fragment> fragmentList;
    String [] TITLE;
    ViewPager myViewPager;
    TabPageIndicator myTabPageIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitVariable();
        myTabPageIndicator= (TabPageIndicator) findViewById(R.id.myIndicatorViewPage);
        myViewPager= (ViewPager) findViewById(R.id.myViewPager);
        InitListener();
        myViewPager.setAdapter(new AdapterInditorViewPage(getSupportFragmentManager(),fragmentList,TITLE));
        myViewPager.setOffscreenPageLimit(3);
        myTabPageIndicator.setViewPager(myViewPager);
    }
    public void InitVariable(){
        meiziFragment=MeiziFragment.newInstance(this);
        androidFragment=AndroidFragment.newInstance(this);
        frontFragment=FrontFragment.newInstance(this);
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
       ((UserFragment)userFragment).setCollectionInUserFragmentOnClick(new UserFragment.collectionInUserFragmentOnClick() {
           @Override
           public void collectionOnClick() {
               Intent intent=new Intent();
               intent.setClass(MainActivity.this,UserActivity.class);
               startActivity(intent);
           }
       });
   }

}
