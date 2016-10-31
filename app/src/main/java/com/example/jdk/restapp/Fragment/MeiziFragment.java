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


import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/8/4.
 */
public class MeiziFragment extends BaseFragment {
    private static Context mContext;
    private List<Base> meiziList;
    public MeiziFragment() {
        super(R.layout.fragment_watch_meizi);
    }
    public static MeiziFragment newInstance(Context context){
        MeiziFragment meiziFragment=new MeiziFragment();
        mContext=context;
        return meiziFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        meiziList = new ArrayList<>();
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        getData(1);
        InitListener();
    }
}
