package team_project;

import java.util.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MasterList 
{
	private ArrayList<String> masterList;
	
	protected MasterList() 
	{
		this.masterList = new ArrayList<String>();
	}
	
	
	protected ArrayList<String> getMasterList() {
		return this.masterList;
	}
	
	
	protected String getCourse(int index)
	{
		return this.masterList.get(index);
	}
	
	
	protected void addCourse(String courseCode)
	{
		this.masterList.add(courseCode);
	}
	
	
	protected int getSize()
	{
		return this.masterList.size();
	}
	
	
	
	protected static MasterList showMasterList(MasterList courses, TranscriptHandler cohort)
	{
		ConfigGUI.openConfig();
		
		Stage window = new Stage();
		Scene scene;
		
		// ----- Config Menu -----
		
		Text masterList = new Text("Master Course List");
		masterList.setFont(Font.font(20));
		
		
		Button back = new Button("Back");
		back.setOnAction(e ->
		{
			window.close();
		});
		
		
		
		HBox title = new HBox();
		title.setPadding(new Insets(10,10,10,10));
		title.setSpacing(10);
		title.setAlignment(Pos.CENTER);
		title.getChildren().addAll(masterList);
		
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(back);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setBottom(bottomButtons);
		
		
		scene = new Scene(layout, 250, 200);
		
		// -------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			ConfigGUI.closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			updateMasterList(courses, cohort);
		});
		
		window.setScene(scene);
		window.setTitle("Master Course List");
		window.showAndWait();
		
		// ------------------------------------
		return courses;
	}
	
	
	private static MasterList updateMasterList(MasterList courses, TranscriptHandler cohort)
	{
		for(int k = 0; k<cohort.getSize(); k++)
		{
			Transcript tScript = cohort.getTranscript(k);
			
			for(int i = 0; i < tScript.getSize(); i++) 
			{
				
				if(courses.getSize() == 0)
				{
					courses.addCourse(tScript.getCourse(i).getCourseCode());
					continue;
				}
				
				for(int j = 0; j < courses.getSize(); j++) 
				{
					try 
					{
						if(tScript.getCourse(i).getCourseCode().equals(courses.getCourse(j))) 
						{
							break;
						}
						
						if(courses.getCourse(j) == null) 
						{
							courses.addCourse(tScript.getCourse(i).getCourseCode());
							break;
						}
					}
					catch(NullPointerException e) 
					{
						System.out.println("Unable to read Master List.");
					}
				}
			}
		}
		return courses;
	}
	
	
	/*public String printMasterList() 
	{
		String list = null;
		for(int i = 0; i <= masterList.size(); i++) {
			list = list + masterList.get(i);
		}
		return list;
	}*/
	
}
