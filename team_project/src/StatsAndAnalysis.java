package team_project;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/*
 * try-catch for each method to try setting list and tScript
 * 1. output(filename, var): if all output is standard
 * 2. output(type, var): if all output is categorized
 */
public class StatsAndAnalysis {
  
	private static TranscriptHandler tScriptList;

	
  
	protected static int getNumStudetsPerYear(String Year){
		ArrayList<Transcript> list = tScriptList.getList();
		int output = 0;
		for( Transcript t: list) {
			ArrayList<Course> tScript = t.getCourses();
			for(Course c: tScript) {
				if(c.getTerm().contains(Year)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
	
	protected static int getNumStudentsPerLocation(String Location) {
		ArrayList<Transcript> list = tScriptList.getList();
		int output = 0;
		for( Transcript t: list) {
			ArrayList<Course> tScript = t.getCourses();
			for(Course c: tScript) {
				if(c.getSectionCode().contains(Location)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
	
	protected static int getNumStudentsInCoursePerYear(String courseCode, String Year) {
		ArrayList<Transcript> list = tScriptList.getList();
		int output = 0;
		for( Transcript t: list) {
			ArrayList<Course> tScript = t.getCourses();
			for(Course c: tScript) {
				if(c.getCourseCode().equals(courseCode)&&c.getTerm().contains(Year)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
	
	protected static int getNumStudentsInLocationPerYear(String Location, String Year) {
		ArrayList<Transcript> list = tScriptList.getList();
		int output = 0;
		for( Transcript t: list) {
			ArrayList<Course> tScript = t.getCourses();
			for(Course c: tScript) {
				if(c.getSectionCode().contains(Location)&&c.getTerm().contains(Year)) {
					output++;
					break;
				}
			}
		}
		return output;
	}
	
	
	
	
	/*
	 * GUI Component
	 */
	protected static void showStatistics(TranscriptHandler cohort)
	{
		ConfigGUI.openConfig();
		tScriptList = cohort;
		
		Stage window = new Stage();
		Scene scene;
		
		// ----- Config Menu -----
		
		Text genStats = new Text("Generate Statistics");
		genStats.setFont(Font.font(20));
		
		
		Button back = new Button("Back");
		back.setOnAction(e ->
		{
			window.close();
		});
		
		
		Button stat1 = new Button("Stat 1");
		stat1.setMinWidth(100);
		stat1.setMaxWidth(100);
		stat1.setOnAction(e ->
		{
			studentsPerYearWindow();
		});
		
		
		Button stat2 = new Button("Stat 2");
		stat2.setMinWidth(100);
		stat2.setMaxWidth(100);
		stat2.setOnAction(e ->
		{
			studentsPerLocationWindow();
		});
		
		
		Button stat3 = new Button("Stat 3");
		stat3.setMinWidth(100);
		stat3.setMaxWidth(100);
		stat3.setOnAction(e ->
		{
			studentsPerCoursePerYearWindow();
		});
		
		
		Button stat4 = new Button("Stat 4");
		stat4.setMinWidth(100);
		stat4.setMaxWidth(100);
		stat4.setOnAction(e ->
		{
			studentsInLocationByYearWindow();
		});
		
		
		
		HBox title = new HBox();
		title.setPadding(new Insets(15, 15, 15, 15));
		title.setSpacing(15);
		title.setAlignment(Pos.CENTER);
		title.getChildren().addAll(genStats);
		
		
		VBox stats = new VBox();
		stats.setPadding(new Insets(15, 15, 15, 15));
		stats.setSpacing(15);
		stats.setAlignment(Pos.CENTER);
		stats.getChildren().addAll(stat1, stat2, stat3, stat4);
		
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(15, 15, 15, 15));
		bottomButtons.setSpacing(15);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(back);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setCenter(stats);
		layout.setBottom(bottomButtons);
		
		scene = new Scene(layout, 250, 350);
		
		// -------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			ConfigGUI.closeConfig();
		});
		
		window.setOnShowing(e -> 
		{
			
		});
		
		window.setScene(scene);
		window.setTitle("Statistics");
		window.show();
		
		// ------------------------------------
	}
	
	
	
	protected static void studentsPerYearWindow()
	{
		Stage window = new Stage();
		Scene scene;
		
		
		
		Text enterYear = new Text("Enter Year:");
		enterYear.setFont(Font.font(14));
		
		TextField yearField = new TextField();
		yearField.setMinWidth(75);
		yearField.setMaxWidth(75);
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			getNumStudetsPerYear(enterYear.getText());
			//create output
			window.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
		
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(enterYear, yearField);
		
		VBox bottomButtons = new VBox();
		bottomButtons.setPadding(new Insets(10,10,10,10));
		bottomButtons.setSpacing(10);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(ok, cancel);
		
		BorderPane layout = new BorderPane();
		layout.setCenter(contents);
		layout.setBottom(bottomButtons);
		
		
		
		
		//define scene
		scene = new Scene(layout, 225, 310);
		
		
		
		// ----- Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
		
		window.setScene(scene);
		window.setTitle("Students By Year");
		window.showAndWait();
		
	}
	
	
	
	protected static void studentsPerLocationWindow()
	{
		Stage window = new Stage();
		Scene scene;
		
		
		
		Text enterLocation = new Text("Enter Location:");
		enterLocation.setFont(Font.font(14));
		
		TextField locationField = new TextField();
		locationField.setMinWidth(75);
		locationField.setMaxWidth(75);
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			getNumStudentsPerLocation(enterLocation.getText());
			//create output
			window.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
		
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(enterLocation, locationField);
		
		VBox bottomButtons = new VBox();
		bottomButtons.setPadding(new Insets(10,10,10,10));
		bottomButtons.setSpacing(10);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(ok, cancel);
		
		BorderPane layout = new BorderPane();
		layout.setCenter(contents);
		layout.setBottom(bottomButtons);
		
		
		
		
		//define scene
		scene = new Scene(layout, 225, 310);
		
		
		
		// ----- Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
		
		window.setScene(scene);
		window.setTitle("Students By Location");
		window.showAndWait();
		
	}
	
	
	
	protected static void studentsPerCoursePerYearWindow()
	{
		Stage window = new Stage();
		Scene scene;
		
		
		
		Text enterCourse = new Text("Enter Course Code:");
		enterCourse.setFont(Font.font(14));
		
		TextField courseField = new TextField();
		courseField.setMinWidth(75);
		courseField.setMaxWidth(75);
		
		Text enterYear = new Text("Enter Year:");
		enterYear.setFont(Font.font(14));
		
		TextField yearField = new TextField();
		yearField.setMinWidth(75);
		yearField.setMaxWidth(75);
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			getNumStudentsInCoursePerYear(courseField.getText(), yearField.getText());
			//create output
			window.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
		
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(enterCourse, courseField, enterYear, yearField);
		
		VBox bottomButtons = new VBox();
		bottomButtons.setPadding(new Insets(10,10,10,10));
		bottomButtons.setSpacing(10);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(ok, cancel);
		
		BorderPane layout = new BorderPane();
		layout.setCenter(contents);
		layout.setBottom(bottomButtons);
		
		
		
		
		//define scene
		scene = new Scene(layout, 225, 310);
		
		
		
		// ----- Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
		
		window.setScene(scene);
		window.setTitle("Students Per Course Per Year");
		window.showAndWait();
		
	}
	
	
	
	protected static void studentsInLocationByYearWindow()
	{
		Stage window = new Stage();
		Scene scene;
		
		
		
		Text enterLocation = new Text("Enter Location:");
		enterLocation.setFont(Font.font(14));
		
		TextField locationField = new TextField();
		locationField.setMinWidth(75);
		locationField.setMaxWidth(75);
		
		Text enterYear = new Text("Enter Year:");
		enterYear.setFont(Font.font(14));
		
		TextField yearField = new TextField();
		yearField.setMinWidth(75);
		yearField.setMaxWidth(75);
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			getNumStudentsInLocationPerYear(locationField.getText(), yearField.getText());
			//create output
			window.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
	
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(enterLocation, locationField, enterYear, yearField);
		
		VBox bottomButtons = new VBox();
		bottomButtons.setPadding(new Insets(10,10,10,10));
		bottomButtons.setSpacing(10);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(ok, cancel);
		
		BorderPane layout = new BorderPane();
		layout.setCenter(contents);
		layout.setBottom(bottomButtons);
		
		
		
		
		//define scene
		scene = new Scene(layout, 225, 310);
		
		
		
		// ----- Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window.close();
			}
		});
		
		window.setScene(scene);
		window.setTitle("Students In Location By Year");
		window.showAndWait();
		
	}
	
}