package com.restrain.bean;

import com.restrain.annotation.RelevanceClass;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/1/23.
 */
@RelevanceClass("Activity")
@Data
public class ActivityBean extends MainBean {
    private long id;
    private String name;
    private String createrWxId;
    private Timestamp createTime;
    private String content;
    private Short isTime;
    private Date startDate;
    private Date endDate;
    private Short isSign;
    private Short isLimit;
    private String limits;
    private String img;
    private String video;
    private String music;
    private Integer likeCount;
    private String bgImg;
    private String bgColor;

    private Integer activityFlagCount;

    private Integer activityGreatCoun;

    private String activityDescribe;
    private List<SignHeads> signHeads;
}
