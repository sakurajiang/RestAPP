package com.jdk.gank.restapp.Fragment;




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
import com.jdk.gank.restapp.R;



/**
 * Created by JDK on 2016/8/4.
 */
//这个BaseFragment主要实现了下拉刷新和上拉加载
public abstract  class BaseFragment extends Fragment {
    //判断是第一次加载数据还是后面的更新数据
    private boolean isFirst=true;
    private SwipeToLoadLayout swipeToLoadLayout;
    private int mLayout_Id;
    private RecyclerView myRecyclerView;
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
            v = inflater.inflate(mLayout_Id, container, false);
            myRecyclerView = (RecyclerView) v.findViewById(R.id.swipe_target);
            swipeToLoadLayout = (SwipeToLoadLayout) v.findViewById(R.id.swipeToLoadLayout);
            setMyRecyclerView(myRecyclerView);
            return  v;
    }
    public abstract void getData(int page);
    public void InitListener(){

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIsFirst(false);
                getData(1);
                swipeToLoadLayout.setRefreshing(false);
            }
        });
        //为swipeToLoadLayout设置上拉加载更多监听者
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                setIsFirst(false);
                t++;
               getData(t);
                swipeToLoadLayout.setLoadingMore(false);
            }
        });
    }



}
