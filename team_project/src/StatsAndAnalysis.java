package team_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
public class StatsAndAnalysis
{  
	private static TranscriptHandler tScriptList;

	
	protected static int getNumStudetsPerYear(String Year){
		try
		{
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
		catch(NullPointerException e)
		{
			return -1;
		}
		
	}
	
	protected static int getNumStudentsPerLocation(String Location) {
		try
		{
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
		catch(NullPointerException e)
		{
			return -1;
		}
	}
	
	protected static int getNumStudentsInCoursePerYear(String courseCode, String Year) {
		try
		{
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
		catch(NullPointerException e)
		{
			return -1;
		}
	}
	
	protected static int getNumStudentsInLocationPerYear(String Location, String Year) {
		try
		{
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
		catch(NullPointerException e)
		{
			return -1;
		}
	}
	
	
	
	/*
	 * GUI Component
	 */
	protected static Distributions[] showStatistics(TranscriptHandler cohort, Distributions[] distributions, Area[] areas)
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
		
		
		Button stat1 = new Button("Students/Year");
		stat1.setMinWidth(175);
		stat1.setMaxWidth(175);
		stat1.setOnAction(e ->
		{
			studentsPerYearWindow();
		});
		
		
		Button stat2 = new Button("Students/Location");
		stat2.setMinWidth(175);
		stat2.setMaxWidth(175);
		stat2.setOnAction(e ->
		{
			studentsPerLocationWindow();
		});
		
		
		Button stat3 = new Button("Students/Course/Year");
		stat3.setMinWidth(175);
		stat3.setMaxWidth(175);
		stat3.setOnAction(e ->
		{
			studentsPerCoursePerYearWindow();
		});
		
		
		Button stat4 = new Button("Students/Location/Year");
		stat4.setMinWidth(175);
		stat4.setMaxWidth(175);
		stat4.setOnAction(e ->
		{
			studentsInLocationByYearWindow();
		});
		
		Button raw = new Button("Get Raw Distribution");
		raw.setMinWidth(175);
		raw.setMaxWidth(175);
		raw.setOnAction(e ->
		{
			distributions[0] = getRawDistributions(cohort);
		});
		
		Button area = new Button("Get Area Distribution");
		area.setMinWidth(175);
		area.setMaxWidth(175);
		area.setOnAction(e ->
		{	
			
			
			//distributions[1] = getAreaDistributions(cohort, a);
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
		stats.getChildren().addAll(stat1, stat2, stat3, stat4, raw, area);
		
		
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
		window.showAndWait();
		
		// ------------------------------------
		
		return distributions;
	}
	
	
	
	/*
	 * GUI Helper-Methods
	 */
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
			int numStudents = getNumStudetsPerYear(yearField.getText());
			
			output("Students Per Year", yearField.getText(), ("" + numStudents), "");
			
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
			int numStudents = getNumStudentsPerLocation(locationField.getText());
			
			output("Students Per Location", locationField.getText(), ("" + numStudents), "");
			
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
			int numStudents = getNumStudentsInCoursePerYear(courseField.getText(), yearField.getText());
			
			output("Students Per Course Per Year", courseField.getText(), yearField.getText(), ("" + numStudents));
			
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
			int numStudents = getNumStudentsInLocationPerYear(locationField.getText(), yearField.getText());
			
			output("Students In Location By Year", yearField.getText(), locationField.getText(), ("" + numStudents));
			
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

	
	/*
	 * Output for statistics
	 */
	protected static void output(String sheetName, String rowValueOne, String rowValueTwo, String rowValueThree)
	{
		int currentRow = 0;
		
		XSSFWorkbook workbook = null;
		XSSFSheet spreadsheet = null;
		XSSFRow row;
		XSSFCell cell;
		
		File file = new File(ConfigGUI.getOutputFolderPath() + "output.xlsx");
		FileInputStream in = null;
		FileOutputStream out = null;
		
		
		//check for file
		try
		{
			in = new FileInputStream(file);
			workbook = new XSSFWorkbook(in);
		}
		catch(Exception e)
		{
			AlertBox.displayAlert("Attention", "Output file does not exist. New file will be created.");
			workbook = new XSSFWorkbook();
			
			spreadsheet = workbook.createSheet("Students Per Year");
			spreadsheet = workbook.createSheet("Students Per Location");
			spreadsheet = workbook.createSheet("Students Per Course Per Year");
			spreadsheet = workbook.createSheet("Students In Location By Year");
		} 
		
		
		try
		{
			out = new FileOutputStream(file);
		}
		catch(Exception e)
		{
			AlertBox.displayAlert("Error", "Could not set File Output Stream.");
		}
		
		
		//file now exists. get proper sheet
		spreadsheet = workbook.getSheet(sheetName);
		
		//create cells
		currentRow = spreadsheet.getLastRowNum() + 1;
		row = spreadsheet.createRow(currentRow);
		cell = row.createCell(0);
		cell.setCellValue(rowValueOne);
		cell = row.createCell(1);
		cell.setCellValue(rowValueTwo);
		cell = row.createCell(2);
		cell.setCellValue(rowValueThree);
		
		//write new data to excel file
		try
		{
			workbook.write(out);
			out.close();
			workbook.close();
		}
		catch(Exception e)
		{
			AlertBox.displayAlert("Error", "Could not write to excel file.");
		}
		
		try
		{
			in.close();
		}
		catch(Exception e)
		{
			
		}
		
	}

	
	protected static Distributions getAreaDistributions(TranscriptHandler tHandler, Area area) 
	{
		ArrayList<String> courseCode = new ArrayList<String>();
		courseCode.addAll(null);
		ArrayList<Integer> others = new ArrayList<Integer>();
		int other = 0;
		ArrayList<Integer> fails = new ArrayList<Integer>();
		int fail = 0;
		ArrayList<Integer> marginals = new ArrayList<Integer>();
		int marginal = 0;
		ArrayList<Integer> meets = new ArrayList<Integer>();
		int meet = 0;
		ArrayList<Integer> exceeds = new ArrayList<Integer>();
		int exceed = 0;
		
		
		
		int num = 0;
		for(int i = 0; i <= tHandler.getSize(); i++) 
		{
			for(int j = 0; j <= tHandler.getTranscript(j).getSize(); j++) 
			{
				Transcript tScript = tHandler.getTranscript(j);
				
				if(tScript.getCourse(j).getCourseCode().equals(courseCode.get(i))) 
				{
					
					if(tScript.getCourse(i).getLetterGrade() == "F" || tScript.getCourse(i).getLetterGrade() == "D") {
						num = 2;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "C" || tScript.getCourse(i).getLetterGrade() == "C+") {
						num = 3;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "B-" || tScript.getCourse(i).getLetterGrade() == "B" || tScript.getCourse(i).getLetterGrade() == "B+") {
						num = 4;
					}
					else if(tScript.getCourse(i).getLetterGrade() == "A-" || tScript.getCourse(i).getLetterGrade() == "A" || tScript.getCourse(i).getLetterGrade() == "A+") {
						num = 5;
					}
					else 
					{
						num = 1;
					}
					
					courseCode.add(tScript.getCourse(i).getCourseCode());
					switch(num) {
						case 1: other = other + 1;
								others.add(other);
						case 2: fail = fail + 1;
								fails.add(fail);
								break;
						case 3: marginal = marginal + 1;
								marginals.add(marginal);
								break;
						case 4: meet = meet + 1;
								meets.add(meet);
								break;
						case 5: exceed = exceed + 1;
								exceeds.add(exceed);
								break; 
					}
				}
			}
		}
		Distributions distribution = new Distributions(courseCode, others, fails, marginals, meets, exceeds);
		return distribution;
	}
	
	
	protected static Distributions getRawDistributions(TranscriptHandler tHandler) 
	{
		ArrayList<String> courseCode = new ArrayList<String>();
		courseCode.add(null);
		ArrayList<Integer> others = new ArrayList<Integer>();
		int other = 0;
		ArrayList<Integer> fails = new ArrayList<Integer>();
		int fail = 0;
		ArrayList<Integer> marginals = new ArrayList<Integer>();
		int marginal = 0;
		ArrayList<Integer> meets = new ArrayList<Integer>();
		int meet = 0;
		ArrayList<Integer> exceeds = new ArrayList<Integer>();
		int exceed = 0;
		
		
		int num = 0;
		for(int i=0; i < tHandler.getSize(); i++)
		{
			Transcript tScript = tHandler.getTranscript(i);
			
			for(int j=0; j < tScript.getSize(); j++) 
			{
				Course c = tScript.getCourse(j);
				
				for(int k=0; k < courseCode.size(); k++)
				{
					String ccCourse = courseCode.get(k);
					
					if(c.getCourseCode().equals(ccCourse)) {
						if(tScript.getCourse(i).getLetterGrade() == "F" || tScript.getCourse(i).getLetterGrade() == "D") {
							num = 2;
						}
						else if(tScript.getCourse(i).getLetterGrade() == "C" || tScript.getCourse(i).getLetterGrade() == "C+") {
							num = 3;
						}
						else if(tScript.getCourse(i).getLetterGrade() == "B-" || tScript.getCourse(i).getLetterGrade() == "B" || tScript.getCourse(i).getLetterGrade() == "B+") {
							num = 4;
						}
						else if(tScript.getCourse(i).getLetterGrade() == "A-" || tScript.getCourse(i).getLetterGrade() == "A" || tScript.getCourse(i).getLetterGrade() == "A+") {
							num = 5;
						}
						else {
							num = 1;
						}
						
						courseCode.add(tScript.getCourse(i).getCourseCode());
						switch(num) {
							case 1: other = other + 1;
									others.add(other);
							case 2: fail = fail + 1;
									fails.add(fail);
									break;
							case 3: marginal = marginal + 1;
									marginals.add(marginal);
									break;
							case 4: meet = meet + 1;
									meets.add(meet);
									break;
							case 5: exceed = exceed + 1;
									exceeds.add(exceed);
									break; 
						}
					}
				}
			}
		}
		Distributions distribution = new Distributions(courseCode, others, fails, marginals, meets, exceeds);
		return distribution;
	}
	
	
	protected static void outputDistributions(Distributions[] distributions)
	{
		
	}
	
	
	protected static void selectArea(Area[] areas)
	{
		Stage window = new Stage();
		Scene scene;
		
		
		
		Text enterArea = new Text("Enter Area:");
		enterArea.setFont(Font.font(14));
		
		TextField areaField = new TextField();
		areaField.setMinWidth(75);
		areaField.setMaxWidth(75);
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			
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
		contents.getChildren().addAll(enterArea, areaField);
		
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
	
	
}