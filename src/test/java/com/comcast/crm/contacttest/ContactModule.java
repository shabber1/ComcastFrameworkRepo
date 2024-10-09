package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.listenergeneric.ListenerPraticeImpClass;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;

public class ContactModule extends BaseClass {

	@Test(groups = { "smokeTest" }, retryAnalyzer = com.comcast.crm.listenergeneric.RetryAnalyzerImpClass.class)
	public void createContact() {

		ContactPage contact = page.getContacts();
		CreatingNewContactPage createCnt = page.getCreateContact();
		ContactInformationPage cntInfo = page.getContactInfo();
		
		Assert.assertTrue(driver.getTitle().contains("Commercial"));
		ListenerPraticeImpClass.stest.log(Status.INFO, "Home page displayed");
		Assert.assertTrue(driver.getTitle().contains("Home"));

		home.clickContactLnk();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact page displayed");
		boolean status = driver.getTitle().contains("Contacts"); 
		Assert.assertTrue(status);

		contact.clickCreateCntBtn();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact creation page displayed");
		String createContactHeader = createCnt.getHeader().getText();
		Assert.assertTrue(createContactHeader.contains("Creating"));

		String contactLastName = eLib.getDataFromExcel("contact", 1, 2);
		createCnt.getLstName().sendKeys(contactLastName);
		createCnt.clickSaveBtn();

		// verify the header
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact info page displayed");
		String title = cntInfo.getHeader().getText();

		if (title.contains(contactLastName)) {
			eLib.setDataIntoExcel("contact", 1, 4, "PASS");
			System.out.println("Test case passed :)");
		} else {
			eLib.setDataIntoExcel("contact", 1, 4, "FAIL");
			System.out.println("Test case failed :( ");
		}
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() {

		ContactPage contact = page.getContacts();
		CreatingNewContactPage createCnt = page.getCreateContact();
		ContactInformationPage cntInfo = page.getContactInfo();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Home page displayed");
		Assert.assertTrue(driver.getTitle().contains("Commercial"));
		Assert.assertTrue(driver.getTitle().contains("Home"));

		home.clickContactLnk();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact page displayed");
		boolean status = driver.getTitle().contains("Contacts");
		Assert.assertTrue(status);
		contact.clickCreateCntBtn();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact creation page displayed");
		String createContactHeader = createCnt.getHeader().getText();
		Assert.assertTrue(createContactHeader.contains("Creating"));

		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNum();
		String parentWindowId = driver.getWindowHandle();

		createCnt.getLstName().sendKeys(contactLastName);
		createCnt.getOrgNamePlusBtn();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org page displayed");
		dLib.switchToWindowOnURL("module=Accounts");

		String orgName = eLib.getDataFromExcel("contact", 7, 2);
		driver.findElement(By.xpath("//a[contains(.,'" + orgName + "')]")).click();

		driver.switchTo().window(parentWindowId);

		createCnt.clickSaveBtn();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact info page displayed");
		// verify the contact with Organization Name
		String orgNameVerify = cntInfo.getOrgNameTxt().getText();

		if (orgNameVerify.contains(orgName)) {
			eLib.setDataIntoExcel("contact", 7, 4, "PASS");
			System.out.println(orgNameVerify + " Testcase verified passed :)");
		} else {
			eLib.setDataIntoExcel("contact", 7, 4, "FAIL");
			System.out.println(orgNameVerify + " Testcase failed :( ");
		}
		
	}

	@Test(groups = { "regressionTest" })

	public void contactWitchSupportDateTest() {

		ContactPage contact = page.getContacts();
		CreatingNewContactPage createCnt = page.getCreateContact();
		ContactInformationPage cntInfo = page.getContactInfo();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Home page displayed");
		Assert.assertTrue(driver.getTitle().contains("Commercial"));
		Assert.assertTrue(driver.getTitle().contains("Home"), "Home page is displayed");
		
		home.clickContactLnk();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact page displayed");
		boolean status = driver.getTitle().contains("Contacts");
		Assert.assertTrue(status, "Contacts Page is Displayed");
		
		contact.clickCreateCntBtn();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact creation page displayed");
		String createContactHeader = createCnt.getHeader().getText();
		Assert.assertTrue(createContactHeader.contains("Creating"), "Creating contact page is displayed");
		
		String currentDate = jLib.getCurrentDateYYYYMMDD();
		String reqDate = jLib.getRequiredDate(30);

		String contactLastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNum();

		System.out.println(contactLastName);
		createCnt.getLstName().sendKeys(contactLastName);

		WebElement presentDate = createCnt.getStartDateEdt();
		presentDate.clear();
		presentDate.sendKeys(currentDate);

		WebElement exeDate = createCnt.getEndDateEdt();
		exeDate.clear();
		exeDate.sendKeys(reqDate);
		createCnt.clickSaveBtn();
		
		Assert.fail();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Contact info page displayed");
		// verify the header
		String verifyCurDt = cntInfo.getStartDateText().getText();
		String verifyreqDt = cntInfo.getEndDateText().getText();
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(verifyCurDt.contains(currentDate), verifyCurDt + " Current Date information added successfully");
		soft.assertTrue(verifyreqDt.contains(reqDate), verifyreqDt + " Support Date information added successfully ");
	
		if (driver.getTitle().contains("Commercial")) {
			eLib.setDataIntoExcel("contact", 4, 4, "PASS");
			System.out.println("Logged out successfully");
		} else {
			eLib.setDataIntoExcel("contact", 4, 4, "FAIL");
			System.out.println("Login page not displayed...(");
		}
		
	}

}
