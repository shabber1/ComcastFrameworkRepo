package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplimentationClass implements ITestListener,ISuiteListener{
	public ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./Advancereport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setReportName("crm report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("OS Version", System.getProperty("os.version"));
		report.setSystemInfo("JDK Version", System.getProperty("java.specification.version"));
		report.setSystemInfo("Author", System.getProperty("user.name"));
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+">===START===");
		test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO,result.getMethod().getMethodName()+"===> STARTED <===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+">===END===");
		test.log(Status.PASS,result.getMethod().getMethodName()+"===> COMPLETED <===");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts=(TakesScreenshot)(BaseClass.sdriver); 
		String filepath=ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName()+"===> FAILED <===");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getMethod().getMethodName()+"===> SKIPPED <===");
	}
}
