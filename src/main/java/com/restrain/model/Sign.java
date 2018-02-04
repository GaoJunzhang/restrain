package com.restrain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by user on 2018/1/30.
 */
@Entity
public class Sign {
    private long id;
    private Integer activityId;
    private Timestamp createTime;
    private String img;
    private String video;
    private String music;
    private String content;
    private String position;
    private String isHide;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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
    @Column(name = "music")
    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
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
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "is_hide")
    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sign sign = (Sign) o;

        if (id != sign.id) return false;
        if (activityId != null ? !activityId.equals(sign.activityId) : sign.activityId != null) return false;
        if (createTime != null ? !createTime.equals(sign.createTime) : sign.createTime != null) return false;
        if (img != null ? !img.equals(sign.img) : sign.img != null) return false;
        if (video != null ? !video.equals(sign.video) : sign.video != null) return false;
        if (music != null ? !music.equals(sign.music) : sign.music != null) return false;
        if (content != null ? !content.equals(sign.content) : sign.content != null) return false;
        if (position != null ? !position.equals(sign.position) : sign.position != null) return false;
        if (isHide != null ? !isHide.equals(sign.isHide) : sign.isHide != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (music != null ? music.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (isHide != null ? isHide.hashCode() : 0);
        return result;
    }
}
