package com.restrain.dao;

import com.restrain.model.ActivityUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityUsersRespority extends JpaRepository<ActivityUsers, Long> {

    public List<ActivityUsers> findByActivityIdAndWxno(@Param("activityId") Long activityId, @Param("wxno") String wxno);
}
