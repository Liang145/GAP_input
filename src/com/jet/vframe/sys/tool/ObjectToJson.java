package com.jet.vframe.sys.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
	
	/**
	 * 
	 * @param object
	 * @return 返回json字符串，如果对象参数不是null值，那么就返回对象生成的json字符串
	 * 如果对象参数为null，那么就返回{"isNull":""}对象
	 * 前台可以将json字符串转成对象之后，判断data.isNull是undefined还是""
	 * 如果是undefined，那么表示有对象数据
	 * 如果是"",表示没有对象数据
	 */
	public static String getJsonString(Object object) {
		String jsonString = "{\"isNull\":\"\"}";
		if (object != null) {
			try {
				jsonString = new ObjectMapper().writeValueAsString(object);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("general menu error: convert to json exception");
			}
		}
		return jsonString;
	}
}
