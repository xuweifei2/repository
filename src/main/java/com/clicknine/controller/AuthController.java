package com.clicknine.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clicknine.common.resule.RetResult;
import com.clicknine.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/api/auth/auth", method = RequestMethod.POST)
	public RetResult auth(String code) {
		try {
			authService.auth(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RetResult.setSuccess("");
	}
	
}
