package com.restrain.bean;

import com.restrain.annotation.RelevanceClass;
import lombok.Data;

/**
 * Created by user on 2018/5/23.
 */
@RelevanceClass("SignUsers")
@Data
public class SignUsersBean extends MainBean{
    private Long sid;
    private Long uid;
    private String uName;
    private String uHead;
}
