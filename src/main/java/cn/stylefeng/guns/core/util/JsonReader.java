package cn.stylefeng.guns.core.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class JsonReader {
	//返回JSONObject格式数据
	public static JSONObject receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
 
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		//将json字符串转换为json对象
		JSONObject json= JSONObject.parseObject(sb.toString(), Feature.OrderedField);
		return json;
	}
	//返回JSONArray格式数据
	public static JSONArray receivePostToJsonArray(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
		 
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		//将json字符串转换为json对象
		JSONArray json= JSONArray.parseArray(sb.toString());
		return json;
	}
}