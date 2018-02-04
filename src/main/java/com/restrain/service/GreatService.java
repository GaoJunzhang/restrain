package com.restrain.service;

import com.restrain.dao.GreatRepository;
import com.restrain.model.Great;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreatService {

    @Autowired
    private GreatRepository greatRepository;

    public int deleteByWxUserIdAndActivityId(Long wxUserId,Long activityId){
        return greatRepository.deleteByWxUserIdAndActivityId(wxUserId,activityId);
    }

    public int countByActivityId(Long activityId){
        return greatRepository.countByActivityId(activityId);
    }

    public Great saveGreate(Long activityId,Long wxUserId){
        Great great = new Great();
        great.setActivityId(activityId);
        great.setWxUserId(wxUserId);
        return greatRepository.save(great);
    }

    public int countByActivityIdAndWxUserId(Long activityId,Long wxUserId){
        return greatRepository.countByActivityIdAndWxUserId(activityId,wxUserId);
    }
}
