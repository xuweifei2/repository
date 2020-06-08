package com.clicknine.configuration.logger;

/**
 * 日志类型枚举类
 * @author 95886
 *
 */
public enum LoggerTypeEnum {
	ACCESS("ACCESS"),
	SQL("SQL"),
	GWS("GWS");
	
	private String logName;
	
	public String getLogName() {
		return logName;
	}

	private LoggerTypeEnum(String logName) {
		this.logName = logName;
	}
}
