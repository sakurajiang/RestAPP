package com.example.jdk.restapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jdk.restapp.ModelData.Constant;

/**
 * Created by JDK on 2016/8/11.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    private  static Context mContext;
    private callBackOnCreateTable myCallBackOnCreateTable;
    private static MySQLiteOpenHelper mySQLiteOpenHelper;
    private MySQLiteOpenHelper(Context mContext) {
        super(mContext, Constant.MYDATABASE_NAME, null, Constant.MYDATABASE_VERSION);
        this.mContext=mContext;
    }
   public static MySQLiteOpenHelper getInstance(Context context){
       if(mySQLiteOpenHelper==null){
           synchronized (MySQLiteOpenHelper.class){
               mySQLiteOpenHelper=new MySQLiteOpenHelper(context);
           }
       }
       return mySQLiteOpenHelper;
   }
    public interface callBackOnCreateTable{
        public void myOnCreateTable(SQLiteDatabase sqLiteDatabase);
    }
    public void setCallBackOnCreateTable(callBackOnCreateTable myCallBackOnCreateTable){
        this.myCallBackOnCreateTable=myCallBackOnCreateTable;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        if(myCallBackOnCreateTable!=null) {
            myCallBackOnCreateTable.myOnCreateTable(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
