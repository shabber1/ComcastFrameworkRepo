package com.comcast.crm.orgtest;

import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepository.Utility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepository.Utility.HomePage;
import com.comcast.crm.objectrepository.Utility.OrganizationInfoPage;
import com.comcast.crm.objectrepository.Utility.OrganizationsPage;
//@Listeners(com.comcast.crm.listenerutility.ListenerImplimentationClass.class)
public class CreateOrganizationTest extends BaseClass{
	@Test(groups="smokeTest")
	public void createOrganizationTest() throws Throwable {
		
		//UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		//UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage op = new HomePage(driver);
		op.getOrglink().click();

		//UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateneworgbtn().click();

		//UtilityClassObject.getTest().log(Status.INFO, "create a new org page");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgname);
		//UtilityClassObject.getTest().log(Status.INFO, orgname+"created a new org page");

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeadermsg().getText();
		boolean status=actOrgName.contains(orgname);
		Assert.assertEquals(status, true);

		String actOrgname = oip.getOrgnamemsg().getText();
		boolean status1=actOrgname.equals(orgname);
		Assert.assertEquals(status1, true);
	}

	@Test(groups="regressionTest")
	public void CreateOrganizationWithIndustriesTest() throws Throwable {
		String orgname = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		HomePage op = new HomePage(driver);
		op.getOrglink().click();

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateneworgbtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgname, industry, type);

		OrganizationInfoPage org = new OrganizationInfoPage(driver);
		String actindustry = org.getIndustrynamemsg().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actindustry,industry);
		
		String acttype = org.getTypenamemsg().getText();
		soft.assertEquals(acttype,type);

	}

	@Test(groups="regressionTest")
	public void CreateOrganizationwithphoneno() throws Throwable {
		String orgname = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
		String phonenumber = elib.getDataFromExcel("org", 7, 3);

		HomePage op = new HomePage(driver);
		op.getOrglink().click();

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateneworgbtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgname, phonenumber);

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actOrgName = oip.getHeadermsg().getText();
		boolean status=actOrgName.contains(orgname);
		Assert.assertEquals(status, true);
		
		String actOrgname = oip.getOrgnamemsg().getText();
		boolean status1=actOrgname.equals(orgname);
		Assert.assertEquals(status1, true);
		
		String actphonenumber = oip.getPhonemsg().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actphonenumber,phonenumber);
	}
}