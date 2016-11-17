package com.example.jdk.restapp.HttpUtils;

import com.example.jdk.restapp.ModelData.Constant;
import com.example.jdk.restapp.ModelData.FrontData;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.MyAndroidData;
import com.example.jdk.restapp.ModelData.RestVideoData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JDK on 2016/8/2.
 */
public interface gankAPI {
    @GET("data/福利/" + Constant.WATCH_MEIZI_SIZE + "/{page}")
    Observable<MeiziData> getWatchMeiziData(@Path("page") int page);
    @GET("data/Android/" +Constant.WATCH_ANDROID_SIZE+ "/{page}")
    Observable<MyAndroidData> getWatchAndroidData(@Path("page") int page);
    @GET("data/前端/" +Constant.WATCH_FRONT_SIZE+ "/{page}")
    Observable<FrontData> getWatchFrontData(@Path("page") int page);
    @GET("data/休息视频/"+Constant.WATCH_GANK_SIZE+"/{page}")
    Observable<RestVideoData> getWatchRestVideoData(@Path("page") int page);
    @GET("random/data/福利/" + Constant.SHAKE_MEIZI_SIZE )
    Observable<MeiziData> getShakeMeiziData();
    @GET("random/data/Android/" +Constant.SHAKE_ANDROID_SIZE)
    Observable<MyAndroidData> getShakeAndroidData( );
    @GET("random/data/前端/" +Constant.SHAKE_FRONT_SIZE)
    Observable<FrontData> getShakeFrontData( );
    @GET("random/data/休息视频/"+Constant.SHAKE_GANK_SIZE)
    Observable<RestVideoData> getShakeRestVideoData();

}
