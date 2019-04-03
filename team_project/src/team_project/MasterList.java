package team_project;

import java.util.*;

public class MasterList {
	
	private ArrayList<String> masterList;
	
	public MasterList() {
		masterList = new ArrayList<String>();
	}
	
	public String updateMasterList() {
		for(int i = 0; i <= tScript.size(); i++) {
			for(int j = 0; j <= tScript.size(); j++) {
				try {
					if(tScript.get(i).getCourseCode().equals(masterList.get(j))) {
						break;
					}
					else if(masterList.get(j).isEmpty()) {
						masterList.add(tScript.get(i).getCourseCode());
					}
				}catch(NullPointerException e) {System.out.println("Unable to read Master List.");}
			}
		}
		return "Master List has been successfully updated.";
	}
	
	public String printMasterList() {
		String list = null;
		for(int i = 0; i <= masterList.size(); i++) {
			list = list + masterList.get(i);
		}
		return list;
	}
	
	public ArrayList<String> getMasterList() {
		return masterList;
	}
}
