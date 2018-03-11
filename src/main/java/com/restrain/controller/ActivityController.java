package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.bean.ActivityBean;
import com.restrain.model.Activity;
import com.restrain.model.Sign;
import com.restrain.service.ActivityService;
import com.restrain.service.SignService;
import com.restrain.util.BeanPage;
import com.restrain.util.RedisUtil;
import com.restrain.util.StringTools;
import com.restrain.util.VTools;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = "/saveActivity")
    @ApiOperation(value = "保存密圈",notes = "发布密圈活动")
    public Map<String,Object> saveActivity(String sessionId,String name, String content, Short isTime, String startDate, String endDate, Short isSign, Short isLimit, String limits, String bgImg, String bgColor) {
        if (VTools.StringIsNullOrSpace(sessionId)){
            return rtnParam(40009,"sessionId为空");
        }
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
    @RequestMapping("/activity")
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

    @GetMapping("/activitys")
    public BeanPage<ActivityBean> activityBeanPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "desc") String sortType, @RequestParam(defaultValue = "createTime") String sortValue){
        Page<Activity> activities = activityService.activities(null,page-1,size,sortType,sortValue);
        BeanPage<ActivityBean> beanPage = new BeanPage<ActivityBean>();
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
            activityBeans.add(activityBean);
        }
        beanPage.setRows(activityBeans);
        return beanPage;
    }

    /*@RequestMapping("/picture")
    @ApiOperation(value = "上传照片",notes = "微信端上传照片")
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取文件需要上传到的路径
//        String path = request.getRealPath("/upload") + "/";
        String path = request.getSession().getServletContext().getRealPath("/upload");
        System.out.println(path);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        logger.debug("path=" + path);

        request.setCharacterEncoding("utf-8");  //设置编码
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //如果没以下两行设置的话,上传大的文件会占用很多内存，
        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
        *//**
         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到对应目录的硬盘上
         *//*
        factory.setRepository(dir);
        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
        factory.setSizeThreshold(1024 * 1024);
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            FileItem picture = null;
            for (FileItem item : list) {
                //获取表单的属性名字
                String name = item.getFieldName();
                //如果获取的表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name, value);
                    logger.debug("name=" + name + ",value=" + value);
                } else {
                    picture = item;
                }
            }

            //自定义上传图片的名字为userId.jpg
            String fileName = request.getAttribute("userId") + ".jpg";
            String destPath = path + fileName;
            logger.debug("destPath=" + destPath);

            //真正写到磁盘上
            File file = new File(destPath);
            OutputStream out = new FileOutputStream(file);
            InputStream in = picture.getInputStream();
            int length = 0;
            byte[] buf = new byte[1024];
            // in.read(buf) 每次读到的数据存放在buf 数组中
            while ((length = in.read(buf)) != -1) {
                //在buf数组中取出数据写到（输出流）磁盘上
                out.write(buf, 0, length);
            }
            in.close();
            out.close();
        } catch (FileUploadException e1) {
            logger.error("", e1);
        } catch (Exception e) {
            logger.error("", e);
        }
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        printWriter.write(JSON.toJSONString(res));
        printWriter.flush();
    }*/

    @ApiOperation(value = "我的密圈",notes = "根据用户wxno,查询我所参与和发起的密圈活动")
    @GetMapping("myActivitys")
    public Map<String,Object> myActivitys(@RequestParam(name = "sessionId",required = true) String sessionId,@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "desc") String sortType,
                                          @RequestParam(defaultValue = "createTime") String sortValue){
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String sessionKey = wxSessionStr.split("#")[0];
        Page<Activity> activities = activityService.activities(sessionKey,page,size,sortType,sortValue);
        BeanPage<ActivityBean> beanPage = new BeanPage<ActivityBean>();
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
            activityBeans.add(activityBean);
        }
        beanPage.setRows(activityBeans);
        return rtnParam(0, ImmutableMap.of("data",beanPage));
    }
}
