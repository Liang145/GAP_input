package com.jet.vframe.sys.tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class SP {

	private SP() {

	}
	private static Properties sysProp;
	private static String sysFilePath;

	public static String getSystemValue(String key) {
		if (sysProp != null) {
			return sysProp.getProperty(key);
		} else {
			if (sysFilePath == null) {
				try {
					sysFilePath = Thread.currentThread().getContextClassLoader().getResource("system.properties").toURI().getPath();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			sysProp = new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(sysFilePath);
				sysProp.load(fis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("**read system.prop exception:");
				e.printStackTrace();
				return null;
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return sysProp.getProperty(key);
		}

	}
}
