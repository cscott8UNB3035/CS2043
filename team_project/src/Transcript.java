package team_project;

public class Transcript {
	
	private String courseCode;
	private String sectionCode;
	private String title;
	private String letterGrade;
	private double creditHour;
	private String term;
	
	protected Transcript(String courseCode, String sectionCode, String title, String letterGrade, double creditHour, String term) {
		this.courseCode = courseCode;
		this.sectionCode = sectionCode;
		this.title = title;
		this.letterGrade = letterGrade;
		this.creditHour = creditHour;
		this.term = term;
	}

	protected String getCourseCode() {
		return courseCode;
	}

	protected void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	protected String getSectionCode() {
		return sectionCode;
	}

	protected void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	protected String getTitle() {
		return title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	protected String getLetterGrade() {
		return letterGrade;
	}

	protected void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	protected double getCreditHour() {
		return creditHour;
	}

	protected void setCreditHour(double creditHour) {
		this.creditHour = creditHour;
	}

	protected String getTerm() {
		return term;
	}

	protected void setTerm(String term) {
		this.term = term;
	}
}