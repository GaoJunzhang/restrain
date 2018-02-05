package com.restrain.dao;

import com.restrain.model.Sign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 2018/1/23.
 */
public interface SignRepository extends JpaRepository<Sign,Long> {

    Page<Sign> findAll(Specification<Sign> spec, Pageable pageable);
}
