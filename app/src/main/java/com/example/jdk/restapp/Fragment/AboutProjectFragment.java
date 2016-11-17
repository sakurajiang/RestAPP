package com.example.jdk.restapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.jdk.restapp.BuildConfig;
import com.example.jdk.restapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JDK on 2016/9/9.
 */
public class AboutProjectFragment extends Fragment implements View.OnClickListener,AppBarLayout.OnOffsetChangedListener{
    @Bind(R.id.app_bar)
     AppBarLayout appBarLayout;
    @Bind(R.id.tool_bar)
     Toolbar toolbar;
    @Bind(R.id.tv_app_version)
    TextView version_tv;
    @Bind(R.id.fab)
     FloatingActionButton floatingActionButton;
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    private boolean mIsTheTitleVisible = false;
    private static AboutProjectFragment aboutProjectFragment;
    private AppCompatActivity appCompatActivity;
    private View v;
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
        if (percentage >= 0.5f) {
            toolbar.setNavigationIcon(R.drawable.ic_navigation_30dp);
        }
        handleToolbarTitleVisibility(percentage);
    }


    public interface aboutProjectDrawerIconListener{
        public void aboutProjectDrawerIcon();
    }
    public static AboutProjectFragment newInstance(){
        if( aboutProjectFragment ==null){
            aboutProjectFragment =new AboutProjectFragment();
        }
        return aboutProjectFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v!=null){
            ButterKnife.bind(this,v);
            return v;
        }
         v=inflater.inflate(R.layout.fragment_about_project,container,false);
        ButterKnife.bind(this, v);
        version_tv.setText(getResources().getString(R.string.app_version) + " " + BuildConfig.VERSION_NAME);
        appBarLayout.addOnOffsetChangedListener(this);
        initFabListener();
       appCompatActivity=((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);
        return v;
    }
    // 设置渐变的动画
    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
    // 处理ToolBar的显示
    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }
    @Override
    public void onClick(View v) {
        if (getActivity() instanceof aboutProjectDrawerIconListener) {
            ((aboutProjectDrawerIconListener) getActivity()).aboutProjectDrawerIcon();
        }
    }
    public void initFabListener(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://github.com/sakurajiang/RestAPP");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

}
