package com.example.jdk.restapp.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jdk.restapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/8/12.
 */
public abstract class MySQLiteBase implements MySQLiteOpenHelper.callBackOnCreateTable{
    private SQLiteDatabase mySqLiteDatabase;
    private Context mContext;
    public MySQLiteBase(Context context){
        this.mContext=context;
    }
    public SQLiteDatabase getMySqLiteDatabase(){
        if(mySqLiteDatabase==null){
            mySqLiteDatabase=MySQLiteOpenHelper.getInstance(mContext).getReadableDatabase();
        }
        return mySqLiteDatabase;
    }
    public void beginTransaction()
    {
        mySqLiteDatabase.beginTransaction();
    }

    public void setTransactionSuccessful()
    {
        mySqLiteDatabase.setTransactionSuccessful();
    }

    public void endTransaction()
    {
        mySqLiteDatabase.endTransaction();
    }
    public List<String> getTables(){
        List<String> stringList=new ArrayList<>();
        String [] collection=mContext.getResources().getStringArray(R.array.table_name);
        for (int i=0;i<collection.length;i++){
            stringList.add(collection[i]);
        }
        return stringList;
    }
    public Cursor MyRawQuery(String sql){
        return getMySqLiteDatabase().rawQuery(sql,null);
    }
    public List getList(String sql){
       Cursor cursor= MyRawQuery(sql);
       return CursorToList(cursor);
    }
    public boolean deleteFromURLTableInBase(String table_name,int _id){
        return getMySqLiteDatabase().delete(table_name,"1=1 And _id = "+_id,null)>=0;

    }
    protected abstract Object FindModel(Cursor p_Cursor);
    protected List CursorToList(Cursor p_Cursor)
    {
        List _List = new ArrayList();
        while(p_Cursor.moveToNext())
        {
            Object _Object = FindModel(p_Cursor);
            _List.add(_Object);
        }
        p_Cursor.close();
        return _List;
    }
}
