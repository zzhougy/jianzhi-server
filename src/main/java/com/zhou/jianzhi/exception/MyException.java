package com.zhou.jianzhi.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MyException extends RuntimeException{
    private Integer code;
    private String msg;
    public MyException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyException(String msg){
        super(msg);
        this.msg = msg;
    }

}
