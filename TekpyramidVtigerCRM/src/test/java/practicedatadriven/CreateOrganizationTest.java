package practicedatadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateOrganizationTest {

	@Test
	public void createOrgTest(XmlTest test) throws FileNotFoundException, IOException, ParseException  {
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("C:\\Users\\shabber\\Desktop\\practicejava\\jsoncommandata.json"));
		JSONObject map=(JSONObject)obj;
		String url=test.getParameter("url");
		String browser=test.getParameter("browser");
		String username=test.getParameter("username");
		String password=test.getParameter("password");
		
		
		
		Random random =new Random();
		int randomInt= random.nextInt(1000);
		
		FileInputStream fis1=new FileInputStream("C:\\Users\\shabber\\Desktop\\practicejava\\testscriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("org");
		Row row=sh.getRow(1);
		String orgname=row.getCell(2).toString() + randomInt;
		wb.close();
		
		WebDriver driver=null;
		if(browser.equals("chrome"))
		driver=new ChromeDriver();
		else if (browser.equals("firefox"))
			driver=new FirefoxDriver();
		else if(browser.equals("edge"))
			driver=new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		WebElement headertext = driver.findElement(By.xpath("//span[@class='small']/preceding::span[@class='dvHeaderText']"));
		
		String w1=headertext.getText();
		System.out.println(w1);
		if(w1.contains(orgname))
		System.out.println("organization created successfull");
		else
			driver.quit();
		WebElement w=driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']/parent::td"));
		Actions a=new Actions(driver);
		a.moveToElement(w).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}

}
