package com.restrain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "great")
public class Great implements Serializable{
    private long id;
    private Long signId;
    private Long wxUserId;
    private String wxNo;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sign_id", nullable = true)
    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    @Basic
    @Column(name = "wx_user_id", nullable = true)
    public Long getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(Long wxUserId) {
        this.wxUserId = wxUserId;
    }

    @Basic
    @Column(name = "wx_no", nullable = true, length = 32)
    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Great great = (Great) o;

        if (id != great.id) return false;
        if (signId != null ? !signId.equals(great.signId) : great.signId != null) return false;
        if (wxUserId != null ? !wxUserId.equals(great.wxUserId) : great.wxUserId != null) return false;
        if (wxNo != null ? !wxNo.equals(great.wxNo) : great.wxNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (signId != null ? signId.hashCode() : 0);
        result = 31 * result + (wxUserId != null ? wxUserId.hashCode() : 0);
        result = 31 * result + (wxNo != null ? wxNo.hashCode() : 0);
        return result;
    }
}
