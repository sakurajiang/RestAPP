package com.example.jdk.restapp.Utils;

<<<<<<< HEAD
import android.app.Activity;
=======
>>>>>>> origin/master
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

<<<<<<< HEAD
import com.example.jdk.restapp.R;
=======
>>>>>>> origin/master
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
<<<<<<< HEAD
    public void share(String text,String type){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        switch (type){
            case "textvideo":
                mContext.startActivity(Intent.createChooser(intent, mContext.getResources().getString(R.string.share_text_video)));
                break;
            case "program":
                mContext.startActivity(Intent.createChooser(intent, mContext.getResources().getString(R.string.share_program)));
                break;
            default:
                SnackBarUtils.makeShort(((Activity)mContext).getWindow().getDecorView(), mContext.getResources().getString(R.string.type_worry)).danger();
        }
    }
=======
     public  void shareUrl(String url){
         Intent intent=new Intent();
         intent.setAction(Intent.ACTION_SEND);
         intent.putExtra(Intent.EXTRA_TEXT, url);
         intent.setType("text/plain");
         mContext.startActivity(Intent.createChooser(intent,"share"));
     }
>>>>>>> origin/master
    public void shareImage(Uri uri){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
<<<<<<< HEAD
        mContext.startActivity(Intent.createChooser(intent,mContext.getResources().getString(R.string.share_image)));
=======
        mContext.startActivity(Intent.createChooser(intent,"share image"));
>>>>>>> origin/master
    }

}
