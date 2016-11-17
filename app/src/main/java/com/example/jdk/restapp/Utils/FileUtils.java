package com.example.jdk.restapp.Utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by JDK on 2016/10/13.
 */
public class FileUtils {
   private FileUtils(){
        throw new UnsupportedOperationException("cannot be instanced");
   }
    public static boolean isSDCardEnable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static Uri SaveImageToSDCard(final Bitmap bitmap,final String title){
        File appDir=new File(Environment.getExternalStorageDirectory(),"RestGank");
        if(!appDir.exists())
            appDir.mkdirs();
        String fileName=title.replace("/","-")+"-meizi.jpg";
        File file=new File(appDir,fileName);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream=new FileOutputStream(file);
            assert bitmap !=null;
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(file);
    }
}
