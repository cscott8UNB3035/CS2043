package team_project;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.io.FileInputStream;

public class ExcelReader{
	
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	
	protected static void openExcel(String filepath) {
		
		try {
			
			fis = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
			System.out.println("Success: Excel Document Opened.\n");
			
		}catch (Exception e){
			
			System.out.println("Error: Excel Document not found.");}
	}
	
	protected static void printExcelFile() {
		try{
			int currentCell;
			Iterator < Row > rowIterator = spreadsheet.iterator();
			
			try{row = (XSSFRow) rowIterator.next();
			}catch(NullPointerException e){System.out.println("Error: Could not find next row.");}
			
			System.out.println("Excel File Printed Contents:\n-----------------------------------"
								+ "--------------------------------------------------");
			
			while(rowIterator.hasNext()){
				currentCell = 0;
				while (currentCell < 6){
					
					if(row.getCell(currentCell)==null || row.getCell(currentCell).getCellType()==CellType.BLANK)
						currentCell++;
					else {
				
						try{
							System.out.print(row.getCell(currentCell).toString() + "\t");							
						
						}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
						currentCell++;
					}
				
				}//end of inner while loop
				System.out.println();
				try{
					row = (XSSFRow) rowIterator.next();
				}catch(NullPointerException e){System.out.println("Error: Could not find next row.");break;}
				
			}//end of outer while loop
		}catch (Exception e){System.out.println("Error: Could not read excel file.");}
		System.out.println("-------------------------------------------------------------------------------------\n");
	}
	
	protected static Transcript readExcelFile(/*ArrayList<course> tScript*/) {
		
		Transcript t = new Transcript();
		String courseCode = null, sectionCode = null, title = null, letterGrade = null, creditHour = null, term = null;
		int currentCell;
		
		try{Iterator < Row > rowIterator = spreadsheet.iterator();
		
			try{row = (XSSFRow) rowIterator.next();
			}catch(NullPointerException e){System.out.println("Error: Could not find next row.");}
			
			while(rowIterator.hasNext()){
				currentCell = 0;
				while (currentCell < 6){
				
						if(currentCell==0) {
							try{courseCode = row.getCell(currentCell).toString();
							}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
							currentCell++;
						}
						
						if(currentCell==1) {
							try{sectionCode = row.getCell(currentCell).toString();
							}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
							currentCell++;
						}
						
						if(currentCell==2) {
							try{title = row.getCell(currentCell).toString();
							}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
							currentCell++;
						}
						
						if(currentCell==3) {
							try{letterGrade = row.getCell(currentCell).toString();
							}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
							currentCell++;
						}
						
						if(currentCell==4) {
							try{creditHour = row.getCell(currentCell).toString();
							}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
							currentCell++;
						}
						
						if(currentCell==5) {
							try{term = row.getCell(currentCell).toString();
							}catch(NullPointerException e){System.out.println("Error: Could not read cell data.");break;}
							currentCell++;
						}
						
				}//end of inner while loop
				
				t.add(new Course(courseCode, sectionCode, title, letterGrade, creditHour, term));
				try{row = (XSSFRow) rowIterator.next();
				}catch(NullPointerException e){System.out.println("Error: Could not find next row.");break;}
				
			}//end of outer while loop
			
			System.out.println(t.toString());
			
		}catch (Exception e){System.out.println("Error: Could not read excel file.");}
		return t;
	}
	
	protected static void closeExcel() {
		
		try{
			
			fis.close();
			
		}catch(Exception e){
			
			System.out.println("Error: Input stream could not be closed. ");
		}
	}
}