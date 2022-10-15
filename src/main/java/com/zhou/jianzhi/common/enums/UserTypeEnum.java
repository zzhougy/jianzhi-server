package com.zhou.jianzhi.common.enums;


public enum UserTypeEnum {

    //SYSTEM_ADMIN(0),//系统管理员admin

    //SYSTEM_USER(1);//系统的普通用户

    //1 求职者 2 招聘单位  3 超级管理员 4 普通用户
    USER_JOBSEEKER(1),
    RECRUIT_UNIT(2),
    SYSTEM_ADMIN(3),
    SYSTEM_USER(4);



    private int typeCode;

    UserTypeEnum(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
