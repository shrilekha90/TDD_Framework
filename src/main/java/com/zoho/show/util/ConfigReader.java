package com.zoho.show.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	static Properties prop;
	static String relativePath = TestUtils.getRelativePath();
	
	/**
	 * This method is used to load the properties from config.properties file
	 * 
	 * 
	 */
	public static Properties init_prop() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(relativePath+"\\src\\test\\resources\\config\\config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * Get property value
	 * 
	 * @param key property 
	 * 
	 * @return value of the the property
	 */
	public static String getValue(String key) {
		return prop.getProperty(key);
	}

}
