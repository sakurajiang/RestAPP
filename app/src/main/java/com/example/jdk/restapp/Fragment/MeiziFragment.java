package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.HttpUtils.RequestData;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.entity.Base;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.SPDataUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/8/4.
 */
public class MeiziFragment extends BaseFragment {
    private static Context mContext;
    private List<Base> meiziList;
<<<<<<< HEAD
    private boolean isCache=false;
=======
<<<<<<< HEAD
    private boolean isCache=false;
=======
>>>>>>> origin/master
>>>>>>> origin/master
    public MeiziFragment() {
        super(R.layout.fragment_watch_meizi);
    }
    public static MeiziFragment newInstance(Context context){
        MeiziFragment meiziFragment=new MeiziFragment();
        mContext=context;
        return meiziFragment;
<<<<<<< HEAD
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
=======
<<<<<<< HEAD
>>>>>>> origin/master
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
=======
>>>>>>> origin/master
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        meiziList = new ArrayList<>();
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
        meiziList=SPDataUtil.getFirstPageGirls(getString(R.string.sharedPreferences_picture),mContext);
        if(meiziList!=null&&meiziList.size()!=0) {
           isCache=true;
        }else{
            meiziList = new ArrayList<>();
        }
<<<<<<< HEAD
=======
=======
>>>>>>> origin/master
>>>>>>> origin/master

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
<<<<<<< HEAD
    }
       @Override
    public void onDestroyView() {
        super.onDestroyView();

=======
<<<<<<< HEAD
>>>>>>> origin/master
    }
       @Override
    public void onDestroyView() {
        super.onDestroyView();

=======
>>>>>>> origin/master
    }
    @Override
<<<<<<< HEAD
=======
    public void onDestroyView() {
        super.onDestroyView();

    }
    @Override
    public void getData(final int page) {
        setSubscriber(page);
    }
    public void setSubscriber(final int page){
        RequestData.getInstance(mContext).requestMeiziData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchMeiziData(page),
                ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchRestVideoData(page),getMyRecyclerView(),page,meiziList,isFirst(),false);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
    @Override
<<<<<<< HEAD
>>>>>>> origin/master
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
<<<<<<< HEAD

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void getData(final int page) {
        setSubscriber(page,false);
    }
    public void setSubscriber(final int page,boolean isRefresh){
        if(isRefresh) {
            SPDataUtil.saveFirstPageGirls(mContext, meiziList);
            meiziList.clear();
        }
        RequestData.getInstance(mContext).requestMeiziData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchMeiziData(page),
                ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchRestVideoData(page), getMyRecyclerView(), page, meiziList, isFirst(), false,isCache);
        isCache=false;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            RequestData.getInstance(mContext).setSHProgressinterface(this);
        }

    }
    @Override
=======

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void getData(final int page) {
        setSubscriber(page,false);
    }
    public void setSubscriber(final int page,boolean isRefresh){
        if(isRefresh) {
            SPDataUtil.saveFirstPageGirls(mContext, meiziList);
            meiziList.clear();
        }
        RequestData.getInstance(mContext).requestMeiziData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchMeiziData(page),
                ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchRestVideoData(page), getMyRecyclerView(), page, meiziList, isFirst(), false,isCache);
        isCache=false;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            RequestData.getInstance(mContext).setSHProgressinterface(this);
        }

    }
    @Override
=======
>>>>>>> origin/master
>>>>>>> origin/master
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
        InitListener();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                setSubscriber(1, false);
            }
        });
<<<<<<< HEAD
=======
=======
        getData(1);
        InitListener();
>>>>>>> origin/master
>>>>>>> origin/master
    }
}
