package team_project;

import java.util.Scanner;

public class excelReaderDriver {

	private static Scanner input = new Scanner(System.in);
	private static String filepath;
	
	public static void main(String[] args) {
		
		//C:\\Users\\tdale\\Documents\\School\\2018-2019\\CS2043 - Software Engineering\\Project\\D3\\exScript.xlsx
		System.out.println("Enter file path:");
		filepath = input.nextLine();
		excelReader.openExcel(filepath);
		excelReader.printExcelData();
		excelReader.closeExcel();
	}
}
