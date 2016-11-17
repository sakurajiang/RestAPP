package com.example.jdk.restapp.Adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.jdk.restapp.ModelData.entity.MyAndroid;
import com.example.jdk.restapp.R;

import java.util.List;

/**
 * Created by JDK on 2016/9/29.
 */
public abstract class RecyclerViewBaseAdapter<T> extends RecyclerView.Adapter<UniversalViewolder>  {
    List<T> mDataList;
    int mLayoutId;
    int mVariableId;
    Context mContext;
    int [] mLayoutIdArray;
    private SparseArray<onItemClickListener> mOnItemClickListeners;

    /**
     *
     * @param context 传入一个context
     * @param dataList 传入数据源
     * @param layoutIdArray  传入显示数据源的layout数组
     * @param variableId 传入layout中的variable，形式如BR.XXX
     */
    public RecyclerViewBaseAdapter(Context context,List<T> dataList,int [] layoutIdArray,int variableId){
        this.mContext=context;
        mDataList=dataList;
       mLayoutIdArray=layoutIdArray;
        mOnItemClickListeners=new SparseArray<>();
        mVariableId=variableId;
    }

    @Override
    public UniversalViewolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mLayoutIdArray.length==1){
            mLayoutId=mLayoutIdArray[0];
        }else {
            mLayoutId = getLayoutId(viewType);
        }
        return UniversalViewolder.getViewHolder(parent,mLayoutId);
    }
    @Override
    public void onBindViewHolder( UniversalViewolder holder, final int position) {
        ViewDataBinding viewDataBinding= holder.getViewDataBinding();
       final T itemModel=mDataList.get(position);
        viewDataBinding.setVariable(mVariableId, mDataList.get(position));
        viewDataBinding.executePendingBindings();
        getViewDataBinding(holder, position);
        for(int i=0;i<mOnItemClickListeners.size();++i){
            View v;
            int viewId;
            if(mOnItemClickListeners.keyAt(i)==0){
                viewId=mOnItemClickListeners.keyAt(i);
                v=holder.itemView;
            }else {
                viewId = mOnItemClickListeners.keyAt(i);
                v = holder.getChildView(viewId);
            }
            if(v==null)
                continue;
            final  onItemClickListener onItemClickListener_=mOnItemClickListeners.get(viewId);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener_ != null) {
                        onItemClickListener_.onItemClick(v,itemModel, position);
                    }
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        boolean b=mLayoutIdArray[0] == R.layout.collection_item;
        if(mLayoutIdArray[0] == R.layout.collection_item){
            return mDataList.size()==0?1:mDataList.size();
        }else {
            return mDataList.size();

        }
    }

    public interface onItemClickListener{
        public <T> void  onItemClick(View v, T t,int position);
    }
    /**
     * 为View设置监听器
     * @param ViewId 是指设置监听器控件的Id，传入0代表整个item
     * @param  mOnItemClickListener 是onItemClickListener接口.
     */
    public void setOnItemClickListener(int ViewId,onItemClickListener mOnItemClickListener){
        onItemClickListener onItemClickListener_=mOnItemClickListeners.get(ViewId);
        if(onItemClickListener_==null){
            mOnItemClickListeners.put(ViewId,mOnItemClickListener);
        }
    }
    public abstract void getViewDataBinding(UniversalViewolder  viewolder,final int position);

    /**
     * 如果需要设置多种type的布局，就需要定义这个个方法，默认的是一个type。
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(int viewType);
}
