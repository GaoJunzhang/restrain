package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.bean.SignBean;
import com.restrain.model.Sign;
import com.restrain.service.CommentService;
import com.restrain.service.GreatService;
import com.restrain.service.SignService;
import com.restrain.util.BeanPage;
import com.restrain.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/2/5.
 */
@RestController
public class SignController extends BaseController{
    @Autowired
    private SignService signService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GreatService greatService;

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "密一下列表",notes = "根据activityId获取主题下所有用户密一下数据")
    @GetMapping("/signs")
    public BeanPage<SignBean> signBeans(String sessionId,@RequestParam(required = true) Long activityId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "desc") String sortType, @RequestParam(defaultValue = "createTime") String sortValue){
        Object wxSessionObj = redisUtil.get(sessionId);
        BeanPage<SignBean> beanPage = new BeanPage<SignBean>();
       //todo 检查是否有权登录
        String wxSessionStr = (String)wxSessionObj;
        String wxno = wxSessionStr.split("#")[1];
        Page<Sign> signs = signService.Signs(activityId,page,size,sortType,sortValue);
        beanPage.setTotal(signs.getTotalElements());
        beanPage.setTotalPage(signs.getTotalPages());
        List<SignBean> activityBeans = new ArrayList<SignBean>();
        for (Sign sign : signs) {
            SignBean signBean = new SignBean();
            signBean.inject(sign);
            //todo  设置评论总数和点赞总数
            signBean.setSignGreatSum(greatService.countBySignId(sign.getId())+"");
            signBean.setSignCommentSum(commentService.sumComment(sign.getId())+"");
            if (greatService.countBySignIdAndWxNo(sign.getId(),wxno)>0){
                signBean.setIsGreat("0");
            }else {
                signBean.setIsGreat("1");
            }
            activityBeans.add(signBean);
        }
        beanPage.setRows(activityBeans);
        return beanPage;
    }

    @ApiOperation(value = "密一下",notes = "保存密一下数据")
    @PostMapping(path = "/saveSign")
    public Map<String,Object> saveSign(String sessionId,Long activityId, String img, String video, String music, String content, String position, String isHide,String inviters){
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String openid = wxSessionStr.split("#")[1];
        Sign sign = signService.saveSign(null,openid,activityId,img,video,music,content,position,isHide,inviters);
        SignBean signBean = new SignBean();
        if (sign!=null){
            return rtnParam(0, ImmutableMap.of("flag",true,"msg","保存成功"));
        }
        return null;
    }
}
