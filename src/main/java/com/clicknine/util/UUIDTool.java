package com.clicknine.util;


import java.util.UUID;

public class UUIDTool {
    
	/**
	 * 生成32位UUID
	 * @return
	 */
    public static String generateKey32(){
        String uuId=  UUID.randomUUID().toString();
        uuId=uuId.replaceAll("-","").toUpperCase();
        return uuId;
    }

}
