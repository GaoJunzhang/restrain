package com.restrain.bean;

import com.restrain.annotation.RelevanceClass;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by user on 2018/2/5.
 */
@Data
@RelevanceClass("Sign")
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
}
