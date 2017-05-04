package com.jet.vframe.sys.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

	public JacksonUtils() {
		// TODO Auto-generated constructor stub
	}

	private final static ObjectMapper mapper = new ObjectMapper();
	static{
		//接受JSON字符串中使用单引号
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	private static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
	
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}

	public static <T> T readValue(String formJson, Class<T> toBeanClass)
			throws JsonParseException, JsonMappingException, IOException {
		return  mapper.readValue(formJson, toBeanClass);
	}

	public static List<?> toArrayList(String formJson, Class<?>... toBeanClass)
			throws JsonParseException, JsonMappingException, IOException {
		JavaType javaType = getCollectionType(ArrayList.class, toBeanClass);
		return mapper.readValue(formJson, javaType);
	}
}
