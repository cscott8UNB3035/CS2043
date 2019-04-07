package team_project;

import java.util.*;

public class Distributions 
{
	private ArrayList<String> courseCode;
	private ArrayList<Integer> others;
	private int other;
	private ArrayList<Integer> fails;
	private int fail;
	private ArrayList<Integer> marginals;
	private int marginal;
	private ArrayList<Integer> meets;
	private int meet;
	private ArrayList<Integer> exceeds;
	private int exceed;
	
	public Distributions(ArrayList<String> courseCode, ArrayList<Integer> others, ArrayList<Integer> fails, ArrayList<Integer> marginals, ArrayList<Integer> meets, ArrayList<Integer> exceeds) {
		this.courseCode = courseCode;
		this.fails = fails;
		this.marginals = marginals;
		this.meets = meets;
		this.exceeds = exceeds;
	}
	
	/*public Distributions()
	{
		this.courseCode = null;
		this.fails = null;
		this.marginals = null;
		this.meets = null;
		this.exceeds = null;
	}*/
	
	protected Distributions getAreaDistributions(TranscriptHandler tHandler, Area area) 
	{
		int num = 0;
		for(int i = 0; i <= tHandler.getSize(); i++) 
		{
			for(int j = 0; j <= tHandler.getTranscript(j).getSize(); j++) 
			{
				Transcript tScript = tHandler.getTranscript(j);
				
				if(tScript.getCourse(j).getCourseCode().equals(courseCode.get(i))) 
				{
					
					if(tScript.getCourse(i).getLetterGrade() == "F" || tScript.getCourse(i).getLetterGrade() == "D") {
						num = 2;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "C" || tScript.getCourse(i).getLetterGrade() == "C+") {
						num = 3;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "B-" || tScript.getCourse(i).getLetterGrade() == "B" || tScript.getCourse(i).getLetterGrade() == "B+") {
						num = 4;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "A-" || tScript.getCourse(i).getLetterGrade() == "A" || tScript.getCourse(i).getLetterGrade() == "A+") {
						num = 5;
					}
					else 
					{
						num = 1;
					}
					
					courseCode.add(tScript.getCourse(i).getCourseCode());
					switch(num) {
						case 1: other = other + 1;
								others.add(other);
						case 2: fail = fail + 1;
								fails.add(fail);
								break;
						case 3: marginal = marginal + 1;
								marginals.add(marginal);
								break;
						case 4: meet = meet + 1;
								meets.add(meet);
								break;
						case 5: exceed = exceed + 1;
								exceeds.add(exceed);
								break; 
					}
				}
			}
		}
		Distributions distribution = new Distributions(courseCode, others, fails, marginals, meets, exceeds);
		return distribution;
	}
	
	protected Distributions getRawDistributions(TranscriptHandler tHandler) 
	{
		int num = 0;
		for(int i = 0; i <= tHandler.getSize(); i++) {
			for(int j = 0; j <= tHandler.getTranscript(j).getSize(); j++) 
			{
				Transcript tScript = tHandler.getTranscript(j);
				if(tHandler.getTranscript(j).getCourse(j).equals(courseCode.get(i))) {
					if(tScript.getCourse(i).getLetterGrade() == "F" || tScript.getCourse(i).getLetterGrade() == "D") {
						num = 2;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "C" || tScript.getCourse(i).getLetterGrade() == "C+") {
						num = 3;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "B-" || tScript.getCourse(i).getLetterGrade() == "B" || tScript.getCourse(i).getLetterGrade() == "B+") {
						num = 4;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "A-" || tScript.getCourse(i).getLetterGrade() == "A" || tScript.getCourse(i).getLetterGrade() == "A+") {
						num = 5;
					}
					else {
						num = 1;
					}
					courseCode.add(tScript.getCourse(i).getCourseCode());
					switch(num) {
						case 1: other = other + 1;
								others.add(other);
						case 2: fail = fail + 1;
								fails.add(fail);
								break;
						case 3: marginal = marginal + 1;
								marginals.add(marginal);
								break;
						case 4: meet = meet + 1;
								meets.add(meet);
								break;
						case 5: exceed = exceed + 1;
								exceeds.add(exceed);
								break; 
					}
				}
			}
		}
		Distributions distribution = new Distributions(courseCode, others, fails, marginals, meets, exceeds);
		return distribution;
	}
	
	
	public ArrayList<String> getCourseCode() {
		return courseCode;
	}
	
	public ArrayList<Integer> getFails() {
		return fails;
	}
	
	public ArrayList<Integer> getMarginals() {
		return marginals;
	}
	
	public ArrayList<Integer> getMeets() {
		return meets;
	}
	
	public ArrayList<Integer> getExceeds() {
		return exceeds;
	}
	
	
	protected void setCourseCode(ArrayList<String> courseCode)
	{
		this.courseCode = courseCode;
	}
	
	protected void setFails(ArrayList<Integer> fails, int fail)
	{
		this.fails = fails;
		this.fail = fail;
	}
	
	protected void setMarginals(ArrayList<Integer> marginals, int marginal)
	{
		this.marginals = marginals;
		this.marginal = marginal;
	}
	
	protected void setMeets(ArrayList<Integer> meets, int meet)
	{
		this.meets = meets;
		this.meet = meet;
	}
	
	protected void setExceeds(ArrayList<Integer> exceeds, int exceed)
	{
		this.exceeds = exceeds;
		this.exceed = exceed;
	}
	
	
}