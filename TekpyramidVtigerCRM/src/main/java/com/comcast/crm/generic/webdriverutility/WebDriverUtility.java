package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	
	public void waitforpageload(WebDriver driver,int duration) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}
	public void waitforelementpresent(WebDriver driver, WebElement element, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToTabOnUrl(WebDriver driver , String partialurl) {
		Set<String>set=driver.getWindowHandles();
		Iterator<String>it=set.iterator();
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			
			String acturl =driver.getCurrentUrl();
			if(acturl.contains(partialurl)) {
				break;
			}
		}
	}
		public void switchToTabOnTitle(WebDriver driver , String partialTitle) {
			Set<String>set=driver.getWindowHandles();
			Iterator<String>it=set.iterator();
			while(it.hasNext()) {
				String windowID = it.next();
				driver.switchTo().window(windowID);
				
				String acttitle =driver.getTitle();
				if(acttitle.contains(partialTitle)) {
					break;
				}
			}
	}
		public void switchToFrame(WebDriver driver,int index) {
			driver.switchTo().frame(index);
		}
		public void switchToFrame(WebDriver driver,String nameid) {
			driver.switchTo().frame(nameid);
		}
		public void switchToFrame(WebDriver driver,WebElement element) {
			driver.switchTo().frame(element);
		}
		public void switchToAlertAndAccept(WebDriver driver) {
			driver.switchTo().alert().accept();
		}
		public void switchToAlertAndCancel(WebDriver driver) {
			driver.switchTo().alert().dismiss();
		}
		public void select(WebElement element , String text) {
			Select sel=new Select(element);
			sel.selectByVisibleText(text);
		}
		public void select(WebElement element , int index) {
			Select sel=new Select(element);
			sel.selectByIndex(index);
		}
		public void mousemoveonElement(WebDriver driver,WebElement element) {
			Actions act=new Actions(driver);
			act.moveToElement(element).perform();
		}
		public void doubleclick(WebDriver driver,WebElement element) {
			Actions act=new Actions(driver);
			act.moveToElement(element).perform();
		}
}
