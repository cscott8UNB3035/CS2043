package team_project;

import java.io.*;
import java.util.Properties;

public class ImportConfig
{
	
	private static Properties prop = new Properties();
	private static String fileName = "C:\\Users\\coby\\Java Workspace\\CS2043\\team_project\\transcript_analyser.config";
	private static InputStream is = null;
	
	
	protected static void loadConfig()
	{
		
		try
		{
			is = new FileInputStream(fileName);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: File not found. ");
		}
		
		try
		{
			prop.load(is);
		}
		catch (IOException e)
		{
			System.out.println("Error: Config could not be opened. ");
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
			System.out.println("Error: Could not read config file. ");
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
			System.out.println("Error: Config is already closed. ");
		}
		
	}
	
	
	
}