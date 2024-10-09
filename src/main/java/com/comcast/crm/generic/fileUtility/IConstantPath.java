package com.comcast.crm.generic.fileUtility;

public interface IConstantPath {
	
	String jdbc_url = "jdbc:mysql://localhost/3306/advsel";
	String excelPath = System.getProperty("user.dir")+"/resourse/testScriptData.xlsx";
	String propertiesPath = System.getProperty("user.dir")+"/configAppData/commonData.properties";
	

}
