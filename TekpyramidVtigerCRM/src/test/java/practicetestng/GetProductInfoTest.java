package practicetestng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	@Test(dataProvider="getdata")
	public void getproductinfotest(String BrandName,String ProductName) {
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.get("http://amazon.in");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(BrandName,Keys.ENTER);
		
		String x="//span[text()='"+ProductName+"']/../../../../div[2]/div/div/div/div/div/a/span/span[2]/span[2]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	@DataProvider
	public Object[][] getdata() throws Throwable{
		ExcelUtility elib=new ExcelUtility();
		int rowCount=elib.getRowcount("Product");
		Object[][] objArr=new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++) {
			objArr[i][0]=elib.getDataFromExcel("Product", i+1, 0);
			objArr[i][1]=elib.getDataFromExcel("Product", i+1, 1);	
		}
		return objArr;
		
	}
}
