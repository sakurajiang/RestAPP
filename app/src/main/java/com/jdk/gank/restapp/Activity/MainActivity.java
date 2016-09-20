package com.jdk.gank.restapp.Activity;

<<<<<<< HEAD
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.jdk.gank.restapp.R;
=======
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
>>>>>>> a17cc0a1ef0b8332501d201e889ff19771b8fe8c

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JDK on 2016/8/26.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    @Bind(R.id.navigation_view)
     NavigationView mNavigationView;
    @Bind(R.id.tool_bar)
     Toolbar toolbar;
    @Bind(R.id.drawerIcon)
     TextView drawerIcon;
    @Bind(R.id.drawer)
     DrawerLayout mDrawerLayout;
    @Bind(R.id.watch_tv)
     TextView watch_tv;
    @Bind(R.id.shake_tv)
     TextView shake_tv;
    @Bind(R.id.view_pager)
     ViewPager view_pager;
    @Bind(R.id.search_tv)
    TextView search_tv;
    @Bind(R.id.search_layout)
     LinearLayout search_layout;
    private boolean isOpen;
    //ToolBar三个按钮对应的Fragment
    private List<Fragment> fragmentlist = new ArrayList<>(2);
    private MyFragmentPagerAdapter adapter;
    private static final String TAG = "HomePageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addFragment();
        initWidgets();
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                isOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


    private void initWidgets() {
        setSupportActionBar(toolbar);
        //去除状态栏文字
        mNavigationView.setItemIconTintList(null);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        view_pager.addOnPageChangeListener(this);
        drawerIcon.setOnClickListener(this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "iconfont1.ttf");
        drawerIcon.setTypeface(typeface);
        watch_tv.setOnClickListener(this);
        watch_tv.setTypeface(typeface);
        shake_tv.setOnClickListener(this);
        shake_tv.setTypeface(typeface);
        search_tv.setTypeface(typeface);
        search_layout.setOnClickListener(this);
        //初始化显示位置
        shake_tv.setSelected(true);
        view_pager.setCurrentItem(1);
    }

    private void addFragment() {
        fragmentlist.add(Main1Activity.getInstance());
        fragmentlist.add(Main2Activity.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawerIcon:
                if (!isOpen) {
                    //LEFT和RIGHT指的是现存DrawerLayout的方向
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    isOpen = true;
                }
            case R.id.watch_tv:
                watch_tv.setSelected(true);
                shake_tv.setSelected(false);
                view_pager.setCurrentItem(0);
                break;
            case R.id.shake_tv:
                watch_tv.setSelected(true);
                shake_tv.setSelected(false);
                view_pager.setCurrentItem(1);
                break;
//            case R.id.search_layout:
////                SearchActivity.start(this);
//                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                watch_tv.setSelected(true);
                shake_tv.setSelected(false);

                break;
            case 1:
                shake_tv.setSelected(true);
                watch_tv.setSelected(false);
                break;

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mDrawerLayout.closeDrawer(mNavigationView);
            isOpen = false;
        }
        return super.onKeyDown(keyCode, event);
    }


=======
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
>>>>>>> a17cc0a1ef0b8332501d201e889ff19771b8fe8c

}
