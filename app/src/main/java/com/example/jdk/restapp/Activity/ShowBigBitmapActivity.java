package com.example.jdk.restapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.SnackBarUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JDK on 2016/8/11.
 */
public class ShowBigBitmapActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.big_Image)
    ImageView big_ImageView;
    @Bind(R.id.activity_showbigbitmap)
    LinearLayout linearLayout;
    @Bind(R.id.webview_toolbar)
    Toolbar big_picture_toolbar;
    @Bind(R.id.desc_tv_showwebview)
    TextView desc_tv_big_picture;
    private Intent intent;
    String url;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big_bitmap);
        intent=getIntent();
        url=intent.getStringExtra("URL");
        desc=intent.getStringExtra("DESC");
        ButterKnife.bind(this);
        InitToolbar();
        showBigBitmap(url);
        linearLayout.setOnClickListener(this);
    }

    public void showBigBitmap(String url){
        Picasso.with(this).load(url).resize(700,1000).centerCrop().into(big_ImageView);
    }
    public void InitToolbar(){
        desc_tv_big_picture.setVisibility(View.GONE);
        big_picture_toolbar.setTitle(desc);
        setSupportActionBar(big_picture_toolbar);
        big_picture_toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        big_picture_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override
    public void onClick(View v) {
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.big_picture_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
//        RxPermissions.getInstance(getApplication())
//                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean granted) {
//                        if (granted) {
//                            if (id == R.id.action_save) {
//                                mFragment.downloadPicture(0);
//                            } else if (id == R.id.action_share) {
//                                mFragment.downloadPicture(1);
//                            }
//                        } else {
//                            Toast.makeText(getApplicationContext(), "无读写权限", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
        if (id == R.id.action_share) {
            SnackBarUtils.makeShort(getWindow().getDecorView(), getResources().getString(R.string.onClick_share)).danger();
        }else if(id==R.id.action_save){
            SnackBarUtils.makeShort(getWindow().getDecorView(),getResources().getString(R.string.onClick_save)).danger();
        }
        return super.onOptionsItemSelected(item);
    }
}
