package com.example.jdk.restapp.Fragment;




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

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by JDK on 2016/8/4.
 */
//这个BaseFragment主要实现了下拉刷新和上拉加载
public abstract  class BaseFragment extends Fragment {
    @Bind(R.id.swipeToLoadLayout)
     SwipeToLoadLayout swipeToLoadLayout;
    @Bind(R.id.swipe_target)
     RecyclerView myRecyclerView;
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

    public RecyclerView getMyRecyclerView() {
        return myRecyclerView;
    }

    public void setMyRecyclerView(RecyclerView myRecyclerView) {
        this.myRecyclerView = myRecyclerView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v!=null){
            ButterKnife.bind(this,v);
            return v;
        }
        v = inflater.inflate(mLayout_Id, container, false);
        ButterKnife.bind(this,v);
        setMyRecyclerView(myRecyclerView);
        return  v;
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
                setSubscriber(t);
                swipeToLoadLayout.setLoadingMore(false);
            }
        });
    }
    //设置list的规格,若有超出，则从头开始抹去，直到满足条件的size
    public void setListWise(List listWise,int size){
       if(listWise.size()>size){
           for(int i=0;i<listWise.size()-size;i++){
               listWise.remove(i);
           }
       }
    }

}
