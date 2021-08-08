package com.Pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleResultsPage extends BasePage{
	By links=By.xpath("//cite[@class='iUh30 Zu0yb qLRx3b tjvcx']");
	By pageNumberElement=By.xpath("//td[@class='YyVfkd']/node()[2]");
	By nextButton=By.xpath("//span[text()='Next']");
	By totalNoOfLinks=By.xpath("//cite[@class='iUh30 Zu0yb qLRx3b tjvcx' or @class='iUh30 Zu0yb tjvcx']");
	By lastPage=By.xpath("//a[text()='repeat the search with the omitted results included']");
	
	
	String pageNumber=null;
	public GoogleResultsPage(WebDriver driver) {
		super(driver);
	}
	public List<WebElement> getLinks() {
		return getElements(links);
	}
	public int getTotalNoOfLinks() {
		return getElements(totalNoOfLinks).size();
	}
	public WebElement getNextButton() {
		return getElement(nextButton);
	}
	public WebElement getPageNumber() {
		return getElement(pageNumberElement);
	}
	public List<WebElement> getLastPage() {
		return getElements(lastPage);
	}
	public String isLinkFound(String linkToBeFound) {	
		List<WebElement> listOfLinks= getLinks();
		int noOfLinks=getTotalNoOfLinks();
		for(int i=0;i<noOfLinks;i++) {
		while(pageNumber==null) {
		for(WebElement ele:listOfLinks) {
			if(ele.getText()!=null) {	
			if(ele.getText().contains(linkToBeFound)) {
				pageNumber=getPageNumber().getText().substring(5, 6);
				return pageNumber;
			}		
			}
		}
		if(getLastPage().size()==0) {
		if((getNextButton()!=null)) {
		getNextButton().click();
		}
		else {
			break;
		}
		}
		else {
			break;
		}
		noOfLinks=getTotalNoOfLinks();
		listOfLinks= getLinks();
		
		}		
		}
		return null;	
	}
	//TestCaseID, PageNumber
	public String returnPageNumber(String TestCaseID, String linkToBeFound){
		return isLinkFound(linkToBeFound);
	}
}
