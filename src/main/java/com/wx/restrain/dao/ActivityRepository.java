package com.wx.restrain.dao;

import com.wx.restrain.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 2018/1/23.
 */
public interface ActivityRepository extends JpaRepository<Activity,Long> {
}