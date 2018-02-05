package com.restrain.dao;

import com.restrain.model.WxUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user on 2018/1/23.
 */
public interface WxUserRepository extends JpaRepository<WxUsers,Long> {

//    public List<WxUsers> findByIdIn(@Param("ids") Long[] ids);

    public List<WxUsers> findByWxno(@Param("wxno") String wxno);

    public List<WxUsers> findByWxnoIn(@Param("wxnos") String[] wxnos);
}
