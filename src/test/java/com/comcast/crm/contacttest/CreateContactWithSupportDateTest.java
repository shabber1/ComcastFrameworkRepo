	package com.comcast.crm.contacttest;
	
	import org.openqa.selenium.WebElement;
	import org.testng.annotations.Test;
	
	import com.comcast.crem.basetest.BaseClass;
	import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
	import com.comcast.crm.objectrepositoryutility.ContactPage;
	import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
	
	public class CreateContactWithSupportDateTest extends BaseClass {
		
		@Test(groups = {"regressionTest"})
	
		public void contactWitchSupportDateTest(){
			
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
			
			String createContactHeader = createCnt.getHeader().getText();
			if(createContactHeader.contains("Creating")) {
				System.out.println("Creating new Contact Page displayed...");
			}else {
				System.out.println("Creating new Contact page not displayed...(");
			}
			
			String currentDate = jLib.getCurrentDateYYYYMMDD();
			String reqDate = jLib.getRequiredDate(30);
			
			String contactLastName = eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNum();
			
			System.out.println(contactLastName);
			createCnt.getLstName().sendKeys(contactLastName);
			
			WebElement presentDate = createCnt.getStartDateEdt();
			presentDate.clear();
			presentDate.sendKeys(currentDate);
			
			WebElement exeDate = createCnt.getEndDateEdt();
			exeDate.clear();
			exeDate.sendKeys(reqDate);
			createCnt.clickSaveBtn();
	
			//verify the header
			String verifyCurDt = cntInfo.getStartDateText().getText();
			String verifyreqDt = cntInfo.getEndDateText().getText();
			
			if(verifyCurDt.contains(currentDate)) {
				
				System.out.println(verifyCurDt + " Current Date information added successfully");
			}else {
				System.out.println("Current Date information not added successfully");
			}
			if(verifyreqDt.contains(reqDate)) {
				System.out.println(verifyreqDt + " Support Date information added successfully");
			}else {
				
				System.out.println("Support Date information not added successfully");
			}
			
			if(welPageTitle.contains("Commercial")) {
				eLib.setDataIntoExcel("contact", 4, 4, "PASS");
				System.out.println("Logged out successfully");
			}else {
				eLib.setDataIntoExcel("contact", 4, 4, "FAIL");
				System.out.println("Login page not displayed...(");
			}
		}
	
	}
