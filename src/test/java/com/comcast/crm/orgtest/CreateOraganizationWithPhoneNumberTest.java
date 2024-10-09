package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOraganizationWithPhoneNumberTest extends BaseClass {
	@Test(groups = {"regressionTest"})
	public void createOrgWithPhnoTest() {
		
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
		
		String createOrgHeader = driver.findElement(By.className("lvtHeaderText")).getText();
		if(createOrgHeader.contains("Creating")) {
			System.out.println("Creating new organization Page displayed...");
		}else {
			System.out.println("Creating new organization page not displayed...(");
		}
		
		
		String orgName = eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNum();
		String number =  eLib.getDataFromExcel("org", 7, 3);
		
		createOrg.getOrgName().sendKeys(orgName);
		createOrg.getPhnNum().sendKeys(number);
		createOrg.clickSaveBtn();
		
		//verify the header
		String phnoVerify = orgInfo.getPhone().getText();
	
		
		if(phnoVerify.contains(number)) {
			System.out.println("Phone number information verified :) "+ phnoVerify);
		}else {
			System.out.println("Phone number information verified  failed :( "+phnoVerify);
		}
		
		if(welPageTitle.contains("Commercial")) {
			eLib.setDataIntoExcel("org", 7, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			eLib.setDataIntoExcel("org", 7, 5, "Fail");
			System.out.println("Login page not displayed...(");
		}
	}

}
