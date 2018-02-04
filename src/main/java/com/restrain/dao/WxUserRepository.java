package com.restrain.dao;

import com.restrain.model.WxUsers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 2018/1/23.
 */
public interface WxUserRepository extends JpaRepository<WxUsers,Long> {
}
