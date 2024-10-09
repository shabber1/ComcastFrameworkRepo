package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.IConstantPath;
import com.comcast.crm.generic.fileUtility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.mysql.cj.util.Util;

public class CreateOraganizationWithIndustryTypeTest extends BaseClass {
	@Test(groups = {"regressionTest"})
	public void createOrgWithIndustry(){
		OrganizationPage org = page.getOrganization();
		CreatingNewOrganizationPage createOrg = page.getCreateOrg();
		OrganizationInformationPage orgInfo = page.getOrgInfo();
		
		String welPageTitle = driver.getTitle();
		if(welPageTitle.contains("Commercial")) {
			System.out.println("Login Page displayed...");
		}else {
			System.out.println("Login page not displayed...(");
		}
		
		String homePageTitle = driver.getTitle();
		if(homePageTitle.contains("Home")) {
			System.out.println("Home Page displayed...");
		}else {
			System.out.println("Home page not displayed...(");
		}
	
		home.clickOrgLnk();
		
		String orgPageTitle = driver.getTitle();
		if(orgPageTitle.contains("Organization")) {
			System.out.println("Organization Page displayed...");
		}else {
			System.out.println("Organization page not displayed...(");
		}
		
		org.getCretaeOrgBtn();
		
		String createOrgHeader = createOrg.getHeader().getText();
		if(createOrgHeader.contains("Creating")) {
			System.out.println("Creating new organization Page displayed...");
		}else {
			System.out.println("Creating new organization page not displayed...(");
		}
		
		String orgName = eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNum();
		String industryText = eLib.getDataFromExcel("org", 4, 3);
		String typeText = eLib.getDataFromExcel("org", 4, 4);
		
		createOrg.getOrgName().sendKeys(orgName);
		
		WebElement industry = createOrg.getIndustryName();
		dLib.select(industryText, industry);
		
		WebElement type = createOrg.getAcType();
		dLib.select(typeText, type);
		
		createOrg.clickSaveBtn();
		
		//verify the header
		
		String industryVerify = orgInfo.getIndustryCheck().getText();
		String typeVerify = orgInfo.getTypeCheck().getText();
		
		
		if(industryVerify.contains(industryText)) {
			System.out.println("Industry information verified :) " + industryVerify);
		}else {
			eLib.setDataIntoExcel("org", 4, 5, "FAIL");
			System.out.println("Industry verify  failed :( " );
		}
		if(typeVerify.contains(typeText)) {
			System.out.println("Type information verified :) " + typeVerify);
		}else {
			eLib.setDataIntoExcel("org", 4, 5, "FAIL");
			System.out.println("type verify  failed :( ");
		}

		if(welPageTitle.contains("Commercial")) {
			eLib.setDataIntoExcel("org", 4, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			System.out.println("Login page not displayed...(");
		}

		
	}

}
