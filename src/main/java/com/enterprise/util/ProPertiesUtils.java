package com.enterprise.util;

import com.enterprise.interceptor.ConfigurationManager;

import java.io.IOException;

public class ProPertiesUtils {

	
	public static String getProperties(String filename,String name){
		
		
      java.util.Properties props=new java.util.Properties();
		
		try {
			props.load(ConfigurationManager.class.getResourceAsStream(filename));
			return props.getProperty(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
}
