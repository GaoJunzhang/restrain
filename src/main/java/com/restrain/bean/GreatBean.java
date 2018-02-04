package com.restrain.bean;

import com.restrain.annotation.RelevanceClass;
import lombok.Data;

@RelevanceClass("Great")
@Data
public class GreatBean extends MainBean{
    private long id;
    private Long activityId;
    private Long wxUserId;

}
