package com.comcast.crm.contacttest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;
import com.comcast.crem.basetest.BaseClass234;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;


public class CreateContact2 extends BaseClass234 {

	@Test(groups = {"smokeTest"})
	public void createContact() {
		ContactPage contact = page.getContacts();
		CreatingNewContactPage createCnt = page.getCreateContact();
		ContactInformationPage cntInfo = page.getContactInfo();

		Assert.assertTrue(driver.getTitle().contains("Commercial"));
		Assert.assertTrue(driver.getTitle().contains("Home"));
		
		home.clickContactLnk();
		
		Assert.assertTrue(driver.getTitle().contains("Contacts"));

		contact.clickCreateCntBtn();
		
		String createContactHeader = createCnt.getHeader().getText();
		Assert.assertTrue(createContactHeader.contains("Creating"));
			
		String contactLastName = eLib.getDataFromExcel("contact", 1, 2);
		createCnt.getLstName().sendKeys(contactLastName);
		createCnt.clickSaveBtn();
		
		//verify the header
		String title = cntInfo.getHeader().getText();
		
		if(title.contains(contactLastName)) {
			eLib.setDataIntoExcel("contact", 1, 4, "PASS");
			System.out.println("Test case passed :)");
		}else {
			eLib.setDataIntoExcel("contact", 1, 4, "FAIL");
			System.out.println("Test case failed :( ");
		}
	}
}
