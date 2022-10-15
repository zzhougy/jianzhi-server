package com.zhou.jianzhi.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @program: watchapp
 * @description: 微信授权VO
 * @author: DRF
 * @create: 2020.09.11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatAuthTokenVO implements Serializable {

    private static final long serialVersionUID = -4650586170502293643L;

    private String openid;

    private String sessionKey;


}
