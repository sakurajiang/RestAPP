package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ikidou.fragmentBackHandler.FragmentBackHandler;
import com.example.jdk.restapp.Activity.ShowWebViewActivity;
import com.example.jdk.restapp.CustomizedWidget.CustomClearAndSearechEdittext;
import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JDK on 2016/8/26.
 */
public class WatchAndShakeFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener,CustomClearAndSearechEdittext.OnClickSearchListener,TextView.OnEditorActionListener,FragmentBackHandler{
    @Bind(R.id.tool_bar)
     Toolbar toolbar;
    @Bind(R.id.drawerIcon)
     TextView drawerIcon;
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
    @Bind(R.id.search_til)
    TextInputLayout textInputLayout;
    @Bind(R.id.search_et)
    EditText editText;
    private ActionBar actionBar;
    //ToolBar三个按钮对应的Fragment
    private List<Fragment> fragmentlist = new ArrayList<>(2);
    private View v;
    private MyFragmentPagerAdapter adapter;
    private static WatchAndShakeFragment watchAndShakeFragment;
    public static WatchAndShakeFragment newInstance(){
        if( watchAndShakeFragment ==null){
            watchAndShakeFragment =new WatchAndShakeFragment();
        }
        return watchAndShakeFragment;
    }

    @Override
    public void onClickSearch() {
        search();
    }

    @Override
    public boolean onBackPressed() {
        if(shake_tv.getVisibility()!=View.VISIBLE) {
            shake_tv.setVisibility(View.VISIBLE);
            watch_tv.setVisibility(View.VISIBLE);
            drawerIcon.setVisibility(View.VISIBLE);
            actionBar.setDisplayHomeAsUpEnabled(false);
            toolbar.setNavigationIcon(null);
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
            editText.clearFocus();
            textInputLayout.setVisibility(View.GONE);
            return true;
        }else{
            return false;
        }
    }
 //通过点击软键盘可以搜索
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
           search();
        }
        return false;
    }


    public interface watchAndShakeFragmentListener{
        public void watchAndShakeFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v!=null){
            ButterKnife.bind(this, v);
            return v;
        }
         v=inflater.inflate(R.layout.fragment_watch_shake,container,false);
        ButterKnife.bind(this, v);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        actionBar= ((AppCompatActivity)getActivity()).getSupportActionBar();
        getActivity().getSupportFragmentManager().popBackStack();
        addFragment();
        initWidgets();
        return v;
    }
    private void initWidgets() {

        //去除状态栏文字
//        mNavigationView.setItemIconTintList(null);
        adapter = new MyFragmentPagerAdapter(getFragmentManager());
//        shakeUtils=new ShakeUtils(getActivity());
        getFragmentManager();
        view_pager.setAdapter(adapter);
        view_pager.addOnPageChangeListener(this);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        drawerIcon.setTypeface(typeface);
        drawerIcon.setOnClickListener(this);
        watch_tv.setOnClickListener(this);
        watch_tv.setTypeface(typeface);
        shake_tv.setOnClickListener(this);
        shake_tv.setTypeface(typeface);
        search_tv.setTypeface(typeface);
//        jjSearchView.setController(new JJCircleToSimpleLineController());
        search_layout.setOnClickListener(this);
        //初始化显示位置
        watch_tv.setSelected(true);
        view_pager.setCurrentItem(0);
    }
    public void search(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), ShowWebViewActivity.class);
        String url="http://gank.io/search?q="+editText.getText().toString();
        URLTableData urlTableData=new URLTableData(url,"","搜索 ["+editText.getText().toString()+"] 的结果",null);
        urlTableData.setType("");
        urlTableData.setIsCollected(false);
        Bundle bundle=new Bundle();
        bundle.putSerializable("urlTableData",urlTableData);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void addFragment() {
        fragmentlist.add(WatchFragment.getInstance());
        fragmentlist.add(ShakeFragment.getInstance(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        editText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
////                    InputMethodManager inputMethodManager= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
////                    inputMethodManager.hideSoftInputFromInputMethod(editText.getWindowToken(), 0);
//                  getFocus();
//                }
//                return false;
//            }
//        });
    }
    private void getFocus(){
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK&&shake_tv.getVisibility()!=View.VISIBLE) {
                    shake_tv.setVisibility(View.VISIBLE);
                    watch_tv.setVisibility(View.VISIBLE);
                    drawerIcon.setVisibility(View.VISIBLE);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    toolbar.setNavigationIcon(null);
                    textInputLayout.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.watch_tv:
                watch_tv.setSelected(true);
                shake_tv.setSelected(false);
                view_pager.setCurrentItem(0);
                break;
            case R.id.shake_tv:
                shake_tv.setSelected(true);
                watch_tv.setSelected(false);
                view_pager.setCurrentItem(1);
                break;
            case R.id.drawerIcon:
                if(getActivity()instanceof watchAndShakeFragmentListener){
                    ((watchAndShakeFragmentListener) getActivity()).watchAndShakeFragment();
                }
                break;
            case R.id.search_layout:
                shake_tv.setVisibility(View.GONE);
                watch_tv.setVisibility(View.GONE);
                drawerIcon.setVisibility(View.GONE);
                textInputLayout.setVisibility(View.VISIBLE);
                editText.setFocusableInTouchMode(true);
                editText.setFocusable(true);
                editText.requestFocus();
                ((CustomClearAndSearechEdittext)editText).setOnClickSearchListener(this);
                editText.setOnEditorActionListener(this);
                toolbar.setNavigationIcon(R.drawable.ic_back_28dp);
                  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          shake_tv.setVisibility(View.VISIBLE);
                          watch_tv.setVisibility(View.VISIBLE);
                          drawerIcon.setVisibility(View.VISIBLE);
                          actionBar.setDisplayHomeAsUpEnabled(false);
                          toolbar.setNavigationIcon(null);
                          editText.clearFocus();
                          InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                          imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                          textInputLayout.setVisibility(View.GONE);
                      }
                  });
            default:
                break;
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
                view_pager.setCurrentItem(0);
                break;
            case 1:
                shake_tv.setSelected(true);
                watch_tv.setSelected(false);
                view_pager.setCurrentItem(1);
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



}
