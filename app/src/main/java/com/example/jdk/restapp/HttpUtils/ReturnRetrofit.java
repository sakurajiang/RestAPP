package com.example.jdk.restapp.HttpUtils;

import com.example.jdk.restapp.ModelData.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JDK on 2016/8/2.
 */
public class ReturnRetrofit {
    gankAPI myGankApi;
    Retrofit retrofit;
    static final ReturnRetrofit returnRetrofit=new ReturnRetrofit();
    static Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
    private ReturnRetrofit(){};
    private   void createRetrofit(){
                 retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public static ReturnRetrofit getInstance(){
        return returnRetrofit;
    }
    public gankAPI getMyGankApiRetrofit(){
        returnRetrofit.createRetrofit();
        myGankApi=retrofit.create(gankAPI.class);
        return myGankApi;
    }


}
