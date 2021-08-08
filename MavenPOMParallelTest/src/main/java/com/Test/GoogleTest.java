package com.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Reporters.ExtTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import FrameWorkMethods.ReadTestDataFromExcel;

public class GoogleTest extends BaseTest{
	@Test(dataProvider = "Test cases")
	public void searchResults(String TestCaseID,Map<String,String> testCase) {
		test = extent.startTest(TestCaseID);
		ExtTest.setTest(test);
		Map <String,String> testCaseWithPageNumber=new HashMap<String,String>();
		try {
			String pageNumber=googlePage.searchResult(testCase.get("Search Keyword")).returnPageNumber(TestCaseID,testCase.get("Link to be found"));
			testCaseWithPageNumber.put(TestCaseID, pageNumber);
			System.out.println("Test case ID: "+TestCaseID+"Page Number: "+pageNumber);
			ExtTest.getTest().log(LogStatus.INFO,"Test case ID: "+TestCaseID+"Page Number: "+pageNumber);
			Assert.assertNotNull(pageNumber);
		} catch (Exception e) {
			 ExtTest.getTest().log(LogStatus.FAIL, "unexpected error " + e.getStackTrace().toString());		 
			e.printStackTrace();
		}
		extent.endTest(test);
		
	}
	
	
	
	@DataProvider(name="Test cases")
	private Object[][] getTestCase() throws IOException{
		ReadTestDataFromExcel rt = null;
		try {
			rt = new ReadTestDataFromExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Map<String,String>> testCases=rt.read();
		Object[][] arr=new Object[testCases.size()][2];
		
		Set<Entry<String, Map<String,String>>> entries=testCases.entrySet();
		Iterator<Entry<String, Map<String,String>>> entriesIterator=entries.iterator();
		int i=0;
		while(entriesIterator.hasNext()) {
			Map.Entry mapping=entriesIterator.next();
			arr[i][0]=mapping.getKey();
			arr[i][1]=mapping.getValue();
			i++;
		}
		return arr;
	}


}
