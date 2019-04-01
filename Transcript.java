package team_project;

import java.util.ArrayList;

public class Transcript {
	
	private ArrayList<Course> tScript;
	
	protected Transcript() {
		super();
		this.tScript = new ArrayList<Course>();
	
	protected void add(Course c) {
		this.tScript.add(c);
	}

	public String toString() {
		String output = "Transcript Object:\n-------------------------------------"
						+ "------------------------------------------------\n";
		int max = this.tScript.size();
		for(int i = 0; i<max; i++) {
			output+=this.tScript.get(i).toString();
		}
		output = output + "-------------------------------------"
						+ "------------------------------------------------\n";
		return output;
	}
	
}
