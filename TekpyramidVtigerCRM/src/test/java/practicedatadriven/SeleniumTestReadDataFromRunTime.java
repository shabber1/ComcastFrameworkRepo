package practicedatadriven;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumTestReadDataFromRunTime {
@Test
public void seleniumtest()throws Throwable{
	String browser=System.getProperty("browser");
	String url=System.getProperty("url");
	
	String username=System.getProperty("username");
	String password=System.getProperty("password");
	
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
}
}
