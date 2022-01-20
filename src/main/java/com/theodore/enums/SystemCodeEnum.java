package com.theodore.enums;

public enum SystemCodeEnum {

    // 系统异常编码
    SYS_200(200, "操作成功"),
    SYS_400(400, "操作失败"),
    SYS_401(401, "认证失败"),
    SYS_439(439, "配额已用完"),
    SYS_500(500, "系统异常"),

    ;

    /**
     * 状态代码
     */
    private int code;

    /**
     * 状态信息
     */
    private String message;

    /**
     *
     * {状态枚举构造方法}
     *
     * @param code 状态码
     * @param message 状态信息
     * @return ValidStateEnum
     */
    SystemCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过code获取枚举对象静态方法。
     *
     * @param code 状态码
     * @return SystemCodeEnum
     */
    public static SystemCodeEnum getEnumByCode(String code) {
        for (SystemCodeEnum systemCodeEnum : SystemCodeEnum.values()) {
            if (code.equals(systemCodeEnum.getCode())) {
                return systemCodeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
