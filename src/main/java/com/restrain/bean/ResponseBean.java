package com.restrain.bean;

/**
 * Created by user on 2018/1/30.
 */
public class ResponseBean {
    Integer errcode;
    String errmsg;

    public ResponseBean() {

    }

    public ResponseBean(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
