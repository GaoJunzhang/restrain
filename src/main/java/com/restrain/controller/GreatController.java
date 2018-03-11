package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.bean.SignBean;
import com.restrain.bean.SignHeads;
import com.restrain.model.Great;
import com.restrain.model.WxUsers;
import com.restrain.service.GreatService;
import com.restrain.service.WxUserService;
import com.restrain.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GreatController extends BaseController {

    @Autowired
    private GreatService greatService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WxUserService wxUserService;

    @Value("${wx.anonymous.img}")
    private String anonymousImg;

    @ApiOperation(value = "点赞", notes = "点赞用户id，点赞活动id")
    @PostMapping(value = "saveGreat")
    public Map<String, Object> saveGreat(String sessionId, @RequestParam(required = true, value = "signId") Long signId) {
        Object wxSessionObj = redisUtil.get(sessionId);
        if (null == wxSessionObj) {
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String) wxSessionObj;
        String wxno = wxSessionStr.split("#")[1];

        greatService.updateSgin(signId);
        greatService.saveGreate(signId, wxno);
        return rtnParam(0, ImmutableMap.of("flag",2,"msg", "点赞成功"));
    }

    @ApiOperation(value = "活动点赞总数", notes = "根据activityId获取点赞总数")
    @GetMapping(value = "getGreatCount")
    public int getGreatCount(@RequestParam(required = true, value = "signId") Long signId) {
        return greatService.countBySignId(signId);
    }

    @GetMapping("/signHeads")
    public List<SignHeads> signHeads(Long signId){
        List<Great> greatList = greatService.findBySignId(signId);
//        boolean flag = false;
        String[] wxnos = null;
        if (greatList.size() > 0) {
            wxnos = new String[greatList.size()];
            for (int i = 0; i < greatList.size(); i++){
//                if (wxno.equals(greatList.get(i).getWxNo())){
//                    flag = true;
//                }
                wxnos[i] = greatList.get(i).getWxNo();
            }
        }
//        if (flag) {
        List<WxUsers> wxUsers = wxUserService.wxUsersListByIdIn(wxnos);
        List<SignHeads> signHeads = new ArrayList<SignHeads>(wxUsers.size());
        for (int j=0;j<wxUsers.size();j++){
            SignHeads signHeads1 = new SignHeads();
            signHeads1.setId((long)j);
            if (StringUtils.isEmpty(wxUsers.get(j).getImg())){
                signHeads1.setLog(anonymousImg);
            }else {
                signHeads1.setLog(wxUsers.get(j).getImg());
            }
            signHeads.add(signHeads1);
        }
            // greatService.deleteByWxNoAndActivityId(wxno,activityId);
        return signHeads;
//        }
    }
}
