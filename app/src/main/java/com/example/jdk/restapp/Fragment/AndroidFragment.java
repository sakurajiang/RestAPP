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
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.MyDecoration;
<<<<<<< HEAD
import com.example.jdk.restapp.Utils.SPDataUtil;
=======
<<<<<<< HEAD
import com.example.jdk.restapp.Utils.SPDataUtil;
=======
>>>>>>> origin/master
>>>>>>> origin/master

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import rx.Observable;

=======
<<<<<<< HEAD
import rx.Observable;

=======
>>>>>>> origin/master
>>>>>>> origin/master
/**
 * Created by JDK on 2016/8/4.
 */
public class AndroidFragment extends BaseFragment {
    private List<Base> androidList;
    static Context mContext;
<<<<<<< HEAD
    private boolean isCache=false;
=======
<<<<<<< HEAD
    private boolean isCache=false;
=======
>>>>>>> origin/master
>>>>>>> origin/master

    public AndroidFragment() {
        super(R.layout.fragment_watch_android);
    }
    public static AndroidFragment newInstance(Context context){
        AndroidFragment androidFragment=new AndroidFragment();
        mContext = context;
        return androidFragment;
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidList=SPDataUtil.getFirstPageGirls(getString(R.string.sharedPreferences_android),mContext);
        if(androidList!=null&&androidList.size()!=0){
            isCache=true;
        }else {
            androidList = new ArrayList<>();
        }
<<<<<<< HEAD
=======
=======

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidList=new ArrayList<>();
>>>>>>> origin/master
>>>>>>> origin/master

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
    @Override
    public void setSubscriber(int page,boolean isRefresh){
        if(isRefresh) {
            SPDataUtil.saveFirstPageGirls(mContext,androidList);
            androidList.clear();
        }
        RequestData.getInstance(mContext).requestAndroidData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchAndroidData(page),getMyRecyclerView(),page,androidList,isFirst(),false,isCache);
        isCache=false;
<<<<<<< HEAD
=======
=======
       setSubscriber(page);
    }
    @Override
    public void setSubscriber(int page){
        RequestData.getInstance(mContext).requestAndroidData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchAndroidData(page),getMyRecyclerView(),page,androidList,isFirst(),false);
>>>>>>> origin/master
>>>>>>> origin/master
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
        if(isVisibleToUser) {
            RequestData.getInstance(mContext).setSHProgressinterface(this);
        }

<<<<<<< HEAD
=======
=======
>>>>>>> origin/master
>>>>>>> origin/master
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
        getMyRecyclerView().addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        getData(1);
        InitListener();
}
<<<<<<< HEAD
=======
=======
        getMyRecyclerView().addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        getData(1);
        InitListener();
    }
>>>>>>> origin/master
>>>>>>> origin/master

}
