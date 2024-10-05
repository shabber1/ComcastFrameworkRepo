package com.comcast.crm.objectrepository.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//span[@class='small']/preceding::span[@class='dvHeaderText']")
	private WebElement headermsg;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industrynamemsg;
	
	@FindBy(id="dtlview_Type")
	private WebElement typenamemsg;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phonemsg;
	
	public WebElement getPhonemsg() {
		return phonemsg;
	}


	public WebElement getIndustrynamemsg() {
		return industrynamemsg;
	}


	public WebElement getTypenamemsg() {
		return typenamemsg;
	}
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgnamemsg;
	
	
	public WebElement getOrgnamemsg() {
		return orgnamemsg;
	}


	public WebElement getHeadermsg() {
		return headermsg;
	}
	
}
