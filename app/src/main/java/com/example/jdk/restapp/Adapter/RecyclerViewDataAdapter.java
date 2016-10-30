package com.example.jdk.restapp.Adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.jdk.restapp.ModelData.entity.Base;
import com.example.jdk.restapp.ModelData.entity.Front;
import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.ModelData.entity.MyAndroid;
import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.ModelData.entity.Video;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.databinding.AndroidItemBinding;
import com.example.jdk.restapp.databinding.CollectionItemBinding;
import com.example.jdk.restapp.databinding.FrontItemBinding;
import com.example.jdk.restapp.databinding.MeiziItemBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JDK on 2016/9/30.
 */
public class RecyclerViewDataAdapter extends RecyclerViewBaseAdapter {
    private int[] mLayoutIdArray;
    private recyclerViewDataBindingItemOnClickListener myRecyclerViewDataBindingItemOnClick;
    public RecyclerViewDataAdapter(Context context, List dataList, int[] layoutIdArray,int variableId) {
        super(context, dataList,layoutIdArray, variableId);
        mLayoutIdArray=layoutIdArray;
    }
    @Override
    public void getViewDataBinding(UniversalViewolder viewolder, final int position) {
        if (myRecyclerViewDataBindingItemOnClick != null) {
            super.setOnItemClickListener(0, new onItemClickListener() {
                @Override
                public <T> void onItemClick(View v, T b, int position) {
                    if (b instanceof  MyAndroid)
                        myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((MyAndroid) b).url, ((MyAndroid) b).desc, ((MyAndroid) b).who, ((MyAndroid) b).publishedAt, ((MyAndroid) b).type, position,0);
                   if(b instanceof Front)
                       myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((Front) b).url, ((Front) b).desc, ((Front) b).who, ((Front) b).publishedAt, ((Front) b).type, position,0);
                    if(b instanceof Video)
                        myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((Video) b).url, ((Video) b).desc, ((Video) b).who, ((Video) b).publishedAt, ((Video) b).type, position,0);
                    if(b instanceof URLTableData)
                        myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((URLTableData) b).url, ((URLTableData) b).desc, ((URLTableData) b).who, ((URLTableData) b).publishedAt, ((URLTableData) b).type, position,0);
                }

            });
            super.setOnItemClickListener(R.id.meizi_imageView, new onItemClickListener() {
                @Override
                public <T> void onItemClick(View v, T t, int position) {
                    if(t instanceof Video)
                        myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((Video) t).url, ((Video) t).desc, ((Video) t).who, ((Video) t).publishedAt, ((Video) t).type, position,R.id.meizi_imageView);
                }
            });
        }

    }

    @Override
    public int getLayoutId(int viewType) {
        return 0;
    }


    public interface recyclerViewDataBindingItemOnClickListener{
        public void recyclerViewDataBindingItemOnClick(String url,String desc,String who,Date CreateAt,String type,int position,int clickPosition);
    }
    public void setRecyclerViewItemOnClickListener(recyclerViewDataBindingItemOnClickListener myRecyclerViewDataBindingItemOnClick){
        this.myRecyclerViewDataBindingItemOnClick=myRecyclerViewDataBindingItemOnClick;
    }
}
