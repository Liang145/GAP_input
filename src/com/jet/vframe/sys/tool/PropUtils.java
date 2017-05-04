package com.jet.vframe.sys.tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropUtils {

	/**
	 * 获取Properties对象
	 * @param filePath
	 * @return
	 */
	public static Properties load(String filePath) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return prop;
	}

	//
	/**
	 * 把Properties写入到文件
	 * @param prop
	 * @param filePath
	 * @return
	 */
	public static boolean write(Properties prop, String filePath) {
		boolean mark = false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			prop.store(fos, "Copyright (c) gap");
			mark = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mark;
	}
	
	// 根据key值获取value值
		public String getValueByKey(String filePath, String key) {
			Properties prop = new Properties();
			String value = null;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(filePath);
				prop.load(fis);
				if (key != null) {
					value = prop.getProperty(key);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return value;
		}

		// 根据key集合获取value集合
		public List<String> getValueListBKeyList(String filePath, List<String> keyList) {
			Properties prop = new Properties();
			List<String> valueList = new ArrayList<String>();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(filePath);
				prop.load(fis);
				if (keyList != null && keyList.size() > 0) {
					for (String key : keyList) {
						valueList.add(prop.getProperty(key));
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return valueList;
		}

}
