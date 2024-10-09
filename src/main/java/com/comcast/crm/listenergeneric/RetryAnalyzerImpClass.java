package com.comcast.crm.listenergeneric;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImpClass implements IRetryAnalyzer {
	
	int count = 0;
	int repeat = 5;
	boolean flag = false;
	@Override
	public boolean retry(ITestResult result) {
		if(count < repeat) {
			count++;
			return flag = true;
		}
		return false;
	}
	

}
