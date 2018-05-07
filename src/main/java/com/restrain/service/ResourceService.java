package com.restrain.service;

import com.restrain.dao.ResourceRepository;
import com.restrain.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> resourceList(){
        return resourceRepository.findAll();
    }
}
