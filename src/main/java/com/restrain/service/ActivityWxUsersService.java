package com.restrain.service;

import com.restrain.dao.ActivityWxUsersRespority;
import com.restrain.model.ActivityWxUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ActivityWxUsersService {
    @Autowired
    private ActivityWxUsersRespority activityWxUsersRespority;

    public ActivityWxUsers saveActivityWxusers(Long id,Long activityId,String wxno,Short isLogin){

        ActivityWxUsers activityWxUsers = null;
        if (id == null){
            activityWxUsers = new ActivityWxUsers();
            activityWxUsers.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
        activityWxUsers.setActivityId(activityId);
        activityWxUsers.setWxno(wxno);
        activityWxUsers.setIsLogin(isLogin);
        return activityWxUsersRespority.save(activityWxUsers);
    }
}
