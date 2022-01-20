package com.theodore.idempotent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theodore.enums.SystemCodeEnum;
import com.theodore.global.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ExtApiAopIdempotent {

    public static final String REQUEST_KEY = "RequestKey:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(com.theodore.idempotent.ExtApiIdempotent)")
    public void myPoint() {

    }

    @Around(value = "myPoint()")
    public Object apiIdempotentCheck(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper om = new ObjectMapper();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        //宿主机访问地址
        String ipAddr = IpUtils.getIpAddr(request);
        // 请求URL
        String requestURI = request.getRequestURI();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ExtApiIdempotent annotation = method.getAnnotation(ExtApiIdempotent.class);

        //无注解直接放行
        if (annotation == null) {
            return pjp.proceed();
        }

        //获取请求参数
        Object arg = pjp.getArgs()[0];
        String apiName = null;
        // 参数
        String parameterName = null;
        // 方法名
        String apiMethodName = null;
        String key = null;

        //接口方法名称
        apiMethodName = method.getName();
        // 获取参数
        Parameter[] parameters = method.getParameters();
        //获取接口间隔时间
        long expireTime = annotation.expireTime();
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        apiName = getRequestName(requestURI, apiName, declaredAnnotations);
        Parameter parameter = parameters[0];

        //接口参数名称
        parameterName = parameter.getName();
        //如果用户自定义则默认使用修改后的规则
        if (StringUtils.isNotBlank(annotation.key())) {
            //TODO 后续对用户设置自定义key进行校验
            key = annotation.key();
        } else {
            key = REQUEST_KEY + ipAddr + ":" + apiName + ":" + apiMethodName + ":" + parameterName;
        }
        //如果当前key存在
        if (stringRedisTemplate.hasKey(key)) {
            // 验证入参
            boolean valid = annotation.validParam();
            if (valid) {
                String value = stringRedisTemplate.opsForValue().get(key);
                // 如果入参相同
                if (value.equals(om.writeValueAsString(arg))) {
                    throw new BusinessException(SystemCodeEnum.SYS_400.getCode(), annotation.message());
                }
            }
        }
        if (arg != null && !"".equals(arg)) {
            stringRedisTemplate.opsForValue().set(key, om.writeValueAsString(arg), expireTime, TimeUnit.SECONDS);
        } else {
            stringRedisTemplate.opsForValue().set(key, "1", expireTime, TimeUnit.SECONDS);
        }

        //接口执行
        Object proceed = pjp.proceed();
        //默认不删除 如果修改则使用修改后的配置
        if (annotation.delKey()) {
            stringRedisTemplate.delete(key);
        }
        return proceed;
    }

    private String getRequestName(String requestURI, String apiName, Annotation[] declaredAnnotations) {
        //获取接口请求类型以及接口api
        for (Annotation declaredAnnotation : declaredAnnotations) {
            if (declaredAnnotation instanceof RequestMapping) {
                RequestMapping requestMapping = (RequestMapping) declaredAnnotation;
                String requestType = requestMapping.method()[0].name();
                apiName = requestType + ":" + requestURI;
            }
            if (declaredAnnotation instanceof DeleteMapping) {
                apiName = "DELETE:" + requestURI;
            }
            if (declaredAnnotation instanceof GetMapping) {
                apiName = "GET:" + requestURI;
            }
            if (declaredAnnotation instanceof PutMapping) {
                apiName = "PUT:" + requestURI;
            }
            if (declaredAnnotation instanceof PostMapping) {
                apiName = "POST:" + requestURI;
            }
        }
        return apiName;
    }
}
