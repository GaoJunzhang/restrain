package com.restrain.controller;

import com.restrain.bean.GreatBean;
import com.restrain.model.Great;
import com.restrain.service.GreatService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreatController extends BaseController{

    @Autowired
    private GreatService greatService;

    @ApiOperation(value = "点赞", notes = "点赞用户id，点赞活动id")
    @PostMapping(value = "saveGreat")
    public GreatBean saveGreat(@RequestParam(required = true,value = "wxUserId") Long wxUserId,@RequestParam(required = true,value = "activityId") Long activityId) {
        if (greatService.countByActivityIdAndWxUserId(activityId, wxUserId) > 0) {
            greatService.deleteByWxUserIdAndActivityId(wxUserId,activityId);
            return null;
        }
        Great great = greatService.saveGreate(activityId, wxUserId);
        if (great != null){
            GreatBean greatBean = new GreatBean();
            greatBean.inject(great);
            return greatBean;
        }
        return null;
    }

    @ApiOperation(value = "活动点赞总数",notes = "根据activityId获取点赞总数")
    @GetMapping(value = "getGreatCount")
    public int getGreatCount(@RequestParam(required = true,value = "activityId") Long activityId){
        return greatService.countByActivityId(activityId);
    }
}
