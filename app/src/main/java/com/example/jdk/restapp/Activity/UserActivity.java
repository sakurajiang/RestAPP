package com.example.jdk.restapp.Activity;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jdk.restapp.Fragment.CollectionFragment;
import com.example.jdk.restapp.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater;


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
        CollectionFragment collectionFragment=new CollectionFragment(UserActivity.this);
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
