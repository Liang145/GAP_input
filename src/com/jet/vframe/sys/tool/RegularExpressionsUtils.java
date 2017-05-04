package com.jet.vframe.sys.tool;

public class RegularExpressionsUtils {
	public static boolean isLetterNumberOrChinese(String str) {
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		return str.matches(regex);
	}

	public static boolean isLetterNumber(String str) {
		// 首先,使用Pattern解释要使用的正则表达式，其中^表是字符串的开始，$表示字符串的结尾。
		String regex = "^[a-z0-9A-Z_]+$";
		return str.matches(regex);
	}

	public static boolean isNumber(String number) {
		if (number == null)
			return false;
		return number.matches("[+-]?[1-9]+[0-9]*(\\.[0-9]+)?");
	}

	public static boolean isLetter(String alpha) {
		if (alpha == null)
			return false;
		return alpha.matches("[a-zA-Z]+");
	}

	public static boolean isChinese(String chineseContent) {
		if (chineseContent == null)
			return false;
		return chineseContent.matches("[\u4e00-\u9fa5]");
	}
	public static void main(String[] args) {
		System.out.println(isLetterNumber("sd"));
	}
}
