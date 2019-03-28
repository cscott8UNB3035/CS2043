package team_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.stage.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConfigGUI extends Application
{
	Stage window;
	Scene scene1, scene2, scene3;
	
	// ----- Configuration File Variables -----
	private static InputStream is = null;
	private static Properties prop = new Properties();
	private static String configFileName = "C:\\Users\\coby\\Java Workspace\\CS2043\\team_project\\transcript_analyser.config";
	
	
	// ----- Excel File Variables -----
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static XSSFWorkbook workbook;
	private static XSSFSheet spreadsheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	
	// ----- Configuration File Methods -----
	
	protected static void openConfig()
	{
		
		try
		{
			is = new FileInputStream(configFileName);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: Config file not found.");
		}
		
		try
		{
			prop.load(is);
		}
		catch (IOException e)
		{
			System.out.println("Error: Config could not be opened.");
		}
		
	}
	
	
	protected static String getCourseEquivPath()
	{
		
		try
		{
			return prop.getProperty("course_equiv_path") + "course_equivalency_list.xlsx";
		}
		catch (Exception e)
		{
			System.out.println("Error: Could not find Course Equivalency List location.");
			return null;
		}
		
	}
	
	
	protected static String getTranscriptFolderPath()
	{
		
		try
		{
			return prop.getProperty("transcript_path");
		}
		catch (Exception e)
		{
			System.out.println("Error: Could not find Raw Transcript location.");
			return null;
		}
		
	}
	
	
	protected static void closeConfig()
	{
		
		try
		{	
			is.close();
		}
		catch(Exception e)
		{
			System.out.println("Error: Exception when closing config file.");
		}
		
	}
	
	
	protected static void openCourseEquiv(String filepath)
	{
		
		try 
		{
			fis = new FileInputStream(filepath);
			//fos = new FileOutputStream(filepath);
			
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheetAt(0);
		}
		catch (Exception e)
		{
			System.out.println("Error: Course Equivalency List not found.");
		}
		
	}
	
	
	protected static void closeCourseEquiv()
	{
		
		try
		{	
			fis.close();
			//fos.close();
			workbook.close();
		}
		catch(Exception e)
		{
			System.out.println("Error: Course Equivalency List could not be saved. ");
		}
		
	}
	
	
	// ----- GUI Methods -----
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	public void start(Stage primaryStage)
	{
		openConfig();
		window = new Stage();
		
		// ----- Main Config Menu (scene1) -----
		
		Text configMenu = new Text("Configuration Menu");
		configMenu.setFont(Font.font(16));
		
		
		Button viewConfig = new Button("View Configuration");
		
		Button viewCourseEquivList = new Button("View Course Equivalencies");
		viewCourseEquivList.setOnAction(e -> 
		{
			window.close();
			window.setScene(scene3);
			window.show();
		});
		
		
		VBox mainMenu = new VBox();
		mainMenu.setPadding(new Insets(10,10,10,10));
		mainMenu.setSpacing(10);
		mainMenu.setAlignment(Pos.CENTER);
		mainMenu.getChildren().addAll(configMenu, viewConfig, viewCourseEquivList);
		
		scene1 = new Scene(mainMenu, 250, 200);
		
		// -------------------------------------
		
		
		
		// ----- Course Equivalency Window (scene3) -----
		
		//top
		Text equivTitle = new Text("Course Equivalencies");
		equivTitle.setFont(Font.font(16));
		
		HBox topMenu = new HBox();
		topMenu.setPadding(new Insets(10,10,10,10));
		topMenu.setSpacing(10);
		topMenu.setAlignment(Pos.CENTER);
		topMenu.getChildren().add(equivTitle);
		
		
		
		//mid
		ScrollPane data = new ScrollPane();
		
		
		
		//bottom
		Button addEquivalency = new Button("Add Equivalency");
		addEquivalency.setOnAction(e ->
		{
			window.close();
			addCourseEquiv();
			window.show();
		});
		
		Button back = new Button("Back");
		back.setOnAction(e ->
		{
			window.close();
			window.setScene(scene1);
			window.show();
		});
		
		VBox bottomMenu = new VBox();
		bottomMenu.setPadding(new Insets(10,10,10,10));
		bottomMenu.setSpacing(10);
		bottomMenu.setAlignment(Pos.CENTER);
		bottomMenu.getChildren().addAll(addEquivalency, back);
		
		
		
		//entire layout
		BorderPane courseEquivMenu = new BorderPane();
		courseEquivMenu.setTop(topMenu);
		courseEquivMenu.setCenter(data);
		courseEquivMenu.setBottom(bottomMenu);
		
		
		
		//set scene
		scene3 = new Scene(courseEquivMenu, 350, 300);
		
		// ----------------------------------------------
		
		
		
		// ----- Global Window Properties -----
		
		window.setOnCloseRequest(e -> 
		{
			e.consume();
			closeProgram();
		});
		
		window.setOnShowing(e -> 
		{
			updateCourseEquivList(data);
		});
		
		window.setScene(scene1);
		window.setTitle("Configuration");
		window.show();
		
		// ------------------------------------
	}
	
	
	//ConfirmBox will be opened that confirms the user really wants to close the program.
	private void closeProgram()
	{
		boolean answer = ConfirmBox.display("Exit Program", "Are you sure you want to exit?");
		if(answer)
		{
			closeConfig();
			window.close();
		}
	}
	
	
	//GridPane will be updated with newest course-equiv data, and inserted into data scrollpane in scene3.
	private static void updateCourseEquivList(ScrollPane sp)
	{
		openCourseEquiv(getCourseEquivPath());
		
		row = spreadsheet.getRow(0);
		int maxRow = spreadsheet.getLastRowNum();
		int maxCell = row.getLastCellNum();
		
		
		
		GridPane excel = new GridPane();
		excel.setPadding(new Insets(10, 10, 10, 10));
		excel.setVgap(10);
		excel.setHgap(20);
		
		Text delete = new Text("Delete");
		delete.setFont(Font.font(14));
		GridPane.setConstraints(delete, 0, 0);
		
		Text title = new Text("Equivalencies");
		title.setFont(Font.font(14));
		GridPane.setConstraints(title, 1, 0);
		
		excel.getChildren().addAll(delete, title);
		
		
		//button to remove current row's data needed
		//get info column by column, then row by row.
		//once the end of a column has been reached (nullPointer), go back to row 0, and go to the next column.
		//do this until the last column has been reached (maxCell)
		
		for(int i=0; i<maxCell; i++)
		{
			
			for(int j=0; j<=maxRow+1; j++)
			{
				try
				{
					if(j==0)
					{
						Button temp = new Button("X");
						temp.setOnAction(e -> 
						{
							
						});
						
						GridPane.setConstraints(temp, j, i+1);
						excel.getChildren().add(temp);
					}
					else
					{
						Label temp = new Label(spreadsheet.getRow(j-1).getCell(i).getStringCellValue());
						
						GridPane.setConstraints(temp, j, i+1);
						excel.getChildren().add(temp);
					}
				}
				catch (NullPointerException e)
				{
					break;
				}
			}
			
		}
		
		
		
		sp.setContent(excel);
		
		closeCourseEquiv();
	}
	
	
	private static void addCourseEquiv()
	{
		Stage window2 = new Stage();
		Scene sceneA;
		
		
		
		Text addCourseEquivTitle = new Text("Enter # of Courses: ");
		addCourseEquivTitle.setFont(Font.font(14));
		
		TextField numCoursesField = new TextField();
		numCoursesField.setMinWidth(75);
		numCoursesField.setMaxWidth(75);
		
		Button go = new Button("Go");
		go.setOnAction(e ->
		{	
			openCourseEquiv(getCourseEquivPath());
			
			int currentCell = row.getLastCellNum();
				
			for(int i=0; i<Integer.parseInt(numCoursesField.getText()); i++)
			{
				row = spreadsheet.getRow(i);
				cell = row.createCell(currentCell);
				
				addCell(cell);
			}
			
			try
			{
				fos = new FileOutputStream(getCourseEquivPath());
				workbook.write(fos);
				fos.close();
			}
			catch(Exception exc)
			{
				
			}
			
			closeCourseEquiv();
			window2.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window2.close();
			}
		});
		
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(addCourseEquivTitle, numCoursesField, go, cancel);
		
		
		
		//define scene
		sceneA = new Scene(contents, 225, 310);
		
		
		
		// ----- Window Properties -----
		
		window2.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window2.close();
			}
		});
		
		window2.setScene(sceneA);
		window2.setTitle("Configuration");
		window2.showAndWait();
		
	}
	
	
	private static void addCell(XSSFCell cell)
	{
		Stage window2 = new Stage();
		Scene sceneA;
		
		
		
		Text enterCourse = new Text("Enter Course: ");
		enterCourse.setFont(Font.font(14));
		
		TextField course = new TextField();
		course.setMinWidth(75);
		course.setMaxWidth(75);
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			cell.setCellValue(course.getText());
			window2.close();
		});
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e ->
		{
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window2.close();
			}
		});
		
		
		
		VBox contents = new VBox();
		contents.setPadding(new Insets(10,10,10,10));
		contents.setSpacing(10);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(enterCourse, course, ok, cancel);
		
		
		
		//define scene
		sceneA = new Scene(contents, 225, 310);
		
		
		
		// ----- Window Properties -----
		
		window2.setOnCloseRequest(e -> 
		{
			e.consume();
			boolean answer = ConfirmBox.display("Cancel", "Are you sure you want to go back?");
			if(answer)
			{
				window2.close();
			}
		});
		
		window2.setScene(sceneA);
		window2.setTitle("Configuration");
		window2.showAndWait();
	}
	
	
}