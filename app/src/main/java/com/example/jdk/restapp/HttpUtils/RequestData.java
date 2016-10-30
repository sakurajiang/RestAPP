package com.example.jdk.restapp.HttpUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.example.jdk.restapp.Activity.ShowBigBitmapActivity;
import com.example.jdk.restapp.Activity.ShowWebViewActivity;
import com.example.jdk.restapp.Adapter.RecyclerViewDataAdapter;
import com.example.jdk.restapp.BR;
import com.example.jdk.restapp.ModelData.FrontData;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.MyAndroidData;
import com.example.jdk.restapp.ModelData.RestVideoData;
import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.ChangeTimeFormat;
import com.example.jdk.restapp.Utils.SnackBarUtils;
import com.example.jdk.restapp.Utils.SplitUtils;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/10/8.
 */
public class RequestData {
    private static RecyclerViewDataAdapter mRecyclerViewDataAdapter;
    private static Context mContext;
    private final int meiziLayoutIdArray[]={R.layout.meizi_item};
    private final int androidLayoutIdArray[]={R.layout.android_item};
    private final int frontLayoutIdArray[]={R.layout.front_item};
    public static class StaticInsideRequestData{
        private static final RequestData requestData=new RequestData();
    }
    public static RequestData getInstance(Context context){
        mContext=context;
        return StaticInsideRequestData.requestData;
    }
    public  void requestMeiziData(final Observable o1,final Observable o2,final RecyclerView mRecyclerView,final int page, final List meiziList,final boolean isFirst ,final boolean isShake){
      final   ProgressDialog pd=new ProgressDialog(mContext);
        if(isFirst){
        pd.show();
        }
        Observable.zip(o1,o2, new Func2<MeiziData, RestVideoData, RestVideoData>() {
                    @Override
                    public RestVideoData call(MeiziData meiziData, RestVideoData restVideoData) {
                        return combineMeiziDataWithRestVideoData(meiziData, restVideoData);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RestVideoData>() {
                    @Override
                    public void onCompleted() {
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pd.dismiss();
                        SnackBarUtils.makeLong(((Activity)mContext).getWindow().getDecorView(), mContext.getResources().getString(R.string.network_error)).danger();
                    }

                    @Override
                    public void onNext(RestVideoData restVideoData) {
                        for (int i = 0; i < restVideoData.results.size(); i++) {
                            if(meiziList!=null)
                            meiziList.add(restVideoData.results.get(i));
                        }
                        if (isFirst) {
                            if(isShake){
                                setListWise(restVideoData.results,2);
                                mRecyclerViewDataAdapter = new RecyclerViewDataAdapter(mContext, restVideoData.results,meiziLayoutIdArray, BR.video);
                            }else {
                                mRecyclerViewDataAdapter = new RecyclerViewDataAdapter(mContext, meiziList,meiziLayoutIdArray, BR.video);
                            }
                            mRecyclerView.setAdapter(mRecyclerViewDataAdapter);
                        }
                        mRecyclerViewDataAdapter.setRecyclerViewItemOnClickListener(new RecyclerViewDataAdapter.recyclerViewDataBindingItemOnClickListener() {
                            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void recyclerViewDataBindingItemOnClick(String url, String desc, String who, Date PublishedAt, String type, int position, int clickPosition) {
                                if (clickPosition == 0) {
                                    Intent intent = new Intent();
                                    intent.setClass(mContext, ShowWebViewActivity.class);
                                    URLTableData urlTableData = new URLTableData(SplitUtils.splitWithComma(url)[0], who, SplitUtils.splitWithComma(desc)[0], PublishedAt);
                                    urlTableData.setType(type);
                                    urlTableData.setIsCollected(false);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("urlTableData", urlTableData);
                                    intent.putExtras(bundle);
                                    mContext.startActivity(intent);
                                } else {
                                    Intent intent = new Intent();
                                    intent.setClass(mContext, ShowBigBitmapActivity.class);
                                    intent.putExtra("URL", SplitUtils.splitWithComma(url)[1]);
                                    intent.putExtra("DESC", SplitUtils.splitWithComma(desc)[1]);
                                    intent.putExtra("PUBLISHEDAT",ChangeTimeFormat.changeToYearMonthDay(PublishedAt));
                                   ImageView imageView= (ImageView) LayoutInflater.from(mContext).inflate(R.layout.meizi_item,null).findViewById(R.id.meizi_imageView);
                                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(((Activity) mContext), imageView, mContext.getString(R.string.jump_meizi)).toBundle());
                                }
                            }
                        });
                        mRecyclerViewDataAdapter.notifyDataSetChanged();
                    }
                });

    }
    public void requestAndroidData(final Observable o1,final RecyclerView mRecyclerView,final int page, final List androidList,final boolean isFirst,final boolean isShake){
        final   ProgressDialog pd=new ProgressDialog(mContext);
        if(isFirst){
            pd.show();
        }
                o1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyAndroidData>() {
                    @Override
                    public void onCompleted() {
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pd.dismiss();
                        SnackBarUtils.makeLong(((Activity)mContext).getWindow().getDecorView(), mContext.getResources().getString(R.string.network_error)).danger();
                    }

                    @Override
                    public void onNext(MyAndroidData myAndroidData) {
                        for (int i = 0; i < myAndroidData.results.size(); i++) {
                            if(androidList!=null)
                            androidList.add(myAndroidData.results.get(i));
                        }
                        //在这里设置这个标志位是为了加载后是直接在尾部加载数据，不会造成加载后整个数据又滑到了最开始。
                        if(isFirst) {
                            if(isShake){
                                mRecyclerViewDataAdapter=new RecyclerViewDataAdapter(mContext,myAndroidData.results,androidLayoutIdArray, BR.myAndroid);
                            }else {
                                mRecyclerViewDataAdapter = new RecyclerViewDataAdapter(mContext, androidList, androidLayoutIdArray, BR.myAndroid);
                            }
                             mRecyclerView.setAdapter(mRecyclerViewDataAdapter);
                        }
                        mRecyclerViewDataAdapter.setRecyclerViewItemOnClickListener(new RecyclerViewDataAdapter.recyclerViewDataBindingItemOnClickListener() {
                            @Override
                            public void recyclerViewDataBindingItemOnClick(String url, String desc, String who, Date PublishedAt, String type, int position,int clickPosition) {
                                Intent intent = new Intent();
                                intent.setClass(mContext, ShowWebViewActivity.class);
                                URLTableData urlTableData=new URLTableData(url,who,desc,PublishedAt);
                                urlTableData.setType(type);
                                urlTableData.setIsCollected(false);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("urlTableData",urlTableData);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);
                            }
                        });
                        mRecyclerViewDataAdapter.notifyDataSetChanged();
                    }
                });

    }
    public void requestFrontData(final Observable o1,final RecyclerView mRecyclerView,final int page, final List frontList,final boolean isFirst,final boolean isShake){
        final   ProgressDialog pd=new ProgressDialog(mContext);
        if(isFirst){
            pd.show();
        }
                o1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FrontData>() {
                    @Override
                    public void onCompleted() {
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pd.dismiss();
                    }

                    @Override
                    public void onNext(FrontData frontData) {
                        for (int i = 0; i < frontData.results.size(); i++) {
                            if(frontList!=null)
                            frontList.add(frontData.results.get(i));
                        }
                        if(isFirst) {
                            if(isShake){
                                mRecyclerViewDataAdapter = new RecyclerViewDataAdapter(mContext, frontData.results, frontLayoutIdArray, BR.front);
                            }else {
                                mRecyclerViewDataAdapter = new RecyclerViewDataAdapter(mContext, frontList, frontLayoutIdArray, BR.front);
                            }
                                mRecyclerView.setAdapter(mRecyclerViewDataAdapter);
                        }
                        mRecyclerViewDataAdapter.setRecyclerViewItemOnClickListener(new RecyclerViewDataAdapter.recyclerViewDataBindingItemOnClickListener() {
                            @Override
                            public void recyclerViewDataBindingItemOnClick(String url, String desc, String who, Date PublishedAt, String type, int position,int clickPosition) {
                                Intent intent = new Intent();
                                intent.setClass(mContext, ShowWebViewActivity.class);
                                URLTableData urlTableData=new URLTableData(url,who,desc,PublishedAt);
                                urlTableData.setType(type);
                                urlTableData.setIsCollected(false);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("urlTableData",urlTableData);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);
                            }
                        });
                        mRecyclerViewDataAdapter.notifyDataSetChanged();
                    }


                });
    }

    /**
     * 使用zip将Video和Meizi数据放在一起
     * @param meiziData
     * @param restVideoData
     * @return
     */
    public RestVideoData combineMeiziDataWithRestVideoData(MeiziData meiziData, RestVideoData restVideoData){
        int size=Math.min(meiziData.results.size(),restVideoData.results.size());
        for(int i=0;i<size;i++){
            restVideoData.results.get(i).desc=restVideoData.results.get(i).desc+",./"+meiziData.results.get(i).desc;
            restVideoData.results.get(i).url=restVideoData.results.get(i).url+",./"+meiziData.results.get(i).url;
            restVideoData.results.get(i).publishedAt=meiziData.results.get(i).publishedAt;
        }
        restVideoData.results.size();
        return restVideoData;
    }
    /**
     * 设置list的规格,若有超出，则从头开始抹去，直到满足条件的size
     * @param listWise
     * @param size
     */
    public void setListWise(List listWise,int size){
            if (listWise.size() > size) {
                for (int i = 0; i < listWise.size() - size; i++) {
                    listWise.remove(i);
                }
            }
    }
}
