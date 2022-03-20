package com.zhou.jianzhi.common;


import java.util.HashMap;


public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";
    private int code;
    private String msg;
    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(Integer code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        if (data != null) {
            super.put("data", data);
        }
    }

    public AjaxResult(Integer code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    public static AjaxResult error(Integer code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    public static AjaxResult error(Integer code, String msg) {
        return error(code, msg, (Object) null);
    }

    public static AjaxResult error(String msg) {
        return error(AjaxResult.Type.ERROR.value, msg, (Object) null);
    }

    public static AjaxResult error() {
        return error(AjaxResult.Type.ERROR.value, "操作失败");
    }

    public static AjaxResult success() {
        return success("操作成功");
    }

    public static AjaxResult success(Object data) {
        return success("操作成功", data);
    }

    public static AjaxResult success(String msg) {
        return success(msg, (Object) null);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(AjaxResult.Type.SUCCESS.value, msg, data);
    }

    public static AjaxResult warn(String msg) {
        return warn(msg, (Object) null);
    }

    public static AjaxResult warn(String msg, Object data) {
        return new AjaxResult(AjaxResult.Type.WARN.value, msg, data);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static enum Type {
        SUCCESS(200),
        NORECORD(-1),
        WARN(301),
        ERROR(500);

        private final int value;

        private Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
