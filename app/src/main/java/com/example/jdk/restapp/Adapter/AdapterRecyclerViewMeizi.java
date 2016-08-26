package com.example.jdk.restapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.ModelData.entity.Video;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.ChangeTimeFormat;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

/**
 * Created by JDK on 2016/8/4.
 */
public class AdapterRecyclerViewMeizi extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private MyViewHolder myViewHolder;
    private List<Meizi> meiziList;
    private List<Video> videoList;
    private recyclerViewMeiziItemOnClickListener myRecyclerViewMeiziItemOnClickListener;
    public AdapterRecyclerViewMeizi(Context context,List<Meizi> meiziList,List<Video> videoList) {
        this.mContext=context;
        this.meiziList=meiziList;
<<<<<<< HEAD
        this.videoList=videoList;
    }
    public interface recyclerViewMeiziItemOnClickListener{
        public void recyclerViewItemTextOnClick(String url,String desc,String who,Date CreateAt,String type,int position);
        public void recyclerViewItemImageOnClick(String url,String desc);
    }
    public void setRecyclerViewMeiziItemOnClickListener(recyclerViewMeiziItemOnClickListener myRecyclerViewMeiziItemOnClickListener){
        this.myRecyclerViewMeiziItemOnClickListener=myRecyclerViewMeiziItemOnClickListener;
=======
        Log.i("Logcat","meizi"+meiziList.size());
        Log.i("Logcat","Adapter");
>>>>>>> origin/master
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
        View v=getInflater().inflate(R.layout.meizi_item_older,parent,false);
        myViewHolder=new MyViewHolder(v);
        Log.i("Logcat","onCreate");
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MyViewHolder) {
<<<<<<< HEAD
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            ((MyViewHolder) holder).meiziImage.setBackgroundColor(Color.argb(204, red, green, blue));
            Picasso.with(mContext).load(meiziList.get(position).url).placeholder(R.mipmap.account_book_default).error(R.mipmap.ic_launcher).resize(1000,1000).centerCrop().into(((MyViewHolder) holder).meiziImage);
            ((MyViewHolder) holder).videoItemDesc.setText(videoList.get(position).desc);
            ((MyViewHolder) holder).videoItemCreateTime.setText(ChangeTimeFormat.changeToYearMonthDay(videoList.get(position).createdAt));
            ((MyViewHolder) holder).videoItemWho.setText(videoList.get(position).who);
=======
            Log.i("Logcat","on"+meiziList.get(position).url);
            Picasso.with(mContext).load(meiziList.get(position).url).into(((MyViewHolder)holder).meiziImage);
>>>>>>> origin/master
        }
        ((MyViewHolder) holder).videoItemRelativeLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (myRecyclerViewMeiziItemOnClickListener != null) {
                   myRecyclerViewMeiziItemOnClickListener.recyclerViewItemTextOnClick(videoList.get(position).url,videoList.get(position).desc,videoList.get(position).who,videoList.get(position).createdAt,videoList.get(position).type,position);
               }
           }
       });
        ((MyViewHolder) holder).meiziImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myRecyclerViewMeiziItemOnClickListener!=null){
                    myRecyclerViewMeiziItemOnClickListener.recyclerViewItemImageOnClick(meiziList.get(position).url,meiziList.get(position).desc);
                }
            }
        });
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
        TextView videoItemDesc;
        TextView videoItemWho;
        TextView videoItemCreateTime;
        RelativeLayout videoItemRelativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i("Logcat", "MyViewHolder");
            meiziImage= (ImageView) itemView.findViewById(R.id.meizi_imageView);
            videoItemRelativeLayout= (RelativeLayout) itemView.findViewById(R.id.video_item_id_rl);
            videoItemDesc= (TextView) itemView.findViewById(R.id.video_item_id_desc_tv);
            videoItemWho= (TextView) itemView.findViewById(R.id.video_item_id_who_tv);
            videoItemCreateTime= (TextView) itemView.findViewById(R.id.video_item_id_createtime);
        }

    }
    class MyEmptyViewHolder extends RecyclerView.ViewHolder{

        public MyEmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
