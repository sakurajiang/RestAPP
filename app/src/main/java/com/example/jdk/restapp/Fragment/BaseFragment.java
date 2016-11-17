package com.example.jdk.restapp.Fragment;



import android.support.v4.app.Fragment;

import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JDK on 2016/8/4.
 */
public class BaseFragment extends Fragment {
    private Subscription subscription;
    public Subscription MyObserverOn(Subscriber subscriber){
       subscription= ReturnRetrofit.getInstance().getMyGankApiRetrofit().getMeiziData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return subscription;
    }
}
