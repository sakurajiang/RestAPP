package com.jdk.gank.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdk.gank.restapp.Activity.ShowWebViewActivity;
import com.jdk.gank.restapp.Adapter.RecyclerViewDataBindingAdapter;
import com.jdk.gank.restapp.HttpUtils.ReturnRetrofit;
import com.jdk.gank.restapp.ModelData.Constant;
import com.jdk.gank.restapp.ModelData.MyAndroidData;
import com.jdk.gank.restapp.ModelData.entity.Base;
import com.jdk.gank.restapp.ModelData.entity.URLTableData;
import com.jdk.gank.restapp.CustomizedWidget.ProgressSubscriber;
import com.jdk.gank.restapp.R;
import com.jdk.gank.restapp.Utils.MyDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class AndroidFragment extends BaseFragment {
        private List<Base> baseList;
    RecyclerViewDataBindingAdapter recyclerViewDataBindingAdapter;
    static Context mContext;

    public AndroidFragment() {
        super(R.layout.fragment_android);
    }
    public static AndroidFragment newInstance(Context context){
        AndroidFragment androidFragment=new AndroidFragment();
        mContext = context;
        return androidFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseList=new ArrayList<>();

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
    public void setSubscriber(int page){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getAndroidData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyAndroidData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyAndroidData myAndroidData) {
                        for (int i = 0; i < myAndroidData.results.size(); i++) {
                            baseList.add(myAndroidData.results.get(i));
                        }
                        //在这里设置这个标志位是为了加载后是直接在尾部加载数据，不会造成加载后整个数据又滑到了最开始。
                        if(isFirst()) {
                            recyclerViewDataBindingAdapter = new RecyclerViewDataBindingAdapter(mContext, baseList, Constant.IsAndroid);
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
    public void setProgressSubscribler(int page){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getAndroidData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<MyAndroidData>(getActivity()){

                    @Override
                    public void onNext(MyAndroidData myAndroidData) {
                        for (int i = 0; i < myAndroidData.results.size(); i++) {
                            baseList.add(myAndroidData.results.get(i));
                        }
                        //在这里设置这个标志位是为了加载后是直接在尾部加载数据，不会造成加载后整个数据又滑到了最开始。
                        if(isFirst()) {
                            recyclerViewDataBindingAdapter = new RecyclerViewDataBindingAdapter(mContext, baseList, Constant.IsAndroid);
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
        getMyRecyclerView().addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        getData(1);
        InitListener();
    }

}
