package com.zhou.jianzhi.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.common.util.HttpClientUtils;
import com.zhou.jianzhi.common.util.JWTUtils;
import com.zhou.jianzhi.service.BaseUserService;
import com.zhou.jianzhi.entity.vo.WechatUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/app")
public class LoginController {

    @Autowired
    private BaseUserService baseUserService;

    //小程序获取本机手机号
    @RequestMapping("/getPhoneNumber")
    public AjaxResult getPhoneNumber(@RequestParam(value = "openid", required = false) String openid,
                                     @RequestParam(value = "session_key", required = false) String session_key,
                                     @RequestParam(value = "encryptedData", required = false) String encryptedData,
                                     @RequestParam(value = "iv", required = false) String iv) throws Exception {

        System.out.println("-------------------------获取手机时----------------------------------------------------------------------");
        System.out.println("session_key:" + session_key);
        System.out.println("encryptedData:" + encryptedData);
        System.out.println("openid:" + openid);
        System.out.println("--------------------------------------------------------------------------------------------------------");

        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject phoneNumber = getPhoneNumberOrRealUserInfo(session_key, encryptedData, iv);
        map.put("phoneDetail", phoneNumber);
        return AjaxResult.success(map);
    }


    //登陆授权
    @RequestMapping("/wxLogin")
    public AjaxResult doLogin(
            @RequestParam(value = "openId", required = false) String openId,
            @RequestParam(value = "avatarUrl", required = false) String avatarUrl,
            @RequestParam(value = "phone", required = false) String phone) {

        // phone ：由小程序段发生过来解析好的手机号
        // avatarUrl+openId :
        WechatUserInfoVO wechatUserInfoVO = new WechatUserInfoVO();
        wechatUserInfoVO.setOpenId(openId);  //用户openid
        wechatUserInfoVO.setAvatarUrl(avatarUrl); //用户的头像地址
        Map<String, Object> map = baseUserService.weChatLogin(phone, wechatUserInfoVO);
        return AjaxResult.success(map);
    }

    //登陆授权  -- 原始接口这里授权登陆需要解析用户信息，其实没必要解析因为解析出来的用户信息基本上不用，只用到用户头像
    /*@RequestMapping("/wxLogin")
    public AjaxResult doLogin(
                              @RequestParam(value = "session_key",required = false) String session_key,
                              @RequestParam(value = "encryptedData",required = false) String encryptedData,
                              @RequestParam(value = "iv",required = false)String iv,
                              @RequestParam(value = "phone",required = false) String phone) throws Exception {

        // pnohe ：由小程序段发生过来解析好的手机号
        // sessiokey:使用微信登陆得到的code解析出来的sessiokey
        // encryptedData与iv: 这里的encryptedData与iv与手机号获取时的不一样，他解析出来的是用户信息
        System.out.println(session_key+"--");
        System.out.println(encryptedData+"--");
        System.out.println(iv+"--");
        JSONObject userInfo = getPhoneNumberOrRealUserInfo(session_key, encryptedData, iv);
        WechatUserInfoVO wechatUserInfoVO = userInfo.toJavaObject(WechatUserInfoVO.class);
        System.out.println("---------------------------登陆时------------------------------------------------------------------------");
        System.out.println("phone:"+phone);
        System.out.println("wechatUserInfoVO:"+wechatUserInfoVO);
        System.out.println("--------------------------------------------------------------------------------------------------------");
        Map<String,Object> map = baseUserService.weChatLogin(phone,wechatUserInfoVO);

        return AjaxResult.success(map);
    }*/

    @RequestMapping("/getWxSessionKeyAndOpenIdByCode")
    public AjaxResult getWxSessionKeyAndOpenIdByCode(@RequestParam(value = "js_code", required = false) String code,
                                                     @RequestParam(value = "appid", required = false) String APPID,
                                                     @RequestParam(value = "secret", required = false) String SECRET
    ) throws IOException {
        String wxJsCodeUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                APPID, SECRET, code);
        String responseBody = HttpClientUtils.sendPost(wxJsCodeUrl);
        JSONObject result = JSONObject.parseObject(responseBody);
        return AjaxResult.success(result);
    }

    //检查token是否过期
    @RequestMapping("/cheakToken")
    public AjaxResult checkToken(
            @RequestParam(value = "empId", required = false) String empId,
            @RequestParam(value = "token", required = false) String token) {

        if (JWTUtils.isExpire(token)) {
            //过期
            String newtoken = baseUserService.AppRefreshToken(empId);
            return AjaxResult.success("401", newtoken);
        } else {
            //没过期
            return AjaxResult.success("200", "token没过期");
        }

    }

    /**
     * 解析   电话号码/用户信息  方法
     */
    public static JSONObject getPhoneNumberOrRealUserInfo(String sessionkey, String encryptedData, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + 1;
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, StandardCharsets.UTF_8);
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            log.error("wechat 用户信息解密失败：", e);
        }
        return null;
    }
}
