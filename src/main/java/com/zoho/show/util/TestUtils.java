package com.zoho.show.util;

import java.io.File;

public class TestUtils {
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long WAIT = 30;
	public static long FLUENT_WAIT_IN_MINUTES = 5;
	
	/**
	 * Get Relative path 
	 **/
	public static String getRelativePath() {
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		if(relativePath.endsWith("bin")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}
		return relativePath;
	}
	
	/**
	 * Get Downloads path 
	 **/
	public static String getDownloadsPath() {
		String downloadPath = new File(System.getProperty("user.home")+"/Downloads").getAbsolutePath();
		return downloadPath;
	}

}
