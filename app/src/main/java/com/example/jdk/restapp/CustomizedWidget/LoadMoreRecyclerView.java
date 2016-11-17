package com.example.jdk.restapp.CustomizedWidget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by JDK on 2016/10/31.
 */
public class LoadMoreRecyclerView extends RecyclerView{
    private LoadMoreListener mLoadMoreListener;
    private boolean isBottom=true;
    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public interface LoadMoreListener{
        public void loadMore();
    }
    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.mLoadMoreListener=loadMoreListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        isBottom=dy>0;
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (computeVerticalScrollExtent() + computeVerticalScrollOffset()
                >= computeVerticalScrollRange()) {
            if (mLoadMoreListener != null&&isBottom)
                mLoadMoreListener.loadMore();
        }
    }
}
