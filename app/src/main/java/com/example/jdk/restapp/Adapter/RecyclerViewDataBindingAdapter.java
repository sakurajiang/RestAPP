package com.example.jdk.restapp.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.Date;
import java.util.List;

/**
 * Created by JDK on 2016/8/10.
 */
public class RecyclerViewDataBindingAdapter extends RecyclerView.Adapter<RecyclerViewDataBindingAdapter.MyViewHolder> {
    private List<Base> myList;
    private List<Base> addList;
    int selectCategory;
    private Context mContext;
    private MyViewHolder myViewHolder;
    private MeiziItemBinding meiziItemBinding;
    private CollectionItemBinding collectionItemBinding;
    private AndroidItemBinding androidItemBinding;
    private FrontItemBinding frontItemBinding;
    private recyclerViewDataBindingItemOnClickListener myRecyclerViewDataBindingItemOnClick;
    private recyclerViewDoubleDataBindingItemOnClickListener myRecyclerViewDoubleDataBindingItemOnClickListener;
    public interface recyclerViewDataBindingItemOnClickListener{
        public void recyclerViewDataBindingItemOnClick(String url,String desc,String who,Date CreateAt,String type,int position);
    }
    //这个监听器的作用是当数据是经过两个RxJava得到的时候使用到的监听器
    //这个监听器其实就是当多个RecyclerView共用一个接口时，其中有的RecyclerView所需要的数据由于需要使用两个RxJava，所以会比别的
    //RecyclerView更晚就收到数据，所以没有别的RecyclerView创建的早，Databinding没有别的创建的早，其实就是没有别的Fragment创建的早，然后当你使用接口不为空
    //去判断的时候就会出现各种问题，有的是不能点击，还有的就是奇数个能点击，这都是因为setListener发生的时间不同，所以另外写一个
    //接口来设置监听，这样可以避免在相应的DataBinding还没有创建的时候就开始监听。
    public interface recyclerViewDoubleDataBindingItemOnClickListener{
        public void  recyclerViewDoubleDataBindingItemOnClick(String url,String desc);
    }
    public void setRecyclerViewItemOnClickListener(recyclerViewDataBindingItemOnClickListener myRecyclerViewDataBindingItemOnClick){
        this.myRecyclerViewDataBindingItemOnClick=myRecyclerViewDataBindingItemOnClick;
    }
    public void setRecyclerViewDoubleDataBindingItemOnClickListener(recyclerViewDoubleDataBindingItemOnClickListener myRecyclerViewDoubleDataBindingItemOnClickListener){
        this.myRecyclerViewDoubleDataBindingItemOnClickListener=myRecyclerViewDoubleDataBindingItemOnClickListener;
    }
    public RecyclerViewDataBindingAdapter(Context mContext, List<Base> myList, int selectCategory) {
        this.myList=myList;
        this.mContext=mContext;
        this.selectCategory=selectCategory;
    }
    public void addList(List<Base> addList){
        this.addList=addList;
    }
    public LayoutInflater getInflater(){
        return LayoutInflater.from(mContext);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (selectCategory){
            case 1:
                 v=getInflater().inflate(R.layout.android_item,parent,false);
                break;
            case 0:
                 v=getInflater().inflate(R.layout.front_item,parent,false);
                break;
            case 2:
                v = getInflater().inflate(R.layout.collection_item, parent, false);
                break;
            case 3:
                v=getInflater().inflate(R.layout.meizi_item,parent,false);
                break;
            default:
                v=getInflater().inflate(R.layout.android_item,parent,false);
                break;
        }
        myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        switch (selectCategory){
            case 1:
                 androidItemBinding= DataBindingUtil.bind(holder.itemView);
                androidItemBinding.setMyAndroid((MyAndroid) myList.get(position));
                break;
            case 0:
                 frontItemBinding=DataBindingUtil.bind(holder.itemView);
                frontItemBinding.setFront((Front) myList.get(position));
                break;
            case 2:
                if(myList.size()!=0){
                     collectionItemBinding=DataBindingUtil.bind(holder.itemView);
                    collectionItemBinding.setUrlTableData((URLTableData)myList.get(position));
                }
                break;
            case 3:
                 meiziItemBinding=DataBindingUtil.bind(holder.itemView);
                meiziItemBinding.setMeizi((Meizi) addList.get(position));
                meiziItemBinding.setVideo((Video) myList.get(position));
                break;
            default:
                androidItemBinding= DataBindingUtil.bind(holder.itemView);
                androidItemBinding.setMyAndroid((MyAndroid) myList.get(position));
                break;
        }
        //当变量的值更新的时候，binding 对象将在下个更新周期中更新,但是会有一点时间间隔
        holder.getViewDataBinding().executePendingBindings();
        //设置回调
        if(myRecyclerViewDataBindingItemOnClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (selectCategory) {
                        case 1:
                            myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((MyAndroid) myList.get(position)).url,((MyAndroid) myList.get(position)).desc,((MyAndroid) myList.get(position)).who,((MyAndroid) myList.get(position)).createdAt,((MyAndroid) myList.get(position)).type,position);
                        break;
                        case 0:
                            myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((Front) myList.get(position)).url,((Front) myList.get(position)).desc,((Front) myList.get(position)).who,((Front) myList.get(position)).createdAt,((Front) myList.get(position)).type,position);
                            break;
                        case 2:
                            if(myList.size()!=0){
                                myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((URLTableData) myList.get(position)).getUrl(), ((URLTableData) myList.get(position)).getDesc(), ((URLTableData) myList.get(position)).getWho(), ((URLTableData) myList.get(position)).getCreatedAt(), "Collection", position);
                            }
                            break;
                        default:
                            myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((MyAndroid) myList.get(position)).url, ((MyAndroid) myList.get(position)).desc, ((MyAndroid) myList.get(position)).who, ((MyAndroid) myList.get(position)).createdAt, ((MyAndroid) myList.get(position)).type, position);
                            break;
                    }
                }
            });
        }
        //myRecyclerViewDoubleDataBindingItemOnClickListener只有当meiziFragment中执行完毕后才会被创建，也就是说当 meiziItemBinding创建的时候再创建。
        if(myRecyclerViewDoubleDataBindingItemOnClickListener!=null){
            meiziItemBinding.meiziImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myRecyclerViewDoubleDataBindingItemOnClickListener.recyclerViewDoubleDataBindingItemOnClick(((Meizi) addList.get(position)).url, ((Meizi) addList.get(position)).desc);
                }
            });
            meiziItemBinding.videoItemIdRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myRecyclerViewDataBindingItemOnClick.recyclerViewDataBindingItemOnClick(((Video) myList.get(position)).url, ((Video) myList.get(position)).desc, ((Video) myList.get(position)).who, ((Video) myList.get(position)).createdAt, ((Video) myList.get(position)).type, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myList.size()==0?1:myList.size();
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

