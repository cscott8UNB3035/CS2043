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
	
	public Distributions(ArrayList<String> courseCode, ArrayList<Integer> others, ArrayList<Integer> fails, ArrayList<Integer> marginals, ArrayList<Integer> meets, ArrayList<Integer> exceeds)
	{
		this.courseCode = courseCode;
		this.fails = fails;
		this.marginals = marginals;
		this.meets = meets;
		this.exceeds = exceeds;
	}
	

	protected static Distributions getAreaDistributions(TranscriptHandler tHandler, Area area) {
		for(int i = 0; i <= tHandler.getList.getSize(); i++) {
			for(int j = 0; j <= tHandler.getList.getTranscript(i).size(); j++) {
				if(!tHandler.getList.getTranscript(j).get(j).equals(courseCode.get(i)) && checkArea(tHandler.getList.getTranscript(j).get(j), area)) {
					courseCode.add(tScript.get(i).getCourseCode());
				}
				if(tHandler.getList.getTranscript(j).get(j).equals(courseCode.get(i))) {
					if(tScript.get(i).getLetterGrade().equals("F") || tScript.get(i).getLetterGrade().equals("D")) {
						fail = fail + 1;
						fails.add(fail);
					}
					else if(tScript.get(i).getLetterGrade().equals("C") || tScript.get(i).getLetterGrade().equals("C+")) {
						marginal = marginal + 1;
						marginals.add(marginal);
					}
					else if(tScript.get(i).getLetterGrade().equals("B-") || tScript.get(i).getLetterGrade().equals("B") || tScript.get(i).getLetterGrade().equals("B+")) {
						meet = meet + 1;
						meets.add(meet);
					}
					else if(tScript.get(i).getLetterGrade().equals("A-") || tScript.get(i).getLetterGrade().equals("A") || tScript.get(i).getLetterGrade().equals("A+")) {
						exceed = exceed + 1;
						exceeds.add(exceed);
					}
					else {
						other = other + 1;
						others.add(other);
					}
					courseCode.add(tScript.get(i).getCourseCode());
				}
			}
		}
		Distributions distribution = new Distributions(courseCode, others, fails, marginals, meets, exceeds);
		return distribution;
	}
	
	protected static Distributions getRawDistributions(TranscriptHandler tHandler) {
		for(int i = 0; i <= tHandler.getList.getSize(); i++) {
			for(int j = 0; j <= tHandler.getList.getTranscript(i).size(); j++) {
				if(!tHandler.getList.getTranscript(j).get(j).equals(courseCode.get(i))) {
					courseCode.add(tScript.get(i).getCourseCode());
				}
				if(tHandler.getList.getTranscript(j).get(j).equals(courseCode.get(i))) {
					if(tHandler.getList.getTranscript(j).get(j).equals("F") || tHandler.getList.getTranscript(j).get(j).equals("D")) {
						fail = fail + 1;
						fails.add(fail);
					}
					else if(tHandler.getList.getTranscript(j).get(j).equals("C") || tHandler.getList.getTranscript(j).get(j).equals("C+")) {
						marginal = marginal + 1;
						marginals.add(marginal);
					}
					else if(tHandler.getList.getTranscript(j).get(j).equals("B-") || tHandler.getList.getTranscript(j).get(j).equals("B") || tHandler.getList.getTranscript(j).get(j).equals("B+")) {
						meet = meet + 1;
						meets.add(meet);
					}
					else if(tHandler.getList.getTranscript(j).get(j).equals("A-") || tHandler.getList.getTranscript(j).get(j).equals("A") || tHandler.getList.getTranscript(j).get(j).equals("A+")) {
						exceed = exceed + 1;
						exceeds.add(exceed);
					}
					else {
						other = other + 1;
						others.add(other);
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

	
	protected static boolean checkArea(Course course, Area area) {
		boolean result = false;
		for(int i = 0; i <= area.getAreaList().size(); i++) {
			if(course.getCourseCode().equals(area.getAreaList().get(i))) {
				result = true;
			}
		}
		return result;
	}
}

}

