package com.clicknine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import com.clicknine.common.exception.RepException;

/**
 * @ClassName: HttpURLConnectionRequest
 * @Description: TODO
 * @author Administrator
 */
public class HttpRequest {
	/**
	 * @Title: httpGET 
	 * @Description: 发送HTTP协议GET请求 
	 * @param url
	 * @param param
	 * @throws IOException
	 * @return String
	 */
	public static String httpGET(String url, String param) throws IOException {
		BufferedReader reader = null;
		InputStream is = null;
		StringBuffer sbf = new StringBuffer();
		try {
			if (!url.endsWith("?")) {
				url += "?";
			}
			URL realUrl = new URL(url + param);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(false);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Charset", "UTF-8");
			//conn.setRequestProperty("Connection", "close");
			conn.connect();
			int resultCode = conn.getResponseCode();
			if(resultCode == HttpURLConnection.HTTP_OK){
				is = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
				}
			} else {
//				GwsLogger.error("HTTP请求失败:HTTP_CODE={}", resultCode);
			}
			//conn.disconnect();//关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return sbf.toString();
	}
	
	/**
	 * @Title: httpPOST 
	 * @Description: 发送HTTP协议POST请求 
	 * @param url
	 * @param param
	 * @param contentType
	 * @return
	 * @return String
	 */
	public static String httpPOST(String url, String param, String contentType) throws IOException {
		return post(url, param, contentType);
	}
	
	/**
	 * @Title: httpPOST 
	 * @Description: 发送HTTP协议POST请求 
	 * @param url
	 * @param param
	 * @throws IOException
	 * @return String
	 */
	public static String httpPOST(String url, String param) throws IOException {
		return post(url, param, null);
	}
	
	/**
	 * @Title: post 
	 * @Description: 发送HTTP协议POST请求 
	 * @param url
	 * @param param
	 * @throws IOException
	 * @return String
	 */
	public static String post(String url, String param, String type) throws IOException {
		PrintWriter out = null;
		BufferedReader reader = null;
		InputStream is = null;
		StringBuffer sbf = new StringBuffer();
		String contentType = (null == type) ? "x-www-form-urlencoded" : type;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			//设置参数
			conn.setDoOutput(true);		//需要输出
			conn.setDoInput(true);		//需要输入
			conn.setUseCaches(false);	//不缓存
			conn.setRequestMethod("POST");//设置POST方式连接
			//设置请求属性
			conn.setRequestProperty("Content-Type", "application/" + contentType);
			//conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			//conn.setRequestProperty("Connection", "close");
			conn.connect();//连接
			//建立输入流，向指向的URL传入参数
			out = new PrintWriter(conn.getOutputStream());
			//发送请求参数
			out.print(param);
			//flush输出流的缓冲
			out.flush();
			out.close();
			//获得响应状态
			int resultCode = conn.getResponseCode();
			if(resultCode == HttpURLConnection.HTTP_OK){
				is = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead).append("\n");
				}
			} else {
//				GwsLogger.error("HTTP请求失败:HTTP_CODE={}", resultCode);
			}
			//conn.disconnect();//关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return sbf.toString();
	}
	
	public static String httpsPostFormData(String url, Map<String, Object> pM) throws IOException {
		if (url.contains("http://")) {
			return httpPostFormData(url, pM);
		}
		PrintWriter out = null;
		BufferedReader reader = null;
		InputStream is = null;
		StringBuffer sbf = new StringBuffer();
		String boundary = "--kcwlcrm";
		try {
			URL realUrl = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			//设置参数
			conn.setDoOutput(true);		//需要输出
			conn.setDoInput(true);		//需要输入
			conn.setUseCaches(false);	//不缓存
			conn.setRequestMethod("POST");//设置POST方式连接
			//设置请求属性
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			//conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			//conn.setRequestProperty("Connection", "close");
			conn.connect();//连接
			//建立输入流，向指向的URL传入参数
			out = new PrintWriter(conn.getOutputStream());
			//拼接body参数
			StringBuffer sb = new StringBuffer();
			for (Entry<String, Object> entry : pM.entrySet()) {
				//在boundary关需添加两个横线
				sb = sb.append("--").append(boundary);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");
				//提交的数据前要有两个回车换行
				sb.append("\r\n\r\n");
				sb.append(entry.getValue());
				sb.append("\r\n");
			}
			//body结束时 boundary前后各需添加两上横线，最添加添回车换行
			sb.append("--").append(boundary).append("--").append("\r\n");
			
			//发送请求参数
			out.print(sb.toString());
			//flush输出流的缓冲
			out.flush();
			out.close();
			//获得响应状态
			int resultCode = conn.getResponseCode();
			if(resultCode == HttpURLConnection.HTTP_OK){
				is = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead).append("\n");
				}
			} else {
//				GwsLogger.error("HTTP请求失败:HTTP_CODE={}", resultCode);
				throw new RepException("001", "HTTP请求失败");
			}
			//conn.disconnect();//关闭连接
		} catch (Exception e) {
			throw new RepException("001", e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return sbf.toString();
	}
	
	public static String httpPostFormData(String url, Map<String, Object> pM) throws IOException {
		if (url.contains("https://")) {
			return httpsPostFormData(url, pM);
		}
		PrintWriter out = null;
		BufferedReader reader = null;
		InputStream is = null;
		StringBuffer sbf = new StringBuffer();
		String boundary = "--kcwlcrm";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			//设置参数
			conn.setDoOutput(true);		//需要输出
			conn.setDoInput(true);		//需要输入
			conn.setUseCaches(false);	//不缓存
			conn.setRequestMethod("POST");//设置POST方式连接
			//设置请求属性
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			//conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			//conn.setRequestProperty("Connection", "close");
			conn.connect();//连接
			//建立输入流，向指向的URL传入参数
			out = new PrintWriter(conn.getOutputStream());
			//拼接body参数
			StringBuffer sb = new StringBuffer();
			for (Entry<String, Object> entry : pM.entrySet()) {
				//在boundary关需添加两个横线
				sb = sb.append("--").append(boundary);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");
				//提交的数据前要有两个回车换行
				sb.append("\r\n\r\n");
				sb.append(entry.getValue());
				sb.append("\r\n");
			}
			//body结束时 boundary前后各需添加两上横线，最添加添回车换行
			sb.append("--").append(boundary).append("--").append("\r\n");
			
			//发送请求参数
			out.print(sb.toString());
			//flush输出流的缓冲
			out.flush();
			out.close();
			//获得响应状态
			int resultCode = conn.getResponseCode();
			if(resultCode == HttpURLConnection.HTTP_OK){
				is = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead).append("\n");
				}
			} else {
//				GwsLogger.error("HTTP请求失败:HTTP_CODE={}", resultCode);
				throw new RepException("001", "HTTP请求失败");
			}
			//conn.disconnect();//关闭连接
		} catch (Exception e) {
			throw new RepException("001", e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return sbf.toString();
	}
	
	/**
	 * @Title: httpsGET 
	 * @Description: 发送HTTPS协议GET请求 
	 * @param url
	 * @param param
	 * @throws IOException
	 * @return String
	 */
	public static String httpsGET(String url, String param) throws IOException {
		BufferedReader reader = null;
		InputStream is = null;
		StringBuffer sbf = new StringBuffer();
		try {
			if (!url.endsWith("?")) {
				url += "?";
			}
			URL realUrl = new URL(url + param);
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(false);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Charset", "UTF-8");
			//conn.setRequestProperty("Connection", "close");
			conn.connect();
			int resultCode = conn.getResponseCode();
			if(resultCode == HttpURLConnection.HTTP_OK){
				is = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
				}
			} else {
//				GwsLogger.error("HTTP请求失败:HTTP_CODE={}", resultCode);
			}
			//conn.disconnect();//关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return sbf.toString();
	}

	/**
	 * @Title: httpsPOST 
	 * @Description: 发送HTTPS协议POST请求 
	 * @param url
	 * @param param
	 * @throws IOException
	 * @return String
	 */
	public static String httpsPOST(String url, String param) throws IOException {
		PrintWriter out = null;
		BufferedReader reader = null;
		InputStream is = null;
		StringBuffer sbf = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			//设置参数
			conn.setDoOutput(true);		//需要输出
			conn.setDoInput(true);		//需要输入
			conn.setUseCaches(false);	//不缓存
			conn.setRequestMethod("POST");//设置POST方式连接
			//设置请求属性
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			//conn.setRequestProperty("Connection", "close");
			conn.connect();//连接
			//建立输入流，向指向的URL传入参数
			out = new PrintWriter(conn.getOutputStream());
			//发送请求参数
			out.print(param);
			//flush输出流的缓冲
			out.flush();
			out.close();
			//获得响应状态
			int resultCode = conn.getResponseCode();
			if(resultCode == HttpsURLConnection.HTTP_OK){
				is = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead).append("\n");
				}
			} else {
//				GwsLogger.error("HTTPS请求失败:HTTPS_CODE={}", resultCode);
			}
			//conn.disconnect();//关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return sbf.toString();
	}
	
}
