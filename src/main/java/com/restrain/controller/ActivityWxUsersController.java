package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.service.ActivityUsersService;
import com.restrain.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ActivityWxUsersController extends BaseController{

    @Autowired
    private ActivityUsersService activityUsersService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "接受邀请",notes = "用户接受邀请加入圈子")
    @PostMapping("saveActivityUsers")
    public Map<String,Object> saveActivityUsers(@RequestParam(required = true,name = "sessionId") String sessionId,Long activityId){
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String openid = wxSessionStr.split("#")[1];
        activityUsersService.saveActivityWxusers(null,activityId,openid,(short)1);
        return rtnParam(0, ImmutableMap.of("flag",true,"msg","success"));
    }

}
