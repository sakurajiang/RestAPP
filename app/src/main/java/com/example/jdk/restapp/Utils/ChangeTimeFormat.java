package com.example.jdk.restapp.Utils;

import android.databinding.BindingConversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JDK on 2016/8/11.
 */
public class ChangeTimeFormat {
    @BindingConversion
    public static String changeToYearMonthDay(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        if(date==null){
            date=new Date();
        }
        return simpleDateFormat.format(date);
    }

    public static Date changeStringToDate(String string)  {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
