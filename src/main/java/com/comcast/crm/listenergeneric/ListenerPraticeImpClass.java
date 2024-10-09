package com.comcast.crm.listenergeneric;

import java.util.Date;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ListenerPraticeImpClass implements ISuiteListener, ITestListener{
	public ExtentSparkReporter spark ;
	public ExtentReports report;
	public ExtentTest test;
	public static ExtentTest stest;
	
	public void onStart(ITestContext context) {
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		spark = new ExtentSparkReporter("./AdvanceReports/report"+time+".html");
		spark.config().setDocumentTitle("Vtiger crm project");
		spark.config().setReportName("VTiger");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("OS version", System.getProperty("os.version"));
		report.setSystemInfo("Author", System.getProperty("user.name"));
		
	}
	public void onFinish(ITestContext context) {
		report.flush();
		
	}
	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		Capabilities cap = ((RemoteWebDriver) BaseClass.sdriver).getCapabilities();
		report.setSystemInfo("Browser name", cap.getBrowserName());
		report.setSystemInfo("Browser Version", cap.getBrowserVersion());
		
		stest = test;
		
	}
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.pass(methodName + " is PASS");
		
	}
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.fail(methodName + " is FAILED");
		test.fail(result.getThrowable());
		test.addScreenCaptureFromBase64String(new WebDriverUtility().getScreenshotPage(BaseClass.sdriver));
		
	}
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.skip(methodName + " is a skipped : ");
		test.skip(result.getThrowable());
		
	}

}
