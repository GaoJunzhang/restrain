package com.wx.restrain.bean;

import com.wx.restrain.annotation.RelevanceClass;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by user on 2018/1/23.
 */
@RelevanceClass("Activity")
public class ActivityBean extends MainBean {
    private long id;
    private String name;
    private String createrWxId;
    private Timestamp createTime;
    private String content;
    private Short isTime;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private Short isSign;
    private Short style;
    private String limits;
    private String img;
    private String video;
    private Integer likeCount;
    private String music;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreaterWxId() {
        return createrWxId;
    }

    public void setCreaterWxId(String createrWxId) {
        this.createrWxId = createrWxId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getIsTime() {
        return isTime;
    }

    public void setIsTime(Short isTime) {
        this.isTime = isTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Short getIsSign() {
        return isSign;
    }

    public void setIsSign(Short isSign) {
        this.isSign = isSign;
    }

    public Short getStyle() {
        return style;
    }

    public void setStyle(Short style) {
        this.style = style;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
