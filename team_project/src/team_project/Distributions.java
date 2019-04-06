package team_project;

import java.util.*;

public class Distributions {
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
	
	protected Distributions getAreaDistributions(TranscriptHandler tHandler, Area area) {
		int num = 0;
		for(int i = 0; i <= tHandler.getList.getSize(); i++) {
			for(int j = 0; j <= tHandler.getList.getTranscript(j).size(); j++) {
				if(tHandler.getList.getTranscript(j).get(j).equals(courseCode.get(i))) {
					if(tScript.get(i).getLetterGrade() == "F" || tScript.get(i).getLetterGrade() == "D") {
						num = 2;
					}
					else if(tScript.get(i).getLetterGrade() == "C" || tScript.get(i).getLetterGrade() == "C+") {
						num = 3;
					}
					else if(tScript.get(i).getLetterGrade() == "B-" || tScript.get(i).getLetterGrade() == "B" || tScript.get(i).getLetterGrade() == "B+") {
						num = 4;
					}
					else if(tScript.get(i).getLetterGrade() == "A-" || tScript.get(i).getLetterGrade() == "A" || tScript.get(i).getLetterGrade() == "A+") {
						num = 5;
					}
					else {
						num = 1;
					}
					courseCode.add(tScript.get(i).getCourseCode());
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
	
	protected Distributions getRawDistributions(TranscriptHandler tHandler, tScript) {
		int num = 0;
		for(int i = 0; i <= tHandler.getList.getSize(); i++) {
			for(int j = 0; j <= tHandler.getList.getTranscript(j).size(); j++) {
				if(tHandler.getList.getTranscript(j).get(j).equals(courseCode.get(i))) {
					if(tScript.get(i).getLetterGrade() == "F" || tScript.get(i).getLetterGrade() == "D") {
						num = 2;
					}
					else if(tScript.get(i).getLetterGrade() == "C" || tScript.get(i).getLetterGrade() == "C+") {
						num = 3;
					}
					else if(tScript.get(i).getLetterGrade() == "B-" || tScript.get(i).getLetterGrade() == "B" || tScript.get(i).getLetterGrade() == "B+") {
						num = 4;
					}
					else if(tScript.get(i).getLetterGrade() == "A-" || tScript.get(i).getLetterGrade() == "A" || tScript.get(i).getLetterGrade() == "A+") {
						num = 5;
					}
					else {
						num = 1;
					}
					courseCode.add(tScript.get(i).getCourseCode());
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
		return distribution;s
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
}
