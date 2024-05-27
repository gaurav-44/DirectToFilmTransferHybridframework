package com.dtf.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Utilities {
	public static String genratetimeStramo() {
		Date date=new Date();
		String Time= date.toString().replace(" ", "_").replace(":", "_");
		System.out.println(Time);
		return Time+"@gmail.com";
	}
	
	
	
	public static Object[][] gettestdataFromExl(String sheetName) {
		File excelFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dtf\\qa\\testdata\\idpassword.xlsx");
		XSSFWorkbook workBook=null;
		try {
			FileInputStream file=new FileInputStream(excelFile);
			workBook=new XSSFWorkbook(file);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	    
	   // XSSFWorkbook workBook=new XSSFWorkbook(file);
		XSSFSheet sheet=workBook.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum();
		int col=sheet.getRow(0).getLastCellNum();
		
		Object [] [] data=new Object [rows] [col];
		
		for(int i=0; i<rows; i++) {
			XSSFRow row=sheet.getRow(i+1);
			
			for(int j=0;j<col;j++) {
				XSSFCell cell=row.getCell(j);
				CellType cellType=cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data [i][j] = cell.getRichStringCellValue();
					break;
				case NUMERIC:
					data [i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data [i][j] = cell.getBooleanCellValue();
					break;
				
				}
			}
			
		}
		return data;
	}

}
