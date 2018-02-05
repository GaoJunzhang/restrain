package com.restrain.service;

import com.restrain.dao.GreatRepository;
import com.restrain.model.Great;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreatService {

    @Autowired
    private GreatRepository greatRepository;

    public int deleteByWxNoAndActivityId(String wxno,Long activityId){
        return greatRepository.deleteByWxNoAndActivityId(wxno,activityId);
    }

    public int countByActivityId(Long activityId){
        return greatRepository.countByActivityId(activityId);
    }

    public Great saveGreate(Long activityId, String wxno){
        Great great = new Great();
        great.setActivityId(activityId);
//        great.setWxUserId(wxUserId);
        great.setWxNo(wxno);
        return greatRepository.save(great);
    }

    public int countByActivityIdAndWxNo(Long activityId,String wxno){
        return greatRepository.countByActivityIdAndWxNo(activityId, wxno);
    }
}
