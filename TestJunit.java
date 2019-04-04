package team_project;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {

    //ConfigGUI test
	@Test
    protected static void getCourseEquivPathTest(){
		openConfig();
		String result = getCourseEquivPath();
		closeConfig();
		assertEquals("C:\\Users\\coby\\Java Workspace\\CS2043\\src\\team_project\\course_equiv\\course_equivalency_list.xlsx", result);
	}
	
	@Test
    protected static void getTranscriptFolderPathTest(){
		openConfig();
		String result = getTranscriptFolderPath();
		closeConfig();
		assertEquals("C:\\Users\\coby\\Java Workspace\\CS2043\\src\\team_project\\transcripts\\", result);
	}
	
	@Test
    protected static void getOutputFolderPathTest(){
		openConfig();
		String result = getOutputFolderPath();
		closeConfig();
		assertEquals("C:\\Users\\coby\\Java Workspace\\CS2043\\src\\team_project\\output\\", result);
	}
	
	//Course test
	@Test
	protected void getCourseCodeTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		String result = c.getCourseCode();
		assertEquals("APSC1023", result);
	}

	@Test
	protected void setCourseCodeTest()  {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		c.setCourseCode("CE3963");
		String result = c.getCourseCode()
		assertEquals("CE3963", result);
	}

	@Test
	protected void getSectionCodeTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		String result = c.getSectionCode();
		assertEquals("SJ01B", result);
	}

	@Test
	protected void setSectionCodeTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		c.setSectionCode("FR01B");
		String result = c.getSectionCode();
		assertEquals("FR01B", result);
	}

	@Test
	protected void getTitleTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		String result = c.getTitle();
		assertEquals("MECHANICS II", result);
	}

	@Test
	protected void setTitleTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		c.setTitle("ENGINEERING ECONOMY");
		String result = c.getTitle();
		assertEquals("ENGINEERING ECONOMY", result);
	}

	@Test
	protected void getLetterGradeTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		String result = c.getLetterGrade();
		assertEquals("B-", result);
	}

	@Test
	protected void setLetterGradeTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		c.setLetterGrade("A+");
		String result = c.getLetterGrade();
		assertEquals("A+", result);
	}

	@Test
	protected void getCreditHourTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		String result = c.getCreditHour();
		assertEquals("5", result);
	}

	@Test
	protected void setCreditHourTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		c.setCreditHour("2");
		String result = c.getCreditHour();
		assertEquals("2", result);
	}

	@Test
	protected void getTermTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		String result = c.getTerm();
		assertEquals("2011/WI", result);
	}
	
	@Test
	protected void setTermTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		c.setTerm("2014/WI");
		String result = c.getTerm();
		assertEquals("2014/WI", result);
	}
	
	@Test
	public void toStringTest() {
		Course c = new Course("APSC1023", "SJ01B", "MECHANICS II", "B-", "5", "2011/WI");
		String result = c.toString();
		assertEquals("APSC1023\tSJ01B\tMECHANICS II\tB-\t5\t2011/WI\n", result);
	}
	
	//StatsAndAnalysis test
	@Test
    protected static void getNumStudentsPerYearTest(){
		int result = getNumStudentsPerYear(/*year*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
	
	@Test
	protected static void getNumStudentsPerLocationTest() {
		int result = getNumStudentsPerLocation(/*location*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
	
	@Test
	protected static void getNumStudentsInCoursePerYearTest() {
		int result = getNumStudentsInCoursePerYear(/*courseCode, year*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
	
	@Test
	protected static void getNumStudentsInLocationPerYearTest() {
		int result = getNumStudentsInLocationPerYear(/*location, year*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
}