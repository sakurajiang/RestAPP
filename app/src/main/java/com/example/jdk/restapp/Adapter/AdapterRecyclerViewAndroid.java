package com.example.jdk.restapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.Activity.ShowWebViewActivity;
import com.example.jdk.restapp.ModelData.MyAndroidData;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.databinding.AndroidItemBinding;

/**
 * Created by JDK on 2016/8/7.
 */
public class AdapterRecyclerViewAndroid extends RecyclerView.Adapter<AdapterRecyclerViewAndroid.MyViewHolder> {
    private MyAndroidData myAndroidData;
    private Context mContext;
    private MyViewHolder myViewHolder;
    private androidRecyclerViewItemOnClickListener myAndroidRecyclerViewItemOnClickListener;
    public interface androidRecyclerViewItemOnClickListener{
        public void androidRecyclerViewItemObClick(String url);
    }
    public void setAndroidRecyclerViewItemOnClickListener(androidRecyclerViewItemOnClickListener myAndroidRecyclerViewItemOnClickListener){
        this.myAndroidRecyclerViewItemOnClickListener=myAndroidRecyclerViewItemOnClickListener;
    }
    public AdapterRecyclerViewAndroid( MyAndroidData myAndroidData, Context mContext) {
        this.myAndroidData=myAndroidData;
        this.mContext=mContext;
    }
    public LayoutInflater getInflater(){
        return LayoutInflater.from(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=getInflater().inflate(R.layout.android_item,parent,false);
        myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.i("Logcat","onBindViewHolder111"+myAndroidData.results);
        Log.i("Logcat", "onBindViewHolder" + myAndroidData.results.get(position).url);
        AndroidItemBinding androidItemBinding=DataBindingUtil.bind(holder.itemView);
        androidItemBinding.setMyAndroid(myAndroidData.results.get(position));
        //当变量的值更新的时候，binding 对象将在下个更新周期中更新,但是会有一点时间间隔
        holder.getViewDataBinding().executePendingBindings();
        //设置回调
        if(myAndroidRecyclerViewItemOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myAndroidRecyclerViewItemOnClickListener.androidRecyclerViewItemObClick(myAndroidData.results.get(position).url);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        Log.i("Logcat", "ItemCountAndroid" + myAndroidData.results.size());
        return myAndroidData.results.size()==0?1:myAndroidData.results.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding viewDataBinding;
        public MyViewHolder(View itemView) {
            super(itemView);
            viewDataBinding=DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }

        public void setViewDataBinding(ViewDataBinding viewDataBinding) {
            this.viewDataBinding = viewDataBinding;
        }
    }
}
