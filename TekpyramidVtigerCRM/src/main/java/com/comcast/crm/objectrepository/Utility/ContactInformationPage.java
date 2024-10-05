package com.comcast.crm.objectrepository.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	// Declaration
	@FindBy(css = "span.dvHeaderText")
	private WebElement pageHeader;

	@FindBy(xpath = "//input[@name='Delete']")
	private WebElement deleteBTN;


	
	public String getPageHeader() {
		return pageHeader.getText();
	}

	public void clickDeleteBTN() {
		deleteBTN.click();
	}

}