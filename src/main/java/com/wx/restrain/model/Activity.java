package com.wx.restrain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by user on 2018/1/23.
 */
@Entity
public class Activity {
    private long id;
    private String name;
    private String createrWxId;
    private Timestamp createTime;
    private String content;
    private Short isTime;
    private Date startDate;
    private Date endDate;
    private Short isSign;
    private Short style;
    private String limits;
    private String img;
    private String video;
    private Integer likeCount;
    private String music;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "creater_wx_id")
    public String getCreaterWxId() {
        return createrWxId;
    }

    public void setCreaterWxId(String createrWxId) {
        this.createrWxId = createrWxId;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "is_time")
    public Short getIsTime() {
        return isTime;
    }

    public void setIsTime(Short isTime) {
        this.isTime = isTime;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "is_sign")
    public Short getIsSign() {
        return isSign;
    }

    public void setIsSign(Short isSign) {
        this.isSign = isSign;
    }

    @Basic
    @Column(name = "style")
    public Short getStyle() {
        return style;
    }

    public void setStyle(Short style) {
        this.style = style;
    }

    @Basic
    @Column(name = "limits")
    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "video")
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "like_count")
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "music")
    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (id != activity.id) return false;
        if (name != null ? !name.equals(activity.name) : activity.name != null) return false;
        if (createrWxId != null ? !createrWxId.equals(activity.createrWxId) : activity.createrWxId != null)
            return false;
        if (createTime != null ? !createTime.equals(activity.createTime) : activity.createTime != null) return false;
        if (content != null ? !content.equals(activity.content) : activity.content != null) return false;
        if (isTime != null ? !isTime.equals(activity.isTime) : activity.isTime != null) return false;
        if (startDate != null ? !startDate.equals(activity.startDate) : activity.startDate != null) return false;
        if (endDate != null ? !endDate.equals(activity.endDate) : activity.endDate != null) return false;
        if (isSign != null ? !isSign.equals(activity.isSign) : activity.isSign != null) return false;
        if (style != null ? !style.equals(activity.style) : activity.style != null) return false;
        if (limits != null ? !limits.equals(activity.limits) : activity.limits != null) return false;
        if (img != null ? !img.equals(activity.img) : activity.img != null) return false;
        if (video != null ? !video.equals(activity.video) : activity.video != null) return false;
        if (likeCount != null ? !likeCount.equals(activity.likeCount) : activity.likeCount != null) return false;
        if (music != null ? !music.equals(activity.music) : activity.music != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createrWxId != null ? createrWxId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (isTime != null ? isTime.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (isSign != null ? isSign.hashCode() : 0);
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (limits != null ? limits.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (likeCount != null ? likeCount.hashCode() : 0);
        result = 31 * result + (music != null ? music.hashCode() : 0);
        return result;
    }
}
