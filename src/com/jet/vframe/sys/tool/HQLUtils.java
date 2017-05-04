package com.jet.vframe.sys.tool;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class HQLUtils {

	public HQLUtils() {
		// TODO Auto-generated constructor stub
	}

	public static Map<String, Object> generalMapParamsSingle(String target,
			Object value) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put(target, value);

		return params;
	}

	public static Map<String, Object> generalMapParams(String[] targets,
			Object[] values) {
		Map<String, Object> params = new HashMap<String, Object>();

		for (int i = 0; i < targets.length; i++) {
			params.put(targets[i], values[i]);
		}
		return params;
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> generalMapParamsFromPojo(
			String targetSrc, Object obj) {
		String[] array = targetSrc.split(",");
		Map<String, Object> params = new HashMap<String, Object>();
		for (String str : array) {
			String fieldName = str.substring(str.indexOf(":") + 1);
			Class objClass = obj.getClass();
			Field field = null;
			try {
				field = objClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				Class superClass = objClass.getSuperclass();
				try {
					field = superClass.getDeclaredField(fieldName);
				} catch (NoSuchFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return null;
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return null;
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			field.setAccessible(true);

			try {
				params.put(fieldName, field.get(obj));
				System.out.println(field.get(obj));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}
		return params;
	}

}
