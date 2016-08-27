package com.jdk.gank.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdk.gank.restapp.Activity.ShowBigBitmapActivity;
import com.jdk.gank.restapp.Activity.ShowWebViewActivity;
import com.jdk.gank.restapp.Adapter.RecyclerViewDataBindingAdapter;
import com.jdk.gank.restapp.HttpUtils.ReturnRetrofit;
import com.jdk.gank.restapp.ModelData.Constant;
import com.jdk.gank.restapp.ModelData.MeiziData;
import com.jdk.gank.restapp.ModelData.RestVideoData;
import com.jdk.gank.restapp.ModelData.entity.Base;
import com.jdk.gank.restapp.ModelData.entity.URLTableData;
import com.jdk.gank.restapp.CustomizedWidget.ProgressSubscriber;
import com.jdk.gank.restapp.R;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class MeiziFragment extends BaseFragment {
    private static Context mContext;
    private List<Base> meiziList;
    private List<Base> videoList;
    private RecyclerViewDataBindingAdapter recyclerViewDataBindingAdapter;
    Subscriber<MeiziData> meiziDataSubscriber;
    public MeiziFragment() {
        super(R.layout.fragment_meizi);
    }
    public static MeiziFragment newInstance(Context context){
        MeiziFragment meiziFragment=new MeiziFragment();
        mContext=context;
        return meiziFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meiziList = new ArrayList<>();
        videoList=new ArrayList<>();

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
        setProgressSubscriber(page);
    }
    public void setSubscriabler(final int page){
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
                ReturnRetrofit.getInstance().getMyGankApiRetrofit().getRestVideoData(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<RestVideoData>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

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
                                    recyclerViewDataBindingAdapter = new RecyclerViewDataBindingAdapter(mContext,videoList, Constant.IsMeizi);
                                    recyclerViewDataBindingAdapter.addList(meiziList);
                                    getMyRecyclerView().setAdapter(recyclerViewDataBindingAdapter);
                                }
                                recyclerViewDataBindingAdapter.setRecyclerViewItemOnClickListener(new RecyclerViewDataBindingAdapter.recyclerViewDataBindingItemOnClickListener() {
                                    @Override
                                    public void recyclerViewDataBindingItemOnClick(String url, String desc, String who, Date CreateAt, String type, int position) {
                                        Intent intent = new Intent();
                                        intent.setClass(mContext, ShowWebViewActivity.class);
                                        URLTableData urlTableData = new URLTableData(url, who, desc, CreateAt);
                                        urlTableData.setType(type);
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

                                recyclerViewDataBindingAdapter.notifyDataSetChanged();
                            }
                        });


            }
        };
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getMeiziData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiziDataSubscriber);
    }
    public void setProgressSubscriber(final int page){
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
                ReturnRetrofit.getInstance().getMyGankApiRetrofit().getRestVideoData(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ProgressSubscriber<RestVideoData>(getActivity()) {
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
                                    recyclerViewDataBindingAdapter = new RecyclerViewDataBindingAdapter(mContext,videoList, Constant.IsMeizi);
                                    recyclerViewDataBindingAdapter.addList(meiziList);
                                    getMyRecyclerView().setAdapter(recyclerViewDataBindingAdapter);
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

                                recyclerViewDataBindingAdapter.notifyDataSetChanged();
                            }
                        });
            }
        };
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getMeiziData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiziDataSubscriber);
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
