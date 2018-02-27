package com.restrain.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by user on 2018/2/27.
 */
@Entity
@Table(name = "activity_users", schema = "restrain", catalog = "")
public class ActivityUsers {
    private long id;
    private Long activityId;
    private String wxno;
    private Timestamp createTime;
    private Short isLogin;

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
    @Column(name = "wxno")
    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno;
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
    @Column(name = "is_login")
    public Short getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Short isLogin) {
        this.isLogin = isLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityUsers that = (ActivityUsers) o;

        if (id != that.id) return false;
        if (activityId != null ? !activityId.equals(that.activityId) : that.activityId != null) return false;
        if (wxno != null ? !wxno.equals(that.wxno) : that.wxno != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (isLogin != null ? !isLogin.equals(that.isLogin) : that.isLogin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (wxno != null ? wxno.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (isLogin != null ? isLogin.hashCode() : 0);
        return result;
    }
}
