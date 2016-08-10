package com.example.jdk.restapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jdk.restapp.ModelData.Constant;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by JDK on 2016/8/4.
 */
public class AdapterRecyclerViewMeizi extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private MyViewHolder myViewHolder;
    private List<Meizi> meiziList;
    public AdapterRecyclerViewMeizi(Context context,List<Meizi> meiziList) {
        this.mContext=context;
        this.meiziList=meiziList;
        Log.i("Logcat","meizi"+meiziList.size());
        Log.i("Logcat","Adapter");
    }
    public LayoutInflater getInflater(){
        return LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==-1){
            View v=getInflater().inflate(R.layout.meizi_none_item,parent,false);
            return new MyEmptyViewHolder(v);
        }
        View v=getInflater().inflate(R.layout.meizi_item,parent,false);
        myViewHolder=new MyViewHolder(v);
        Log.i("Logcat","onCreate");
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder) {
            Log.i("Logcat","on"+meiziList.get(position).url);
            Picasso.with(mContext).load(meiziList.get(position).url).into(((MyViewHolder)holder).meiziImage);
        }
       // holder.meiziImage.setImageBitmap();
        //Toast.makeText(mContext,"onBinViewHolder",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
//        Log.i("Logcat","getItemCont"+meiziList.size());
       return meiziList.size()>0?meiziList.size():1;
       // return meiziList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(meiziList.size()<=0){
            return -1;
        }
        return super.getItemViewType(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView meiziImage;
        TextView videoText;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i("Logcat", "MyViewHolder");
            meiziImage= (ImageView) itemView.findViewById(R.id.meizi_imageView);
            videoText= (TextView) itemView.findViewById(R.id.video_TextView);
        }

    }
    class MyEmptyViewHolder extends RecyclerView.ViewHolder{

        public MyEmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
