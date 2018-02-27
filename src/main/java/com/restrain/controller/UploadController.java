package com.restrain.controller;

import com.restrain.common.annotation.Api;
import com.restrain.common.constant.ApiConstant;
import com.restrain.util.RedisUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class UploadController extends BaseController{
	//文件存储路径
	@Value("${img.local.path}")
	private String imgLocalPath;
	//文件网络访问路径
	@Value("${img.host}")
	private String imgHost;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 上传文件
	 * @param file
	 * @return
	 *//*
	@Api(name = ApiConstant.UPLOAD_IMAGE)
	@RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json")
	public Map<String,Object> upload(String sessionId,@RequestParam(required=true,value="file")MultipartFile file,String path){
		Object wxSessionObj = redisUtil.get(sessionId);
		if(null == wxSessionObj){
			return rtnParam(40008, null);
		}
		if(null == file){
			return rtnParam(40010, null);
		}
		String random = RandomStringUtils.randomAlphabetic(16);
		String fileName = file.getOriginalFilename();
		String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length());
		fileName = random+fileTyle;
		try {
			String uploadDirName = imgLocalPath.substring(imgLocalPath.lastIndexOf("/"), imgLocalPath.length());
			FileCopyUtils.copy(file.getBytes(), new File(imgLocalPath + "/", fileName));
			return rtnParam(0, ImmutableMap.of("url", imgHost + uploadDirName + "/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rtnParam(40011, null);
	}*/
	@Api(name = ApiConstant.UPLOAD_IMAGE)
	@RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json")
	public String upload(String sessionId, @RequestParam(required=true,value="file")MultipartFile file, String path){
		Object wxSessionObj = redisUtil.get(sessionId);
		if(null == wxSessionObj){
			return "errorCode:"+"40008";
		}
		if(null == file){
			return "errorCode:"+"40010";
		}
		String random = RandomStringUtils.randomAlphabetic(16);
		String fileName = file.getOriginalFilename();
		String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length());
		fileName = random+fileTyle;
		try {
			String uploadDirName = imgLocalPath.substring(imgLocalPath.lastIndexOf("/"), imgLocalPath.length());
			FileCopyUtils.copy(file.getBytes(), new File(imgLocalPath + "/", fileName));
			return imgHost + uploadDirName + "/" + fileName;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "errorCode:"+"40011";

	}


}
