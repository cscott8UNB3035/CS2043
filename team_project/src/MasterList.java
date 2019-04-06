package team_project;

import java.util.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	
	
	protected void addCourse(int index, String courseCode)
	{
		this.masterList.add(index, courseCode);
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
		
		//mid
		ScrollPane data = new ScrollPane();
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(back);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setCenter(data);
		layout.setBottom(bottomButtons);
		
		
		scene = new Scene(layout, 250, 500);
		
		// -------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			ConfigGUI.closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			updateMasterList(courses, cohort);
			updateMasterListWindow(data, courses);
		});
		
		window.setScene(scene);
		window.setTitle("Master Course List");
		window.showAndWait();
		
		// ------------------------------------
		return courses;
	}
	
	
	private static MasterList updateMasterList(MasterList mList, TranscriptHandler cohort)
	{
		if(mList.getSize() == 0)
		{
			mList.addCourse(cohort.getTranscript(0).getCourse(0).getCourseCode());
			mList.addCourse(null);
		}
		
		for(int i=0; i<cohort.getSize(); i++)
		{
			Transcript tScript = cohort.getTranscript(i);
			
			for(int j=0; j<tScript.getSize(); j++)
			{
				String newCourse = tScript.getCourse(j).getCourseCode();
				
				for(int k=0; k<mList.getSize(); k++)
				{
					try
					{
						String mlistCourse = mList.getCourse(k);
						
						if(newCourse.equals(mlistCourse)) 
						{
							break;
						}
						
						if(mlistCourse == null) 
						{
							mList.addCourse(k, newCourse);
							break;
						}
					}
					catch(Exception e)
					{
						break;
					}
					
				}
			}
			
		}
		
		
		return mList;
	}

	
	private static void updateMasterListWindow(ScrollPane sp, MasterList mlist)
	{
		VBox stuff = new VBox();
		stuff.setPadding(new Insets(10,10,10,10));
		stuff.setSpacing(10);
		stuff.setAlignment(Pos.CENTER);
		
		for(int i=0; i<mlist.getSize(); i++)
		{
			Text temp = new Text(mlist.getCourse(i));
			stuff.getChildren().add(temp);
			temp.setFont(Font.font(13));
		}
		
		sp.setContent(stuff);
	}

	
}