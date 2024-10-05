package practicetestng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

public class screenshot {
	@Test
	public void amazontest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://flipkart.com");
		
		//step-1 downcasting takescreenshot interface
		TakesScreenshot ts=(TakesScreenshot)driver; 
		//step-2 using getscreenshotas method and storing
		File srcfile=ts.getScreenshotAs(OutputType.FILE);
		//step-3 copying to folder
		FileUtils.copyFile(srcfile, new File("./screenshot/test.png"));
	}
}
