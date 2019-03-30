package team_project;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader{
	
	private static ArrayList<Transcript> TranscriptList = new ArrayList<Transcript>();
	static FileInputStream is;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	
	protected static void transcriptCollector() {
		
		try {
			is = new FileInputStream(ConfigGUI.getTranscriptFolderPath() + "exScript.xlsx");
			wb = new XSSFWorkbook(is);
			sheet = wb.getSheetAt(0);
		}catch(FileNotFoundException e) {System.out.println("Error: Could not find specified file.");} 
		catch (IOException e) {System.out.println("Error: Could not find workbook.");}
		
		
		for(int i = 0; i <= sheet.getLastRowNum(); i++) {
			
			try{
				int cell = 0;
				
				String courseCode = sheet.getRow(i).getCell(cell).getStringCellValue();
				cell++;
				String sectionCode = sheet.getRow(i).getCell(cell).getStringCellValue();
				cell++;
				String title = sheet.getRow(i).getCell(cell).getStringCellValue();
				cell++;
				String letterGrade = sheet.getRow(i).getCell(cell).getStringCellValue();
				cell++;
				double creditHour = sheet.getRow(i).getCell(cell).getNumericCellValue();
				cell++;
				String term = sheet.getRow(i).getCell(cell).getStringCellValue();
				
				Transcript transcript = new Transcript(courseCode, sectionCode, title, letterGrade, creditHour, term);
				
				TranscriptList.add(transcript);
				
				System.out.println("line done");
				
			}catch(NullPointerException e){System.out.println("Error: End of file."); break;}
		}
			
		
				
	}
	
	protected static void closeExcel() {
		
		try{
			
			wb.close();
			is.close();
			
		}catch(Exception e){
			
			System.out.println("Error: Input stream could not be closed. ");
		}
	}
	
	
	
}
