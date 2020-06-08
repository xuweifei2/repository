package com.clicknine.configuration.interceptor;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.clicknine.configuration.logger.LoggerTypeEnum;
import com.clicknine.configuration.logger.RepLogger;
import com.clicknine.util.DateUtil;
import com.clicknine.util.RequestUtil;
import com.clicknine.util.UUIDTool;

/**
 *	请求拦截器
 *	1.日志中增加request标识
 *	2.记录每次request时间
 *	3.记录请求URI&参数信息
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = RepLogger.getlogger(LoggerTypeEnum.ACCESS);
	private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();
	private static final String TRACE_ID = "TRACE_ID";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 为请求添加标识id
		String traceId = UUIDTool.generateKey32();
		if (StringUtils.isEmpty(MDC.get(TRACE_ID))) {
            MDC.put(TRACE_ID, traceId);
        }
		// 设置请求接收时间
		Long startTime = new Date().getTime();
		startTimeThreadLocal.set(startTime);
		// 输出accessLog
		logger.info("startTime={}" + RequestUtil.getLogContent(request), DateUtil.DATA_PATTON_YYMMDD);
		return true;
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// 获取请求接收时间
		Long startTime = startTimeThreadLocal.get();
		Long endTime = new Date().getTime();
		logger.info("take time = {} " + RequestUtil.getLogContent(request), endTime - startTime);
//		Object startTime = request.getAttribute("startTime");
//		long time = new Date().getTime() - (Long)startTime;
//		if(time > 1000) {
//			RepLogger.info(request.getServletPath() + "结束,耗时={}", time);
//		}
	}


}