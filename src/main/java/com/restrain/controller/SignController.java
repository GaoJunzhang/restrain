package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.bean.SignBean;
import com.restrain.model.Sign;
import com.restrain.model.SignImg;
import com.restrain.model.WxUsers;
import com.restrain.service.*;
import com.restrain.util.BeanPage;
import com.restrain.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SignController extends BaseController {
    @Autowired
    private SignService signService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GreatService greatService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SignImgService signImgService;

    @Autowired
    private WxUserService wxUserService;

    @Value("${wx.anonymous.img}")
    private String headImg;


    @ApiOperation(value = "密一下列表", notes = "根据activityId获取主题下所有用户密一下数据")
    @GetMapping("/signs")
    public BeanPage<SignBean> signBeans(String sessionId, @RequestParam(required = true) Long activityId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "desc") String sortType, @RequestParam(defaultValue = "createTime") String sortValue) {
        Object wxSessionObj = redisUtil.get(sessionId);
        BeanPage<SignBean> beanPage = new BeanPage<SignBean>();
        String wxSessionStr = (String) wxSessionObj;
        String wxno = wxSessionStr.split("#")[1];
        Page<Sign> signs = signService.Signs(activityId, page, size, sortType, sortValue);
        beanPage.setTotal(signs.getTotalElements());
        beanPage.setTotalPage(signs.getTotalPages());
        List<SignBean> activityBeans = new ArrayList<SignBean>();
        for (Sign sign : signs) {
            SignBean signBean = new SignBean();
            signBean.inject(sign);
            //图片集
            if (sign.getImg() != null && sign.getImg() != 0) {
                List<SignImg> signImg = signImgService.signImgsBySignId(sign.getId());

                signBean.setImgUrls(signImgService.signImgsBySignId(sign.getId()));
            }
            //昵称
            if (!StringUtils.isEmpty(sign.getWxno())) {
                List<WxUsers> wxUsers = wxUserService.findByOpenid(sign.getWxno());
                if (wxUsers.size() > 0) {
                    signBean.setNickName(wxUsers.get(0).getNickName());
                    signBean.setWxUrl(wxUsers.get(0).getImg());
                } else {
                    signBean.setNickName("密某人");
                    signBean.setWxUrl(headImg);
                }
            } else {
                signBean.setNickName("密某人");
                signBean.setWxUrl(headImg);
            }
            signBean.setSignGreatSum(greatService.countBySignId(sign.getId()) + "");
            signBean.setSignCommentSum(commentService.sumComment(sign.getId()) + "");
            if (greatService.countBySignIdAndWxNo(sign.getId(), wxno) > 0) {
                signBean.setIsGreat("0");
            } else {
                signBean.setIsGreat("1");
            }
            activityBeans.add(signBean);
        }
        beanPage.setRows(activityBeans);
        return beanPage;
    }

    @ApiOperation(value = "密一下", notes = "保存密一下数据")
    @PostMapping(path = "/saveSign")
    public Map<String, Object> saveSign(String sessionId, Long activityId, String[] img, String video, String music, String content, String position, String isHide, String inviters) {
        System.out.println("sessionId=====" + sessionId);
        Object wxSessionObj = redisUtil.get(sessionId);
        if (null == wxSessionObj) {
            return rtnParam(40008, null);
        }
        Long signImgId = null;
        Long signMaxId = signService.maxId() + 1;
        Long maxid = signImgService.maxId();
        if (img.length > 0) {
            SignImg signImg = new SignImg();
            for (int i = 0; i < img.length; i++) {
                maxid++;
                signImg = signImgService.saveSignImg(maxid, signMaxId, img[i]);
            }
            signImgId = signImg.getSignId();
        }
        String wxSessionStr = (String) wxSessionObj;
        String openid = wxSessionStr.split("#")[1];
        Sign sign = signService.saveSign(signMaxId, openid, activityId, signImgId, video, music, content, position, isHide, inviters);
        SignBean signBean = new SignBean();
        if (sign != null) {
            return rtnParam(0, ImmutableMap.of("flag", true, "msg", "保存成功"));
        }
        return null;
    }
}
