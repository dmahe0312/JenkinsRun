package com.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.MavenPOMParallelTest.ConfigFileReader;
import com.Pages.BasePage;
import com.Pages.GooglePage;
import com.Reporters.ExtReport;
import com.Reporters.ExtTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import FrameWorkMethods.BrowserInitialisation;
import FrameWorkMethods.HandlingScreenshot;

public class BaseTest {
	public static WebDriver driver;
	public GooglePage googlePage;
	public BasePage basePage;
	String url;
	BrowserInitialisation bi=new BrowserInitialisation();
	//this is to get the extent report created and declared in test class
	ExtentReports extent;
	ExtentTest test;
	@BeforeSuite
	public void before() {
		extent = ExtReport.getReport();
	}
	
	@AfterSuite
	public void setup() {		
		 extent.flush();
	}	
	@BeforeMethod
	@Parameters("browser")
	public void browserSetup(String browser) {
		ConfigFileReader cf=new ConfigFileReader();
		try {
			url=cf.getApplicationUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bi.setDriver(browser);
		bi.getDriver().get(url);
		googlePage=new GooglePage(bi.getDriver());
		basePage=new BasePage(bi.getDriver());
		
	}

	@AfterMethod
	 public void getResult(ITestResult result) throws Exception{
		
	
	 if(result.getStatus() == ITestResult.FAILURE){
		 test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		 test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
	 //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
	                        //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 
	                        String screenshotPath = HandlingScreenshot.getScreenshot(bi.getDriver(), result.getName());
	 //To add it in the extent report 
	 test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
	 }else if(result.getStatus() == ITestResult.SKIP){
	 test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
	 }
	 bi.getDriver().close();
	}
	
}
