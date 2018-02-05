package com.restrain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "sign")
public class Sign implements Serializable{
    private long id;
    private Long activityId;
    private Timestamp createTime;
    private String img;
    private String video;
    private String music;
    private String content;
    private String position;
    private String isHide;
    private Long userId;
    private String wxno;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "activity_id", nullable = true)
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 255)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "is_hide", nullable = true, length = 255)
    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "wxno", nullable = true, length = 32)
    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno;
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
        if (userId != null ? !userId.equals(sign.userId) : sign.userId != null) return false;
        if (wxno != null ? !wxno.equals(sign.wxno) : sign.wxno != null) return false;

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
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (wxno != null ? wxno.hashCode() : 0);
        return result;
    }
}
