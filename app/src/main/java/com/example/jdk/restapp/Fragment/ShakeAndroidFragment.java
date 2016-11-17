package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.HttpUtils.RequestData;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.MyDecoration;
import com.example.jdk.restapp.Utils.SnackBarUtils;

/**
 * Created by JDK on 2016/8/4.
 */
public class ShakeAndroidFragment extends BaseFragment{
    static Context mContext;
    public ShakeAndroidFragment() {
        super(R.layout.fragment_shake_android);
    }
    public static ShakeAndroidFragment newInstance(Context context){
        ShakeAndroidFragment androidFragment=new ShakeAndroidFragment();
        mContext = context;
        return androidFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void getData(int page) {
       setSubscriber(page,false);
    }
    public void setSubscriber(int page,boolean isRefresh){
        if(isRefresh)
            swipeRefreshLayout.setRefreshing(false);
        if(page>1) {
            SnackBarUtils.makeLong(getActivity().getWindow().getDecorView(), getResources().getString(R.string.shake_loadmore_footer)).danger();
            return;
        }else if(page==1){
            SnackBarUtils.makeLong(getActivity().getWindow().getDecorView(), getResources().getString(R.string.shake_refresh_header)).danger();
            return;
        }
        RequestData.getInstance(mContext).requestAndroidData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getShakeAndroidData(), getMyRecyclerView(), page, null, isFirst(), true,false);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
        getMyRecyclerView().addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        getData(0);
        InitListener();
    }

}
