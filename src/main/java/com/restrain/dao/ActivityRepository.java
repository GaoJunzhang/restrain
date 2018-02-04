package com.restrain.dao;

import com.restrain.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 2018/1/23.
 */
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    Page<Activity> findAll(Specification<Activity> spec, Pageable pageable);
}
