package com.Pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
	WebDriver driver;
	
	public Page(WebDriver driver) {
		this.driver=driver;
	}
	public abstract String getPageTitle();
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver=driver);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
