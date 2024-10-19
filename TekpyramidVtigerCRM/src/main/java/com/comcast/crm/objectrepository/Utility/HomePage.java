package com.comcast.crm.objectrepository.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="Organizations")
	private WebElement orglink;
	
	@FindBy(xpath="//img[@style='padding: 0px;padding-left:5px']/parent::td")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signoutlink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText="More")
	private WebElement morelink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignslink;

	public WebElement getMorelink() {
		return morelink;
	}

	public WebElement getCampaignslink() {
		return campaignslink;
	}

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}
	
	public void navigatetocampaginpage() {
		Actions act=new Actions(driver);
		act.moveToElement(morelink).perform();
		campaignslink.click();
	}
	public void logout() throws InterruptedException {
		Actions act=new Actions(driver);
		act.moveToElement(adminImg).perform();
		Thread.sleep(2000);
		signoutlink.click();
	}
}
