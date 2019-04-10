package team_project;

public class Course {
	
	private String courseCode;
	private String sectionCode;
	private String title;
	private String letterGrade;
	private double creditHour;
	private String cHour;
	private String term;
	
	protected Course(String courseCode, String sectionCode, String title, String letterGrade, double creditHour, String term)
	{
		this.courseCode = courseCode;
		this.sectionCode = sectionCode;
		this.title = title;
		this.letterGrade = letterGrade;
		this.creditHour = creditHour;
		this.term = term;
	}
	
	
	protected Course(String courseCode, String sectionCode, String title, String letterGrade, String creditHour, String term)
	{
		this.courseCode = courseCode;
		this.sectionCode = sectionCode;
		this.title = title;
		this.letterGrade = letterGrade;
		this.cHour = creditHour;
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
	
	protected String getCHour()
	{
		return cHour;
	}
	
	protected void setCHour(String cHour)
	{
		this.cHour = cHour;
	}
	
	public String toString() {
		String cC, t;
		
		if(this.courseCode.length()<8) {
			cC = this.courseCode;
			cC=cC+"\t";
		}
		else
			cC=this.getCourseCode();
		
		if(this.getTitle().length()<8) {
			t = this.getTitle();
			t=t+"\t\t\t";
		}
		else if(this.getTitle().length()<16) {
			t = this.getTitle();
			t=t+"\t\t";
		}
		else if(this.getTitle().length()<24) {
			t = this.getTitle();
			t=t+"\t";
		}
		else
			t=this.getTitle();
		
		
		return cC + "\t" + sectionCode + "\t" + t + "\t" + letterGrade + "\t" + creditHour + "\t" + term + "\n";
	}
}