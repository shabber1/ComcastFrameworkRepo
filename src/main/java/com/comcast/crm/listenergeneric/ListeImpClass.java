package com.comcast.crm.listenergeneric;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class ListeImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;
	public static ExtentTest stest;
	
	@Override
	public void onStart(ITestContext context) {
		spark = new ExtentSparkReporter("./AdvanceReports/reports.html");
		spark.config().setDocumentTitle("VTiger CRM Project Reports");
		spark.config().setReportName("VTiger");
		spark.config().setTheme(Theme.DARK);
		
		reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", System.getProperty("os.name"));
		reports.setSystemInfo("OS Version", System.getProperty("os.version"));
		reports.setSystemInfo("JDK Version", System.getProperty("java.vm.specification.version"));
		reports.setSystemInfo("Author ",System.getProperty("user.name"));	
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		Capabilities cap = ((RemoteWebDriver) BaseClass.sdriver).getCapabilities();
		reports.setSystemInfo("Browser Name", cap.getBrowserName());
		reports.setSystemInfo("Browser Version", cap.getBrowserVersion());
		
		
		String methodName = result.getMethod().getMethodName();
		test = reports.createTest(methodName);
		stest = test;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.pass(methodName+ " PASS");
	}

	@Override
	public void onTestFailure(ITestResult result) {	
		
		String mname = result.getMethod().getMethodName();
		test.fail(result.getThrowable());
		
		test.fail(mname+ " method is FAILED");
//		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
//		String srcFile = ts.getScreenshotAs(OutputType.BASE64);
//		try {
//			FileUtils.copyFile(srcFile, new File("./screenshots/"+mname+".png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	    test.addScreenCaptureFromBase64String(new WebDriverUtility().getScreenshotPage(BaseClass.sdriver));
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.skip(methodName + " Skipped");
		test.fail(result.getThrowable());
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		reports.flush();
	}
	
	
}
