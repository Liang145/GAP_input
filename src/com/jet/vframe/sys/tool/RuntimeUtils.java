package com.jet.vframe.sys.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RuntimeUtils {
	public static void main(String[] args) {
		// String res =
		// execWithReturn("java -jar /home/GAP_OUT/GAPProvider.jar -v");
		// System.out.println("version:"+res);

		String res = execWithReturn("ping 192.168.4.248", null);
		System.out.println("version:" + res);

	}

	public static String execWithReturn(String cmd, String dir) {
		try {
			// String[] cmdA = { "/bin/sh", "-c", cmd };
			Process process;
			if (dir != null && dir.trim().length() > 0) {
				process = Runtime.getRuntime().exec(cmd, null, new File(dir));
			} else {
				process = Runtime.getRuntime().exec(cmd);
			}
			LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line).append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void exec(String cmd) {

		// String[] cmdA = { "/bin/sh", "-c", cmd };
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getDateByCmd() {
		BufferedReader br = null;
		String str;
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		try {
			Process ps = Runtime.getRuntime().exec("date -R");
			br = new BufferedReader(new InputStreamReader(ps.getInputStream(), "UTF-8"));
			str = br.readLine();
			if (str != null) {
				// Tue, 08 Sep 2015 11:20:00 +0800
				Date d = sdf.parse(str.split("\\+")[0].trim());
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String changeDateByCmd(String newDate) {
		BufferedReader br = null;
		try {
			// String [] command={"date","-s",newDate};
			Process ps = Runtime.getRuntime().exec(new String[] { "timedatectl", "set-time", newDate });
			br = new BufferedReader(new InputStreamReader(ps.getInputStream(), "UTF-8"));
			if (br.readLine() != null) {
				return "修改失败";
			}
			Runtime.getRuntime().exec("clock -w");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "修改成功";
	}
}
