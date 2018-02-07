package com.restrain.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restrain.annotation.RelevanceClass;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by user on 2018/2/5.
 */
@RelevanceClass("Sign")
@Data
public class SignBean extends MainBean{
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

    private String signGreatSum;

    private String signCommentSum;
    private String isGreat;

    private String inviters;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
