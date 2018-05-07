package com.restrain.dao;

import com.restrain.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource,Integer> {
    public List<Resource> findAll();
}
