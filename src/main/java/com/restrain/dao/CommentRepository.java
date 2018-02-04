package com.restrain.dao;

import com.restrain.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user on 2018/1/23.
 */
public interface CommentRepository extends JpaRepository<Comments,Long> {

    @Query(value = "SELECT t1.wx_name,t.content,t.creater_time,t.parent_type,(SELECT t3.wx_name from wx_users t3 where t3.id=t.target_user_id) as targetUser from comments t left join wx_users t1 on t.owner_user_id=t1.id and t.activity_id=:activityId ORDER BY creater_time  ASC",nativeQuery = true)
    public List<Comments> findByActivityId(@Param(value = "activityId") Long activityId);

    int countByActivityId(@Param(value = "activityId") Long activityId);

}
