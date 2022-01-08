package com.neusoft.mybatis.expand.weblog;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class LoggerUtil {

	public static String convertLogStr(JoinPoint pjp) {
		StringBuffer sb = new StringBuffer();
		try {
			// 获取方法签名
			MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
			// 获取方法
			Method method = methodSignature.getMethod();
			// 获取方法上面的注解
			ControllerWebLog logAnno = method.getAnnotation(ControllerWebLog.class);

			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			ServletRequestAttributes sra = (ServletRequestAttributes) ra;
			HttpServletRequest request = sra.getRequest();

			StringBuffer logStr = new StringBuffer();
			Object[] args = (pjp.getArgs());
			for (Object arg : args) {
				logStr.append(new ObjectMapper().writeValueAsString(arg));
			}
			sb.append("方法:");
			sb.append(logAnno.name());
			sb.append("$");
			sb.append("url:");
			sb.append(request.getRequestURL().toString());
			sb.append("$");
			sb.append("方法:");
			sb.append(method.getName());
			sb.append("$");
			sb.append("uri:");
			sb.append(request.getRequestURI());
			sb.append("$");
			sb.append("请求客户端的IP地址:");
			sb.append(request.getRemoteAddr());
			sb.append("$");
			sb.append("请求客户端主机名:");
			sb.append(request.getRemoteHost());
			sb.append("$");
			sb.append("入参:");
			sb.append(logStr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
