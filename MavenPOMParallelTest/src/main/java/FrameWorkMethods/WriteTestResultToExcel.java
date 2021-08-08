package FrameWorkMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteTestResultToExcel {
	public void connectExcel() throws FileNotFoundException {
		File file=new File("TestDataGoogleSearchKeyword.xlsx");
		FileOutputStream fos=new FileOutputStream(file);
	}
	public void writeData(Map <String,String> testCaseWithPageNumber) {
		Workbook wb=new XSSFWorkbook();
		Sheet s=wb.getSheetAt(0);
		int lastColumn;
		int lastRow=s.getLastRowNum();
		String cellValue = null,columnHeader;
		Double celValue;
		
		Map<String, Map<String,String>> testCaseMap=new HashMap<String, Map<String,String>>();
	    for (int i = 1; i<=lastRow; i++) {
	        Row row = s.getRow(i);
	        Row headerRow=s.getRow(0);
	        lastColumn=row.getLastCellNum();
	        Map<String,String> dataMap=new HashMap<String,String>();
	        testCaseWithPageNumber.get("TestCaseID");
	        for (int j = 0; j <lastColumn; j++) {
	        	Cell keyCell=headerRow.getCell(j);
	        	Cell valueCell=row.getCell(j+1);
	        	columnHeader=keyCell.getStringCellValue();
	        	keyCell.setCellValue("Page Number");
	        	//valueCell.setCellValue(pageNumber);
	        }
	    } 
	}
}
