package com.restrain.bean;

import com.restrain.annotation.RelevanceClass;
import lombok.Data;

import java.sql.Timestamp;

@RelevanceClass("Comment")
@Data
public class CommentBean extends MainBean{

    private long id;
    private Long ownerUserId;
    private Long targetUserId;
    private String content;
    private Timestamp createrTime;
    private Long parentId;
    private Short parentType;
    private Long activityId;
}
