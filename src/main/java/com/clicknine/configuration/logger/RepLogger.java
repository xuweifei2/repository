package com.clicknine.configuration.logger;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局日志类
 * @author 95886
 *
 */
public class RepLogger {
	
	private static Logger accessLog = LoggerFactory.getLogger(LoggerTypeEnum.ACCESS.getLogName());
    private static Logger sqlLog = LoggerFactory.getLogger(LoggerTypeEnum.SQL.getLogName());
    private static Logger gwslog = LoggerFactory.getLogger(LoggerTypeEnum.GWS.getLogName());
	private static Map<LoggerTypeEnum, Logger> loggerMap = new HashMap<>();
	static {
		loggerMap.put(LoggerTypeEnum.ACCESS, accessLog);
		loggerMap.put(LoggerTypeEnum.SQL, sqlLog);
		loggerMap.put(LoggerTypeEnum.GWS, gwslog);
	}
	
	/**
	 * 日志调用
	 * @param loggerType
	 * @return
	 */
	public static Logger getlogger(LoggerTypeEnum loggerType) {
		return loggerMap.get(loggerType);
	}
	
    /**
     * 通用error log
     * @param msg
     * @param args
     */
	public static void error(String msg, Object... args) {
		gwslog.error(msg, args);
	}
	
	/**
	 * 通用info log
	 * @param msg
	 * @param args
	 */
	public static void info(String msg, Object... args) {
		gwslog.info(msg, args);
	}
	
	
}
