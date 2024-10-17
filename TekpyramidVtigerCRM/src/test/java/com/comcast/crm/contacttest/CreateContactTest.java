package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepository.Utility.ContactInformationPage;
import com.comcast.crm.objectrepository.Utility.ContactsPage;
import com.comcast.crm.objectrepository.Utility.CreatingNewContactpage;
import com.comcast.crm.objectrepository.Utility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepository.Utility.HomePage;
import com.comcast.crm.objectrepository.Utility.OrganizationInfoPage;
import com.comcast.crm.objectrepository.Utility.OrganizationsPage;


//@Listeners(com.comcast.crm.listenerutility.ListenerImplimentationClass.class)

public class CreateContactTest extends BaseClass {
	@Test(groups="smokeTest")
	public void createContactTest() throws Throwable {

		String lastname = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		HomePage op = new HomePage(driver);
		op.getContactlink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactBTN();

		CreatingNewContactpage ccp = new CreatingNewContactpage(driver);
		ccp.setContactLastName(lastname);
		ccp.clickSaveBTN();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String actheader = cip.getPageHeader();
		boolean status=actheader.contains(lastname); 
		Assert.assertEquals(status, true);	
	}

	@Test(groups="regressionTest")
	public void CreateContactwithsupportdatetest() throws Throwable {

		String lastname = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		HomePage op = new HomePage(driver);
		op.getContactlink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactBTN();

		String startDate = jlib.getsystemdateYYYYDDMM();
		String endDate = jlib.getrequiredDateYYYYDDMM(30);

		CreatingNewContactpage cncp = new CreatingNewContactpage(driver);
		cncp.setContactLastName(lastname);

		cncp.getContactStartdate().clear();
		cncp.getContactStartdate().sendKeys(startDate);
		cncp.getContactEnddate().clear();
		cncp.getContactEnddate().sendKeys(endDate);

		cncp.clickSaveBTN();

		String actstartdate = cncp.getverifyStartdate();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actstartdate,startDate);
			
		String actenddate = cncp.getverifyEnddate();
		soft.assertEquals(actenddate,endDate);
	}

	@Test(groups="regressionTest")
	public void CreateContactwithorgtest() throws Throwable {

		String orgname = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastname = elib.getDataFromExcel("contact", 7, 3);

		HomePage op = new HomePage(driver);
		op.getOrglink().click();

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateneworgbtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgname);

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeadermsg().getText();
		boolean status=actOrgName.contains(orgname);
		Assert.assertEquals(status, true);

		String actOrgname = oip.getOrgnamemsg().getText();
		boolean status1=actOrgname.equals(orgname);
		Assert.assertEquals(status1, true);

		op.getContactlink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactBTN();

		CreatingNewContactpage ccp = new CreatingNewContactpage(driver);
		ccp.setContactLastName(lastname + jlib.getRandomNumber());
		ccp.clickOrgPlusBTN();

		wlib.switchToTabOnUrl(driver, "module=Accounts");
		ccp.sendorginSearchBTN(orgname);
		ccp.SearchBTN();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		wlib.switchToTabOnTitle(driver, "Contacts&action");
		ccp.clickSaveBTN();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String headerInfo = cip.getPageHeader();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(headerInfo,lastname);
		
		String actorgname = ccp.getverifyorgname();
		System.out.println(actorgname);
		boolean status2=actorgname.trim().equals(orgname);
		Assert.assertEquals(status2, true);

	}
}
