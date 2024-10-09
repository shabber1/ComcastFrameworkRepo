package com.comcast.crem.basetest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.IConstantPath;
import com.comcast.crm.generic.fileUtility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.PageObjectManager;

public class BaseClass234 {
	public DatabaseUtility dbLib;
	public ExcelUtility eLib;
	public PropertiesFileUtility pLib;
	public WebDriverUtility dLib;
	
	public WebDriver driver;
	
	public static WebDriver sdriver=null;
	
	//public static WebDriver sdriver;
	
	public HomePage home;
	public LoginPage login;
	public OrganizationPage org;
	public CreatingNewOrganizationPage createOrg;
	public OrganizationInformationPage orgInfo;
	public ContactPage contact;
	public CreatingNewContactPage createCnt;
	public ContactInformationPage ctnInfo;
	public PageObjectManager page;
	
	
	
	@BeforeSuite
	public void configSuite() {
		
		dbLib = new DatabaseUtility();
		dbLib.getDBConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
		
	}
	@BeforeClass
	public void classConfig() {
		eLib = new ExcelUtility();
		pLib = new PropertiesFileUtility();
		dLib = new WebDriverUtility();
		
//		home = new HomePage(driver);
//		login = new LoginPage(driver);
//		org = new OrganizationPage(driver);
//		createOrg = new CreatingNewOrganizationPage(driver);
//		orgInfo = new OrganizationInformationPage(driver);
//		contact = new ContactPage(sdriver);
//		createCnt = new CreatingNewContactPage(driver);
//		ctnInfo = new ContactInformationPage(driver);
		
		pLib.propertiesInit(IConstantPath.propertiesPath);
		eLib.excelInit(IConstantPath.excelPath);
		String BROWSER = pLib.getDataFromPropertiesFile("browser");
		
		driver = dLib.launchBrowser(BROWSER);
		
		
		

		eLib.excelInit("resourse/testScriptData.xlsx");
		sdriver = driver;
	}
	
	@BeforeMethod
	public void methodConfig() {
		
		page = new PageObjectManager(driver);
		login = page.getLogin();
		home = page.getHome();
		
		String URL = pLib.getDataFromPropertiesFile("url");
		String USERNAME = pLib.getDataFromPropertiesFile("username");
		String PASSWORD = pLib.getDataFromPropertiesFile("password");
		String timeout = pLib.getDataFromPropertiesFile("timeouts");
		int time = Integer.parseInt(timeout);
		
		driver.get(URL);
		
		login.loginToVtiger(USERNAME, PASSWORD);	
	}
	
	@AfterMethod
	public void methodTearDown() {
		eLib.saveToExcel();
		home.signOutOfVtiger(dLib);
	}
	
	@AfterClass
	public void classTearDown() {
		eLib.closeExcel();
		dLib.closeAllWindows();
	}
	@AfterSuite
	public void suiteTearDown() {
		dbLib.closeDBconnection();
	}

}
