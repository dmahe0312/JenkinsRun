package com.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage extends BasePage{
	
	By searchBox=By.xpath("//textarea[@jsname='yZiJbe']");
	By findButton=By.xpath("//input[@class='gNO89b' and @value='Google Search']/ancestor::div[@jsname='VlcLAe']/center/input[1]");
	
	public GooglePage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getSearchKeyword() {
		return getElement(searchBox);
	}
	public WebElement getFindButton() {
		return getElement(findButton);
	}
	
	public GoogleResultsPage searchResult(String searchKeyword) throws InterruptedException {
		getSearchKeyword().sendKeys(searchKeyword);
		if(getFindButton()!=null)
			{
			getFindButton().click();
			}
		return getInstance(GoogleResultsPage.class);
	}
}
