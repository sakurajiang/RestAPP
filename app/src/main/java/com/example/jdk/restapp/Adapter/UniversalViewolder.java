package com.example.jdk.restapp.Adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JDK on 2016/9/30.
 */
public class UniversalViewolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mChildViews;
    private View mItemView;
    private ViewDataBinding viewDataBinding;
    public UniversalViewolder(View itemView) {
        super(itemView);
        mItemView=itemView;
        mChildViews=new SparseArray<>();
        viewDataBinding= DataBindingUtil.bind(itemView);
    }
    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }
    public void setViewDataBinding(ViewDataBinding viewDataBinding) {
        this.viewDataBinding = viewDataBinding;
    }
    public static UniversalViewolder getViewHolder(ViewGroup parent,int LayoutId){
        View v=LayoutInflater.from(parent.getContext()).inflate(LayoutId, parent, false);
        return new UniversalViewolder(v);
    }
    public View getChildView(int ChildId){
        View childView=mChildViews.get(ChildId);
        if(childView==null){
            childView=mItemView.findViewById(ChildId);
            mChildViews.put(ChildId,childView);
        }
        return childView;
    }

}
