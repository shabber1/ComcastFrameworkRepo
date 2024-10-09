package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class DeleteOrgName extends BaseClass {
	@Test(groups = {"regressionTest"})
	public void deleteOrgTest() {
		OrganizationPage org = page.getOrganization();
		CreatingNewOrganizationPage createOrg = page.getCreateOrg();
		OrganizationInformationPage orgInfo = page.getOrgInfo();
		
		String welPageTitle = driver.getTitle();
		if(welPageTitle.contains("Commercial")) {
			System.out.println("Login Page displayed...");
		}else {
			System.out.println("Login page not displayed...(");
		}
		home.clickOrgLnk();
		org.getCretaeOrgBtn();
		
		String orgName = eLib.getDataFromExcel("org", 10, 2)+jLib.getRandomNum();
		String num = eLib.getDataFromExcel("org", 10, 3);
		createOrg.getOrgName().sendKeys(orgName);
		createOrg.getPhnNum().sendKeys(num);
		createOrg.clickSaveBtn();
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//td[@class='tabSelected']/a[.='Organizations']")).click();
		
		org.getSearchBar().sendKeys(orgName);
		WebElement searchDropdown = org.getSearchDD();
		dLib.select("Organization Name", searchDropdown);
		org.getSearchBarBtn();
		
		//verification organization is added or not
		String orgVerify = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr/td/a[.='"+orgName+"']")).getText();
		if(orgName.equals(orgVerify)) {
			System.out.println("Organization is added to Vtiger : "+orgVerify);
		}else {
			System.out.println("Not added : "+orgVerify);
		}
		
		// delete the organization
		
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr/td/a[.='del']")).click();
		dLib.alertHandle("ok");
		if(orgName.equals(orgVerify)) {
			System.out.println("Organization is not deleted to Vtiger : "+orgVerify);
		}else {
			System.out.println("Deleted to Vtiger : "+orgVerify);
		}
		
		if(welPageTitle.contains("Commercial")) {
			eLib.setDataIntoExcel("org", 10, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			eLib.setDataIntoExcel("org", 10, 5, "Fail");
			System.out.println("Login page not displayed...(");
		}
	}

}
