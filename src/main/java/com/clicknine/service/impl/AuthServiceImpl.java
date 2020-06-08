package com.clicknine.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.clicknine.service.AuthService;
import com.clicknine.util.HttpRequest;

@Service
public class AuthServiceImpl implements AuthService{

	/**
	 * 测试openid
	 */
	@Override
	public Map<String, Object> auth(String code) throws IOException {
		String url = "https://api.weixin.qq.com/sns/jscode2session?";
		String param = "appid=" + "wx9cad9c1ac7ec323c" + "&secret=" + "c6bbabdb29c3205fff7712d154e3b03c" + "&js_code=" + code + "&grant_type=authorization_code";
		String content = HttpRequest.httpsGET(url, param);
		JSONObject json = JSONObject.parseObject(content);
		if (null != json && json.containsKey("errcode") && Integer.valueOf("0").equals(json.getInteger("errcode"))) {
			Object object = json.get("openid");
			if(null == object) {
				return new HashMap<>();
			}
			Map<String, Object> map = new HashMap<>();
			map.put("openid", object.toString());
			return map;
		}
		return new HashMap<>();
	}

}
