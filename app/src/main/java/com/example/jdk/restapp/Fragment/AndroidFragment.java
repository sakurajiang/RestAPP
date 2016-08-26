package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
<<<<<<< HEAD
=======
import android.support.v7.widget.RecyclerView;
>>>>>>> origin/master
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.Activity.ShowWebViewActivity;
<<<<<<< HEAD
import com.example.jdk.restapp.Adapter.RecyclerViewDataBindingAdapter;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.Constant;
import com.example.jdk.restapp.ModelData.MyAndroidData;
import com.example.jdk.restapp.ModelData.entity.Base;
import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.Progress.ProgressSubscriber;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.MyDecoration;

import java.util.ArrayList;
import java.util.Date;
=======
import com.example.jdk.restapp.Adapter.AdapterRecyclerViewAndroid;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.MyAndroidData;
import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.R;

import java.util.ArrayList;
>>>>>>> origin/master
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class AndroidFragment extends BaseFragment {
<<<<<<< HEAD
//    MyAndroidData myAndroidData;
        private List<Base> baseList;
    RecyclerViewDataBindingAdapter recyclerViewDataBindingAdapter;
    Context mContext;

    public AndroidFragment(Context mContext) {
        super(R.layout.fragment_android);
=======
    MyAndroidData myAndroidData;
    AdapterRecyclerViewAndroid adapterRecyclerViewAndroid;
    RecyclerView recyclerView;
    Context mContext;

    public AndroidFragment(Context mContext) {
>>>>>>> origin/master
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        baseList=new ArrayList<>();

=======
        myAndroidData=new MyAndroidData();
>>>>>>> origin/master
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
<<<<<<< HEAD
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void getData(int page) {
       setProgressSubscribler(page);
    }
    public void setSubscriber(int page){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getAndroidData(page)
=======
        View v=inflater.inflate(R.layout.fragment_android,container,false);
        recyclerView= (RecyclerView) v.findViewById(R.id.android_fragment_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getMyObserverable();
        return v;
    }
    public void getMyObserverable(){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getAndroidData(3)
>>>>>>> origin/master
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
<<<<<<< HEAD
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
=======
                        Log.i("Logcat","onNext"+myAndroidData.results);
                        adapterRecyclerViewAndroid=new AdapterRecyclerViewAndroid(myAndroidData,mContext);
                        adapterRecyclerViewAndroid.setAndroidRecyclerViewItemOnClickListener(new AdapterRecyclerViewAndroid.androidRecyclerViewItemOnClickListener() {
                            @Override
                            public void androidRecyclerViewItemObClick(String url) {
                                Intent intent=new Intent();
                                intent.setClass(mContext, ShowWebViewActivity.class);
                                intent.putExtra("WebViewURL",url);
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapterRecyclerViewAndroid);
                        adapterRecyclerViewAndroid.notifyDataSetChanged();
>>>>>>> origin/master
                    }
                });
    }

<<<<<<< HEAD
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
=======
>>>>>>> origin/master

}
