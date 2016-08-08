package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.Adapter.AdapterRecyclerViewMeizi;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.ModelData.entity.Meizi;
import com.example.jdk.restapp.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by JDK on 2016/8/4.
 */
public class MeiziFragment extends BaseFragment{
    private RecyclerView myRecyclerView;
    private Context mContext;
    private List<Meizi> meiziList;
    private AdapterRecyclerViewMeizi adapterRecyclerViewMeizi;
    public MeiziFragment(Context context) {
        ;this.mContext=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meiziList=new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_meizi,container,false);
        myRecyclerView= (RecyclerView) v.findViewById(R.id.meizi_fragment);
        return  v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        s();
    }

    public void s(){
        Subscriber<MeiziData> meiziDataSubscriber=new Subscriber<MeiziData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MeiziData meiziData) {
                meiziList=meiziData.results;
                Log.i("Logcat","meiziList"+meiziList);
                adapterRecyclerViewMeizi=new AdapterRecyclerViewMeizi(mContext, meiziList);
                myRecyclerView.setAdapter(adapterRecyclerViewMeizi);
             //  adapterRecyclerViewMeizi.notifyDataSetChanged();
            }
        };
        MyObserverOn(meiziDataSubscriber);
    }
}
