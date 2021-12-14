package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtility {

	
	

	public  String getPageObjectByPageField(String pageFieldName) throws IOException {
		String value = null;

		File file = new File("./\\src\\test\\resources\\Excel\\WebData.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook excel = new XSSFWorkbook(fis);
		XSSFSheet sheet = excel.getSheet("Task1");

		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			if (sheet.getRow(rowNum).getCell(2).getStringCellValue().equalsIgnoreCase(pageFieldName)) {
				value = sheet.getRow(rowNum).getCell(3).getStringCellValue();
			}
		}

		excel.close();

		return value;
	}
	
	public  String getDataByPageField(String pageFieldName) throws IOException {
		String value = null;

		File file = new File("./\\src\\test\\resources\\Excel\\WebData.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook excel = new XSSFWorkbook(fis);
		XSSFSheet sheet = excel.getSheet("Task1");

		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			if (sheet.getRow(rowNum).getCell(2).getStringCellValue().equalsIgnoreCase(pageFieldName)) {
				value = sheet.getRow(rowNum).getCell(4).getStringCellValue();
				System.out.println("value is  " +value);
			}
		}
		
		excel.close();

		return value;
	}

}
