package team_project;

import java.io.*;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportCourseEquiv
{
	
	private static FileInputStream fis;
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	
	
	protected static void openCourseEquiv(String filepath)
	{
		//open excel file
		try 
		{
			//fis = new FileInputStream(new File(filepath + "\\course_equivalency_list.xlsx"));
			fis = new FileInputStream(filepath + "\\course_equivalency_list.xlsx");
			
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
			
			//if(file.isFile() && file.exists())
			//{
				System.out.println("Success: Course Equivalency List Opened.");
			//}
		}
		catch (Exception e)
		{
			System.out.println("Error: Course Equivalency List not found.");
		}
	}
	
	
	
	protected static void printCourseEquiv()
	{
		//for each column, print out each cell from each row in a list, then new line.
		//e.g: ECE1813, EE1813, EE1713		ECE2701, ECE2703		ECE3031, EE3031
		try 
		{
			
			Iterator < Row > rowIterator = spreadsheet.iterator();
			
			while (rowIterator.hasNext())
			{
				row = (XSSFRow) rowIterator.next();
				Iterator < Cell > cellIterator = row.cellIterator();
				
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					System.out.print(cell.getStringCellValue() + " ");
				}
				System.out.println();
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Error: Could not read Course Equivalency List.");
		}
	}
	
	
	
	protected static void addCourseEquiv()
	{
		try
		{
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	
	protected static void closeCourseEquiv()
	{
		try
		{	
			
			fis.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error: Course Equivalency List could not be saved. ");
		}
	}
}