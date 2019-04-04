package team_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//8 areas
public class CourseAreas
{	
	private static CourseAreasList courseAreas;
	
	// ----- Excel File Variables -----
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	
	// ----- Course Areas Methods -----
	
	protected static void openCourseAreas(String filepath)
	{
		
		try 
		{
			fis = new FileInputStream(ConfigGUI.getCourseAreasPath());
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
		}
		catch (Exception e)
		{
			//AlertBox.displayAlert("Error", "Cannot find Course Equivalence file.");
		}
		
	}
	
	
	protected static void openOutputStream()
	{
		
	}
	
	
	protected static void closeCourseAreas()
	{
		
		try
		{	
			fis.close();
			workbook.close();
		}
		catch(Exception e)
		{
			//AlertBox.displayAlert("Error", "Error while closing Course Equivalence file.");
		}
		
	}
	
	
	protected static void closeOutputStream()
	{
		
	}
	
	
	protected static void createArrays()
	{	
		openCourseAreas(ConfigGUI.getCourseAreasPath());
		
		row = spreadsheet.getRow(0);
		int maxRow = spreadsheet.getLastRowNum();
		int maxCell = row.getLastCellNum();
		
		
		//get info column by column, then row by row.
		//once the end of a column has been reached (nullPointer), go back to row 0, and go to the next column.
		//do this until the last column has been reached (maxCell)
		
		for(int i=0; i<maxCell; i++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			courseAreas.courses.add(temp);
			
			try
			{
				String a = spreadsheet.getRow(0).getCell(i).getStringCellValue();	//will throw an exxception if empty
				
				for(int j=0; j<=maxRow+1; j++)
				{
					
					
					try
					{	
						if(j == 0)
						{
							courseAreas.names.add(spreadsheet.getRow(j).getCell(i).getStringCellValue());
						}
						else
						{
							temp.add(spreadsheet.getRow(j).getCell(i).getStringCellValue());
						}
						
					}
					catch (NullPointerException e)
					{
						break;
					}
					
				}	//end inner for-loop
				
			
			}
			catch(NullPointerException noCell)
			{
				continue;
			}
				
				
			
		}	//end outer for-loop
		
		
		
		closeCourseAreas();
	}
	
	
	public static void main(String[] args)
	{
		ConfigGUI.openConfig();
		openCourseAreas(ConfigGUI.getCourseAreasPath());
		
		courseAreas = new CourseAreasList();
		createArrays();
		
		closeCourseAreas();
		ConfigGUI.closeConfig();
	}
	
}