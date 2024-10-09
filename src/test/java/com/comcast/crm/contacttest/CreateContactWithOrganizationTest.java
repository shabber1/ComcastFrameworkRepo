package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;

public class CreateContactWithOrganizationTest extends BaseClass {
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest(){
		
		ContactPage contact = page.getContacts();
		CreatingNewContactPage createCnt = page.getCreateContact();
		ContactInformationPage cntInfo = page.getContactInfo();
		
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
	
		home.clickContactLnk();
		
		String contactPageTitle = driver.getTitle();
		if(contactPageTitle.contains("Contacts")) {
			System.out.println("Contacts Page displayed...");
		}else {
			System.out.println("Contacts page not displayed...(");
		}
		contact.clickCreateCntBtn();
		
		String createContactHeader = driver.findElement(By.className("lvtHeaderText")).getText();
		if(createContactHeader.contains("Creating")) {
			System.out.println("Creating new Contact Page displayed...");
		}else {
			System.out.println("Creating new Contact page not displayed...(");
		}
		
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3)+jLib.getRandomNum();
		String parentWindowId = driver.getWindowHandle();
		
		createCnt.getLstName().sendKeys(contactLastName);
		createCnt.getOrgNamePlusBtn();
		
		dLib.switchToWindowOnURL("module=Accounts");
		
		String orgName = eLib.getDataFromExcel("contact", 7, 2);
		driver.findElement(By.xpath("//a[contains(.,'"+orgName+"')]")).click();
		
		driver.switchTo().window(parentWindowId);
		
		createCnt.clickSaveBtn();
		
		//verify the contact with Organization Name
		String orgNameVerify =  cntInfo.getOrgNameTxt().getText();
		
		if(orgNameVerify.contains(orgName)) {
			eLib.setDataIntoExcel("contact", 7, 4, "PASS");
			System.out.println(orgNameVerify+" Testcase verified passed :)");
		}else {
			eLib.setDataIntoExcel("contact", 7, 4, "FAIL");
			System.out.println(orgNameVerify+" Testcase failed :( ");
		}
	}

}
