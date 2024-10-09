package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.listenergeneric.ListenerPraticeImpClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class OrgModule extends BaseClass {
	@Test(groups = {"smokeTest"})
	public void createOrgTest() {
		OrganizationPage org = page.getOrganization();
		CreatingNewOrganizationPage createOrg = page.getCreateOrg();
		OrganizationInformationPage orgInfo = page.getOrgInfo();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Home page displayed");
		String welPageTitle = driver.getTitle();
		
		String homePageTitle = driver.getTitle();
		Assert.assertTrue(homePageTitle.contains("Home"));
	
		home.clickOrgLnk();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Organization page displayed");
		String orgPageTitle = driver.getTitle();
		Assert.assertTrue(orgPageTitle.contains("Organization"));
		
		org.getCretaeOrgBtn();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Organization creation page displayed");
		String createOrgHeader = createOrg.getHeader().getText();
		Assert.assertTrue(createOrgHeader.contains("Creating"));
		
		String orgName = eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNum();
		
		createOrg.getOrgName().sendKeys(orgName);
		createOrg.clickSaveBtn();
		
		//verify the header
		ListenerPraticeImpClass.stest.log(Status.INFO, "Organization info page displayed");
		String title = orgInfo.getHeader().getText();
		Assert.assertTrue(title.contains(orgName));
		
		if(welPageTitle.contains("Commercial")) {
			eLib.setDataIntoExcel("org", 1, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			eLib.setDataIntoExcel("org", 1, 5, "FAIL");
			System.out.println("Login page not displayed...(");
		}
	}

	@Test(groups = {"regressionTest"})
	public void createOrgWithIndustry(){
		OrganizationPage org = page.getOrganization();
		CreatingNewOrganizationPage createOrg = page.getCreateOrg();
		OrganizationInformationPage orgInfo = page.getOrgInfo();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Home page displayed");
		Assert.assertTrue(driver.getTitle().contains("Home"));
	
		home.clickOrgLnk();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org page displayed");
		Assert.assertTrue(driver.getTitle().contains("Organization"));
		
		org.getCretaeOrgBtn();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org creation page displayed");
		Assert.assertTrue(driver.getTitle().contains("Creating"));
		
		String orgName = eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNum();
		String industryText = eLib.getDataFromExcel("org", 4, 3);
		String typeText = eLib.getDataFromExcel("org", 4, 4);
		
		createOrg.getOrgName().sendKeys(orgName);
		
		WebElement industry = createOrg.getIndustryName();
		dLib.select(industryText, industry);
		
		WebElement type = createOrg.getAcType();
		dLib.select(typeText, type);
		
		createOrg.clickSaveBtn();
		
		//verify the header
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org info page displayed");
		String industryVerify = orgInfo.getIndustryCheck().getText();
		String typeVerify = orgInfo.getTypeCheck().getText();
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(industryVerify.contains(industryText));
		soft.assertTrue(industryVerify.contains(industryText));
		soft.assertTrue(typeVerify.contains(typeText));
		ListenerPraticeImpClass.stest.log(Status.PASS, "Industry information verified :) ");
		
		ListenerPraticeImpClass.stest.log(Status.PASS, "Type information verified :) ");

		
	}
	
	@Test(groups = {"regressionTest"})
	public void createOrgWithPhnoTest() {
		
		OrganizationPage org = page.getOrganization();
		CreatingNewOrganizationPage createOrg = page.getCreateOrg();
		OrganizationInformationPage orgInfo = page.getOrgInfo();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Home page displayed");
		Assert.assertTrue(driver.getTitle().contains("Home"), "Home page is displayed");
	
		home.clickOrgLnk();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org page displayed");
		Assert.assertTrue(driver.getTitle().contains("Organizations"), "Contacts Page is Displayed");
		
		org.getCretaeOrgBtn();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org Creating page displayed");
		String createOrgHeader = createOrg.getHeader().getText();
		Assert.assertTrue(createOrgHeader.contains("Creating"), "Creating contact page is displayed");
			
		String orgName = eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNum();
		String number =  eLib.getDataFromExcel("org", 7, 3);
		
		createOrg.getOrgName().sendKeys(orgName);
		createOrg.getPhnNum().sendKeys(number);
		createOrg.clickSaveBtn();
		
		//verify the header
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org info page displayed");
		String phnoVerify = orgInfo.getPhone().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(phnoVerify.contains(number), phnoVerify + " Phone is verified");
		
		if(driver.getTitle().contains("Commercial")) {
			eLib.setDataIntoExcel("org", 7, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			eLib.setDataIntoExcel("org", 7, 5, "Fail");
			System.out.println("Login page not displayed...(");
		}
		soft.assertAll();
	}
	
	@Test(groups = {"regressionTest"})
	public void deleteOrgTest() throws InterruptedException {
		OrganizationPage org = page.getOrganization();
		CreatingNewOrganizationPage createOrg = page.getCreateOrg();
		OrganizationInformationPage orgInfo = page.getOrgInfo();
		
		ListenerPraticeImpClass.stest.log(Status.INFO, "Home page displayed");
		Assert.assertTrue(driver.getTitle().contains("Home"), "Home page is displayed");

		home.clickOrgLnk();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org page displayed");
		org.getCretaeOrgBtn();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org Creating page displayed");
		String orgName = eLib.getDataFromExcel("org", 10, 2)+jLib.getRandomNum();
		String num = eLib.getDataFromExcel("org", 10, 3);
		createOrg.getOrgName().sendKeys(orgName);
		createOrg.getPhnNum().sendKeys(num);
		createOrg.clickSaveBtn();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org info page displayed");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement orglink = org.getOrgLink();
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org page displayed");
		Thread.sleep(2000);
		js.executeScript("arguments[0].click()", orglink);
		
		org.getSearchBar().sendKeys(orgName);
		WebElement searchDropdown = org.getSearchDD();
		dLib.select("Organization Name", searchDropdown);
		org.getSearchBarBtn();
		
		//verification organization is added or not
		String orgVerify = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr/td/a[.='"+orgName+"']")).getText();
		boolean status = orgName.equals(orgVerify);
		Assert.assertTrue(status, orgName + " is a added to Vtiger");
		
		// delete the organization
		
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr/td/a[.='del']")).click();
		dLib.alertHandle("ok");
		ListenerPraticeImpClass.stest.log(Status.INFO, "Org is deleted ");
		if(orgName.equals(orgVerify)) {
			System.out.println("Organization is not deleted to Vtiger : "+orgVerify);
		}else {
			System.out.println("Deleted to Vtiger : "+orgVerify);
		}
		
		if(driver.getTitle().contains("Commercial")) {
			eLib.setDataIntoExcel("org", 10, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			eLib.setDataIntoExcel("org", 10, 5, "Fail");
			System.out.println("Login page not displayed...(");
		}
	}

}
