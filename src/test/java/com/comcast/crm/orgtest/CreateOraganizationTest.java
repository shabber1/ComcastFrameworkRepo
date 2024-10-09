package com.comcast.crm.orgtest;

import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOraganizationTest extends BaseClass {
	@Test(groups = {"smokeTest"})
	public void createOrgTest() {
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
		
		String orgName = eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNum();
		
		createOrg.getOrgName().sendKeys(orgName);
		createOrg.clickSaveBtn();
		
		//verify the header
		String title = orgInfo.getHeader().getText();
		
		if(title.contains(orgName)) {
			System.out.println("Test case passed :)");
		}else {
			System.out.println("Test case failed :( ");
		}
		
		if(welPageTitle.contains("Commercial")) {
			eLib.setDataIntoExcel("org", 1, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			eLib.setDataIntoExcel("org", 1, 5, "FAIL");
			System.out.println("Login page not displayed...(");
		}
	}

}
