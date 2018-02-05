package com.restrain.bean;

import com.restrain.annotation.RelevanceClass;
import lombok.Data;

import java.sql.Timestamp;

@RelevanceClass("WxUsers")
@Data
public class WxUserBean extends MainBean{
    private long id;
    private String wxName;
    private String nickName;
    private Short sex;
    private String img;
    private String tel;
    private Short status;
    private Timestamp loginTime;
    private String wxno;
}
