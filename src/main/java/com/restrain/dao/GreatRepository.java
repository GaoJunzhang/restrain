package com.restrain.dao;

import com.restrain.model.Great;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GreatRepository extends JpaRepository<Great,Long> {

    @Transactional
    int deleteByWxUserIdAndActivityId(@Param(value = "wxUserId") Long wxUserId,@Param(value = "activityId") Long activityId);

    int countByActivityId(@Param(value = "activityId") Long activityId);

    int countByActivityIdAndWxUserId(@Param(value = "activityId") Long activityId,@Param(value = "wxUserId") Long wxUserId);

}
