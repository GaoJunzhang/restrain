package com.restrain.dao;

import com.restrain.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user on 2018/1/23.
 */
public interface CommentRepository extends JpaRepository<Comments,Long> {

//    @Query(value = "SELECT t1.wx_name,t.content,t.creater_time,t.parent_type from comments t left join wx_users t1 on t.owner_user_id=t1.id and t.activity_id=:activityId ",nativeQuery = true)
    public List<Comments> findBySignId(@Param("signId") Long signId);

    int countBySignId(@Param(value = "signId") Long signId);

}
