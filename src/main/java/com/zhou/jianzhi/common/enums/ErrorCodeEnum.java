package com.zhou.jianzhi.common.enums;


public enum ErrorCodeEnum {
    //数据校验
    VALIDATE_ERROR(10000,"数据校验不通过"),
    DoNotAllowToDisableTheCurrentUser(10002,"不允许禁用当前用户"),
    PARSE_ERROR(10017,"日期解析错误"),
    //系统界别
    SYS_ERROR(500,"系统异常，请联系管理员"),
    SYS_PERMISSION(403,"无权限"),
    USER_ACCOUNT_NOT_FOUND(20020,"账号不存在"),
    USER_PHONE_NOT_FOUND(20040,"未找到与改手机号绑定的职员"),
    USER_PHONE_NOT_AGGREE(20050,"此用户无法登陆"),
    USER_PHONE_NOT_YOURS(20060,"此手机号不是您微信绑定的手机号，登陆失败！"),
    PASSWORD_DEFINED(20010,"密码错误"),
    ROLE_NAME_EXIT(20030,"角色名被占用")
    ;



    private Integer code;
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static ErrorCodeEnum getError(Integer code){
        for(ErrorCodeEnum error : ErrorCodeEnum.values()){
            if(code== error.getCode()){
                return error;
            }
        }
        return null;
    }
}
