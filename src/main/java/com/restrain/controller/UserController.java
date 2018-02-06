package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.common.aes.AES;
import com.restrain.common.annotation.Api;
import com.restrain.common.constant.ApiConstant;
import com.restrain.model.WxUsers;
import com.restrain.service.WxService;
import com.restrain.service.WxUserService;
import com.restrain.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController extends BaseController{

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private WxService wxService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "保存用戶信息", notes = "用户进入小程序后，保存用户基础信息")
    @Api(name= ApiConstant.POST_USER)
    @RequestMapping(value = "saveWxUserInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String,Object> saveWxUserInfo(Long id, @RequestParam(name = "name",required = true) String wxName, String nickName, Short sex, String headImg, String tel, Short status, String wxno){
        Map<String,Object> map = new HashMap<>();
        if (wxUserService.findByOpenid(wxno).size()>0){
            map.put("Msg","User Already exists");
        }else {
            WxUsers wxUsers = wxUserService.saveWxusers(id,wxName,nickName,sex,headImg,tel,status,wxno);
            if (wxUsers!=null){
                /*WxUserBean wxUserBean = new WxUserBean();
                wxUserBean.inject(wxUsers);*/
                map.put("Msg","success");
            }else {
                map.put("Msg","error");
            }
        }
        return map;
    }

    @ApiOperation(value = "登录",notes = "微信小程序登录，第一次登录保存用户数据，返回3dSessionId,redis生成session")
    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "application/json")
    public Map<String,Object> login(HttpSession session,@RequestParam(required = true,value = "code")String wxCode,
                                    @RequestParam(required = true,value = "encryptedData")String encryptedData,
                                    @RequestParam(required = true,defaultValue = "iv")String iv){
        Map<String,Object> wxSessionMap = wxService.getWxSession(wxCode);
        if(null == wxSessionMap){
            return rtnParam(50010, null);
        }
        //获取异常
        if(wxSessionMap.containsKey("errcode")){
            return rtnParam(50020, null);
        }
        String wxOpenId = (String)wxSessionMap.get("openid");
        String wxSessionKey = (String)wxSessionMap.get("session_key");
        System.out.println(wxSessionKey);
//        String thirdSession = wxService.create3rdSession(wxOpenId, wxSessionKey, (long)7200);
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        StringBuffer sb = new StringBuffer();
        sb.append(wxSessionKey).append("#").append(wxOpenId);
        session.setAttribute(thirdSessionKey,sb.toString());
        JSONObject jsonObject = null;
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(thirdSessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                jsonObject = new JSONObject(new String(resultByte, "UTF-8"));
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        short sex = Short.parseShort(jsonObject.getString("gender"));
        if (wxUserService.findByOpenid(wxOpenId).size()<=0){
            wxUserService.saveWxusers(null,jsonObject.getString("nickName"),jsonObject.getString("nickName"),sex,null,null,(short)1,jsonObject.getString("openId"));
        }
        return rtnParam(0, ImmutableMap.of("sessionId",thirdSessionKey));
    }
}
