package com.zeng.toolbar.detail;

/**
 * Created by Administrator on 2017/1/31 0031.
 */
public class Partner
{
    private String name;//伙伴名称
    private int imageId;//伙伴对应头像的资源ID
    private  int profileId;//人物简介的资源ID

    public Partner(String name,int imageId,int profileId)
    {
        this.name = name;
        this.imageId = imageId;
        this.profileId = profileId;
    }

    public String getName()
    {
        return name;
    }

    public int getImageId()
    {
        return imageId;
    }

    public int getProfileId()
    {
        return profileId;
    }
}
