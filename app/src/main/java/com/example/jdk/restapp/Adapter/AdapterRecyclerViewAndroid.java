package com.example.jdk.restapp.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.databinding.AndroidMeiziItemBinding;


import java.util.List;

/**
 * Created by JDK on 2016/8/7.
 */
public class AdapterRecyclerViewAndroid extends RecyclerView.Adapter<AdapterRecyclerViewAndroid.MyViewHolder> {
    MeiziData meiziData;
    Context mContext;
    MyViewHolder myViewHolder;
    public AdapterRecyclerViewAndroid( MeiziData meiziData, Context mContext) {
        this.meiziData=meiziData;
        this.mContext=mContext;
    }
    public LayoutInflater getInflater(){
        return LayoutInflater.from(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=getInflater().inflate(R.layout.android_meizi_item,parent,false);
        myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("Logcat","onBindViewHolder111"+meiziData.results);
        Log.i("Logcat", "onBindViewHolder" + meiziData.results.get(position).url);
        AndroidMeiziItemBinding androidMeiziItemBinding= DataBindingUtil.bind(holder.itemView);
        androidMeiziItemBinding.setAndroidMeiziData(meiziData.results.get(position));
        holder.getViewDataBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        Log.i("Logcat", "ItemCountAndroid" + meiziData.results.size());
        return meiziData.results.size()==0?1:meiziData.results.size();
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
