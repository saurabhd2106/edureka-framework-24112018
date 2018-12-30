package designPattern;

import commonLibs.utils.ExcelDriver;

public class DemoExcelDriver {

	public static void main(String[] args) {
		try {
			String filename = "C:/Users/Saurabh Dhingra/git/edureka-framework24112018/EdurekaFramework-24112018/outputFolder/TestData.xlsx";
			
			String sheetname = "Test Data";
			
			ExcelDriver excelDriver = new ExcelDriver();
			
			excelDriver.createAWorkBook(filename);
			
			excelDriver.openExcelWorkbook(filename);
			
			excelDriver.createASheet(sheetname);
			
			excelDriver.setCellData(sheetname, 0, 0, "Sreeni");
			excelDriver.setCellData(sheetname, 0, 1, "Saurabh");
			excelDriver.setCellData(sheetname, 0, 2, "Gaurav");
			excelDriver.setCellData(sheetname, 0, 3, "Deepak");
			excelDriver.setCellData(sheetname, 0, 4, "Rishab");
			
			excelDriver.saveWorkbook();
			
			excelDriver.closeAWorkbook();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
