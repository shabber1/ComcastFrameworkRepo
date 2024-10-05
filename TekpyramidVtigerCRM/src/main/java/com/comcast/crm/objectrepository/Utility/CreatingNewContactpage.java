package com.comcast.crm.objectrepository.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class CreatingNewContactpage {
	

	WebDriver driver;
	public CreatingNewContactpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;

	@FindBy(name = "lastname")
	private WebElement contactLastNameTF;
	
	@FindBy(name="support_start_date")
	private WebElement contactStartdate;
	
	@FindBy(name="support_end_date")
	private WebElement contactEnddate;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement verifyStartdate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement verifyEnddate;
	
	@FindBy(xpath = "//input[contains(@title,'Save')]")
	private WebElement saveBTN;

	@FindBy(xpath = "//img[contains(@onclick,'Accounts')]")
	private WebElement organizationPlusBTN;
	
	@FindBy(name="search_text")
	private WebElement sendorginsearchBTN;
	
	@FindBy(name="search")
	private WebElement searchBTN;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement verifyorgname;
	
	public void SearchBTN() {
		searchBTN.click();
	}
	public void sendorginSearchBTN(String orgname) {
		sendorginsearchBTN.sendKeys(orgname);
	}
	public void clickOrgPlusBTN() {
		organizationPlusBTN.click();
	}
	public WebElement getContactStartdate() {
		return contactStartdate;
	}
	public WebElement getContactEnddate() {
		return contactEnddate;
	}
	public String getverifyStartdate() {
		return verifyStartdate.getText();
	}
	public String getverifyEnddate() {
		return verifyEnddate.getText();
	}
	public String getverifyorgname() {
		return verifyorgname.getText();
	}
	public String getPageHeader() {
		return pageHeader.getText();
	}
	public void setContactLastName(String lastname) {
		contactLastNameTF.sendKeys(lastname);
	}
	public void clickSaveBTN() {
		saveBTN.click();
	}
}