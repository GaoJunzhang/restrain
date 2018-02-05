package com.restrain.controller;

import com.restrain.bean.SignBean;
import com.restrain.model.Sign;
import com.restrain.service.SignService;
import com.restrain.util.BeanPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/2/5.
 */
@RestController
public class SignController extends BaseController{
    @Autowired
    private SignService signService;

    @ApiOperation(value = "活动打开列表",notes = "根据activityId获取活动打卡列表")
    @GetMapping("/signs")
    public BeanPage<SignBean> signBeans(@RequestParam(required = true) Long activityId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "desc") String sortType, @RequestParam(defaultValue = "createTime") String sortValue){
        Page<Sign> signs = signService.Signs(activityId,page,size,sortType,sortValue);
        BeanPage<SignBean> beanPage = new BeanPage<SignBean>();
        beanPage.setTotal(signs.getTotalElements());
        beanPage.setTotalPage(signs.getTotalPages());
        List<SignBean> activityBeans = new ArrayList<SignBean>();
        for (Sign sign : signs) {
            SignBean signBean = new SignBean();
            signBean.inject(sign);
            activityBeans.add(signBean);
        }
        beanPage.setRows(activityBeans);
        return beanPage;
    }

    @ApiOperation(value = "打卡",notes = "签到打卡")
    @PostMapping(path = "/saveSign")
    public SignBean saveSign(Long wxUserId,Long activityId, String img, String video, String music, String content, String position, String isHide){
        Sign sign = signService.saveSign(null,wxUserId,activityId,img,video,music,content,position,isHide);
        SignBean signBean = new SignBean();
        if (sign!=null){
            signBean.inject(sign);
            return signBean;
        }
        return null;
    }
}
