package com.restrain.controller;

import com.restrain.bean.ActivityBean;
import com.restrain.model.Activity;
import com.restrain.service.ActivityService;
import com.restrain.util.BeanPage;
import com.restrain.util.StringTools;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/23.
 */
@RestController
public class ActivityController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @PostMapping(path = "/saveActivity")
    @ApiOperation(value = "保存密圈",notes = "发布密圈活动")
    public ActivityBean saveActivity(Long id, String name, String wxno, String content, Short isTime, String startDate, String endDate, Short isSign, Short style, String limits, String img, String video, String music) {
        Activity activity = activityService.saveActivity(id, name, wxno, content, isTime, StringTools.strToDate("",startDate), StringTools.strToDate("",endDate), isSign, style, limits, img, video, music);
        ActivityBean activityBean = new ActivityBean();
        if (activity != null) {
            activityBean.inject(activity);
            return activityBean;
        }
        return null;
    }
    @RequestMapping("/activity")
    public ActivityBean activity(Long id){
        Activity activity = activityService.activity(id);
        ActivityBean activityBean = new ActivityBean();
        if (activity!=null){
            activityBean.inject(activity);
            return activityBean;
        }
        return null;
    }

    @GetMapping("/activitys")
    public BeanPage<ActivityBean> activityBeanPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "desc") String sortType, @RequestParam(defaultValue = "createTime") String sortValue){
        Page<Activity> activities = activityService.activities(page,size,sortType,sortValue);
        BeanPage<ActivityBean> beanPage = new BeanPage<ActivityBean>();
        beanPage.setTotal(activities.getTotalElements());
        beanPage.setTotalPage(activities.getTotalPages());
        List<ActivityBean> activityBeans = new ArrayList<ActivityBean>();
        for (Activity activity : activities) {
            ActivityBean ActivityBean = new ActivityBean();
            ActivityBean.inject(activity);
            activityBeans.add(ActivityBean);
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
}
