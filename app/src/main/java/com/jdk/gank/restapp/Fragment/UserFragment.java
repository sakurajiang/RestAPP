package com.jdk.gank.restapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdk.gank.restapp.R;

/**
 * Created by JDK on 2016/8/13.
 */
public class UserFragment extends Fragment implements View.OnClickListener{
    private TextView collect_tv;
    private collectionInUserFragmentOnClick myCollectionInUserFragmentOnClick;
    public interface collectionInUserFragmentOnClick{
        public void collectionOnClick();
    }
    public void setCollectionInUserFragmentOnClick(collectionInUserFragmentOnClick myCollectionInUserFragmentOnClick){
        this.myCollectionInUserFragmentOnClick=myCollectionInUserFragmentOnClick;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_user,container,false);
        collect_tv= (TextView) v.findViewById(R.id.collection_tv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        collect_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(myCollectionInUserFragmentOnClick!=null){
            myCollectionInUserFragmentOnClick.collectionOnClick();
        }
    }
}
