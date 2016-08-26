package com.example.jdk.restapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jdk.restapp.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JDK on 2016/8/11.
 */
public class ShowBigBitmapActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.big_Image)
    ImageView big_ImageView;
    @Bind(R.id.image_desc_tv)
    TextView desc_tv;
    @Bind(R.id.activity_showbigbitmap)
    LinearLayout linearLayout;
    private Intent intent;
    String url;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_show_big_bitmap);
        intent=getIntent();
        url=intent.getStringExtra("URL");
        desc=intent.getStringExtra("DESC");
        ButterKnife.bind(this);
        showBigBitmap(url, desc);
        linearLayout.setOnClickListener(this);
    }

    public void showBigBitmap(String url,String desc){
        desc_tv.setText(desc);
        Picasso.with(this).load(url).resize(600,1018).centerCrop().into(big_ImageView);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
