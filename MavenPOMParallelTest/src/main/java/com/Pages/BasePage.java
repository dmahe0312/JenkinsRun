package com.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page{

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageTitle() {
		return null;
	}
	
	public WebElement findElement(By locator) {
		WebDriverWait wait=new WebDriverWait(driver,10);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		//return driver.findElement(locator);
	}
	
	public WebElement  getElement(By locator) {
		WebElement element=null;
		try {
			element=driver.findElement(locator);
			return element;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<WebElement> getElements(By locator) {
		List<WebElement> element=null;
		try {
			element=driver.findElements(locator);
			return element;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
