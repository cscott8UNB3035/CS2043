package team_project;

import java.util.Iterator;
import java.util.Properties;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Configuration
{
	// ----- Configuration File Variables -----
	private static InputStream is = null;
	private static Properties prop = new Properties();
	private static String fileName = "C:\\Users\\coby\\Java Workspace\\CS2043\\team_project\\transcript_analyser.config";
	
	
	// ----- Excel File Variables -----
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	
	
	
	// ----- Configuration File Methods -----
	
	protected static void openConfig()
	{
		
		try
		{
			is = new FileInputStream(fileName);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: Config file not found.");
		}
		
		try
		{
			prop.load(is);
		}
		catch (IOException e)
		{
			System.out.println("Error: Config could not be opened.");
		}
		
	}
	
	
	protected static void printConfig()
	{
		
		try
		{
			System.out.println("Transcript Path: " + prop.getProperty("transcript_path"));
			System.out.println("Course Equivalency Path: " + prop.getProperty("course_equiv_path"));
			
			System.out.println();
			for(int i = 1; i <= Integer.parseInt(prop.getProperty("number_of_distributions")); i++)
			{
				System.out.print(prop.getProperty("distribution" + i + "_name") + ": ");
				System.out.print(prop.getProperty("distribution" + i + "_range") + "\n\n");
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Error: Could not read config file.");
		}
		
	}
	
	
	protected static String getCourseEquivPath()
	{
		
		try
		{
			return prop.getProperty("course_equiv_path");
		}
		catch (Exception e)
		{
			System.out.println("Error: Could not find Course Equivalency List location.");
			return null;
		}
		
	}
	
	
	protected static String getTranscriptPath()
	{
		
		try
		{
			return prop.getProperty("transcript_path");
		}
		catch (Exception e)
		{
			System.out.println("Error: Could not find Raw Transcript location.");
			return null;
		}
		
	}
	
	
	protected static void closeConfig()
	{
		
		try
		{	
			is.close();
		}
		catch(Exception e)
		{
			System.out.println("Error: Exception when closing config file.");
		}
		
	}
	
	
	
	// ----- Excel File Methods -----
	
	protected static void openCourseEquiv(String filepath)
	{
		
		try 
		{
			fis = new FileInputStream(filepath + "\\course_equivalency_list.xlsx");
			
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
			
			System.out.println("Success: Course Equivalency List Opened.");
		}
		catch (Exception e)
		{
			System.out.println("Error: Course Equivalency List not found.");
		}
		
	}
	
	
	protected static void printCourseEquiv()
	{
		
		try 
		{
			int currentCell = 0;
			int maxCell = spreadsheet.getRow(0).getLastCellNum();
			
			while (currentCell < maxCell)
			{
				
				Iterator < Row > rowIterator = spreadsheet.iterator();
				while(rowIterator.hasNext())
				{
					try
					{
						row = (XSSFRow) rowIterator.next();
						System.out.print(row.getCell(currentCell).getStringCellValue() + " ");
					}
					//When the end of current column has been reached, break from current loop.
					catch(NullPointerException e)
					{
						break;
					}
				}
				
				System.out.println();
				currentCell++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: Could not read Course Equivalency List.");
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