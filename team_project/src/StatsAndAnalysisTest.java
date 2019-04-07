package team_project;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {

	@Test
    protected static void getNumStudentsPerYearTest(){
		int result = StatsAndAnalysis.getNumStudentsPerYear(/*year*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
	
	@Test
	protected static void getNumStudentsPerLocationTest() {
		int result = StatsAndAnalysis.getNumStudentsPerLocation(/*location*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
	
	@Test
	protected static void getNumStudentsInCoursePerYearTest() {
		int result = StatsAndAnalysis.getNumStudentsInCoursePerYear(/*courseCode, year*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
	
	@Test
	protected static void getNumStudentsInLocationPerYearTest() {
		int result = StatsAndAnalysis.getNumStudentsInLocationPerYear(/*location, year*/);
		assertEquals(/*the pre-determined answer*/, result);
	}
}