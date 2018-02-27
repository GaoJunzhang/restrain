package com.restrain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "activity")
public class Activity implements Serializable{
    private long id;
    private String name;
    private String createrWxId;
    private Timestamp createTime;
    private String content;
    private Short isTime;
    private Date startDate;
    private Date endDate;
    private Short isSign;
    private Short isLimit;
    private String limits;
    private String img;
    private String video;
    private String music;
    private Integer likeCount;
    private String bgImg;
    private String bgColor;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "creater_wx_id", nullable = true, length = 32)
    public String getCreaterWxId() {
        return createrWxId;
    }

    public void setCreaterWxId(String createrWxId) {
        this.createrWxId = createrWxId;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "is_time", nullable = true)
    public Short getIsTime() {
        return isTime;
    }

    public void setIsTime(Short isTime) {
        this.isTime = isTime;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "is_sign", nullable = true)
    public Short getIsSign() {
        return isSign;
    }

    public void setIsSign(Short isSign) {
        this.isSign = isSign;
    }

    @Basic
    @Column(name = "is_limit", nullable = true)
    public Short getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Short isLimit) {
        this.isLimit = isLimit;
    }

    @Basic
    @Column(name = "limits", nullable = true, length = -1)
    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 100)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "video", nullable = true, length = 100)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "music", nullable = true, length = 100)
    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @Basic
    @Column(name = "like_count", nullable = true)
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "bg_img", nullable = true, length = 255)
    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    @Basic
    @Column(name = "bg_color", nullable = true, length = 255)
    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
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
        if (isLimit != null ? !isLimit.equals(activity.isLimit) : activity.isLimit != null) return false;
        if (limits != null ? !limits.equals(activity.limits) : activity.limits != null) return false;
        if (img != null ? !img.equals(activity.img) : activity.img != null) return false;
        if (video != null ? !video.equals(activity.video) : activity.video != null) return false;
        if (music != null ? !music.equals(activity.music) : activity.music != null) return false;
        if (likeCount != null ? !likeCount.equals(activity.likeCount) : activity.likeCount != null) return false;
        if (bgImg != null ? !bgImg.equals(activity.bgImg) : activity.bgImg != null) return false;
        if (bgColor != null ? !bgColor.equals(activity.bgColor) : activity.bgColor != null) return false;

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
        result = 31 * result + (isLimit != null ? isLimit.hashCode() : 0);
        result = 31 * result + (limits != null ? limits.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (music != null ? music.hashCode() : 0);
        result = 31 * result + (likeCount != null ? likeCount.hashCode() : 0);
        result = 31 * result + (bgImg != null ? bgImg.hashCode() : 0);
        result = 31 * result + (bgColor != null ? bgColor.hashCode() : 0);
        return result;
    }
}
