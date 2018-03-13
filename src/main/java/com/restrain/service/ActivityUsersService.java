package com.restrain.service;

import com.restrain.dao.ActivityUsersRespority;
import com.restrain.model.ActivityUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ActivityUsersService {
    @Autowired
    private ActivityUsersRespority activityUsersRespority;

    public ActivityUsers saveActivityWxusers(Long id, Long activityId, String wxno, Short isLogin){

        ActivityUsers activityWxUsers = null;
        if (id == null){
            activityWxUsers = new ActivityUsers();
            activityWxUsers.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
        activityWxUsers.setActivityId(activityId);
        activityWxUsers.setWxno(wxno);
        activityWxUsers.setIsLogin(isLogin);
        return activityUsersRespority.save(activityWxUsers);
    }

    public List<ActivityUsers> findByActivityIdAndWxno(Long activityId,String wxno ){
        return activityUsersRespority.findByActivityIdAndWxno(activityId,wxno);
    }

    public List<ActivityUsers> findByActivityId (Long activityId){
        return activityUsersRespority.findByActivityId(activityId);
    }
}
