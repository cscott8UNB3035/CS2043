import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

	//private static Scanner input = new Scanner(System.in);
	//C:\Users\tdale\Documents\School\2018-2019\CS2043 - Software Engineering\Project\D4\cohort_1\cohort_1\2015EE_1.txt
	private static String filepath1="C:\\Users\\tdale\\Documents\\School\\2018-2019\\CS2043 - Software Engineering\\Project\\D4\\cohort_1\\cohort_1\\2015EE_1.txt";
	private static String filepath2="C:\\Users\\tdale\\Documents\\School\\2018-2019\\CS2043 - Software Engineering\\Project\\D4\\cohort_1\\cohort_1\\2015EE_2.txt";
	
	public static void main(String[] args) {
		
		String courseCode = "APSC1023";
		String sectionCode = "SJ01B";
		String title = "MECHANICS II";
		String letterGrade = "B-";
		String creditHour = "5.0";
		String term = "2011/WI";
		
		File file1 = new File(filepath1);
		File file2 = new File(filepath2);
		
		Course c = new Course(courseCode, sectionCode, title, letterGrade, creditHour, term);
		System.out.println("test: Create and print course object:\n" + c.toString());
		
		try {
			TextReader.openFile(file1);
		} catch (FileNotFoundException e) {
			System.out.println("Error: file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: file not found");
			e.printStackTrace();
		}
		
		try {
		TextReader.readTextFile();
		} catch(NullPointerException e) {
			System.out.println("\n"+e+"\n");
		}
		/*TextReader.closeFile();*/
		
		try {
			TextReader.openFile(file2);
		} catch (FileNotFoundException e) {
			System.out.println("Error: file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: file not found");
			e.printStackTrace();
		}

		try {
			TextReader.readTextFile();
		} catch(NullPointerException e) {
			System.out.println("\n"+e+"\n");
		}
		/*TextReader.closeFile();*/
	}
}