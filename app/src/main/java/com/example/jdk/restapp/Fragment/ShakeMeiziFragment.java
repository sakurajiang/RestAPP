package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.Activity.ShowBigBitmapActivity;
import com.example.jdk.restapp.Activity.ShowWebViewActivity;
import com.example.jdk.restapp.Adapter.RecyclerViewDataBindingAdapter;
import com.example.jdk.restapp.CustomizedWidget.ProgressSubscriber;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.Constant;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.RestVideoData;
import com.example.jdk.restapp.ModelData.entity.Base;
import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.ShakeUtils;
import com.example.jdk.restapp.Utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class ShakeMeiziFragment extends BaseFragment {
    private static Context mContext;
    private List<Base> meiziList;
    private List<Base> videoList;
    private RecyclerViewDataBindingAdapter recyclerViewDataBindingAdapter;
    Subscriber<MeiziData> meiziDataSubscriber;
    public ShakeMeiziFragment() {
        super(R.layout.fragment_shake_meizi);
    }
    public static ShakeMeiziFragment newInstance(Context context){
        ShakeMeiziFragment meiziFragment=new ShakeMeiziFragment();
        mContext=context;
        return meiziFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
    public void onPause() {
        super.onPause();
    }

    @Override
    public void getData(final int page) {
        setProgressSubscriber(page);
    }
    public void setSubscriber(final int page){
        if(page>1) {
            SnackBarUtils.makeLong(getActivity().getWindow().getDecorView(), getResources().getString(R.string.shake_loadmore_footer)).danger();
        }else{
            SnackBarUtils.makeLong(getActivity().getWindow().getDecorView(), getResources().getString(R.string.shake_refresh_header)).danger();
        }
    }
    public void setProgressSubscriber(final int page){
        meiziList = new ArrayList<>();
        videoList=new ArrayList<>();
        meiziDataSubscriber = new Subscriber<MeiziData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MeiziData meiziData) {
                for (int i = 0; i < meiziData.results.size(); i++) {
                    meiziList.add(meiziData.results.get(i));
                }
                ReturnRetrofit.getInstance().getMyGankApiRetrofit().getShakeRestVideoData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ProgressSubscriber<RestVideoData>(mContext) {
                            @Override
                            public void onNext(RestVideoData restVideoData) {
                                //这样设置是为了后面能够更新数据
                                for (int i = 0; i < restVideoData.results.size(); i++) {
                                    videoList.add(restVideoData.results.get(i));
                                }
                                //为什么在AndroidFragment或者FrontFragment这两个中不设置isFirst，加载依然可以，只是每次都需要从头再往下翻
                                //但是在MeiziFragment中不设置isFirst，如果加载的话就会变成白屏，难道是因为前两个用的是mvvm方式，而MeiziFragment
                                //没有用mvvm绑定数据的方式？
                                if (isFirst()) {
                                    setListWise(videoList,2);
                                    setListWise(meiziList,2);
                                    if(videoList.size()==2&&meiziList.size()==2) {
                                        recyclerViewDataBindingAdapter = new RecyclerViewDataBindingAdapter(mContext, videoList, Constant.IsMeizi);
                                        recyclerViewDataBindingAdapter.addList(meiziList);
                                        getMyRecyclerView().setAdapter(recyclerViewDataBindingAdapter);
                                    }
                                }
                                recyclerViewDataBindingAdapter.setRecyclerViewItemOnClickListener(new RecyclerViewDataBindingAdapter.recyclerViewDataBindingItemOnClickListener() {
                                    @Override
                                    public void recyclerViewDataBindingItemOnClick(String url, String desc, String who, Date CreateAt, String type, int position) {
                                        Intent intent = new Intent();
                                        intent.setClass(mContext, ShowWebViewActivity.class);
                                        URLTableData urlTableData = new URLTableData(url, who, desc, CreateAt);
                                        urlTableData.setType(type);
                                        urlTableData.setIsCollected(false);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("urlTableData", urlTableData);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                                recyclerViewDataBindingAdapter.setRecyclerViewDoubleDataBindingItemOnClickListener(new RecyclerViewDataBindingAdapter.recyclerViewDoubleDataBindingItemOnClickListener() {
                                    @Override
                                    public void recyclerViewDoubleDataBindingItemOnClick(String url, String desc) {
                                        Intent intent = new Intent();
                                        intent.setClass(mContext, ShowBigBitmapActivity.class);
                                        intent.putExtra("URL", url);
                                        intent.putExtra("DESC", desc);
                                        startActivity(intent);
                                    }
                                });
                                if(videoList.size()==2&&meiziList.size()==2) {
                                    recyclerViewDataBindingAdapter.notifyDataSetChanged();
                                }
                            }
                        });
            }
        };
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getShakeMeiziData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiziDataSubscriber);
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
