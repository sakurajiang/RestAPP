package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
<<<<<<< HEAD
import android.support.v7.widget.StaggeredGridLayoutManager;
=======
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
>>>>>>> origin/master
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< HEAD
import com.example.jdk.restapp.Activity.ShowBigBitmapActivity;
import com.example.jdk.restapp.Activity.ShowWebViewActivity;
import com.example.jdk.restapp.Adapter.RecyclerViewDataBindingAdapter;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.Constant;
=======
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.jdk.restapp.Adapter.AdapterRecyclerViewMeizi;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
>>>>>>> origin/master
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.RestVideoData;
import com.example.jdk.restapp.ModelData.entity.Base;
import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.Progress.ProgressSubscriber;
import com.example.jdk.restapp.R;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
<<<<<<< HEAD
=======
import rx.Subscription;
>>>>>>> origin/master
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class MeiziFragment extends BaseFragment {
    private Context mContext;
<<<<<<< HEAD
    private List<Base> meiziList;
    private List<Base> videoList;
//    private AdapterRecyclerViewMeizi adapterRecyclerViewMeizi;
    private RecyclerViewDataBindingAdapter recyclerViewDataBindingAdapter;
    Subscriber<MeiziData> meiziDataSubscriber;
    ProgressSubscriber<MeiziData> meiziDataProgressSubscriber;
=======
    private List<Meizi> meiziList;
    private AdapterRecyclerViewMeizi adapterRecyclerViewMeizi;
    private SwipeToLoadLayout swipeToLoadLayout;
    Subscriber<MeiziData> meiziDataSubscriber;
    int t=1;
    int j=0;
>>>>>>> origin/master
    public MeiziFragment(Context context) {
        super(R.layout.fragment_meizi);
        this.mContext = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        meiziList = new ArrayList<>();
        videoList=new ArrayList<>();
=======
        meiziList=new ArrayList<>();

    }
>>>>>>> origin/master

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
<<<<<<< HEAD
        return super.onCreateView(inflater, container, savedInstanceState);
=======
        View v=inflater.inflate(R.layout.fragment_meizi,container,false);
        myRecyclerView= (RecyclerView) v.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) v.findViewById(R.id.swipeToLoadLayout);
        InitListener();
//        adapterRecyclerViewMeizi=new AdapterRecyclerViewMeizi(mContext, meiziList);
        return  v;
>>>>>>> origin/master
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    @Override
<<<<<<< HEAD
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
=======
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
<<<<<<< HEAD
        myRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
=======
        myRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
>>>>>>> origin/master
        s();
       o(1);
    }
    public void o(int page){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getMeiziData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiziDataSubscriber);
    }
    public void s(){
         meiziDataSubscriber=new Subscriber<MeiziData>() {
>>>>>>> origin/master
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MeiziData meiziData) {
<<<<<<< HEAD
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
=======
                meiziList=meiziData.results;
                Log.i("Logcat","meiziList"+meiziList.get(1).url);
               adapterRecyclerViewMeizi=new AdapterRecyclerViewMeizi(mContext, meiziList);
                myRecyclerView.setAdapter(adapterRecyclerViewMeizi);
<<<<<<< HEAD
              adapterRecyclerViewMeizi.notifyDataSetChanged();
=======
             //  adapterRecyclerViewMeizi.notifyDataSetChanged();
>>>>>>> origin/master
            }
        };

    }
    //设置刷新和加载
    public void shuaxinAndjiazai(int page){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getMeiziData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeiziData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MeiziData meiziData) {
                        for(int i=0;i<meiziData.results.size();i++){
                            meiziList.add(meiziData.results.get(i));
                        }
                        //这样可以达到每次增加10张图片
//                            int i;
//                            for (i = j; i < j + 10; i++) {
//                                meiziList.add(meiziData.results.get(i));
//                            }
//                            j = i;
                        //这样一次增加20，之后为什么每次再下拉还会增加1？
//                        for(int i=j;i<i+5;i++){
//                            meiziList.add(meiziData.results.get(i));
//                            j=i;
//                        }
                        //


                    }
                });
>>>>>>> origin/master
    }
    public void InitListener(){
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                shuaxinAndjiazai(1);
                    Log.i("Logcat","shuaixn");

                adapterRecyclerViewMeizi.notifyDataSetChanged();
                //设置下拉刷新结束
                swipeToLoadLayout.setRefreshing(false);
            }
        });
        //为swipeToLoadLayout设置上拉加载更多监听者
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

//                    list.add("加载更多获得的数据"+i);
                shuaxinAndjiazai(t+1);
                t++;
                Log.i("Logcat", "jiazai"+t);

                adapterRecyclerViewMeizi.notifyDataSetChanged();
                //设置上拉加载更多结束
                swipeToLoadLayout.setLoadingMore(false);
            }
        });
    }

}
