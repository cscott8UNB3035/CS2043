package team_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

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
	Scene scene1, scene2, scene3, scene4;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	public void start(Stage primaryStage)
	{
		Configuration.openConfig();
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
			Configuration.closeConfig();
			window.close();
		}
	}
	
	
	//GridPane will be updated with newest course-equiv data, and inserted into data scrollpane in scene3.
	private static void updateCourseEquivList(ScrollPane sp)
	{
		try 
		{
			FileInputStream fis = new FileInputStream(Configuration.getCourseEquivPath());
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			
			XSSFRow row = spreadsheet.getRow(0);
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
			
			workbook.close();
			fis.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: Could not find specified file.");
		} 
		catch (IOException e)
		{
			System.out.println("Error: I/O Exception.");
		}
		
	}
	
	
	private static void addCourseEquiv()
	{
		// ----- Add New Course Equivalency Definition -----
		
		Stage window2 = new Stage();
		Scene sceneA;
		Scene sceneB;
		
		String numCourses;
		
		
		
		Text addCourseEquivTitle = new Text("Enter Courses: ");
		addCourseEquivTitle.setFont(Font.font(14));
		
		//top
		HBox topMenu = new HBox();
		topMenu.setPadding(new Insets(10,10,10,10));
		topMenu.setSpacing(10);
		topMenu.setAlignment(Pos.CENTER);
		topMenu.getChildren().add(addCourseEquivTitle);
		
		
		
		//mid
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		
		//Maximum 5 courses in an equivalency?
		Label courseTitle1 = new Label("Course Title: ");
		GridPane.setConstraints(courseTitle1, 0, 0);
		
		TextField courseField1 = new TextField();
		courseField1.setMinWidth(75);
		courseField1.setMaxWidth(75);
		GridPane.setConstraints(courseField1, 1, 0);
		
		
		Label courseTitle2 = new Label("Course Title: ");
		GridPane.setConstraints(courseTitle2, 0, 1);
		
		TextField courseField2 = new TextField();
		courseField2.setMinWidth(75);
		courseField2.setMaxWidth(75);
		GridPane.setConstraints(courseField2, 1, 1);
		
		
		Label courseTitle3 = new Label("Course Title: ");
		GridPane.setConstraints(courseTitle3, 0, 2);
		
		TextField courseField3 = new TextField();
		courseField3.setMinWidth(75);
		courseField3.setMaxWidth(75);
		GridPane.setConstraints(courseField3, 1, 2);
		
		
		Label courseTitle4 = new Label("Course Title: ");
		GridPane.setConstraints(courseTitle4, 0, 3);
		
		TextField courseField4 = new TextField();
		courseField4.setMinWidth(75);
		courseField4.setMaxWidth(75);
		GridPane.setConstraints(courseField4, 1, 3);
		
		
		Label courseTitle5 = new Label("Course Title: ");
		GridPane.setConstraints(courseTitle5, 0, 4);
		
		TextField courseField5 = new TextField();
		courseField5.setMinWidth(75);
		courseField5.setMaxWidth(75);
		GridPane.setConstraints(courseField5, 1, 4);
		
		grid.getChildren().addAll(courseTitle1, courseField1, courseTitle2, courseField2, courseTitle3, courseField3, 
				courseTitle4, courseField4, courseTitle5, courseField5);
		
		grid.setAlignment(Pos.CENTER);
		// -----
		
		
		
		
		
		Button ok = new Button("OK");
		ok.setOnAction(e ->
		{
			try
			{
				int count = 0;
				
				FileInputStream fis = new FileInputStream(Configuration.getCourseEquivPath());
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet spreadsheet = workbook.getSheetAt(0);
				XSSFCell cell;
				
				int currentCell = 1 + spreadsheet.getRow(0).getLastCellNum();
				
				if(courseField1 != null)
				{
					XSSFRow row = spreadsheet.getRow(count);
					cell = row.createCell(currentCell);
					cell.setCellValue(courseField1.getText());
					count++;
				}
				if(courseField2 != null)
				{
					XSSFRow row = spreadsheet.getRow(count);
					cell = row.createCell(currentCell);
					cell.setCellValue(courseField2.getText());
					count++;
				}
				if(courseField3 != null)
				{
					XSSFRow row = spreadsheet.getRow(count);
					cell = row.createCell(currentCell);
					cell.setCellValue(courseField3.getText());
					count++;
				}
				if(courseField4 != null)
				{
					XSSFRow row = spreadsheet.getRow(count);
					cell = row.createCell(currentCell);
					cell.setCellValue(courseField4.getText());
					count++;
				}
				if(courseField5 != null)
				{
					XSSFRow row = spreadsheet.getRow(count);
					cell = row.createCell(currentCell);
					cell.setCellValue(courseField5.getText());
					count++;
				}
				
				FileOutputStream fos = new FileOutputStream(Configuration.getCourseEquivPath());
				workbook.write(fos);
				
				fos.close();
				workbook.close();
				fis.close();
			}
			catch(Exception ex)
			{
				
			}
			
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
		
		VBox bottomMenu = new VBox();
		bottomMenu.setPadding(new Insets(10,10,10,10));
		bottomMenu.setSpacing(10);
		bottomMenu.setAlignment(Pos.CENTER);
		bottomMenu.getChildren().addAll(ok, cancel);
		
		
		
		//entire layout
		BorderPane layoutA = new BorderPane();
		layoutA.setTop(topMenu);
		layoutA.setCenter(grid);
		layoutA.setBottom(bottomMenu);
		
		
		
		//define scene
		sceneA = new Scene(layoutA, 225, 310);
		
		// -------------------------------------------------
		
		
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