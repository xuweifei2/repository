package com.clicknine.service;

import java.io.IOException;
import java.util.Map;

public interface AuthService {

	Map<String, Object> auth(String code) throws IOException;

}
