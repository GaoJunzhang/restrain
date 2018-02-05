package com.restrain.controller;

import com.restrain.common.annotation.Api;
import com.restrain.common.constant.ApiConstant;
import com.restrain.model.WxUsers;
import com.restrain.service.WxUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController extends BaseController{

    @Autowired
    private WxUserService wxUserService;

    @ApiOperation(value = "保存用戶信息", notes = "用户进入小程序后，保存用户基础信息")
    @Api(name= ApiConstant.POST_USER)
    @RequestMapping(value = "saveWxUserInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String,Object> saveWxUserInfo(Long id, @RequestParam(name = "name",required = true) String wxName, String nickName, Short sex, String headImg, String tel, Short status, String wxno){
        Map<String,Object> map = new HashMap<>();
        if (wxUserService.findByOpenid(wxno).size()>0){
            map.put("msg","已存在");
        }else {
            WxUsers wxUsers = wxUserService.saveWxusers(id,wxName,nickName,sex,headImg,tel,status,wxno);
            if (wxUsers!=null){
                /*WxUserBean wxUserBean = new WxUserBean();
                wxUserBean.inject(wxUsers);*/
                map.put("msg","保存成功");
            }else {
                map.put("msg","失败");
            }
        }
        return map;
    }
}
