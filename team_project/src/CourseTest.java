package team_project;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {
	
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
}