package FrameWorkMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadTestDataFromExcel {
	public Map<String, Map<String,String>> read() throws IOException{
		
	
		File path=new File("TestDataGoogleSearchKeyword.xlsx");
		FileInputStream fis=new FileInputStream(path);
		Workbook wb=new XSSFWorkbook(fis);
		Sheet s=wb.getSheetAt(0);
		int lastColumn;
		int lastRow=s.getLastRowNum();
		String cellValue = null,columnHeader;
		Double celValue;
		
		Map<String, Map<String,String>> testCaseMap=new HashMap<String, Map<String,String>>();
	    for (int i = 1; i<lastRow; i++) {
	        Row row = s.getRow(i);
	        Row headerRow=s.getRow(0);
	        lastColumn=row.getLastCellNum();
	        Map<String,String> dataMap=new HashMap<String,String>();
	        for (int j = 0; j <lastColumn; j++) {
	        	Cell keyCell=headerRow.getCell(j);
	        	Cell valueCell=row.getCell(j);
	        	columnHeader=keyCell.getStringCellValue();
	        	if(valueCell.getCellType().toString().equalsIgnoreCase("STRING")) {
	        		cellValue=valueCell.getStringCellValue();
	        	}
	        	else if(valueCell.getCellType().toString().equalsIgnoreCase("NUMERIC")) {
	        		celValue=valueCell.getNumericCellValue();
	        		cellValue=Double.toString(celValue);
	        	}
	        	dataMap.put(columnHeader,cellValue);
	        }
	        System.out.println();
	        if(dataMap.get("TestCaseID")!=null) {
	        	testCaseMap.put(dataMap.get("TestCaseID"), dataMap);
	        }
	    } 
	    
	    wb.close();
	    return testCaseMap;
	    }  
	}
