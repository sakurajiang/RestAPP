package com.example.jdk.restapp.Utils;

<<<<<<< HEAD
import android.databinding.BindingConversion;

=======
>>>>>>> origin/master
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JDK on 2016/8/11.
 */
public class ChangeTimeFormat {
<<<<<<< HEAD
    @BindingConversion
=======
>>>>>>> origin/master
    public static String changeToYearMonthDay(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
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
