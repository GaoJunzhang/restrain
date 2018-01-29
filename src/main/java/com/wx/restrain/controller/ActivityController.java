package com.wx.restrain.controller;

import com.wx.restrain.bean.ActivityBean;
import com.wx.restrain.model.Activity;
import com.wx.restrain.service.ActivityService;
import com.wx.restrain.util.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2018/1/23.
 */
@RestController
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping(path = "/saveActivity")
    public ActivityBean saveActivity(Long id, String name, String createrWxId, String content, Short isTime, String startDate, String endDate, Short isSign, Short style, String limits, String img, String video, String music) {
        Activity activity = activityService.saveActivity(id, name, createrWxId, content, isTime, StringTools.strToDate("",startDate), StringTools.strToDate("",endDate), isSign, style, limits, img, video, music);
        ActivityBean activityBean = new ActivityBean();
        if (activity != null) {
            activityBean.inject(activity);
            return activityBean;
        }
        return null;
    }
    @RequestMapping("/activity")
    public ActivityBean activity(Long id){
        Activity activity = activityService.activity(id);
        ActivityBean activityBean = new ActivityBean();
        if (activity!=null){
            activityBean.inject(activity);
            return activityBean;
        }
        return null;
    }
}
