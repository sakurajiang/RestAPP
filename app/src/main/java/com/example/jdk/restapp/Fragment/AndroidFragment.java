package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.Activity.ShowWebViewActivity;
import com.example.jdk.restapp.Adapter.AdapterRecyclerViewAndroid;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.MyAndroidData;
import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.R;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class AndroidFragment extends BaseFragment {
    MyAndroidData myAndroidData;
    AdapterRecyclerViewAndroid adapterRecyclerViewAndroid;
    RecyclerView recyclerView;
    Context mContext;

    public AndroidFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAndroidData=new MyAndroidData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_android,container,false);
        recyclerView= (RecyclerView) v.findViewById(R.id.android_fragment_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getMyObserverable();
        return v;
    }
    public void getMyObserverable(){
        ReturnRetrofit.getInstance().getMyGankApiRetrofit().getAndroidData(3)
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
                    }
                });
    }


}
