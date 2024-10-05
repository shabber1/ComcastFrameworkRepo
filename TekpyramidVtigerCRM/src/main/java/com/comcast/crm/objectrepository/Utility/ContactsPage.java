package com.comcast.crm.objectrepository.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	//Declaration
		@FindBy(xpath="//img[@alt='Create Contact...']")
		private WebElement createContactBTN;
		
		//Initilization
		public ContactsPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		//utilization
		/**
		 * this method clicks on the create contact button
		 */
		public void clickCreateContactBTN() {
			createContactBTN.click();
		}
}