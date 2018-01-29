package com.wx.restrain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by user on 2018/1/23.
 */
@Entity
public class Comment {
    private long id;
    private Integer ownerUserId;
    private Integer targetUserId;
    private String content;
    private Integer likeCount;
    private Timestamp createrTime;
    private Long parentId;
    private Short parentType;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "owner_user_id")
    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @Basic
    @Column(name = "target_user_id")
    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
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
    @Column(name = "like_count")
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "creater_time")
    public Timestamp getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Timestamp createrTime) {
        this.createrTime = createrTime;
    }

    @Basic
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "parent_type")
    public Short getParentType() {
        return parentType;
    }

    public void setParentType(Short parentType) {
        this.parentType = parentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (ownerUserId != null ? !ownerUserId.equals(comment.ownerUserId) : comment.ownerUserId != null) return false;
        if (targetUserId != null ? !targetUserId.equals(comment.targetUserId) : comment.targetUserId != null)
            return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (likeCount != null ? !likeCount.equals(comment.likeCount) : comment.likeCount != null) return false;
        if (createrTime != null ? !createrTime.equals(comment.createrTime) : comment.createrTime != null) return false;
        if (parentId != null ? !parentId.equals(comment.parentId) : comment.parentId != null) return false;
        if (parentType != null ? !parentType.equals(comment.parentType) : comment.parentType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ownerUserId != null ? ownerUserId.hashCode() : 0);
        result = 31 * result + (targetUserId != null ? targetUserId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (likeCount != null ? likeCount.hashCode() : 0);
        result = 31 * result + (createrTime != null ? createrTime.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (parentType != null ? parentType.hashCode() : 0);
        return result;
    }
}
