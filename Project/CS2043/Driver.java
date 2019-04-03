package team_project;

//import java.util.Scanner;

public class Driver {

	//private static Scanner input = new Scanner(System.in);
	private static String filepath1="C:\\Users\\tdale\\Documents\\School\\2018-2019\\CS2043 - Software Engineering\\Project\\D3\\exScript1.xlsx";
	private static String filepath2="C:\\Users\\tdale\\Documents\\School\\2018-2019\\CS2043 - Software Engineering\\Project\\D3\\exScript2.xlsx";
	
	public static void main(String[] args) {
		
		String courseCode = "APSC1023";
		String sectionCode = "SJ01B";
		String title = "MECHANICS II";
		String letterGrade = "B-";
		String creditHour = "5.0";
		String term = "2011/WI";
		
		Course c = new Course(courseCode, sectionCode, title, letterGrade, creditHour, term);
		System.out.println("test: Create and print course object:\n" + c.toString());
		
		//C:\\Users\\tdale\\Documents\\School\\2018-2019\\CS2043 - Software Engineering\\Project\\D3\\exScript1.xlsx
		/*System.out.println("Enter file path:");
		filepath = input.nextLine();
		*/
		
		TranscriptHandler th = new TranscriptHandler();
		
		ExcelReader.openExcel(filepath1);
		ExcelReader.printExcelFile();
		th.add(ExcelReader.readExcelFile());
		ExcelReader.closeExcel();
		
		ExcelReader.openExcel(filepath2);
		ExcelReader.printExcelFile();
		th.add(ExcelReader.readExcelFile());
		ExcelReader.closeExcel();
		
		System.out.println(th.toString());
	}
}
