package com.restrain.dao;

import com.restrain.model.Great;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GreatRepository extends JpaRepository<Great,Long> {

    /*@Transactional
    int deleteByWxUserIdAndActivityId(@Param(value = "wxUserId") Long wxUserId,@Param(value = "activityId") Long activityId);*/

    int countBySignId(@Param(value = "signId") Long signId);

//    int countByActivityIdAndWxUserId(@Param(value = "activityId") Long activityId,@Param(value = "wxUserId") Long wxUserId);

    int countBySignIdAndWxNo(@Param(value = "signId") Long signId,@Param(value = "wxno") String wxno);

    @Transactional
    int deleteByWxNoAndSignId(@Param(value = "wxno") String wxno,@Param(value = "signId") Long signId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sign  set like_count=like_count+1 where id=:signId",nativeQuery = true)
    int updateSgin(@Param("signId")Long signId);

}
