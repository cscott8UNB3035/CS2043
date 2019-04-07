package team_project;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportAreas 
{
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	
	
	protected static void openCourseAreas()
	{
		
		try 
		{
			fis = new FileInputStream(ConfigGUI.getCourseAreasPath());
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
		}
		catch (Exception e)
		{
			AlertBox.displayAlert("Error", "Cannot find Course Areas file.");
		}
		
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
			AlertBox.displayAlert("Error", "Error while closing Course Areas file.");
		}
		
	}
	
	
	protected static Area[] importAreas()
	{
		ConfigGUI.openConfig();
		openCourseAreas();
		
		int maxRow = spreadsheet.getLastRowNum();
		int maxCell = spreadsheet.getRow(0).getLastCellNum();
		Area[] areas = new Area[maxCell+1];
		
		
		
		for(int i=0; i<maxCell; i++)
		{
			String areaName = spreadsheet.getRow(0).getCell(i).getStringCellValue();
			ArrayList<String> areaList = new ArrayList<String>();
			
			for(int j=1; j<maxRow; j++)
			{
				try
				{
					areaList.add(spreadsheet.getRow(j).getCell(i).getStringCellValue());
				}
				catch(NullPointerException npe)
				{
					break;
				}
				
			}
			
			Area temp = new Area(areaName, areaList);
			areas[i] = temp;
		}
		
		closeCourseAreas();
		ConfigGUI.closeConfig();
		return areas;
	}
	

}