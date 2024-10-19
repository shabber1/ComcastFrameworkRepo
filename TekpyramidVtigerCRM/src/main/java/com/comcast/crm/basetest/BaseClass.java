package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.Utility.HomePage;
import com.comcast.crm.objectrepository.Utility.LoginPage;

public class BaseClass{
	public DataBaseUtility dblib=new DataBaseUtility();
	public PropertiesFileUtility flib=new PropertiesFileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	
	
	@BeforeSuite(groups={"regressionTest","smokeTest"})
	public void configBS() {
		System.out.println("===Connect to DB , Report config===");
		dblib.getDbconnection();
	}
	//parameters are read from suite file i.e., xml file only for cross browser testing
//	@Parameters("BROWSER")
//	@BeforeClass(groups={"regressionTest","smokeTest"})
//	public void configBC(String browser) throws Throwable {
//	System.out.println("==Launching "+ browser +" BROWSER==");
	
	//parameters to read from properties file
	@BeforeClass(groups={"regressionTest","smokeTest"})
	public void configBC() throws Throwable {
		//String browser=flib.getDataFromPropertiesFile("browser");
		String browser=System.getProperty("browser" ,flib.getDataFromPropertiesFile("browser"));
		String BROWSER =browser;
		if(BROWSER.equals("chrome"))
			driver=new ChromeDriver();
			else if (BROWSER.equals("firefox"))
				driver=new FirefoxDriver();
			else if(BROWSER.equals("edge"))
				driver=new EdgeDriver();
			else
				driver=new ChromeDriver();
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	@BeforeMethod(groups={"regressionTest","smokeTest"})
	public void configBM() throws Throwable {
		System.out.println("==login==");
//		String url=flib.getDataFromPropertiesFile("url");
//		String username=flib.getDataFromPropertiesFile("username");
//		String password=flib.getDataFromPropertiesFile("password");
		String url=System.getProperty("url" ,flib.getDataFromPropertiesFile("url"));
		String username=System.getProperty("username" ,flib.getDataFromPropertiesFile("username"));
		String password=System.getProperty("password" ,flib.getDataFromPropertiesFile("password"));

		LoginPage lp=new LoginPage(driver);
		lp.logintoapp(url,username,password);
	}
	@AfterMethod(groups={"regressionTest","smokeTest"})
	public void congigAM() throws InterruptedException {
		System.out.println("==logout==");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	@AfterClass(groups={"regressionTest","smokeTest"})
	public void configAC() {
		System.out.println("==close the browser==");
		driver.quit();
	}
	@AfterSuite(groups={"regressionTest","smokeTest"})
	public void configAS() throws SQLException {
		System.out.println("==close DB , Report backUP==");
		dblib.closedbconnection();
	}
}