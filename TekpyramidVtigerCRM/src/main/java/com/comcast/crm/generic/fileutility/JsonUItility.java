package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUItility {
	
	public String getDataFromJsonFile(String key) throws Throwable, Exception, ParseException {
		FileReader filr=new FileReader("./src/test/resources/jsoncommandata.json");
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(filr);
		JSONObject map=(JSONObject)obj;	
		String data=(String) map.get(key);
		return data;	
}
}
