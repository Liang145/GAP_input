package com.jet.vframe.sys.tool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConvertUtils {
	public static SimpleDateFormat _sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private static double sqMeterToMu = 666.7;

	public static double sqMeterToMu(Object value) {
		try {
			return round(Double.valueOf(value.toString()) / sqMeterToMu, 1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 对double数据进行取精度.
	 * 
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数). 精度取值方式.BigDecimal.ROUND_HALF_UP
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);// 四舍五入
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

	static public double toPercent(double part, double total) {
		if (total != 0) {
			return round(part * 100 / total, 1);
		} else {
			return 0;
		}
	}

	/**
	 * 对提供的源时间按月为单位进行提前，返回提前后的数据
	 * 
	 * @param ca
	 *            源时间，为空时则取当前时间
	 * @param length
	 *            提前月数
	 * @return 提前length个月的目标时间
	 */
	static public Date getLastDateByMonth(Calendar ca, int length) {
		if (ca == null) {
			ca = Calendar.getInstance();
		}
		ca.add(Calendar.YEAR, -(length / 12));
		ca.add(Calendar.MONTH, (length % 12) + 1);
		return ca.getTime();
	}

	/**
	 * 在对数据左边补齐
	 * 
	 * @param src
	 * @param len
	 * @param pad
	 * @return
	 */
	public static String leftPad(String src, int len, char pad) {
		StringBuilder builder = new StringBuilder();
		int padLen = len - src.length();
		for (int i = 0; i < padLen; i++) {
			builder.append(pad);
		}
		builder.append(src);
		return builder.toString();
	}
}
