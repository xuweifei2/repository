package com.clicknine.configuration.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器入口
 */
@Configuration
public class BaseInterceptor implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// accessInterceptor最新注册,在log中添加[%X{TRACE_ID}]
		registry.addInterceptor(accessInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
	}

	
	@Bean
	public AccessInterceptor accessInterceptor() {
		return new AccessInterceptor();
	}

	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

}
