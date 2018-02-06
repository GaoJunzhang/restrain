package com.restrain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comments implements Serializable{
    private long id;
    private String ownerUserId;
    private String targetUserId;
    private String content;
    private Timestamp createrTime;
    private Long parentId;
    private Short parentType;
    private Long signId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "owner_user_id", nullable = true, length = 32)
    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @Basic
    @Column(name = "target_user_id", nullable = true, length = 32)
    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 100)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "creater_time", nullable = true)
    public Timestamp getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Timestamp createrTime) {
        this.createrTime = createrTime;
    }

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "parent_type", nullable = true)
    public Short getParentType() {
        return parentType;
    }

    public void setParentType(Short parentType) {
        this.parentType = parentType;
    }

    @Basic
    @Column(name = "sign_id", nullable = true)
    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (id != comments.id) return false;
        if (ownerUserId != null ? !ownerUserId.equals(comments.ownerUserId) : comments.ownerUserId != null)
            return false;
        if (targetUserId != null ? !targetUserId.equals(comments.targetUserId) : comments.targetUserId != null)
            return false;
        if (content != null ? !content.equals(comments.content) : comments.content != null) return false;
        if (createrTime != null ? !createrTime.equals(comments.createrTime) : comments.createrTime != null)
            return false;
        if (parentId != null ? !parentId.equals(comments.parentId) : comments.parentId != null) return false;
        if (parentType != null ? !parentType.equals(comments.parentType) : comments.parentType != null) return false;
        if (signId != null ? !signId.equals(comments.signId) : comments.signId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ownerUserId != null ? ownerUserId.hashCode() : 0);
        result = 31 * result + (targetUserId != null ? targetUserId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createrTime != null ? createrTime.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (parentType != null ? parentType.hashCode() : 0);
        result = 31 * result + (signId != null ? signId.hashCode() : 0);
        return result;
    }
}
