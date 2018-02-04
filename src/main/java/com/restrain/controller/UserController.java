package com.restrain.controller;

import com.restrain.bean.WxUserBean;
import com.restrain.common.annotation.Api;
import com.restrain.common.constant.ApiConstant;
import com.restrain.model.WxUsers;
import com.restrain.service.WxUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController{

    @Autowired
    private WxUserService wxUserService;

    @ApiOperation(value = "保存用戶信息", notes = "用户进入小程序后，保存用户基础信息")
    @Api(name= ApiConstant.POST_USER)
    @RequestMapping(value = "/api/v1/user/saveWxUserInfo/{id}", method = RequestMethod.POST, produces = "application/json")
    public WxUserBean saveWxUserInfo(Long id,String wxName,String nickName, Short sex,String img,String tel, Short status){
        WxUsers wxUsers = wxUserService.saveWxusers(id,wxName,nickName,sex,img,tel,status);
        if (wxUsers!=null){
            WxUserBean wxUserBean = new WxUserBean();
            wxUserBean.inject(wxUsers);
            return wxUserBean;
        }
        return null;
    }
}
