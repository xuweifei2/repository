package com.clicknine.configuration.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clicknine.common.resule.RetResult;

/**
 *	登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String path = request.getServletPath();
		String requestURI = request.getRequestURI();
		return true;
	}
	
	private void errorResponse(HttpServletResponse response, String code, String message) throws IOException {
		RetResult result = RetResult.setError(code, message);
		OutputStream ps = response.getOutputStream();
		try {
			response.setContentType("application/json; charset=utf-8");
			JSONObject resultJson = JSON.parseObject(JSON.toJSONString(result));
			ps.write(resultJson.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.flush();
				ps.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}