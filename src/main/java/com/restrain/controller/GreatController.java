package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.service.GreatService;
import com.restrain.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GreatController extends BaseController{

    @Autowired
    private GreatService greatService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "点赞", notes = "点赞用户id，点赞活动id")
    @PostMapping(value = "saveGreat")
    public Map<String,Object> saveGreat(String sessionId, @RequestParam(required = true,value = "signId") Long signId) {
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String wxno = wxSessionStr.split("#")[1];
        if (greatService.countBySignIdAndWxNo(signId, wxno) > 0) {
           // greatService.deleteByWxNoAndActivityId(wxno,activityId);
            return rtnParam(0, ImmutableMap.of("flag",1,"msg","用户已经点赞"));
        }
        greatService.updateSgin(signId);
        greatService.saveGreate(signId, wxno);
        return rtnParam(0, ImmutableMap.of("msg","success"));
    }

    @ApiOperation(value = "活动点赞总数",notes = "根据activityId获取点赞总数")
    @GetMapping(value = "getGreatCount")
    public int getGreatCount(@RequestParam(required = true,value = "signId") Long signId){
        return greatService.countBySignId(signId);
    }
}
