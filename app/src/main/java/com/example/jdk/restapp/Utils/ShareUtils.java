package com.example.jdk.restapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.squareup.picasso.Picasso;

/**
 * Created by JDK on 2016/9/27.
 */
public class ShareUtils {
    public static Context mContext;
    public static class LoadShareUtils{
        private static final ShareUtils shareUtils=new ShareUtils();
    }
    public static ShareUtils getInstance(Context context){
        mContext=context;
        return LoadShareUtils.shareUtils;
    }
     public  void shareUrl(String url){
         Intent intent=new Intent();
         intent.setAction(Intent.ACTION_SEND);
         intent.putExtra(Intent.EXTRA_TEXT, url);
         intent.setType("text/plain");
         mContext.startActivity(Intent.createChooser(intent,"share"));
     }
    public void shareImage(Uri uri){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(Intent.createChooser(intent,"share image"));
    }

}
