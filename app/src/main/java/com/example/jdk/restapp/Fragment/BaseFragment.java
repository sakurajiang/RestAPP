package com.example.jdk.restapp.Fragment;




<<<<<<< HEAD
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.CustomizedWidget.LoadMoreRecyclerView;
import com.example.jdk.restapp.HttpUtils.RequestData;
import com.example.jdk.restapp.R;
=======
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.jdk.restapp.R;

import java.util.List;
>>>>>>> origin/master

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by JDK on 2016/8/4.
 */
//这个BaseFragment主要实现了下拉刷新和上拉加载
<<<<<<< HEAD
public abstract  class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,LoadMoreRecyclerView.LoadMoreListener,RequestData.shProgressinterface {
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.LM_RecyclerView)
     LoadMoreRecyclerView mLMRecyclerView;
=======
public abstract  class BaseFragment extends Fragment {
    @Bind(R.id.swipeToLoadLayout)
     SwipeToLoadLayout swipeToLoadLayout;
    @Bind(R.id.swipe_target)
     RecyclerView myRecyclerView;
>>>>>>> origin/master
    //判断是第一次加载数据还是后面的更新数据
    private boolean isFirst=true;
    private int mLayout_Id;
    private int t=1;
    private View v;

    public BaseFragment(int layout_Id){
        this.mLayout_Id=layout_Id;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

<<<<<<< HEAD
    public LoadMoreRecyclerView getMyRecyclerView() {
        return mLMRecyclerView;
    }

    public void setLoadMoreRecyclerView(LoadMoreRecyclerView LMRecyclerView) {
        this.mLMRecyclerView = LMRecyclerView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

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
=======
    public RecyclerView getMyRecyclerView() {
        return myRecyclerView;
    }

    public void setMyRecyclerView(RecyclerView myRecyclerView) {
        this.myRecyclerView = myRecyclerView;
>>>>>>> origin/master
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v!=null){
            ButterKnife.bind(this,v);
            return v;
        }
        v = inflater.inflate(mLayout_Id, container, false);
<<<<<<< HEAD
        ButterKnife.bind(this, v);
        setLoadMoreRecyclerView(mLMRecyclerView);
        return  v;
    }
    public abstract void getData(int page);
    public abstract void  setSubscriber(int page,boolean isRefersh);
    public void InitListener(){
        mLMRecyclerView.setLoadMoreListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
    }
    @Override
    public void onRefresh() {
     setIsFirst(false);
     setSubscriber(1,true);
    }

    @Override
    public void loadMore() {
            setIsFirst(false);
                t++;
            setSubscriber(t,false);
    }

    @Override
    public void hideProgress() {
            if (swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void showProgress() {
        if (!swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(true);
=======
        ButterKnife.bind(this,v);
        setMyRecyclerView(myRecyclerView);
        return  v;
>>>>>>> origin/master
    }
    public abstract void getData(int page);
    public abstract void  setSubscriber(int page);
    public void InitListener(){

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIsFirst(false);
                setSubscriber(1);
                swipeToLoadLayout.setRefreshing(false);
            }
        });
        //为swipeToLoadLayout设置上拉加载更多监听者
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                setIsFirst(false);
                t++;
                Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(t);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                setSubscriber(t);
                            }
                        });
//                setSubscriber(t);
                swipeToLoadLayout.setLoadingMore(false);
            }
        });
    }


}
