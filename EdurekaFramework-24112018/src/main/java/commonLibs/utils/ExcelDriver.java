package commonLibs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	// To read from a file or any input device
	private InputStream fileReader;

	// To write to a file or any output device
	private OutputStream fileWriter;

	// For Excelsheet workbook an interface from POI apache
	private Workbook excelWorkbook;

	private Sheet sheet;

	private String filename;

	// Creating an empty workbook
	public void createAWorkBook(String filename) throws Exception {
		filename = filename.trim();

		File file = new File(filename);

		if (file.exists()) {
			throw new Exception("File already exists.. create a file with different name");
		}

		if (filename.endsWith(".xls")) {
			excelWorkbook = new HSSFWorkbook();
		} else if (filename.endsWith(".xlsx")) {
			excelWorkbook = new XSSFWorkbook();
		} else {
			throw new Exception("Invalid file extension...");
		}

		// To create an empty excelsheet
		fileWriter = new FileOutputStream(filename);

		excelWorkbook.write(fileWriter);

		fileWriter.close();
		excelWorkbook.close();
	}

	public void openExcelWorkbook(String filename) throws Exception {
		filename = filename.trim();

		this.filename = filename;

		File file = new File(filename);

		if (!file.exists()) {
			throw new Exception("File doesnot exist...");
		}

		// Reading the file in an input stream
		fileReader = new FileInputStream(filename);

		// The stream of data is presented in a workbook format
		excelWorkbook = WorkbookFactory.create(fileReader);
	}

	public void createASheet(String sheetname) throws Exception {
		sheetname = sheetname.trim();

		Sheet sheet = excelWorkbook.getSheet(sheetname);

		if (sheet != null) {
			throw new Exception("Sheet already exists");
		}

		excelWorkbook.createSheet(sheetname);
	}

	public int getRowCountFromASheet(String sheetname) throws Exception {

		sheetname = sheetname.trim();

		verifyIfSheetIsNullOrNot(sheetname);

		return sheet.getLastRowNum();

	}

	public int getCellCountFromARowOFASheet(String sheetname, int rowNumber) throws Exception {
		sheetname = sheetname.trim();

		verifyIfSheetIsNullOrNot(sheetname);

		if (rowNumber < 0) {
			throw new Exception("Row number starts from 1");
		}

		Row row;

		row = sheet.getRow(rowNumber);
		if (row == null) {
			return 0;
		} else {
			return row.getLastCellNum();
		}
	}

	public String getCellData(String sheetname, int rowNumber, int cellNumber) throws Exception {
		sheetname = sheetname.trim();

		verifyIfSheetIsNullOrNot(sheetname);

		if (rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid Row or Cell Number");
		}

		Row row;
		row = sheet.getRow(rowNumber);
		if (row == null) {
			return "";
		}

		Cell cell;
		cell = row.getCell(cellNumber);

		if (cell == null) {
			return "";
		} else {
			if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else {
				return cell.getStringCellValue();
			}
		}

	}

	public void setCellData(String sheetname, int rowNumber, int cellNumber, String cellData) throws Exception {
		sheetname = sheetname.trim();

		verifyIfSheetIsNullOrNot(sheetname);

		if (rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid Row or cell number");
		}

		Row row;
		row = sheet.getRow(rowNumber);

		if (row == null) {
			sheet.createRow(rowNumber);
			row = sheet.getRow(rowNumber);
		}

		Cell cell;

		cell = row.getCell(cellNumber);
		if (cell == null) {
			row.createCell(cellNumber);
			cell = row.getCell(cellNumber);
		}

		cell.setCellValue(cellData);

	}

	public void saveWorkbook() throws Exception {
		fileWriter = new FileOutputStream(filename);

		excelWorkbook.write(fileWriter);

		fileWriter.close();
	}

	public void saveAsWorkbook(String newFilename) throws Exception {

		newFilename = newFilename.trim();

		if (new File(newFilename).exists()) {
			throw new Exception("File already exists...");
		}
		fileWriter = new FileOutputStream(newFilename);

		excelWorkbook.write(fileWriter);

		fileWriter.close();
	}

	public void closeAWorkbook() throws Exception {
		fileReader.close();
		fileWriter.close();
		excelWorkbook.close();
	}

	private void verifyIfSheetIsNullOrNot(String sheetname) throws Exception {

		sheet = excelWorkbook.getSheet(sheetname);

		if (sheet == null) {
			throw new Exception("Sheet doesnot exists");
		}
	}
}
