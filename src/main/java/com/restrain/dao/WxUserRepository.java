package com.restrain.dao;

import com.restrain.model.WxUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user on 2018/1/23.
 */
public interface WxUserRepository extends JpaRepository<WxUsers,Long> {

    public List<WxUsers> findByIdIn(@Param("ids") Long[] ids);

    public List<WxUsers> findByWxno(@Param("wxno") String wxno);

    public List<WxUsers> findByWxnoIn(@Param("wxnos") String[] wxnos);

//    @Query(value = "SELECT t.*,t1.is_login from wx_users t , activity_users t1 where t.wxno=t1.wxno and t1.activity_id=:activityId",nativeQuery = true)
    @Query(value = "SELECT * from wx_users t where t.wxno in (SELECT wxno from activity_users t1 where t1.activity_id=:activityId)",nativeQuery = true)
    public List<WxUsers> findByActivityId(@Param("activityId") Long activityId);



}
