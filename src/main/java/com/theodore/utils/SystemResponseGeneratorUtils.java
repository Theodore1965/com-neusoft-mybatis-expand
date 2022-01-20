package com.theodore.utils;

import com.theodore.enums.SystemCodeEnum;
import com.theodore.global.SystemResponse;

public class SystemResponseGeneratorUtils {
    /**
     *
     * 生成成功返回对象，无返回数据
     *
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeSuccessResponse() {
        return new SystemResponse<T>().setCode(SystemCodeEnum.SYS_200.getCode()).setMsg(SystemCodeEnum.SYS_200.getMessage());
    }

    /**
     *
     * 生成成功返回对象，有返回数据
     *
     * @param data 返回数据
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeSuccessResponse(T data) {
        return new SystemResponse<T>().setCode(SystemCodeEnum.SYS_200.getCode()).setMsg(SystemCodeEnum.SYS_200.getMessage()).setData(data);
    }

    /**
     *
     * 生成失败返回对象，默认失败原因
     *
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeFailResponse() {
        return new SystemResponse<T>().setCode(SystemCodeEnum.SYS_400.getCode()).setMsg(SystemCodeEnum.SYS_400.getMessage());
    }

    /**
     *
     * 生成失败返回对象，有失败原因
     *
     * @param message 失败原因
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeFailResponse(String message) {
        return new SystemResponse<T>().setCode(SystemCodeEnum.SYS_400.getCode()).setMsg(message);
    }

    /**
     *
     * 生成错误返回对象，有错误原因
     *
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeErrorResponse() {
        return new SystemResponse<T>().setCode(SystemCodeEnum.SYS_500.getCode()).setMsg(SystemCodeEnum.SYS_500.getMessage());
    }

    /**
     *
     * 生成错误返回对象，有错误原因
     *
     * @param message 错误原因
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeErrorResponse(String message) {
        return new SystemResponse<T>().setCode(SystemCodeEnum.SYS_500.getCode()).setMsg(message);
    }

    /**
     *
     * 根据输入自动生成返回对象,无返回数据
     *
     * @param code:返回对象状态码
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeResponse(int code) {
        return new SystemResponse<T>().setCode(code).setMsg(SystemCodeEnum.SYS_200.getMessage());
    }

    /**
     *
     * 根据输入自动生成返回对象,无返回数据
     *
     * @param code:返回对象状态码
     * @param message:返回对象中消息
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeResponse(int code, String message) {
        return new SystemResponse<T>().setCode(code).setMsg(message);
    }

    /**
     *
     * 根据输入自动生成返回对象,有返回数据
     *
     * @param code:返回对象状态码
     * @param message:返回对象中消息
     * @param data:返回对象中业务数据
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeResponse(int code, String message, T data) {
        return new SystemResponse<T>().setCode(code).setMsg(message).setData(data);
    }

    /**
     *
     * 根据输入自动生成返回对象,有返回数据
     *
     * @param code:返回对象状态码
     * @param data:返回对象中业务数据
     * @return SystemResponse 统一返回对象
     */
    public static <T> SystemResponse<T> makeResponse(int code, T data) {
        return new SystemResponse<T>().setCode(code).setMsg(SystemCodeEnum.SYS_200.getMessage()).setData(data);
    }
}
