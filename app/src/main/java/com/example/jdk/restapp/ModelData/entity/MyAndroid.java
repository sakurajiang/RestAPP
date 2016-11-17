package com.example.jdk.restapp.ModelData.entity;

import java.util.Date;

/**
 * Created by JDK on 2016/8/7.
 */
public class MyAndroid extends Base{
    public String type;//干货类型，如Android，iOS，福利等
    public String url;//链接地址
    public String who;//作者
    public String desc;//干货内容的描述
    public boolean isCollected;
    public Date createdAt;
    public Date updatedAt;
    public Date publishedAt;
}
