package com.wx.restrain.controller;

import com.wx.restrain.util.HttpUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/2/1.
 */
@RestController
public class UserController extends BaseController {
    @Value("${weixin.app_id}") // spring配置文件配置了appID
    private String appId;

    @Value("${weixin.app_secret}") // spring配置文件配置了secret
    private String secret;

    @RequestMapping("/openId")
    @ResponseBody
    public Map<String, Object> openId(String code) { // 小程序端获取的CODE
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        try {
            boolean check = (StringUtils.isEmpty(code)) ? true : false;
            if (check) {
                throw new Exception("参数异常");
            }
            StringBuilder urlPath = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session"); // 微信提供的API，这里最好也放在配置文件
            urlPath.append(String.format("?appid=%s", appId));
            urlPath.append(String.format("&secret=%s", secret));
            urlPath.append(String.format("&js_code=%s", code));
            urlPath.append(String.format("&grant_type=%s", "authorization_code")); // 固定值
            String data = HttpUtils.doPost(urlPath.toString(),null); // java的网络请求，这里是我自己封装的一个工具包，返回字符串
            System.out.println("请求结果：" + data);
            String openId = new JSONObject(data).getString("openid");
            System.out.println("获得openId: " + openId);
            result.put("openId", openId);
        } catch (Exception e) {
            result.put("code", 1);
            result.put("remark", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

   /* *//**
     * 解密用户敏感数据
     * @param encryptedData 明文
     * @param iv            加密算法的初始向量
     * @param sessionId     会话ID
     * @return
     *//*
    @Api(name = ApiConstant.WX_DECODE_USERINFO)
    @RequestMapping(value = "/api/v1/wx/decodeUserInfo", method = RequestMethod.GET, produces = "application/json")
    public Map<String,Object> decodeUserInfo(@RequestParam(required = true,value = "encryptedData")String encryptedData,
                                             @RequestParam(required = true,value = "iv")String iv,
                                             @RequestParam(required = true,value = "sessionId")String sessionId){

        //从缓存中获取session_key
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String sessionKey = wxSessionStr.split("#")[0];

        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                String userInfo = new String(resultByte, "UTF-8");
                return rtnParam(0, userInfo);
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtnParam(50021, null);
    }*/
   /* public byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    @RequestMapping(value="/getSessionId")
    @ResponseBody
    @ApiOperation(value = "获取session",notes = "根据sessionId获取session")
    public String getSessionId(HttpServletRequest request){

        Object o = request.getSession().getAttribute("springboot");
        if(o == null){
            o = "spring boot 牛逼了!!!有端口"+request.getLocalPort()+"生成";
            request.getSession().setAttribute("springboot", o);
        }

        return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
    }
}
