package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.Activity.ShowWebViewActivity;
import com.example.jdk.restapp.Adapter.RecyclerViewDataBindingAdapter;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.Constant;
import com.example.jdk.restapp.ModelData.FrontData;
import com.example.jdk.restapp.ModelData.entity.Base;
import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.CustomizedWidget.ProgressSubscriber;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.MyDecoration;
import com.example.jdk.restapp.Utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/10.
 */
public class FrontFragment extends BaseFragment {
    private List<Base> myList;
    private static Context mContext;
    RecyclerViewDataBindingAdapter recyclerViewDataBindingAdapter;


    public FrontFragment() {
        super(R.layout.fragment_watch_front);
    }
    public static FrontFragment newInstance(Context context){
        FrontFragment frontFragment=new FrontFragment();
        mContext=context;
        return frontFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myList=new ArrayList<>();
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
        setProgressSubscribler(page);
    }
    //每次请求数据都会有progressdialog
    public void setProgressSubscribler(int page){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchFrontData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<FrontData>(mContext) {
                    @Override
                    public void onNext(FrontData frontData) {
                        for (int i = 0; i < frontData.results.size(); i++) {
                            myList.add(frontData.results.get(i));
                        }
                        if(isFirst()) {
                            recyclerViewDataBindingAdapter = new RecyclerViewDataBindingAdapter(mContext, myList, Constant.IsFront);
                            getMyRecyclerView().setAdapter(recyclerViewDataBindingAdapter);
                        }
                        recyclerViewDataBindingAdapter.setRecyclerViewItemOnClickListener(new RecyclerViewDataBindingAdapter.recyclerViewDataBindingItemOnClickListener() {
                            @Override
                            public void recyclerViewDataBindingItemOnClick(String url, String desc, String who, Date CreateAt,String type,int position) {
                                Intent intent = new Intent();
                                intent.setClass(mContext, ShowWebViewActivity.class);
                                URLTableData urlTableData=new URLTableData(url,who,desc,CreateAt);
                                urlTableData.setType(type);
                                urlTableData.setIsCollected(false);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("urlTableData",urlTableData);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                        recyclerViewDataBindingAdapter.notifyDataSetChanged();
                    }

                });
    }
    //每次请求数据都没有progressdialog,加载和刷新的时候用这个，因为这个progressbar会遮盖界面，不友好
    public void setSubscriber(int page){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getWatchFrontData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FrontData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        SnackBarUtils.makeLong(getActivity().getWindow().getDecorView(), getResources().getString(R.string.network_error)).danger();
                    }

                    @Override
                    public void onNext(FrontData frontData) {
                        for (int i = 0; i < frontData.results.size(); i++) {
                            myList.add(frontData.results.get(i));
                        }
                        if(isFirst()) {
                            recyclerViewDataBindingAdapter = new RecyclerViewDataBindingAdapter(mContext, myList, Constant.IsFront);
                            getMyRecyclerView().setAdapter(recyclerViewDataBindingAdapter);
                        }
                        recyclerViewDataBindingAdapter.setRecyclerViewItemOnClickListener(new RecyclerViewDataBindingAdapter.recyclerViewDataBindingItemOnClickListener() {
                                                                                              @Override
                                                                                              public void recyclerViewDataBindingItemOnClick(String url, String desc, String who, Date CreateAt,String type,int position) {
                                                                                                  Intent intent = new Intent();
                                                                                                  intent.setClass(mContext, ShowWebViewActivity.class);
                                                                                                  URLTableData urlTableData=new URLTableData(url,who,desc,CreateAt);
                                                                                                  urlTableData.setType(type);
                                                                                                  urlTableData.setIsCollected(false);
                                                                                                  Bundle bundle=new Bundle();
                                                                                                  bundle.putSerializable("urlTableData",urlTableData);
                                                                                                  intent.putExtras(bundle);
                                                                                                  startActivity(intent);
                                                                                              }
                                                                                          });

                                recyclerViewDataBindingAdapter.notifyDataSetChanged();
                    }
                });
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

