package com.example.jdk.restapp.HttpUtils;

import com.example.jdk.restapp.ModelData.Constant;
import com.example.jdk.restapp.ModelData.GankData;
import com.example.jdk.restapp.ModelData.GanHuoData;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.MyAndroidData;
import com.example.jdk.restapp.ModelData.RestVideoData;
import com.example.jdk.restapp.ModelData.entity.Gank;
import com.example.jdk.restapp.ModelData.entity.Meizi;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JDK on 2016/8/2.
 */
public interface gankAPI {
    @GET("data/福利/" + Constant.MEIZI_SIZE + "/{page}")
    Observable<MeiziData> getMeiziData(@Path("page") int page);
    @GET("data/Android/" +Constant.ANDROID_SIZE+ "/{page}")
    Observable<MyAndroidData> getAndroidData(@Path("page") int page);
    @GET("data/休息视频/"+Constant.GANK_SIZE+"/{page}")
    Observable<RestVideoData> getRestVideoData(@Path("page") int page);
    //请求某天干货数据
    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getDailyData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);
    //请求不同类型干货（通用）
    @GET("data/{type}/"+ Constant.GANK_SIZE+"/{page}")
    Observable<GanHuoData> getGanHuoData(@Path("type") String type, @Path("page") int page);
}
