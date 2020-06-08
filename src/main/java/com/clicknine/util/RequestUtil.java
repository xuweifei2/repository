package com.clicknine.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求信息处理
 * @author 95886
 *
 */
public class RequestUtil {
	
	/**
	 * 获取URI日志信息
	 * @param request
	 * @return
	 */
	public static String getUrlContent(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("url=" + request.getRequestURI() + " ");
		return sb.toString();
	}
	
	/**
	 * 获取参数日志信息
	 * @param request
	 * @return
	 */
	public static String getParamContent(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		// 获取请求的参数
		Enumeration<String> parameterNames = request.getParameterNames();
		sb.append("params:");
		while(parameterNames.hasMoreElements()){
			String parameterName = parameterNames.nextElement();
			String[] arr = request.getParameterValues(parameterName);
			for(int i = 0; i<arr.length; i++){
				String param = arr[i].toString().length() > 100 ? arr[i].toString().substring(0, 100) : arr[i].toString();
				sb.append(parameterName + "=" + param);
				if(i < arr.length - 1) {
					sb.append(",");
				}else {
					sb.append(" ");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取URI+参数日志信息
	 * @param request
	 * @return
	 */
	public static String getLogContent(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append(getParamContent(request));
		sb.append(getParamContent(request));
		return sb.toString();
	}
	
}
