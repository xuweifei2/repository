package com.clicknine.common.resule;

/**
 * 返回结果
 */
public class RetResult {
	private String code;
	private String message;
	private boolean status;
	private Object data;
	private Object extraData;
	
    public static RetResult setSuccess(Object data) {
    	RetResult result = new RetResult();
    	result.setCode("000");
    	result.setMessage("OK");
    	result.setStatus(true);
    	result.setData(data);
    	return result;
    }
    
    public static RetResult setSuccess(Object data, Object extraDate) {
    	RetResult result = new RetResult();
    	result.setCode("000");
    	result.setMessage("OK");
    	result.setStatus(true);
    	result.setData(data);
    	result.setExtraData(extraDate);
    	return result;
    }
    
    public static RetResult setError(String code, String message) {
    	RetResult result = new RetResult();
    	result.setCode(code);
    	result.setMessage(message);
    	result.setStatus(false);
    	result.setData("");
    	return result;
    }
    
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getExtraData() {
		return extraData;
	}
	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}
	
}
