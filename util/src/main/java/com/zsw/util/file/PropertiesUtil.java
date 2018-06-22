package com.zsw.util.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil {

	private String fileName;//完整路径
	private Properties prop;
	private InputStream in;

	public PropertiesUtil(String fileName) {
		this.fileName = fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("file don't exists");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		prop = new Properties();
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} /// 加载属性列表
	}

	public String get(String key) {
		return prop.getProperty(key);
	}

	
	

	protected void release(){
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 写入properties信息
	public void writeProperties(String parameterName, String parameterValue) {
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(fileName);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(fileName);
			prop.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, null);
		} catch (IOException e) {
			System.err.println("Visit " + fileName + " for updating " + parameterName + " value error");
		}
	}

}
