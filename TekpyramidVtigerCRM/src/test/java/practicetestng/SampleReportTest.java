package practicetestng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	public ExtentReports report;
	@BeforeSuite
	public void configBS() {
		ExtentSparkReporter spark = new ExtentSparkReporter("./Advancereport/report.html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setReportName("crm report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-10");
		report.setSystemInfo("BROWSER", "chrome-100");
	}
	@AfterSuite
	public void configAS() {
		report.flush();
	}
	@Test
		public void createContactTest() {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		String filepath= ts.getScreenshotAs(OutputType.BASE64);
		
			ExtentTest test=report.createTest("createContactTest");
			
			test.log(Status.INFO, "login to app");
			test.log(Status.INFO, "navigate to contact page");
			test.log(Status.INFO, "create contact");
			if("HDFChj".equals("HDFC")) {
				test.log(Status.PASS, "contact is created");
			}else {
				test.addScreenCaptureFromBase64String(filepath, "ErroFile");
		}
	}
	@Test
	public void createContactwithorg() {
		ExtentTest test=report.createTest("createContactwithorg");
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		}else {
			test.log(Status.FAIL, "contact is not created");
		}
	}
	@Test
	public void createContactwithphonenumber() {
		ExtentTest test=report.createTest("createContactwithphonenumber");
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		}else {
			test.log(Status.FAIL, "contact is not created");
		}
	}
}
