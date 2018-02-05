package com.restrain.bean;

import com.restrain.annotation.RelevanceClass;
import lombok.Data;

import java.sql.Timestamp;

@RelevanceClass("Comments")
@Data
public class CommentsBean extends MainBean{

    private long id;
    private String ownerUserId;
    private String targetUserId;
    private String content;
    private Timestamp createrTime;
    private Long parentId;
    private Short parentType;
    private Long activityId;
    private String ownerUserName;
    private String targetUserName;
}
