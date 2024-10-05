package com.comcast.crm.objectrepository.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="accountname")
	private WebElement orgnameEdt;
	
	@FindBy(xpath="//input[@id='phone']")
	private WebElement phonenumEdit;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	@FindBy(name="accounttype")
	private WebElement accounttypeDB;
	
	
	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}	

	public WebElement getPhonenumEdit() {
		return phonenumEdit;
	}
	public void createorg(String orgname) {
		orgnameEdt.sendKeys(orgname);
		saveBtn.click();
		}
	
	public void createorg(String orgname,String phonenumber) {
		orgnameEdt.sendKeys(orgname);
		phonenumEdit.sendKeys(phonenumber);
		saveBtn.click();
	}
	public void createorg(String orgname , String industry,String type) {
		orgnameEdt.sendKeys(orgname);
		Select sel=new Select(industryDB);
		sel.selectByVisibleText(industry);
		Select sel1=new Select(accounttypeDB);
		sel1.selectByVisibleText(type);
		saveBtn.click();
	}
}
