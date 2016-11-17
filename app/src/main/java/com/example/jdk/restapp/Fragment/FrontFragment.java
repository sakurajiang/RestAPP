package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.HttpUtils.RequestData;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.entity.Base;
import com.example.jdk.restapp.ModelData.entity.Front;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.MyDecoration;
import com.example.jdk.restapp.Utils.SPDataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/8/10.
 */
public class FrontFragment extends BaseFragment {
    private List<Base> frontList;
    private static Context mContext;
    private boolean isCache=false;
    public FrontFragment() {
        super(R.layout.fragment_watch_front);
    }
    public static FrontFragment newInstance(Context context){
        FrontFragment frontFragment=new FrontFragment();
        mContext=context;
        return frontFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
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

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            RequestData.getInstance(mContext).setSHProgressinterface(this);
        }

    }@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frontList =SPDataUtil.getFirstPageGirls(getString(R.string.sharedPreferences_front),mContext);
        if(frontList !=null&& frontList.size()!=0){
            isCache=true;
        }else {
            frontList = new ArrayList<>();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void getData(int page) {
        setSubscriber(page,false);
    }
    public void setSubscriber(int page,boolean isRefresh){
        if(isRefresh) {
            SPDataUtil.saveFirstPageGirls(mContext, frontList);
            frontList.clear();
        }
        RequestData.getInstance(mContext).requestFrontData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchFrontData(page), getMyRecyclerView(), page, frontList,isFirst(),false,isCache);
        isCache=false;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
        getMyRecyclerView().addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        getData(1);
        InitListener();
    }



}

