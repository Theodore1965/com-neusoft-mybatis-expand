package com.neusoft.mybatis.expand.global;

import com.neusoft.mybatis.expand.enums.SystemCodeEnum;

public class SystemResponse<T> {

    public SystemResponse() {
        this.code = SystemCodeEnum.SYS_200.getCode();
        this.msg = SystemCodeEnum.SYS_200.getMessage();
    }

    public SystemResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public SystemResponse(T data) {
        this.code = SystemCodeEnum.SYS_200.getCode();
        this.msg = SystemCodeEnum.SYS_200.getMessage();
        this.data = data;
    }

    /**
     * 返回状态码
     */
    public int code;

    /**
     * 返回消息信息
     */
    private String msg;
    /**
     * 返回业务数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public SystemResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public SystemResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public SystemResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
