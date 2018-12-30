package com.mercuryTravel.utils;

import org.testng.annotations.DataProvider;

import commonLibs.utils.ExcelDriver;

public class DataProviderUtils {
	
	private ExcelDriver excelDriver;
	
	
	@DataProvider
	public Object[][] getData() throws Exception{
		String excelWorkbookname;
		String excelsheetname;
		
		excelWorkbookname = "C:/Users/Saurabh Dhingra/git/edureka-framework24112018/EdurekaFramework-24112018/testInputFiles/TestData.xlsx";
		excelsheetname = "TestData";
		
		Object[][] data;
		
		excelDriver = new ExcelDriver();
		
		excelDriver.openExcelWorkbook(excelWorkbookname);
		
		int rowNumber = excelDriver.getRowCountFromASheet(excelsheetname);
		
		int cellNumber = excelDriver.getCellCountFromARowOFASheet(excelsheetname, 1);
		
		data = new Object[rowNumber+1][cellNumber];
		
		for(int row = 0; row <= rowNumber; row++){
			for(int cell =0; cell < cellNumber; cell++){
				data[row][cell] = excelDriver.getCellData(excelsheetname, row, cell);
			}
		}
		
		return data;
	}

}
