package com.wx.restrain.controller;

import com.wx.restrain.bean.ActivityBean;
import com.wx.restrain.model.Activity;
import com.wx.restrain.service.ActivityService;
import com.wx.restrain.util.BeanPage;
import com.wx.restrain.util.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/activitys")
    public BeanPage<ActivityBean> activityBeanPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "desc") String sortType, @RequestParam(defaultValue = "createTime") String sortValue){
        Page<Activity> activities = activityService.activities(page,size,sortType,sortValue);
        BeanPage<ActivityBean> beanPage = new BeanPage<ActivityBean>();
        beanPage.setTotal(activities.getTotalElements());
        beanPage.setTotalPage(activities.getTotalPages());
        List<ActivityBean> activityBeans = new ArrayList<ActivityBean>();
        for (Activity activity : activities) {
            ActivityBean ActivityBean = new ActivityBean();
            ActivityBean.inject(activity);
            activityBeans.add(ActivityBean);
        }
        beanPage.setRows(activityBeans);
        return beanPage;
    }
}
