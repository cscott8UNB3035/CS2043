package team_project;

import java.util.*;

public class Area {
	
	private String areaName;
	private ArrayList<String> areaList;
	
	public Area(String areaName, ArrayList<String> areaList) {
		this.areaName = areaName;
		this.areaList = areaList;
	}
	
	protected String getAreaName() {
		return areaName;
	}
	
	protected ArrayList<String> getAreaList() {
		return areaList;
	}
	
	/*protected String printAreaList(Area area) {
		String list = null;
		list = area.getAreaName();
		for(int i = 0; i <= areaList.size(); i++) {
			list = list + '\n' + area.getAreaList().get(i);
		}
		return list;
	}*/
}