package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesFileUtility {
	
	public String getDataFromPropertiesFile(String key) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		String data=properties.getProperty(key);
		return data;
	
}
}
