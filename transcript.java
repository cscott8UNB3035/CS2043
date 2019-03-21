
public class transcript {
	
	private String courseCode;
	private String sectionCode;
	private String title;
	private String letterGrade;
	private String creditHour;
	private String term;
	
	public transcript(String courseCode, String sectionCode, String title, String letterGrade, String creditHour, String term) {
		super();
		this.courseCode = courseCode;
		this.sectionCode = sectionCode;
		this.title = title;
		this.letterGrade = letterGrade;
		this.creditHour = creditHour;
		this.term = term;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public String getCreditHour() {
		return creditHour;
	}

	public void setCreditHour(String creditHour) {
		this.creditHour = creditHour;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	
}
