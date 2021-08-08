package FrameWorkMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserInitialisation {
private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
String url;
	
	public void setDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		}
		
		
	}
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	 
	public void closeBrowser()
	{
		driver.get().close();
		driver.remove();
	}
}
