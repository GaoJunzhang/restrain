package com.restrain.controller;

import com.restrain.bean.ActivityBean;
import com.restrain.bean.SignHeads;
import com.restrain.model.Activity;
import com.restrain.model.Sign;
import com.restrain.model.WxUsers;
import com.restrain.service.ActivityService;
import com.restrain.service.ActivityUsersService;
import com.restrain.service.SignService;
import com.restrain.service.WxUserService;
import com.restrain.util.BeanPage;
import com.restrain.util.RedisUtil;
import com.restrain.util.StringTools;
import com.restrain.util.VTools;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/1/23.
 */
@RestController
public class ActivityController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SignService signService;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private ActivityUsersService activityUsersService;

    @Value("${wx.anonymous.img}")
    private String anonymousImg;

//    @PostMapping(path = "/saveActivity")
    @RequestMapping(value = "/saveActivity", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "保存密圈",notes = "发布密圈活动")
    @ResponseBody
    public Map<String,Object> saveActivity(String sessionId,String name, String content, Short isTime, String startDate, String endDate, Short isSign, Short isLimit, String limits, String bgImg, String bgColor) {

        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String sessionKey = wxSessionStr.split("#")[1];
        Activity activity = activityService.saveActivity(null, name, sessionKey, content, isTime, StringTools.strToDate("",startDate), StringTools.strToDate("",endDate), isSign, isLimit, limits, bgImg, bgColor);
//        ActivityBean activityBean = new ActivityBean();
        if (activity != null) {
            return rtnParam(0, "保存成功");
        }
        return null;
    }
    @RequestMapping(value = "/activity",method = RequestMethod.GET)
    public ActivityBean activity(Long id){
        Activity activity = activityService.activity(id);
        ActivityBean activityBean = new ActivityBean();
        if (activity!=null){
            activityBean.inject(activity);
            activityBean.setMsg("success");
            return activityBean;
        }
        return null;
    }

//    @GetMapping("/activitys")
    @RequestMapping(value = "/activitys",method = RequestMethod.GET,produces = "application/json")
    public BeanPage<ActivityBean> activityBeanPage(String sessionId,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "desc") String sortType, @RequestParam(defaultValue = "createTime") String sortValue){

        Page<Activity> activities = activityService.activities(null,page-1,size,sortType,sortValue);
        BeanPage<ActivityBean> beanPage = new BeanPage<ActivityBean>();
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            beanPage.setErrorCode("40008");
            return beanPage;
        }
        beanPage.setTotal(activities.getTotalElements());
        beanPage.setTotalPage(activities.getTotalPages());
        List<ActivityBean> activityBeans = new ArrayList<ActivityBean>();
        List<Sign> signs = null;
        int flagCount = 0;
        String content = "";
        for (Activity activity : activities) {
            flagCount = 0;
            ActivityBean activityBean = new ActivityBean();
            activityBean.inject(activity);
            //设置总flga数量
            signs = signService.findByActivityId(activity.getId());
            activityBean.setActivityFlagCount(signs.size());
            if (signs.size()>0){
                for (int i=0;i<signs.size();i++){
                    flagCount += signs.get(i).getLikeCount();
                    if (!StringUtils.isEmpty(signs.get(i).getContent())&&signs.get(i).getContent().length()>15){

                        content += signs.get(i).getContent().substring(0,15)+"...;";
                    }else {
                        content += signs.get(i).getContent()+";";
                    }
                }
            }
            activityBean.setActivityDescribe(content);
            //设置总点赞数量
            activityBean.setActivityGreatCoun(flagCount);
            //设置参与者的头像
            List<SignHeads> signHeads = new ArrayList<SignHeads>(1);
            List<WxUsers> wxUsers = wxUserService.findByOpenid(activity.getCreaterWxId());
            SignHeads signHeads1 = new SignHeads();
            if (wxUsers.size()>0){

                signHeads1.setId((long)1);
                signHeads1.setLog(wxUsers.get(0).getImg());
            }else {
                signHeads1.setId((long)1);
                signHeads1.setLog(anonymousImg);
            }
            signHeads.add(signHeads1);
            activityBean.setSignHeads(signHeads);
            activityBeans.add(activityBean);
        }
        beanPage.setRows(activityBeans);
        return beanPage;
    }


    @ApiOperation(value = "我的密圈",notes = "根据用户wxno,查询我所参与和发起的密圈活动")
//    @GetMapping("/myActivitys")
    @RequestMapping(value = "/myActivitys",method = RequestMethod.GET,produces = "application/json")
    public BeanPage<ActivityBean> myActivitys(@RequestParam(name = "sessionId",required = true) String sessionId,@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "desc") String sortType,
                                          @RequestParam(defaultValue = "createTime") String sortValue){
        Object wxSessionObj = redisUtil.get(sessionId);
        String wxSessionStr = (String)wxSessionObj;
        BeanPage<ActivityBean> beanPage = new BeanPage<ActivityBean>();
        if(null == wxSessionObj){
            beanPage.setErrorCode("40008");
            return beanPage;
        }
        String sessionKey = wxSessionStr.split("#")[1];
        Page<Activity> activities = activityService.activities(sessionKey,page-1,size,sortType,sortValue);
        beanPage.setTotal(activities.getTotalElements());
        beanPage.setTotalPage(activities.getTotalPages());
        List<ActivityBean> activityBeans = new ArrayList<ActivityBean>();
        List<Sign> signs = null;
        int flagCount = 0;
        String content = "";
        for (Activity activity : activities) {
            ActivityBean activityBean = new ActivityBean();
            activityBean.inject(activity);
            //设置总flga数量
            signs = signService.findByActivityId(activity.getId());
            activityBean.setActivityFlagCount(signs.size());
            if (signs.size()>0){
                for (int i=0;i<signs.size();i++){
                    flagCount += signs.get(i).getLikeCount();
                    if (!StringUtils.isEmpty(signs.get(i).getContent())&&signs.get(i).getContent().length()>15){

                        content += signs.get(i).getContent().substring(0,15)+"...;";
                    }else {
                        content += signs.get(i).getContent()+";";
                    }
                }
            }
            activityBean.setActivityDescribe(content);
            //设置总点赞数量
            activityBean.setActivityGreatCoun(flagCount);
            //设置头像
            List<SignHeads> signHeads = new ArrayList<SignHeads>(1);
            List<WxUsers> wxUsers = wxUserService.findByOpenid(activity.getCreaterWxId());
            SignHeads signHeads1 = new SignHeads();
            if (wxUsers.size()>0){

                signHeads1.setId((long)1);
                signHeads1.setLog(wxUsers.get(0).getImg());
            }else {
                signHeads1.setId((long)1);
                signHeads1.setLog(anonymousImg);
            }
            signHeads.add(signHeads1);
            activityBean.setSignHeads(signHeads);
            activityBeans.add(activityBean);
        }
        beanPage.setRows(activityBeans);
        return beanPage;
    }

    @GetMapping("/loadUserByActivitys")
    public Map<String,Object> loadUserByActivitys(String sessionId,Long activityId){
        if (VTools.StringIsNullOrSpace(sessionId)){
            return rtnParam(40009,"sessionId为空");
        }
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        List<WxUsers> wxUsers = wxUserService.findByActivityId(activityId);
        return rtnParam(0,wxUsers);

    }
}
