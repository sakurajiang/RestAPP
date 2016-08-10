package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.jdk.restapp.Adapter.AdapterRecyclerViewMeizi;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class MeiziFragment extends BaseFragment{
    private RecyclerView myRecyclerView;
    private Context mContext;
    private List<Meizi> meiziList;
    private AdapterRecyclerViewMeizi adapterRecyclerViewMeizi;
    private SwipeToLoadLayout swipeToLoadLayout;
    Subscriber<MeiziData> meiziDataSubscriber;
    int t=1;
    int j=0;
    public MeiziFragment(Context context) {
        ;this.mContext=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meiziList=new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_meizi,container,false);
        myRecyclerView= (RecyclerView) v.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) v.findViewById(R.id.swipeToLoadLayout);
        InitListener();
//        adapterRecyclerViewMeizi=new AdapterRecyclerViewMeizi(mContext, meiziList);
        return  v;
    }

    @Override
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
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MeiziData meiziData) {
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
