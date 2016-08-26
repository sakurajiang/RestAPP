package com.jdk.gank.restapp.Activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdk.gank.restapp.Fragment.CollectionFragment;
import com.jdk.gank.restapp.R;



/**
 * Created by JDK on 2016/8/13.
 */
public class UserActivity extends BaseActivity{
    private LinearLayout linearLayout;
    private TextView back_tv_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTitle(R.string.user_collection);
        showCollectionFragment();
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        back_tv_user= (TextView) findViewById(R.id.back_tv_user);
        back_tv_user.setTypeface(iconfont);
        initListener();
    }
    public void showCollectionFragment(){
        CollectionFragment collectionFragment=CollectionFragment.newInstance(UserActivity.this);
        addFragment(R.id.user_content_ll,collectionFragment);
    }
    public void initListener(){
        back_tv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
