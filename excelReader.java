package team_project;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.io.FileInputStream;

public class excelReader{
	
	private ArrayList<Transcript> TranscriptList;
	
	try {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		XSSFWorkbook wb = new HSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
	}catch(FileNotFoundException e) {System.out.println("Error: Could not find specified file")}
	
	protected static void transcriptCollector() {
		try{
			for(int i = 0; i <= sheet.getMaxRow(); i++) {
				cell = 0;
				courseCode = sheet.getRow(i).getCell(cell).getStringCellValue();
				cell++;
				sectionCode =  sheet.getRow(i).getCell(cell).getStringCellValue();;
				cell++;
				title =  sheet.getRow(i).getCell(cell).getStringCellValue();;
				cell++;
				letterGrade =  sheet.getRow(i).getCell(cell).getStringCellValue();;
				cell++;
				creditHour =  sheet.getRow(i).getCell(cell).getStringCellValue();;
				cell++;
				term =  sheet.getRow(i).getCell(cell).getStringCellValue();;
				Transcript transcript = new Transcript(courseCode, sectionCode, title, letterGrade, creditHour, term);
				TranscriptList.add(transcript);
			}
		}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
				
	}
	
	protected static void closeExcel() {
		
		try{
			
			fis.close();
			
		}catch(Exception e){
			
			System.out.println("Error: Input stream could not be closed. ");
		}
	}
}