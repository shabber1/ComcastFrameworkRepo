package practicedatadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class readingfrompropertiesfile {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\shabber\\Desktop\\commondata.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		String browser=properties.getProperty("browser");
		String url=properties.getProperty("url");
		String username=properties.getProperty("username");
		String password=properties.getProperty("password");
		System.out.println(password);
		WebDriver driver=null;
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equals("edge")) {
			driver=new EdgeDriver();
			}else {
				driver=new ChromeDriver();
			}
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.quit();
	}

}
