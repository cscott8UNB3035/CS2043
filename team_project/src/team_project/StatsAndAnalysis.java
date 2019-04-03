package team_project;

public class StatsAndAnalysis {
	protected static int getNumStudetsPerYear(String Year){
		int output = 0;
		for( transcript t: list) {
			for(course c: tScript) {
				if(c.getTerm().conatins(Year)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
	protected static int getNumStudentsPerLocation(String Location) {
		int output = 0;
		for( transcript t: list) {
			for(course c: tScript) {
				if(c.getSecitonCode().contains(Location)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
	protected static int getNumStudentsInCoursePerYear(String courseCode, String Year) {
		int output = 0;
		for( transcript t: list) {
			for(course c: tScript) {
				if(c.getCourseCode().Equals(courseCode)&&c.getTerm().contains(Year)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
	protected static int getNumStudentsInLocationPerYear(String Location, String Year) {
		int output = 0;
		for( transcript t: list) {
			for(course c: tScript) {
				if(c.getSecitonCode().contains(Location)&&c.getTerm().contains(Year)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
}
