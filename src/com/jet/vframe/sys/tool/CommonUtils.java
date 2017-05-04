package com.jet.vframe.sys.tool;

import java.util.List;

public class CommonUtils {

	public CommonUtils() {
		// TODO Auto-generated constructor stub
	}

	public static String listToString(List<String> list, String separator) {
		if (list == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			sb.append(str);
			sb.append(separator);
		}
		return sb.substring(0, sb.length() - separator.length());
	}

}
