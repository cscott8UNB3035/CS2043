package team_project;

import java.util.ArrayList;

public class TranscriptHandler {
	
	private ArrayList<Transcript> list;
	
	protected TranscriptHandler() {
		this.list = new ArrayList<Transcript>();
	}
	
	protected void add(Transcript t) {
		this.list.add(t);
	}
	
	protected ArrayList<Transcript> getList()
	{
		return this.list;
	}
	
	protected int getSize()
	{
		return this.list.size();
	}
	
	protected Transcript getTranscript(int index)
	{
		return this.list.get(index);
	}

	
}