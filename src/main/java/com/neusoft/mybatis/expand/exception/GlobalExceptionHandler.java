package com.neusoft.mybatis.expand.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.mybatis.expand.global.BusinessException;
import com.neusoft.mybatis.expand.global.SystemResponse;
import com.neusoft.mybatis.expand.utils.SystemResponseGeneratorUtils;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Component
@EnableConfigurationProperties(GlobalIgnoreProperties.class)
public class GlobalExceptionHandler implements ResponseBodyAdvice {

    @Autowired
    private GlobalIgnoreProperties globalIgnoreProperties;

    private static final Log log;

    static {
        log = LogFactory.getLog(GlobalExceptionHandler.class);
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SystemResponse<?> getUserError(BusinessException exception) {
        GlobalExceptionHandler.log.error("业务异常", exception);
        return SystemResponseGeneratorUtils.makeFailResponse(exception.getMessage());
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SystemResponse<?> getSystemError(Throwable throwable) {
        GlobalExceptionHandler.log.error("系统异常", throwable);
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return SystemResponseGeneratorUtils.makeErrorResponse(sw.getBuffer().toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SystemResponse<?> handlerConstraintViolationException(ConstraintViolationException exception) {
        log.warn(exception.getMessage(), exception);
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        String errorMessages = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        return SystemResponseGeneratorUtils.makeFailResponse(errorMessages);
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SystemResponse<?> getValidError(final Exception exception) {
        GlobalExceptionHandler.log.error("绑定异常", exception);
        List<ObjectError> errors = new ArrayList<>();
        if (exception instanceof BindException) {
            errors = ((BindException) exception).getBindingResult().getAllErrors();
        } else if (exception instanceof MethodArgumentNotValidException) {
            errors = ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors();
        }
        String errorMessages = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
        return SystemResponseGeneratorUtils.makeFailResponse(errorMessages);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (Objects.isNull(obj)) {
            return SystemResponseGeneratorUtils.makeSuccessResponse();
        }

        if (obj instanceof SystemResponse) {
            return obj;
        }
        if (obj instanceof String) {
            String str = StringUtils.EMPTY;
            try {
                return new ObjectMapper().writeValueAsString(SystemResponseGeneratorUtils.makeSuccessResponse(obj));
            } catch (JsonProcessingException e) {
            }
            return str;
        }
        // 全局URL过滤，过滤swagger路径
        String url = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getRequestURI();
        if(globalIgnoreProperties.getPaths().contains(url)) {
            return obj;
        }

        return SystemResponseGeneratorUtils.makeSuccessResponse(obj);
    }
}
