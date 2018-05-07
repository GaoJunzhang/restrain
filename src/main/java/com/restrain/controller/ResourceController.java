package com.restrain.controller;

import com.restrain.service.ResourceService;
import com.restrain.util.RedisUtil;
import com.restrain.util.VTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/getResourceList")
    public Map<String,Object> getResourceList(String sessionId){
        if (VTools.StringIsNullOrSpace(sessionId)){
            return rtnParam(40009,"sessionId为空");
        }
        Object wxSessionObj = redisUtil.get(sessionId);
        if (null == wxSessionObj) {
            return rtnParam(40008, null);
        }
        return rtnParam(0,resourceService.resourceList());
    }
}
