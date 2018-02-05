package com.restrain.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by user on 2018/1/30.
 */
@Entity
@Table(name = "great")
public class Great implements Serializable{
    private long id;
    private Long activityId;
    private Long wxUserId;

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
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "wx_user_id")
    public Long getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(Long wxUserId) {
        this.wxUserId = wxUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Great great = (Great) o;

        if (id != great.id) return false;
        if (activityId != null ? !activityId.equals(great.activityId) : great.activityId != null) return false;
        if (wxUserId != null ? !wxUserId.equals(great.wxUserId) : great.wxUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (wxUserId != null ? wxUserId.hashCode() : 0);
        return result;
    }
}
