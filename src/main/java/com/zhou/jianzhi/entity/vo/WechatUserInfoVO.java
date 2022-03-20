package com.zhou.jianzhi.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: watchapp
 * @description: 微信解密encryptedData VO
 * @author: DRF
 * @create: 2020-09-11 17:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WechatUserInfoVO implements Serializable {

    private static final long serialVersionUID = 3392596027503549684L;

    private String country;

    private int gender;

    private String province;

    private String city;

    private String avatarUrl;

    private String openId;
    //    private String unionid;

    private String nickName;

    private String language;


}
