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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
import com.example.jdk.restapp.ModelData.entity.Front;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.MyDecoration;
import com.example.jdk.restapp.Utils.SPDataUtil;
<<<<<<< HEAD
=======
=======
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.MyDecoration;
>>>>>>> origin/master
>>>>>>> origin/master

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/8/10.
 */
public class FrontFragment extends BaseFragment {
<<<<<<< HEAD
    private List<Base> frontList;
    private static Context mContext;
    private boolean isCache=false;
=======
<<<<<<< HEAD
    private List<Base> frontList;
    private static Context mContext;
    private boolean isCache=false;
=======
    private List<Base> myList;
    private static Context mContext;
>>>>>>> origin/master
>>>>>>> origin/master
    public FrontFragment() {
        super(R.layout.fragment_watch_front);
    }
    public static FrontFragment newInstance(Context context){
        FrontFragment frontFragment=new FrontFragment();
        mContext=context;
        return frontFragment;
    }

    @Override
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
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
<<<<<<< HEAD
=======
=======
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myList=new ArrayList<>();
>>>>>>> origin/master
>>>>>>> origin/master
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
        setSubscriber(page,false);
    }
    public void setSubscriber(int page,boolean isRefresh){
        if(isRefresh) {
            SPDataUtil.saveFirstPageGirls(mContext, frontList);
            frontList.clear();
        }
        RequestData.getInstance(mContext).requestFrontData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchFrontData(page), getMyRecyclerView(), page, frontList,isFirst(),false,isCache);
        isCache=false;

<<<<<<< HEAD
=======
=======
        setSubscriber(page);
    }
    public void setSubscriber(int page){
        RequestData.getInstance(mContext).requestFrontData( ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchFrontData(page),getMyRecyclerView(),page,myList,isFirst(),false);
>>>>>>> origin/master
>>>>>>> origin/master
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

