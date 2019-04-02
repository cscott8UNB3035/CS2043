package team_project;

import java.util.ArrayList;

public class TranscriptHandler {
	
	private ArrayList<Transcript> list;
	
	protected TranscriptHandler() {
		super();
		this.list = new ArrayList<Transcript>();
	}
	
	protected void add(Transcript c) {
		this.list.add(c);
	}

	public String toString() {
		String output = "List of Transcripts:\n-------------------------------------"
						+ "------------------------------------------------\n";
		int max = this.list.size();
		for(int i = 0; i<max; i++) {
			output+=(i+1) + ")" + this.list.get(i).toString();
		}
		output = output + "-------------------------------------"
						+ "------------------------------------------------\n";
		return output;
	}
	
}