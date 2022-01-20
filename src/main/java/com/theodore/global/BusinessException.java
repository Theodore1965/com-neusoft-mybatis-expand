package com.theodore.global;

import org.apache.commons.lang3.StringUtils;

public class BusinessException extends RuntimeException {

    private int code = 400;

    public int getCode() {
        return code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    @Override
    public String getMessage() {
        StringBuffer message = new StringBuffer(StringUtils.EMPTY);
        message.append(code);
        message.append(StringUtils.SPACE);
        message.append(super.getMessage());
        return message.toString();
    }
}
