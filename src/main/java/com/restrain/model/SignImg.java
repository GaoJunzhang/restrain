package com.restrain.model;

import javax.persistence.*;

/**
 * Created by user on 2018/2/7.
 */
@Entity
@Table(name = "sign_img", schema = "restrain", catalog = "")
public class SignImg {
    private long id;
    private Long signId;
    private String imgUrl;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sign_id")
    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    @Basic
    @Column(name = "imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignImg signImg = (SignImg) o;

        if (id != signImg.id) return false;
        if (signId != null ? !signId.equals(signImg.signId) : signImg.signId != null) return false;
        if (imgUrl != null ? !imgUrl.equals(signImg.imgUrl) : signImg.imgUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (signId != null ? signId.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        return result;
    }
}
