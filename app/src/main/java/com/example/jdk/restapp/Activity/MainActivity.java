package com.example.jdk.restapp.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.github.ikidou.fragmentBackHandler.BackHandlerHelper;
import com.example.jdk.restapp.Fragment.AboutAuthorFragment;
import com.example.jdk.restapp.Fragment.AboutProjectFragment;
import com.example.jdk.restapp.Fragment.CollectionFragment;
import com.example.jdk.restapp.Fragment.WatchAndShakeFragment;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.SnackBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by JDK on 2016/8/28.
 */
public class MainActivity extends BaseActivity implements CollectionFragment.collectionDrawerIconListener,WatchAndShakeFragment.watchAndShakeFragmentListener,AboutProjectFragment.aboutProjectDrawerIconListener,AboutAuthorFragment.aboutAuthorDrawerIconListener {
    @Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    private boolean isOpen;
    private WatchAndShakeFragment watchAndShakeFragment;
    private CollectionFragment collectionFragment;
    private AboutProjectFragment aboutProjectFragment;
    private AboutAuthorFragment aboutAuthorFragment;
    private Fragment currentFragment;
    private long lastBackKeyDownTick = 0;
    private static final long MAX_DOUBLE_BACK_DURATION = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showDefaultFragment();
        mNavigationView.setItemIconTintList(null);
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
        initNavigationViewItemSelected();

    }
    public void showDefaultFragment(){
        if(watchAndShakeFragment ==null){
            watchAndShakeFragment = WatchAndShakeFragment.newInstance();
        }
        addFragment(R.id.activity_main, watchAndShakeFragment);
        currentFragment= watchAndShakeFragment;
    }
    public void initNavigationViewItemSelected(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_sort:
//                        if(!currentTag.equals(SHOWSHAKETAG)) {
//                            getFragmentTransaction().hide(getSupportFragmentManager().findFragmentByTag(currentTag)).commit();
//                            showDefaultFragment(SHOWSHAKETAG);
////                        setFragmentChildViewListenerByTag(COLLECTIONTAG);
//                            currentTag = SHOWSHAKETAG;
//                        }
                        if (watchAndShakeFragment == null) {
                            watchAndShakeFragment = WatchAndShakeFragment.newInstance();
                        }
                        switchFragment(currentFragment, watchAndShakeFragment);
                        break;
                    case R.id.navigation_collection:
                        if (collectionFragment == null) {
                            collectionFragment = CollectionFragment.newInstance(MainActivity.this);
                        }
                        switchFragment(currentFragment, collectionFragment);
                        break;
                    case R.id.navigation_share:
                        SnackBarUtils.makeShort(getWindow().getDecorView(), getResources().getString(R.string.onClick_share)).danger();
                        break;
                    case R.id.navigation_about_project:
                        if (aboutProjectFragment == null) {
                            aboutProjectFragment = AboutProjectFragment.newInstance();
                        }
                        switchFragment(currentFragment, aboutProjectFragment);
//                        setSupportActionBar(toolbar);
                        break;
                    case R.id.navigation_about_author:
                        if (aboutAuthorFragment == null) {
                            aboutAuthorFragment = new AboutAuthorFragment();
                        }
                        switchFragment(currentFragment, aboutAuthorFragment);
                        break;

                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
    }
    public void switchFragment(Fragment from,Fragment to){
        if(currentFragment!=to){
            currentFragment=to;
            if(!to.isAdded()){
                getFragmentTransaction().hide(from).add(R.id.activity_main,to).commit();
            }else{
                getFragmentTransaction().hide(from).show(to).commit();
            }
        }
    }
    @Override
    public void collectionDrawerIcon() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }
    @Override
    public void watchAndShakeFragment() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }
    @Override
    public void onBackPressed() {
        long currentTick = System.currentTimeMillis();
        boolean b=!BackHandlerHelper.handleBackPress(watchAndShakeFragment);
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (isOpen) {
                mDrawerLayout.closeDrawer(mNavigationView);
                isOpen = false;
            } else {
                if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
                    SnackBarUtils.makeShort(getWindow().getDecorView(), "再按一次退出").danger();
                    lastBackKeyDownTick = currentTick;
                } else {
                    finish();
                    System.exit(0);
                }
            }
//            super.onBackPressed();
        }
//            if (isOpen) {
//                mDrawerLayout.closeDrawer(mNavigationView);
//                isOpen = false;
//            } else {
//                if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
//                    SnackBarUtils.makeShort(getWindow().getDecorView(), "再按一次退出").danger();
//                    lastBackKeyDownTick = currentTick;
//                } else {
//                    finish();
//                    System.exit(0);
//                }

    }
    @Override
    public void aboutProjectDrawerIcon() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void aboutAuthorDrawerIcon() {
        if (!isOpen) {
            //LEFT和RIGHT指的是现存DrawerLayout的方向
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }
    public void settingOnClick(View v){
        SnackBarUtils.makeShort(v,getResources().getString(R.string.onClick_setting)).warning();
    }
    public void quitOnClick(View v){
        finish();
        System.exit(0);
    }
}
