package com.example.jdk.restapp.DataBase;

import android.content.Context;

import com.example.jdk.restapp.ModelData.entity.URLTableData;

import java.util.List;

/**
 * Created by JDK on 2016/8/12.
 */
public class MySQLiteWebViewTextBussiness extends MySQLiteDAL{
    private MySQLiteDAL mySQLiteDAL;
    public MySQLiteWebViewTextBussiness(Context context) {
        super(context);
        mySQLiteDAL=new MySQLiteDAL(context);
    }
    public Boolean insertToWebViewURLTable(URLTableData urlTableData){
        mySQLiteDAL.beginTransaction();
        try {
            Boolean _Result = mySQLiteDAL.insertToURLTable(urlTableData);
            if (_Result ) {
                mySQLiteDAL.setTransactionSuccessful();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            mySQLiteDAL.endTransaction();
        }
    }
    public Boolean deleteFromWebViewTable(int _id){
        mySQLiteDAL.beginTransaction();
        try {
            Boolean _Result = mySQLiteDAL.deleteFromURLTable(_id);
            if (_Result ) {
                mySQLiteDAL.setTransactionSuccessful();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            mySQLiteDAL.endTransaction();
        }
    }
    public URLTableData queryFromTable(int _id){
       return mySQLiteDAL.queryFromURLTable(_id);
    }
    public List<URLTableData> queryAllFromTable(){
        return mySQLiteDAL.queryAllFromURLTableInDAL();
    }
    public int getCount(){
        return mySQLiteDAL.getCountInDAL();
    }


}
