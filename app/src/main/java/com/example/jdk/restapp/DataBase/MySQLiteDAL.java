package com.example.jdk.restapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jdk.restapp.ModelData.entity.URLTableData;
import com.example.jdk.restapp.Utils.ChangeTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by JDK on 2016/8/11.
 */
public class MySQLiteDAL extends MySQLiteBase{

    public MySQLiteDAL(Context context) {
        super(context);
        MySQLiteOpenHelper.getInstance(context).setCallBackOnCreateTable(this);
    }
    @Override
    protected Object FindModel(Cursor cursor) {
        URLTableData urlTableData=new URLTableData();
        urlTableData.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
        urlTableData.setDesc(cursor.getString(cursor.getColumnIndex("URLDesc")));
        urlTableData.setUrl(cursor.getString(cursor.getColumnIndex("URLValue")));
        urlTableData.setWho(cursor.getString(cursor.getColumnIndex("URLWho")));
        Date Createdate= ChangeTimeFormat.changeStringToDate(cursor.getString(cursor.getColumnIndex("URLCreateAt")));
        urlTableData.setCreatedAt(Createdate);
        return  urlTableData;
    }

    @Override
    public void myOnCreateTable(SQLiteDatabase sqLiteDatabase) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Create TABLE WebViewTextURL(");
        stringBuilder.append("				[_id] integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE");
        stringBuilder.append(",[URLValue] varchar(20) NOT NULL UNIQUE");
        stringBuilder.append(",[URLDesc] varchar(20) NOT NULL");
        stringBuilder.append(",[URLWho] varchar(20) ");
        stringBuilder.append(",[URLCreateAt] datetime NOT NULL");
        stringBuilder.append(")");
        sqLiteDatabase.execSQL(stringBuilder.toString());
//        sqLiteDatabase.execSQL("CREATE UNIQUE INDEX unique_index_url ON WebViewTextURL(URLValue)");
    }
    public String getTable(){
        return getTables().get(0);
    }
    public Boolean insertToURLTable(URLTableData urlTableData) {
        ContentValues _ContentValues = CreatParms(urlTableData);
        Long p_NewID = getMySqLiteDatabase().replace(getTable(), null, _ContentValues);
        urlTableData.set_id(p_NewID.intValue());
        return p_NewID > 0;
    }
    public URLTableData queryFromURLTable(int _id){
        String sql="select * from "+getTable()+" where 1=1 And _id= "+_id;
        Cursor c=getMySqLiteDatabase().rawQuery(sql, null);
        return (URLTableData) FindModel(c);

    }
    public Boolean deleteFromURLTable(int _id){
        return deleteFromURLTableInBase(getTable(), _id);
    }
    public int getCountInDAL(){
        String sql="select * from "+getTable();
        return getList(sql).size();
    }
    public List<URLTableData> queryAllFromURLTableInDAL(){
        String sql="select * from "+getTable();
        return getList(sql);
    }
    public URLTableData setUrlTableData(URLTableData urlTableData){
        if(urlTableData.getUrl()==null){
            urlTableData.setUrl("该链接不存在");
        }
        if(urlTableData.getWho()==null){
            urlTableData.setWho("佚名");
        }
        if(urlTableData.getDesc()==null){
            urlTableData.setDesc("不存在");
        }
        if(urlTableData.getCreatedAt()==null){
            urlTableData.setCreatedAt(new Date());
        }
        return urlTableData;
    }
    public ContentValues CreatParms(URLTableData urlTableData) {
       setUrlTableData(urlTableData);
        ContentValues _ContentValues = new ContentValues();
        _ContentValues.put("URLValue",urlTableData.getUrl());
        _ContentValues.put("URLCreateAt",ChangeTimeFormat.changeToYearMonthDay(urlTableData.getCreatedAt()));
        _ContentValues.put("URLWho",urlTableData.getWho());
        _ContentValues.put("URLDesc",urlTableData.getDesc());
        return _ContentValues;
    }
}
