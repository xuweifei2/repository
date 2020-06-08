package com.clicknine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clicknine.common.exception.RepException;
import com.clicknine.common.resule.CommConstant;
import com.clicknine.common.resule.RetResult;
import com.clicknine.configuration.logger.RepLogger;

@RestControllerAdvice
public class RepExceptionHandler {
	

	/**
	 * 全局异常处理
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
    public RetResult defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e){

        if(e instanceof RepException){
        	RepLogger.error(request.getServletPath() + "参数非法,code={},message={}",  e.getMessage());
        	return RetResult.setError(((RepException) e).getCode(), e.getMessage());
        } else {
        	RepLogger.error(request.getServletPath() + "异常,e={}",  e.getMessage());
        	return RetResult.setError(CommConstant.GWSCOD0001, CommConstant.GWSMSG0001);
        }
        
    }
	
}
