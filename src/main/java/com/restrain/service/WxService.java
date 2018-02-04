package com.restrain.service;

import com.alibaba.fastjson.JSON;
import com.restrain.util.HttpRequest;
import com.restrain.util.RedisUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxService {
	@Value("${wxapp.appId}") // spring配置文件配置了appID
	private String appId;

	@Value("${wxapp.secret}") // spring配置文件配置了secret
	private String secret;

	@Value("${wxapp.grantType}")
	private String grantType;

	@Value("${wxapp.sessionHost}")
	private String sessionHost;

	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 根据小程序登录返回的code获取openid和session_key
	 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161107
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object>getWxSession(String wxCode){
		StringBuffer sb = new StringBuffer();
		sb.append("appid=").append(appId);
		sb.append("&secret=").append(secret);
		sb.append("&js_code=").append(wxCode);
		sb.append("&grant_type=").append(grantType);
		String res = HttpRequest.sendGet(sessionHost, sb.toString());
		if(res == null || res.equals("")){
			return null;
		}
		return JSON.parseObject(res, Map.class);
	}
	/**
	 * 缓存微信openId和session_key
	 * @param wxOpenId		微信用户唯一标识
	 * @param wxSessionKey	微信服务器会话密钥
	 * @param expires		会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
	 * @return
	 */
	public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires){
		String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
		StringBuffer sb = new StringBuffer();
		sb.append(wxSessionKey).append("#").append(wxOpenId);
		redisUtil.add(thirdSessionKey, expires, sb.toString());
		return thirdSessionKey;
	}
}
