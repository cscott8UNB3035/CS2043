package team_project;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.io.FileInputStream;

public class excelReader{
	
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	
	protected static void openExcel(String filepath) {
		
		try {
			
			fis = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
			System.out.println("Success: Excel Document Opened.");
			
		}catch (Exception e){
			
			System.out.println("Error: Excel Document not found.");}
	}
	
	protected static void printExcelData() {
		try{
			int currentCell;
			Iterator < Row > rowIterator = spreadsheet.iterator();
			while(rowIterator.hasNext()){
				currentCell = 0;
				while (currentCell < 6){
				
					try{
						System.out.print(row.getCell(currentCell).toString() + "\t");							
					
					}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
					currentCell++;
				
				}//end of inner while loop
				System.out.println();
				try{
					row = (XSSFRow) rowIterator.next();
				}catch(NullPointerException e){System.out.println("Error: Could not find next row.");break;}
				
			}//end of outer while loop
		}catch (Exception e){System.out.println("Error: Could not read excel file.");}
	}
	
	protected static void closeExcel() {
		
		try{
			
			fis.close();
			
		}catch(Exception e){
			
			System.out.println("Error: Input stream could not be closed. ");
		}
	}
}