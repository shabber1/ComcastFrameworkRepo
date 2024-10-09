package other.pratice.programs;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ReportsImp  {

	@Test
	public void test() {
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReports/rep123.html");
		spark.config().setDocumentTitle("Sample Test Scripts");
		spark.config().setReportName("VTiger");
		spark.config().setTheme(Theme.DARK);
		
		
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS ", System.getProperty("os.name"));
		reports.setSystemInfo("OS Version ", System.getProperty("os.version"));
		reports.setSystemInfo("JDK Version ", System.getProperty("java.specification.version"));
		reports.setSystemInfo("Author ", System.getProperty("user.name"));
		
		ExtentTest test = reports.createTest("test");
		WebDriver driver= new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		String file = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(file);
		
		reports.flush();
		
	}

}
