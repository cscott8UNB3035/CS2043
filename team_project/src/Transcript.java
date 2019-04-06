package team_project;

import java.util.ArrayList;

public class Transcript {
	
	private ArrayList<Course> tScript;
	
	protected Transcript() {
		this.tScript = new ArrayList<Course>();
	}
	
	protected void add(Course c) {
		this.tScript.add(c);
	}
	
	protected ArrayList<Course> getCourses()
	{
		return this.tScript;
	}
	
	protected int getSize()
	{
		return this.tScript.size();
	}
	
	protected Course getCourse(int index)
	{
		return this.tScript.get(index);
	}
	
}
