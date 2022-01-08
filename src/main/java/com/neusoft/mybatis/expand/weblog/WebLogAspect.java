package com.neusoft.mybatis.expand.weblog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebLogAspect {

	private final Log log = LogFactory.getLog(this.getClass());

	@Pointcut("@annotation(ControllerWebLog)")
	public void ControllerWebLog() {
	}

	@AfterReturning(pointcut = "ControllerWebLog()")
	public void aroundAdvice(JoinPoint pjp) throws Throwable {
		String logStr = LoggerUtil.convertLogStr(pjp);
		log.info(logStr);
	}

	/**
	 * 异常通知 用于拦截记录异常日志
	 *
	 * @param e
	 */
	@AfterThrowing(pointcut = "ControllerWebLog()", throwing = "e")
	public void doAfterThrowing1(JoinPoint pjp, Throwable e) {
		String logStr = LoggerUtil.convertLogStr(pjp);
		log.error(logStr, e);
	}
}
