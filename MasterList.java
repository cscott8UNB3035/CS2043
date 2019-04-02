package team_project;

public class MasterList {
	
	public MasterList() {
		ArrayList<String> masterList = new ArrayList<String>;
		for(int i = 0; i <= TranscriptList.size(); i++) {
			for(int j = 0; j <= TranscriptList.size(); j++) {
				if(TranscriptList.get(i).getCourseCode().equals(masterList(j))) {
					break;
				}
				else if(masterList(j).isEmpty()) {
					masterList(j) = TranscriptList.get(i).getCourseCode();
				}
			}
		}
	}
}
