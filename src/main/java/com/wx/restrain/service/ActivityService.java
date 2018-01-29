package com.wx.restrain.service;

import com.wx.restrain.dao.ActivityRepository;
import com.wx.restrain.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by user on 2018/1/23.
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity saveActivity(Long id, String name, String createrWxId, String content, Short isTime, Date startDate , Date endDate, Short isSign, Short style, String limits, String img, String video, String music){
        Activity activity = null;
        if (id==null){
            activity = new Activity();
            activity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }else {
            activity = activityRepository.findOne(id);
        }
        activity.setName(name);
        activity.setCreaterWxId(createrWxId);
        activity.setContent(content);
        activity.setIsTime(isTime);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setIsSign(isSign);
        activity.setStyle(style);
        activity.setLimits(limits);
        activity.setImg(img);
        activity.setVideo(video);
        activity.setMusic(music);
        return activityRepository.save(activity);
    }

    public Activity activity(Long id){
        return activityRepository.findOne(id);
    }
}
