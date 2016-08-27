package com.jdk.gank.restapp.HttpUtils;

import com.jdk.gank.restapp.ModelData.Constant;
import com.jdk.gank.restapp.ModelData.FrontData;
import com.jdk.gank.restapp.ModelData.MeiziData;
import com.jdk.gank.restapp.ModelData.MyAndroidData;
import com.jdk.gank.restapp.ModelData.RestVideoData;

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
    @GET("data/前端/" +Constant.FRONT_SIZE+ "/{page}")
    Observable<FrontData> getFrontData(@Path("page") int page);
    @GET("data/休息视频/"+Constant.GANK_SIZE+"/{page}")
    Observable<RestVideoData> getRestVideoData(@Path("page") int page);

}
