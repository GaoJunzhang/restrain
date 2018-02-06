package com.restrain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "wx_users")
public class WxUsers implements Serializable{
    private long id;
    private String wxName;
    private String nickName;
    private Short sex;
    private String img;
    private String tel;
    private Short status;
    private Timestamp loginTime;
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
    @Column(name = "wx_name", nullable = true, length = 50)
    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    @Basic
    @Column(name = "nick_name", nullable = true, length = 50)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 500)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 32)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Basic
    @Column(name = "login_time", nullable = true)
    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    @Basic
    @Column(name = "wxno", nullable = false, length = 32)
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

        WxUsers wxUsers = (WxUsers) o;

        if (id != wxUsers.id) return false;
        if (wxName != null ? !wxName.equals(wxUsers.wxName) : wxUsers.wxName != null) return false;
        if (nickName != null ? !nickName.equals(wxUsers.nickName) : wxUsers.nickName != null) return false;
        if (sex != null ? !sex.equals(wxUsers.sex) : wxUsers.sex != null) return false;
        if (img != null ? !img.equals(wxUsers.img) : wxUsers.img != null) return false;
        if (tel != null ? !tel.equals(wxUsers.tel) : wxUsers.tel != null) return false;
        if (status != null ? !status.equals(wxUsers.status) : wxUsers.status != null) return false;
        if (loginTime != null ? !loginTime.equals(wxUsers.loginTime) : wxUsers.loginTime != null) return false;
        if (wxno != null ? !wxno.equals(wxUsers.wxno) : wxUsers.wxno != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (wxName != null ? wxName.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
        result = 31 * result + (wxno != null ? wxno.hashCode() : 0);
        return result;
    }
}
